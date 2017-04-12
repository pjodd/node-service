package se.pjodd.service.node.domain;

import lombok.Data;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * @author kalle
 * @since 2017-04-12
 */
@Data
public class MeshNode implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID identity;
  private String name;

  private OffsetDateTime fistSeen;
  private Location location;
  private OffsetDateTime lastSeen;

  private Map<String, String> files;

}
