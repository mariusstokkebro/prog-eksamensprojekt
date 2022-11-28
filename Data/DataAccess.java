package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataAccess {

    public String path;

    public DataAccess(String path) {
        this.path = path;
    }

    public List<String> load() {

            List<String> data = new ArrayList<String>();
            try {
            File file = new File(path);
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
