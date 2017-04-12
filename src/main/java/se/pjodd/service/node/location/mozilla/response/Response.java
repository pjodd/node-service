package se.pjodd.service.node.location.mozilla.response;

import lombok.Data;

/**
 * @author kalle
 * @since 2017-03-23
 */
@Data
public class Response {

  private Location location;
  private float accuracy;
  private String fallback;

}
