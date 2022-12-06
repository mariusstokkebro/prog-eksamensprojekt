package src;
import Data.DataAccess;
import src.Film;
import src.Medier;
import src.Serie;

import java.util.ArrayList;
import java.util.List;

public class Media {

    List<Film> films;
    List<Serie> series;
    List<Medier> medier;
    public Media() {

        List<Film> films = new ArrayList<>();
        List<Serie> series = new ArrayList<>();

    DataAccess filmsData = new DataAccess("film.txt");
    DataAccess seriesData = new DataAccess("serier.txt");

    List<Medier> medier = new ArrayList<>();

    List<String> filmData = filmsData.load();
        for (String element : filmData) {
        String[] line = element.split(";");
        String movieName = line[0];
        String year = line[1].trim();
        String genre = line[2];
        double rating = Double.parseDouble(line[3].trim().replace(",", "."));
        Film film1 = new Film(movieName, year, genre, rating);

        films.add(film1);
        medier.add(film1);

    }

    List<String> serieData = seriesData.load();
        for (String element : serieData) {
            String[] line = element.split(";");
            String seriesName = line[0];
            String year = line[1].trim();
            String genre = line[2];
            double rating = Double.parseDouble(line[3].trim().replace(",", "."));
            String episodes = line[4];
            Serie serie1 = new Serie(seriesName, year, genre, rating, episodes);

            series.add(serie1);
            medier.add(serie1);

        }

        for (Film film : films) {
            System.out.println(film.getName());
        }

        for (Serie ser : series) {
            System.out.println(ser.getName());
        }


    }

        List movieList() {

            return films;
        }

        List seriesList() {

            return series;
        }

        List mediaList() {

            return medier;
        }

}
