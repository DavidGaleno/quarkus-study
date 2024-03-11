package galeno.david.resources;

import galeno.david.models.MovieDTO;
import galeno.david.services.MovieService;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/movies")
@Tag(name = "Movies", description = "CRUD com filmes")
public class MovieResource {

  @Inject
  MovieService movieService;


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
    operationId = "getMovies",
    summary = "Get Movies",
    description = "Get all movies"
  )
  public Response get() {
    return movieService.get();
  }

  @Transactional
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Response post(MovieDTO body) {
    return movieService.post(body);
  }

  @Transactional
  @PUT
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response put(
    @PathParam("id") @Parameter(
      description = "Identificação",
      required = true
    ) Long id,
    MovieDTO body
  ) {
    return movieService.put(id, body);
  }

  @Transactional
  @DELETE
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  @Path("/{id}")
  public Response delete(@PathParam("id") Long id) {
    return movieService.delete(id);
  }
}
