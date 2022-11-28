package src;

public class Serie {
    private String name;
    private int year;
    private String[] genre;
    private double rating;
    private String episode;

    public Serie(String name, int year, String genre, double rating, String episode) {
        this.name = name;
        this.year = year;
        this.genre = genre.split(",");
        this.rating = rating;
        this.episode = episode;
    }
}
