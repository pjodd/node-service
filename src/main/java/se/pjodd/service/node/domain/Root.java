package se.pjodd.service.node.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;

/**
 * @author kalle
 * @since 2017-04-12
 */
@Data
public class Root implements Serializable {

  private static final long serialVersionUID = 1L;

  private OffsetDateTime initialized;

}
