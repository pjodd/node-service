package se.pjodd.service.node.location.mozilla.request;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class CellTower {

  /**
   * The type of radio network. One of gsm, wcdma or lte.
   * This is a custom extension to the GLS API, which only defines the top-level radioType field.
   */
  private String radioType;

  /**
   * The mobile country code
   */
  private String mobileCountryCode;

  /**
   * The mobile network code.
   */
  private String mobileNetworkCode;

  /**
   * The location area code for GSM and WCDMA networks. The tracking area code for LTE networks.
   */
  private String locationAreaCode;

  /**
   * The cell id or cell identity.
   */
  private String cellId;

  /**
   * The number of milliseconds since this networks was last detected.
   */
  private String age;

  /**
   * The primary scrambling code for WCDMA and physical cell id for LTE. This is an addition to the GLS API.
   */
  private Integer psc;

  /**
   * The signal strength for this cell network, either the RSSI or RSCP.
   */
  private Float signalStrength;

  /**
   * The timing advance value for this cell network.
   */
  private String timingAdvance;


}
