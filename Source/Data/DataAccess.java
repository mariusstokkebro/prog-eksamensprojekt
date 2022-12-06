package Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {

    public String fileName;

    public DataAccess(String fileName) {
        this.fileName = fileName;
    }

    public List<String> load() {

            List<String> data = new ArrayList<String>();
            try {
                System.out.println(fileName);
            File file = new File(fileName);

            Scanner s = new Scanner(file);

            while(s.hasNextLine()) {
                data.add(s.nextLine());
            }
        }

        catch(FileNotFoundException e) {
                System.out.println(e.getMessage());
            }

        return data;
    }



}
