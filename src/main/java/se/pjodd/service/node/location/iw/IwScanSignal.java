package se.pjodd.service.node.location.iw;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-22 08:38
 */
@Data
public class IwScanSignal {

  private String bssid;
  private String ssid;
  private Float signalStrength;
  private Integer lastSeenMs;
  private Integer freq;

}
