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

    JFrame frame = new JFrame();
    JPanel topPanel = new JPanel(new GridBagLayout());

    JPanel posterPanel = new JPanel(new GridBagLayout());

    JPanel mainPanel = new JPanel(new BorderLayout());

    GridBagConstraints constraints = new GridBagConstraints();

    public Display() {
        Media media = new Media();

        mediaList = media.getMediaList();
        filmList = media.getFilmList();
        seriesList = media.getSeriesList();




        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        topPanel.setBackground(Color.black);
        frame.setSize(800, 800);
        frame.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setPreferredSize(new Dimension(600, 600));
        topPanel.setBackground(Color.BLACK);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(posterPanel, BorderLayout.SOUTH);
        homeScreen();
        makeAllPosters(mediaList);
        posterPanel.setBackground(Color.BLACK);
        posterPanel.setBorder(null);
        mainPanel.add(makeScrollPane());

        frame.setVisible(true);


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
        JScrollPane sp = new JScrollPane(posterPanel);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setBorder(null);
        return sp;
    }

    void homeScreen() {


        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.1;
        constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);


        //Medier knap
        JButton but1 = makeButton("Medier", 50, 25, 25, Color.red);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.FIRST_LINE_START;
        but1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //your actions
                homeScreen();
                makeAllPosters(mediaList);
                topPanel.validate();
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
                topPanel.validate();
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
                frame.setVisible(true);
            }
        });

        //Titel med 'Popkorn tid'
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setFont(title.getFont().deriveFont(0, 25));
        constraints.gridx = 3;
        constraints.gridy = 0;

        topPanel.add(title, constraints);

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
        constraints.gridx = 4;
        constraints.gridy = 0;
        topPanel.add(popcorn, constraints);


        JTextField textField = maketextField(20);
        constraints.gridx = 5;
        constraints.gridy = 0;
        topPanel.add(textField, constraints);

    }

    void titleScreen() {
        //Title
        JLabel title = new JLabel("Popkorn Tid");
        title.setForeground(Color.gray);
        title.setFont(title.getFont().deriveFont(0,40));
        topPanel.add(title);

        //Picture
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/Popcorn_Time_logo.png"));
        JLabel label = new JLabel();
        label.setIcon(imageIcon);
        topPanel.add(label);
        frame.setVisible(true);

        //Go to Home screen after 4 seconds

        Timer t = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                homeScreen();
                makeAllPosters(mediaList);
                frame.setVisible(true);
            }
        });
        t.setRepeats(false);
        t.start();

    }

}



