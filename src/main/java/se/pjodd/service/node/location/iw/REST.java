package se.pjodd.service.node.location.iw;

import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import se.pjodd.service.node.location.mozilla.MozillaLocationService;
import se.pjodd.service.node.location.mozilla.request.Request;
import se.pjodd.service.node.location.mozilla.request.WifiAccessPoint;
import se.pjodd.service.node.location.mozilla.response.Response;

import javax.ws.rs.*;
import java.io.StringReader;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Path("api")
public class REST {

  private Logger log = LoggerFactory.getLogger(getClass());

  @Inject
  private MozillaLocationService mozilla;

  @POST
  @Path("iwscan")
  @Consumes("text/plain")
  @Produces("application/json")
  public Response locateFromIwScan(
      String data
  ) throws Exception {

    IwScanParser parser = new IwScanParser();
    parser.parse(new StringReader(data));

    Request request = new Request();
    for (IwScanSignal signal : parser.getSignals()) {
      WifiAccessPoint wifiAccessPoint = new WifiAccessPoint();
      wifiAccessPoint.setMacAddress(signal.getBssid());
      wifiAccessPoint.setSignalStrength(signal.getSignalStrength());
      wifiAccessPoint.setAge(signal.getLastSeenMs());
      wifiAccessPoint.setFrequency(signal.getFreq());

      request.getWifiAccessPoints().add(wifiAccessPoint);
    }

    Response response = mozilla.locate(request);

    return response;

  }

}