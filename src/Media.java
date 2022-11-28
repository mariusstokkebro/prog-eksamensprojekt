package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Media {

    public void medie() {

    Data.DataAccess films = new Data.DataAccess("/Users/casperpilgaard/Downloads/Data/film.txt");
    Data.DataAccess series = new Data.DataAccess("/Users/casperpilgaard/Downloads/Data/serier.txt");

    List<String> media = new ArrayList<String>();

    List<String> filmData = films.load();
        for (String element : filmData) {
        String[] line = element.split(";");
        String movieName = line[0];
        int year = Integer.parseInt(line[1].trim());
        String genre = line[2];
        double rating = Double.parseDouble(line[3].trim().replace(",", "."));

        System.out.println(movieName + " " + year + " " + genre + " " + rating);
        media.add(element);
    }

    List<String> seriesData = series.load();



        Collections.sort(media);

    }

}
