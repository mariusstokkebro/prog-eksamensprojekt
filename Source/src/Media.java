package src;
import Data.DataAccess;
import src.Film;
import src.Medier;
import src.Serie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Media {

    List<Film> films;
    List<Serie> series;
    List<Medier> medier;

    DataAccess filmsData;
    DataAccess seriesData;
    public Media() {

        List<Film> films = new ArrayList<>();
        List<Serie> series = new ArrayList<>();

        filmsData = new DataAccess("film.txt");
        seriesData = new DataAccess("serier.txt");

        List<Medier> medier = new ArrayList<>();

    }

    public void filmData() {

    List<String> filmData = filmsData.load();
        for (String element : filmData) {
            String[] line = element.split(";");
            String movieName = line[0];
            String year = line[1].trim();
            String genre = line[2];
            double rating = Double.parseDouble(line[3].trim().replace(",", "."));
            Film film1 = new Film(movieName, year, genre, rating);
            films.add(film1);

        }
    }
        public void serieData() {
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
            }

        }

        public void mediaData() {

            serieData();
            filmData();

            for(int i = 0; i<series.size();i++) {
            medier.add(series.get(i));

            }


            for(int u = 0; u<films.size();u++) {
                medier.add(films.get(u));

            }


        //Sorterer listen, tager to parameterer og sammenligner dem i forhold til navnets alfabetiske rækkefølge
        Collections.sort(medier, (p1, p2) -> p1.getName().compareTo(p2.getName()));

        System.out.println(getMediaList());

    }

        public List getFilmList() {

            return films;
        }

        public List getSeriesList() {

            return series;
        }

        public List<Medier> getMediaList() {
            List<Medier> list = new ArrayList<Medier>();

            return list;
        }

}
