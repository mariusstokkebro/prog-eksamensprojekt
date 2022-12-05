package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Media {

    List<Film> films;
    List<Serie> series;
    List<String> media;
    public Media() {

        List<Film> films = new ArrayList<>();
        List<Serie> serie = new ArrayList<>();

    Data.DataAccess filmsData = new Data.DataAccess("/Users/casperpilgaard/Downloads/Data/film.txt");
    Data.DataAccess seriesData = new Data.DataAccess("/Users/casperpilgaard/Downloads/Data/serier.txt");

    List<String> media = new ArrayList<String>();

    List<String> filmData = filmsData.load();
        for (String element : filmData) {
        String[] line = element.split(";");
        String movieName = line[0];
        int year = Integer.parseInt(line[1].trim());
        String genre = line[2];
        double rating = Double.parseDouble(line[3].trim().replace(",", "."));
        Film film1 = new Film(movieName, year, genre, rating);

        films.add(film1);

    }

    List<String> serieData = seriesData.load();
        for (String element : serieData) {
            String[] line = element.split(";");
            String seriesName = line[0];
            int year = Integer.parseInt(line[1].trim());
            String genre = line[2];
            double rating = Double.parseDouble(line[3].trim().replace(",", "."));
            String episodes = line[4];
            Serie serie1 = new Serie(seriesName, year, genre, rating, episodes);

            serie.add(serie1);


        }

        for (Film film : films) {
            System.out.println(film.getName());
        }


    }

        List movieList() {

        return films;
        }

        List seriesList() {

        return series;
        }

        List mediaList() {

        return media;
        }

}
