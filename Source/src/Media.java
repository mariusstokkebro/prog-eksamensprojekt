package src;
import Data.DataAccess;
import src.Film;
import src.Medier;
import src.Serie;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Media {

    List<Film> films;
    List<Serie> series;
    List<Medier> medier;
    List<Medier> favoritlist;
    DataAccess filmsData;
    DataAccess seriesData;
    DataAccess favoritListData;
    public Media() {

        films = new ArrayList<>();
        series = new ArrayList<>();
        favoritlist = new ArrayList<>();
        filmsData = new DataAccess("film.txt");
        seriesData = new DataAccess("serier.txt");
        favoritListData = new DataAccess("favoritList.txt");
        medier = new ArrayList<>();

        filmData();
        serieData();
        mediaData();
        favoritlist = medier;
        saveFavoritList();
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
        Collections.sort(films, (p1, p2) -> p1.getName().compareTo(p2.getName()));
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
        Collections.sort(series, (p1, p2) -> p1.getName().compareTo(p2.getName()));

    }

    public void mediaData() {

        for(int i = 0; i<series.size();i++) {
            medier.add(series.get(i));

        }


        for(int u = 0; u<films.size();u++) {
            medier.add(films.get(u));

        }

        //Sorterer listen, tager to parameterer og sammenligner dem i forhold til navnets alfabetiske rækkefølge
        Collections.sort(medier, (p1, p2) -> p1.getName().compareTo(p2.getName()));


    }
    public void loadFavoritList() {
        List<String> favoritList = favoritListData.load();
        for (String element : favoritList) {
            String[] line = element.split(";");
            String name = line[0];
            String year = line[1].trim();
            String genre = line[2];
            double rating = Double.parseDouble(line[3].trim().replace(",", "."));
            if(line[4]==null){
                Film film = new Film(name,year,genre,rating);
                favoritlist.add(film);
            }
            else {
                String episodes = line[4];
                Serie serie = new Serie(name, year, genre, rating, episodes);
                favoritlist.add(serie);
            }
        }
    }
    public void saveFavoritList(){
        String[] genre;
        String genres;

        List<String> favoritList = new ArrayList<>();
        for(int i = 0; i< favoritlist.size();i++)
        {
            genre = favoritlist.get(i).getGenre();
            genres = " "+genre[0];
            for(int u = 1;u< genre.length;u++){
                genres = genres+","+genre[u];

            }
            if(favoritlist.get(i).toString().contains("Serie")){
                favoritList.add(favoritlist.get(i).getName()+"; "+favoritlist.get(i).getYear()+";"+genres+"; "+favoritlist.get(i).getRating()+"; "+favoritlist.get(i).getEpisode()+";");
                favoritListData.save(favoritList);
            }
            else {
                favoritList.add(favoritlist.get(i).getName() + "; " + favoritlist.get(i).getYear() + ";" + genres + "; " + favoritlist.get(i).getRating()+";");
            }
        }
        favoritListData.save(favoritList);
    }

    public List getFilmList() {

        return films;
    }

    public List getSeriesList() {

        return series;
    }

    public List getMediaList() {


        return medier;
    }
    public List getFavoritList(){
        return favoritlist;
    }

}
