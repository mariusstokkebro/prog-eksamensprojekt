package src;

public class Serie  extends Medier{
   private String episode;


    public Serie(String name, String year, String genre, double rating, String episode) {
        super(name,year,genre,rating);
        this.episode = episode;
    }
    public String getEpisode(){
        return episode;
    }
}
