package se.pjodd.service.node.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kalle
 * @since 2017-03-22 09:11
 */
@Data
public class Location implements Serializable {

  private static final long serialVersionUID = 1L;

  private Double accuracy;
  private double latitude;
  private double longitude;

}
