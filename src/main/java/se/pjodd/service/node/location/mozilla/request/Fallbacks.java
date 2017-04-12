package se.pjodd.service.node.location.mozilla.request;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class Fallbacks {

  /**
   * If no exact cell match can be found,
   * fall back from exact cell position estimates to more coarse grained cell location area estimates,
   * rather than going directly to an even worse GeoIP based estimate.
   */
  private Boolean lacf = true;

  /**
   * If no position can be estimated based on any of the provided data points,
   * fall back to an estimate based on a GeoIP database based on the senders IP address at the time of the query.
   */
  private Boolean ipf = false;

}
