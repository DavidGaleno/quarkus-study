package galeno.david.models;

import java.net.URL;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TVSeriesDTO {

  public URL url;
  public String name;
  public String summary;
  public String language;
  public ArrayList<String> genres;
  public URL officialSite;
}
