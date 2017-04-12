package se.pjodd.service.node.location.mozilla.request;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class WifiAccessPoint {

  /**
   * The BSSID of the WiFi network.
   */
  private String macAddress;

  /**
   * The received signal strength (RSSI) in dBm.
   */
  private Float signalStrength;

  /**
   * The number of milliseconds since this network was last detected.
   */
  private Integer age;

  /**
   * The WiFi channel, often 1 - 13 for networks in the 2.4GHz range.
   */
  private Integer channel;

  /**
   * The frequency in MHz of the channel over which the client is communicating with the access point.
   * This is an addition to the GLS API and can be used instead of the channel field.
   */
  private Integer frequency;

  /**
   * The current signal to noise ratio measured in dB.
   */
  private Float signalToNoiseRatio;


}
