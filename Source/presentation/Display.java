package presentation;

import src.Film;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Display implements ActionListener {



    public Display() {
        JPanel jp1 = new JPanel();
        JFrame frame = new JFrame("Popkorn tid");

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp1.setBackground(Color.black);

        jp1.setLayout(null);


        frame.setSize(1920, 1080);

        frame.add(jp1);

        //Medier knap
        JButton but1 = new JButton("Medier");
        but1.setForeground(Color.red);
        but1.setBounds(50, 50, 100, 50);
        jp1.add(but1);

        //Film knap
        JButton but2 = new JButton("Film");
        but2.setForeground(Color.red);
        but2.setBounds(200, 50, 100, 50);
        jp1.add(but2);

        //Serier knap
        JButton but3 = new JButton("Serier");
        but3.setForeground(Color.red);
        but3.setBounds(350, 50, 100, 50);
        jp1.add(but3);


        //Billeder


        frame.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
