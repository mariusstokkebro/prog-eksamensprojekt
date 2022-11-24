package View;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Display implements ActionListener {

    JFrame frame;


    public Display() {

        frame = new JFrame("Popkorn tid");


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);





        frame.setSize(800, 400);
        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
