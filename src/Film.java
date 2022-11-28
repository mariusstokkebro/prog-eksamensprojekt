package src;

import java.util.ArrayList;
import java.util.List;

public class Film {
    private String name;
    private int year;
    private String[] genre;
    private double rating;
    private List<Film> films = new ArrayList<>();
    public Film(String name, int year,String genre, double rating){
        this.name = name;
        this.year = year;
        this.genre = genre.trim().split(",");
        this.rating = rating;
    }
    public String getName(){
        return name;
    }
    public int getYear(){
        return year;
    }
    public String[] getGenre(){
        return genre;
    }
    public double getRating(){
        return rating;
    }


}