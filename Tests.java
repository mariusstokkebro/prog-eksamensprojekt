import org.junit.Test;
import src.Media;
import src.MediaReader;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

public class Tests {

    MediaReader mediaReader = new MediaReader();

    @Test
    public void filmListTest() {
        List<Media> filmList = mediaReader.getFilmList();
        assertEquals(filmList.size(), 100);
    }

    @Test
    public void seriesListTest() {
        List<Media> seriesList = mediaReader.getSeriesList();
        assertEquals(seriesList.size(), 100);
    }

    @Test
    public void mediaListTest() {
        List<Media> mediaList = mediaReader.getMediaList();
        assertEquals(mediaList.size(), 200);
    }

}
