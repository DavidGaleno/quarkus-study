package galeno.david.resources;

import galeno.david.models.Episode;
import galeno.david.models.TVSeries;
import galeno.david.services.EpisodeService;
import galeno.david.services.TVSeriesService;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/tvseries")
public class TVSeriesResource {

  @RestClient
  TVSeriesService tvSeriesService;

  @RestClient
  EpisodeService episodeService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public TVSeries getTvSeries(@QueryParam("title") String title) {
    return tvSeriesService.get(title);
  }
  @Path("/{id}")
  @GET
  public List<Episode> getEpisodes(@PathParam("id") int id){
    return episodeService.get(id);
  }
}
