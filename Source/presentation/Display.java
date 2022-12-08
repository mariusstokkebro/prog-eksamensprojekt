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
import javax.swing.border.Border;
import java.util.List;

public class Display{

List<Medier> mediaList;
List<Medier> filmList;
List<Medier> seriesList;

JFrame frame;
JPanel mainPanel;
GridBagConstraints constraints;

    public Display() {
        Media media = new Media();

        mediaList = media.getMediaList();
        filmList = media.getFilmList();
        seriesList = media.getSeriesList();

    }

    JButton makeButton(String text, int height, int width, int fontSize, Color color) {
        JButton button = new JButton(text);
        button.setForeground(color);
        button.setBorder(null);
        button.setSize(height, width);
        button.setContentAreaFilled(false);
        button.setFont(button.getFont().deriveFont(0, fontSize));
        return button;

    }

    void makeAllPosters(List<Medier> list) {

        int posx = 0;
        int posy = 2;

        for (int i = 0; i < list.size(); i++) {
            if ((i % 7 == 0) && i!=0) {
                posx = 0;
                posy++;

            }
            ImageIcon img = new ImageIcon(getClass().getResource("/" + list.get(i).getName()+".jpg"));
            JButton poster = new JButton(img);
            poster.setBorder(null);
            poster.setContentAreaFilled(false);
            constraints.gridx = posx;
            constraints.gridy = posy;
            mainPanel.add(poster, constraints);
            posx++;

        }
    }



    void homeScreen() {
        frame = new JFrame("Popkorn tid");
        mainPanel = new JPanel(new GridBagLayout());
        frame.setBackground(Color.black);


        constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.1;
        constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        mainPanel.setBackground(Color.black);
        frame.setSize(800, 800);
        frame.add(mainPanel);




        //Medier knap
        JButton but1 = makeButton("Medier", 50, 25, 25, Color.red);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                makeAllPosters(mediaList);

            }
        });
        mainPanel.add(but1, constraints);

        //Film knap
        JButton but2 = makeButton("Film", 50, 25, 25, Color.red);
        constraints.gridx = 1;
        constraints.gridy = 0;
        mainPanel.add(but2, constraints);
        but2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                makeAllPosters(filmList);
                frame.setVisible(true);
            }
        });
        //Serier knap
        JButton but3 = makeButton("Serier", 50, 25, 25, Color.red);
        constraints.gridx = 2;
        constraints.gridy = 0;
        mainPanel.add(but3, constraints);
        but3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                makeAllPosters(seriesList);
                frame.setVisible(true);
            }
        });

        //Titel med 'Popkorn tid'
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setFont(title.getFont().deriveFont(0, 25));
        constraints.gridx = 3;
        constraints.gridy = 0;

        mainPanel.add(title, constraints);

        //Titel billede
        BufferedImage popcornimg = null;
        try {
            popcornimg = ImageIO.read(new File(getClass().getResource("/Popcorn_Time_logo.png").getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Søgefelt



        Image popcornimg2 = popcornimg.getScaledInstance(96, 96, 4);

        ImageIcon popcornicon = new ImageIcon(popcornimg2);
        JLabel popcorn = new JLabel(popcornicon);
        constraints.gridx = 4;
        constraints.gridy = 0;
        mainPanel.add(popcorn, constraints);



        //Filmplakater




        //Scrollbar
        JScrollPane sp = new JScrollPane(mainPanel);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(sp);


        frame.setVisible(true);
    }

    void titleScreen() {
        frame = new JFrame("Popkorn Tid");
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainPanel = new JPanel();
        frame.add(mainPanel);
        mainPanel.setBackground(Color.black);
        mainPanel.setLayout(null);

        //Title
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setFont(title.getFont().deriveFont(0,40));
        mainPanel.add(title);

        //Picture
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Popcorn_Time_logo.png"));
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        mainPanel.add(label);
        frame.setVisible(true);

        //Go to Home screen after 4 seconds

        Timer t = new Timer(4000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                homeScreen();
            }
        });
        t.setRepeats(false);
        t.start();

    }
}



