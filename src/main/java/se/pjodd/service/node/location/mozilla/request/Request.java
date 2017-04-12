package se.pjodd.service.node.location.mozilla.request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class Request {

  /**
   * The clear text name of the cell carrier / operator
   */
  private String carrier;

  /**
   * Should the clients IP address be used to locate it,
   * defaults to false here but true in the official API.
   */
  private boolean considerIp = false;

  /**
   * The mobile country code stored on the SIM card
   */
  private Integer homeMobileCountryCode;

  /**
   * The mobile network code stored on the SIM card.
   */
  private Integer homeMobileNetworkCode;

  /**
   * Same as the radioType entry in each cell record. If all the cell entries have the same radioType,
   * it can be provided at the top level instead.
   */
  private String radioType;

  private List<WifiAccessPoint> wifiAccessPoints = new ArrayList<>();
  private List<CellTower> cellTowers = new ArrayList<>();
  private List<BluetoothBeacon> bluetoothBeacons = new ArrayList<>();

  private Fallbacks fallbacks = new Fallbacks();

}
