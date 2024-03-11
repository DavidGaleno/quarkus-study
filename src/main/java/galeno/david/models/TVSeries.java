package galeno.david.models;

import java.net.URL;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TVSeries {

  public int id;
  public URL url;
  public String name;
  public String summary;
  public String language;
  public ArrayList<String> genres;
  public URL officialSite;
}
