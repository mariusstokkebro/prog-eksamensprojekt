package Data;
import src.Medier;

import java.io.*;
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
            File file = new File(getClass().getResource( "/" + fileName).getFile());
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

    public void save(List<String> favoriteList) {
        try {
            File f = new File(fileName);
            PrintWriter pw = new PrintWriter(f);
            for(String d : favoriteList)
            {
                pw.println(d);
            }
            pw.close();
        } catch (FileNotFoundException e) {
            System.out.println("No file, saving nothing.");
        }
    }




}
