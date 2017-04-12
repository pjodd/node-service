package se.pjodd.service.node.location.mozilla.request;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class BluetoothBeacon {

  /** The address of the Bluetooth Low Energy (BLE) beacon. */
  private String macAddress;


  /** The name of the BLE beacon. */
  private String name;

  /** The number of milliseconds since this BLE beacon was last seen. */
  private Integer age;


  /** The measured signal strength of the BLE beacon in dBm. */
  private Float signalStrength;


}
