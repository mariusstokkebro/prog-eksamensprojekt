package presentation;

import src.Film;
import src.Medier;
import src.Media;
import src.Serie;

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
List<Medier> filmList;
List<Medier> seriesList;
    public Display() {
        Media media = new Media();

        mediaList = media.getMediaList();
        filmList = media.getFilmList();
        seriesList = media.getSeriesList();

    }

    JButton makeButton(String text, int posx, int posy, int height, int width, int fontSize, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setBounds(posx, posy, height, width);
        button.setBorder(null);
        button.setContentAreaFilled(false);
        button.setFont(button.getFont().deriveFont(0, fontSize));
        return button;

    }

    void makeAllPosters(List<Medier> list, JPanel panel) {


        int posx = 80;
        int posy = 0;

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getName());
            if (i % 7 == 0) {
                posx = 80;
                posy += 200;
            }
            ImageIcon img = new ImageIcon(getClass().getResource("/" + list.get(i).getName()+".jpg"));
            JButton poster = new JButton(img);
            poster.setBorder(null);
            poster.setContentAreaFilled(false);
            poster.setBounds(posx, posy, 100, 150);
            panel.add(poster);
            posx += 200;
        }
    }

    void homeScreen() {
        JFrame frame = new JFrame("Popkorn tid");
        JPanel jp1 = new JPanel();


        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jp1.setBackground(Color.black);

        jp1.setLayout(null);


        frame.setSize(1980, 1080);

        frame.add(jp1);



        //Medier knap
        JButton but1 = makeButton("Medier", 50, 50, 100, 50, 20, Color.red);
        jp1.add(but1);
        //Medier knap

        //Film knap
        JButton but2 = makeButton("Film", 200, 50, 100, 50, 20, Color.red);
        jp1.add(but2);

        //Serier knap
        JButton but3 = makeButton("Serier", 350, 50, 100, 50, 20, Color.red);
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
        makeAllPosters(filmList, jp1);

        //Scrollbar
        /*JScrollPane scrollbar = new JScrollPane(jp1);
        scrollbar.setVerticalScrollBarPolicy(scrollbar.VERTICAL_SCROLLBAR_ALWAYS);
        jp1.add(scrollbar, BorderLayout.EAST);*/
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



