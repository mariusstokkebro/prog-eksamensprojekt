package src;

public abstract class Medier {
    private String name;
    private int year;
    private String[] genre;
    private double rating;
    public Medier(String name, int year,String genre, double rating){
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

