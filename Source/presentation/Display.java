package presentation;

import src.Film;
import src.Medier;
import src.Media;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.List;

public class Display{

List<Medier> mediaList;
    public Display() {
        Media media = new Media();

        mediaList = media.getMediaList();

    }

    void homeScreen() {
        JFrame frame = new JFrame("Popkorn tid");
        JPanel jp1 = new JPanel();


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp1.setBackground(Color.black);

        jp1.setLayout(null);


        frame.setSize(1920, 1080);

        frame.add(jp1);

        int fontSize = 20;


        //Medier knap
        JButton but1 = new JButton("Medier");
        but1.setForeground(Color.red);
        but1.setBounds(50, 50, 100, 50);
        but1.setBorder(null);
        but1.setContentAreaFilled(false);
        but1.setFont(but1.getFont().deriveFont(0, fontSize));
        jp1.add(but1);

        //Film knap
        JButton but2 = new JButton("Film");
        but2.setForeground(Color.red);
        but2.setBounds(200, 50, 100, 50);
        but2.setBorder(null);
        but2.setContentAreaFilled(false);
        but2.setFont(but2.getFont().deriveFont(0, fontSize));
        jp1.add(but2);

        //Serier knap
        JButton but3 = new JButton("Serier");
        but3.setForeground(Color.red);
        but3.setBounds(350, 50, 100, 50);
        but3.setBorder(null);
        but3.setContentAreaFilled(false);
        but3.setFont(but3.getFont().deriveFont(0, fontSize));
        jp1.add(but3);


        //Titel med 'Popkorn tid'
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setBounds(630, 30, 300, 50);
        title.setFont(title.getFont().deriveFont(0, 30));
        jp1.add(title);

        //Titel billede
        BufferedImage popcornimg = null;
        try {
            popcornimg = ImageIO.read(new File(getClass().getResource("/Popcorn_Time_logo.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image popcornimg2 = popcornimg.getScaledInstance(96, 96, 4);

        ImageIcon popcornicon = new ImageIcon(popcornimg2);
        JLabel popcorn = new JLabel(popcornicon);
        popcorn.setBounds(810, 20, 96, 96);
        jp1.add(popcorn);



        //Filmplakater
        int posx = 80;
        int posy = 0;

        for (int i = 0; i < mediaList.size(); i++) {
            System.out.println(mediaList.get(i).getName());
            if (i % 7 == 0) {
                posx = 80;
                posy += 200;
            }




            ImageIcon img = new ImageIcon(getClass().getResource("/" + mediaList.get(i).getName()+".jpg"));
            JButton poster = new JButton(img);
            poster.setBorder(null);
            poster.setContentAreaFilled(false);
            poster.setBounds(posx, posy, 100, 150);
            jp1.add(poster);
            posx += 200;
        }
        frame.setVisible(true);
    }





    void titleScreen() {
        JFrame openScreen = new JFrame("Popkorn Tid");
        openScreen.setSize(1980, 1080);

        openScreen.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



        JPanel panel = new JPanel();
        openScreen.add(panel);
        panel.setBackground(Color.black);
        panel.setLayout(null);



        //Title
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setBounds(630, 250, 300, 50);
        title.setFont(title.getFont().deriveFont(0,40));
        panel.add(title);

        //Picture
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Popcorn_Time_logo.png"));
        JLabel label = new JLabel();
        label.setBounds(630, 300, 256, 256);
        label.setIcon(imageIcon);
        panel.add(label);

        openScreen.setVisible(true);

        //Go to Home screen after 4 seconds

        Timer t = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openScreen.setVisible(false);
                homeScreen();
            }
        });
        t.setRepeats(false);
        t.start();

    }







}



