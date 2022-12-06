package src;

import java.util.ArrayList;
import java.util.List;

public class Film extends Medier{

    private List<Film> films = new ArrayList<>();
    public Film(String name, int year,String genre, double rating){
        super(name,year,genre,rating);

    }



}