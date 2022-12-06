package Data;

import javax.swing.*;

public class Image {
    JFrame frame;
    JLabel display;
    ImageIcon Image;



    public Image(){
        JFrame path = new JFrame("");
        frame = new JFrame("filmplakater");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try{
            Image = new ImageIcon(getClass().getResource("/Amadeus.jpg"));
            display = new JLabel(Image);
            frame.add(display);

        }catch (NullPointerException e){
            System.out.println("cannot find");
        }
        frame.setSize(400,400);
        frame.setVisible(true);
    }
}
