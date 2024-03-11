package galeno.david.services;

import galeno.david.models.Movie;
import galeno.david.models.MovieDTO;
import galeno.david.repository.MovieRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class MovieService {

  @Inject
  private MovieRepository movieRepository;

  public Response get() {
    List<Movie> movies = movieRepository.findAll().list();
    return Response.ok().entity(movies).build();
  }

  public Response post(MovieDTO movieDTO) {
    Movie movie = Movie.builder().title(movieDTO.getTitle()).build();
    movieRepository.persist(movie);
    return Response.status(201).build();
  }

  public Response put(Long id, MovieDTO movieDTO) {
    Movie movieToBeEdited = movieRepository.findById(id);
    movieToBeEdited.setTitle(movieDTO.getTitle());
    movieRepository.persist(movieToBeEdited);
    return Response.ok().entity(movieToBeEdited).build();
  }

  public Response delete(Long id) {
    Movie movieToBeDeleted = movieRepository.findById(id);
    movieRepository.delete(movieToBeDeleted);
    return Response.noContent().build();
  }
}
