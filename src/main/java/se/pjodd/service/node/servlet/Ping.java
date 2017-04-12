package se.pjodd.service.node.servlet;

import com.google.inject.Inject;
import lombok.Data;
import se.pjodd.service.node.ServerConfiguration;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * @author kalle
 * @since 2017-03-07 22:31
 */
@Path("api")
public class Ping {

  @Inject
  private ServerConfiguration serverConfiguration;


  @Path("ping")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Pong ping() {
    return new Pong();
  }

  @Data
  public class Pong {
    private String serviceName = serverConfiguration.getServiceName();
  }

}
