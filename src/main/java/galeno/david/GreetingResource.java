package galeno.david;

import galeno.david.services.HelloService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

  @Inject
  private HelloService helloService;

  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String hello() {
    return helloService.oi();
  }
}
