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
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Display{

    List<Medier> mediaList;
    List<Medier> filmList;
    List<Medier> seriesList;

    JFrame frame = new JFrame("Popcorn Tid");
    JPanel topPanel = new JPanel(new GridBagLayout());

    JPanel posterPanel = new JPanel(new GridBagLayout());

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel startPanel = new JPanel(new GridBagLayout());

    JPanel userScreen = new JPanel(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();

    JScrollPane sp;


    public Display() {
        Media media = new Media();

        //Importing all media to the lists:
        mediaList = media.getMediaList();
        filmList = media.getFilmList();
        seriesList = media.getSeriesList();


        //Frame settings
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

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
    JTextField maketextField(int width){
        JTextField textField = new JTextField(width);

        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                posterPanel.removeAll();
                posterPanel.revalidate();
                posterPanel.repaint();
                int posx = 0;
                int posy = 2;

                for(int i = 0;i<mediaList.size();i++){

                    if(Objects.equals(textField.getText(), mediaList.get(i).getName())){
                        ImageIcon img = new ImageIcon(getClass().getResource("/" + mediaList.get(i).getName() + ".jpg"));
                        JButton poster = new JButton(img);
                        poster.setBorder(null);
                        poster.setContentAreaFilled(false);
                        constraints.gridx = posx;
                        constraints.gridy = posy;
                        posterPanel.add(poster, constraints);
                        posx++;
                    }
                    if ((i % 7 == 0) && i!=0) {
                        posx = 0;
                        posy++;

                    }
                    String[] genre = mediaList.get(i).getGenre();
                    for(int u = 0;u<genre.length;u++){
                        if((Objects.equals(textField.getText(), genre[u]))){
                            ImageIcon img = new ImageIcon(getClass().getResource("/" + mediaList.get(i).getName() + ".jpg"));
                            JButton poster = new JButton(img);
                            poster.setBorder(null);
                            poster.setContentAreaFilled(false);
                            constraints.gridx = posx;
                            constraints.gridy = posy;
                            posterPanel.add(poster, constraints);
                            posx++;
                        }
                    }


                }
                frame.setVisible(true);
            }
        });
        return textField;
    }
    void makeAllPosters(List<Medier> list) {
        posterPanel.removeAll();
        posterPanel.revalidate();
        posterPanel.repaint();
        int posx = 0;
        int posy = 2;
        for (int i = 0; i < list.size(); i++) {
            if ((i % 7 == 0) && i!=0) {
                posx = 0;
                posy++;

            }
            ImageIcon img = new ImageIcon(getClass().getResource("/" + list.get(i).getName() + ".jpg"));
            JButton poster = new JButton(img);
            poster.setBorder(null);
            poster.setContentAreaFilled(false);
            constraints.gridx = posx;
            constraints.gridy = posy;
            posterPanel.add(poster, constraints);
            posx++;
        }
    }

    JScrollPane makeScrollPane() {
        sp = new JScrollPane(posterPanel);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBorder(null);
        return sp;
    }

    JLabel makeLabel(String text, int fontSize, Color color) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(0, fontSize));
        label.setForeground(color);
        return label;
    }

    JLabel makeImageIcon(String path, int width, int height) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(getClass().getResource(path).getFile()));
        } catch (IOException e) {
            System.out.println("Image not found");
        }
        Image scaledImg = img.getScaledInstance(width, height, 4);
        JLabel imgIcon = new JLabel(new ImageIcon(scaledImg));
        return imgIcon;
    }

    void homeScreen() {
        //Fjerner alle paneller fra mainPanel og tilføjer mainPanel
        frame.getContentPane().removeAll();
        frame.add(mainPanel);

        //Remove all in mainPanel and update
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        //Settings for toppanel, posterpanel and adding them to mainpanel
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(null);
        posterPanel.setBackground(Color.BLACK);
        posterPanel.setBorder(null);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(posterPanel, BorderLayout.SOUTH);


        //Setting default constraints up
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.5;
        constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);


        //Medier knap
        JButton but1 = makeButton("Medier", 50, 25, 25, Color.red);
        constraints.gridx = 0;
        constraints.gridy = 0;
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                homeScreen();
                makeAllPosters(mediaList);
                sp.getViewport().setViewPosition(new Point(0, 0));

                frame.setVisible(true);
            }
        });
        topPanel.add(but1, constraints);

        //Film knap
        JButton but2 = makeButton("Film", 50, 25, 25, Color.red);
        constraints.gridx = 1;
        constraints.gridy = 0;
        topPanel.add(but2, constraints);
        but2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                homeScreen();
                makeAllPosters(filmList);
                sp.getViewport().setViewPosition(new Point(0, 0));
                frame.setVisible(true);
            }
        });
        //Serier knap
        JButton but3 = makeButton("Serier", 50, 25, 25, Color.red);
        constraints.gridx = 2;
        constraints.gridy = 0;
        topPanel.add(but3, constraints);
        but3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                homeScreen();
                makeAllPosters(seriesList);
                sp.getViewport().setViewPosition(new Point(0, 0));
                frame.setVisible(true);
            }
        });

        //Titel med 'Popkorn tid'
        JLabel title = makeLabel("Popkorn Tid", 25, Color.gray);
        constraints.gridx = 3;
        constraints.gridy = 0;
        topPanel.add(title, constraints);

        //Titel billede
        JLabel popcornIcon = makeImageIcon("/Popcorn_Time_logo.png", 96, 96);
        constraints.gridx = 4;
        constraints.gridy = 0;
        topPanel.add(popcornIcon, constraints);

        //Tekst felt
        JTextField textField = maketextField(20);
        constraints.gridx = 5;
        constraints.gridy = 0;
        topPanel.add(textField, constraints);

        //Tilføj en scrollpane til mainPanel inde i posterPanel
        mainPanel.add(makeScrollPane());


        //Tilføj alle plakater til posterPanel
        makeAllPosters(mediaList);

    }

    void titleScreen() {
        //Fjern alle paneler fra frame og tilføj den nuværende
        frame.getContentPane().removeAll();
        frame.add(startPanel);

        //Sæt baggrunden til sort
        startPanel.setBackground(Color.black);

        //Title
        JLabel title = makeLabel("Popkorn Tid", 40, Color.gray);
        startPanel.add(title);

        //Picture
        JLabel popcornIcon = makeImageIcon("/Popcorn_Time_logo.png", 256, 256);
        startPanel.add(popcornIcon);

        //Lav en timer som skifter hen til en ny scene efter 3 sekunder
        Timer t = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainScreen();
            }
        });
        t.setRepeats(false);
        t.start();
    }

    void userScreen() {
        frame.getContentPane().removeAll();
        frame.add(userScreen);

        constraints.gridx = 0;
        constraints.gridy = 0;


        userScreen.setBackground(Color.black);
        JPanel userBorder = new JPanel();
        userBorder.setBackground(Color.gray);

        JLabel plusSign = makeImageIcon("/plussign.png", 150, 150);
        userBorder.add(plusSign, constraints);
        userScreen.add(userBorder, constraints);
        makeLabel("Create new User", 20, Color.gray);
    }

    void showTitleScreen() {
        titleScreen();
        frame.setVisible(true);
    }

    void showMainScreen() {
        homeScreen();
        frame.setVisible(true);
    }

    void showUserScreen() {
        userScreen();
        frame.setVisible(true);
    }

}



