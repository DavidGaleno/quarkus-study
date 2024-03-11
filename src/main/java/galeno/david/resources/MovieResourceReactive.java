package galeno.david.resources;

import galeno.david.models.Movie;
import io.quarkus.runtime.StartupEvent;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/moviesReactive")
public class MovieResourceReactive {

  @Inject
  PgPool client;

  @Inject
  @ConfigProperty(name = "myapp.schema.create", defaultValue = "true")
  boolean schemaCreate;

  void config(@Observes StartupEvent ev) {
    if (schemaCreate) {
      initdb();
    }
  }

  private void initdb() {
    client
      .query("DROP TABLE IF EXISTS movies")
      .execute()
      .flatMap(m ->
        client
          .query(
            "CREATE TABLE movies (id BIGSERIAL primary key, title varchar(50) not null)"
          )
          .execute()
      )
      .flatMap(m ->
        client.query("INSERT INTO movies (title) values('Godfather')").execute()
      )
      .flatMap(m ->
        client.query("INSERT INTO movies (title) values('Barbie')").execute()
      )
      .await()
      .indefinitely();
  }

  @GET
  public Multi<Movie> get() {
    return Movie.findAll(client);
  }

  @GET
  @Path("/{id}")
  public Uni<Response> getById(@PathParam("id") Long id) {
    return Movie
      .findById(client, id)
      .onItem()
      .transform(movie ->
        movie != null ? Response.ok(movie) : Response.status(Status.NOT_FOUND)
      )
      .onItem()
      .transform(ResponseBuilder::build);
  }
}
