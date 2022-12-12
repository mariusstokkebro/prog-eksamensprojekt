package src;

public abstract class Medier {
    private String name;
    private String year;
    private String[] genre;
    private double rating;
    private String episode;
    public Medier(String name, String year,String genre, double rating){
        this.name = name;
        this.year = year;
        this.genre = genre.trim().split(",");
        this.rating = rating;
    }
    public String getName(){
        return name;
    }
    public String getYear(){
        return year;
    }
    public String[] getGenre(){
        return genre;
    }
    public double getRating(){
        return rating;
    }

    public String getEpisode(){return episode;}

}

