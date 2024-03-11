package galeno.david.services;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import galeno.david.models.TVSeries;

@ApplicationScoped
@Path("/singlesearch")
@RegisterRestClient(configKey = "tv-series-api")
public interface TVSeriesService {
  @GET
  @Path("/shows")
  @Produces(MediaType.APPLICATION_JSON)
  public TVSeries get(@QueryParam("q") String title);
}
