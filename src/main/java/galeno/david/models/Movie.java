package galeno.david.models;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  public static Multi<Movie> findAll(PgPool client) {
    return client
      .query("SELECT id,title from movies ORDER BY title DESC")
      .execute()
      .onItem()
      .transformToMulti(set -> Multi.createFrom().iterable(set))
      .onItem()
      .transform(Movie::from);
  }

  public static Uni<Movie> findById(PgPool client, Long id) {
    return client
      .preparedQuery("SELECT * from movies where id = $1")
      .execute(Tuple.of(id))
      .onItem()
      .transform(RowSet::iterator)
      .onItem()
      .transform(iterator -> iterator.hasNext() ? from(iterator.next()) : null);
  }

  private static Movie from(Row row) {
    return Movie
      .builder()
      .title(row.getString("title"))
      .id(row.getLong("id"))
      .build();
  }
}
