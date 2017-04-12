package se.pjodd.service.node.location.mozilla;

import org.junit.Test;
import se.pjodd.service.node.location.iw.IwScanSignal;
import se.pjodd.service.node.location.mozilla.request.Request;
import se.pjodd.service.node.location.iw.IwScanParser;
import se.pjodd.service.node.location.mozilla.request.WifiAccessPoint;
import se.pjodd.service.node.location.mozilla.response.Response;

import java.io.InputStreamReader;

/**
 * @author kalle
 * @since 2017-03-23
 */
public class TestMozillaLocationService {

  @Test
  public void test() throws Exception {

    IwScanParser iwParser = new IwScanParser();
    iwParser.parse(new InputStreamReader(getClass().getResourceAsStream("/se/pjodd/service/node/location/iw/iwscan.txt"), "UTF8"));


    Request request = new Request();
    for (IwScanSignal signal : iwParser.getSignals()) {
      WifiAccessPoint wifiAccessPoint = new WifiAccessPoint();
      wifiAccessPoint.setMacAddress(signal.getBssid());
      wifiAccessPoint.setSignalStrength(signal.getSignalStrength());
      wifiAccessPoint.setAge(signal.getLastSeenMs());
      wifiAccessPoint.setFrequency(signal.getFreq());

      request.getWifiAccessPoints().add(wifiAccessPoint);
    }

    Response response = new MozillaLocationService().locate(request);

    System.currentTimeMillis();

  }

}
