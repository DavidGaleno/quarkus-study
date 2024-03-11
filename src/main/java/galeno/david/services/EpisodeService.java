package galeno.david.services;

import galeno.david.models.Episode;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@ApplicationScoped
@RegisterRestClient(configKey = "tv-series-api")
public interface EpisodeService {
  @GET
  @Path("shows/{id}/episodes")
  @Produces(MediaType.APPLICATION_JSON)
  public List<Episode> get(@PathParam("id") int id);
}
