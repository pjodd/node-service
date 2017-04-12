package se.pjodd.service.node.util;

/**
 * @author kalle
 * @since 2017-02-22 00:29
 */
public class Environment {

  public static String getValue(String key, String defaultValue) {
    String value = System.getenv(key);
    return value != null ? value : defaultValue;
  }

  public static int getValue(String key, int defaultValue) {
    String stringValue = getValue(key, (String)null);
    return stringValue != null ? Integer.valueOf(stringValue) : defaultValue;
  }

}
