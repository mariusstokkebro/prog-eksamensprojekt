package presentation;

import src.Film;
import src.Medier;
import src.Media;
import src.Serie;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class Display {

    List<Medier> mediaList;
    List<Medier> filmList;
    List<Medier> seriesList;

    JFrame frame = new JFrame("Popcorn Tid");
    JPanel topPanel = new JPanel(new GridBagLayout());

    JPanel posterPanel = new JPanel(new GridBagLayout());

    JPanel mainPanel = new JPanel(new BorderLayout());

    JPanel startPanel = new JPanel(new FlowLayout());

    JPanel userScreen = new JPanel(new GridBagLayout());
    JPanel mediaPanel = new JPanel(new BorderLayout());
    GridBagConstraints constraints = new GridBagConstraints();

    JScrollPane sp;

    List<Medier> favoritListe;

    int played;

    List<Medier> currentList;

    List<String> selectedGenres;

    Media media = new Media();

    public Display() {


        //Importerer alt data til listerne
        mediaList = media.getMediaList();
        filmList = media.getFilmList();
        seriesList = media.getSeriesList();
        favoritListe = media.getFavoritList();

        //Indstillinger til hver frame
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        currentList = mediaList;

        selectedGenres = new ArrayList<>();
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

    JTextField maketextField(int width) {
        JTextField textField = new JTextField(width);

        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                posterPanel.removeAll();
                posterPanel.revalidate();
                posterPanel.repaint();
                int posx = 0;
                int posy = 2;
                int posters = 0;
                for (int i = 0; i < mediaList.size(); i++) {

                    if (mediaList.get(i).getName().toLowerCase().contains(textField.getText().toLowerCase())) {
                        ImageIcon img = new ImageIcon(getClass().getResource("/" + mediaList.get(i).getName() + ".jpg"));
                        JButton poster = new JButton(img);
                        poster.setName(mediaList.get(i).getName());
                        poster.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JButton but = (JButton) e.getSource();
                                String movieName = but.getName();
                                for (Medier medier : mediaList) {
                                    if (medier.getName().equals(movieName)) {
                                        showMediaScene(medier);
                                        break;
                                    }
                                }

                            }
                        });
                        poster.setBorder(null);
                        poster.setContentAreaFilled(false);
                        constraints.gridx = posx;
                        constraints.gridy = posy;
                        posterPanel.add(poster, constraints);
                        posx++;
                        posters++;
                    }
                    if ((posters % 7 == 0) && posters != 0) {
                        posx = 0;
                        posy++;

                    }
                    String[] genre = mediaList.get(i).getGenre();
                    for (int u = 0; u < genre.length; u++) {
                        if ((Objects.equals(textField.getText().toLowerCase(), genre[u].toLowerCase()))) {
                            ImageIcon img = new ImageIcon(getClass().getResource("/" + mediaList.get(i).getName() + ".jpg"));
                            JButton poster = new JButton(img);
                            poster.setName(mediaList.get(i).getName());
                            poster.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    JButton but = (JButton) e.getSource();
                                    String movieName = but.getName();
                                    for (Medier medier : mediaList) {
                                        if (medier.getName().equals(movieName)) {
                                            showMediaScene(medier);
                                            break;
                                        }
                                    }

                                }
                            });
                            poster.setBorder(null);
                            poster.setContentAreaFilled(false);
                            constraints.gridx = posx;
                            constraints.gridy = posy;
                            posterPanel.add(poster, constraints);
                            posx++;
                            posters++;
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
            String movieName = list.get(i).getName();
            ImageIcon img = new ImageIcon(getClass().getResource("/" + movieName + ".jpg"));
            JButton poster = new JButton(img);
            poster.setName(movieName);
            poster.setBorder(null);
            poster.setContentAreaFilled(false);
            poster.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JButton but = (JButton)e.getSource();
                    String movieName = but.getName();
                    for (Medier medier : mediaList) {
                        if (medier.getName().equals(movieName)) {
                            showMediaScene(medier);
                            break;
                        }
                    }

                }
            });
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

    ImageIcon makeImageIcon(String path, int width, int height) {
        try {
            BufferedImage img = ImageIO.read(getClass().getResourceAsStream(path));
            Image scaledImg = img.getScaledInstance(width, height, 4);
            ImageIcon imgIcon = new ImageIcon(scaledImg);
            return imgIcon;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Image not found");
        }
        return null;
    }

    void makeDummyLabel(int amount, JPanel panel) {
        for (int i = 0; i < amount; i++) {
            panel.add(makeLabel("        ", 25, Color.black));
        }
    }





    void homeScreen() {
        //Fjerner alle paneller fra mainPanel og tilføjer mainPanel
        frame.getContentPane().removeAll();
        frame.add(mainPanel);

        //Fjerner hovedpanelet og opdaterer panelet
        mainPanel.removeAll();
        mainPanel.revalidate();
        mainPanel.repaint();

        selectedGenres.removeAll(selectedGenres);

        //Indstillinger til topPanel, posterPanel og mainPanel
        topPanel.setBackground(Color.BLACK);
        topPanel.setBorder(null);
        posterPanel.setBackground(Color.BLACK);
        posterPanel.setBorder(null);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(posterPanel, BorderLayout.SOUTH);


        //Sætter constraints op
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
                homeScreen();
                makeAllPosters(mediaList);
                currentList = mediaList;
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
                homeScreen();
                makeAllPosters(filmList);
                currentList = filmList;
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
                homeScreen();
                makeAllPosters(seriesList);
                currentList = seriesList;
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
        ImageIcon popcornIcon = makeImageIcon("/Popcorn_Time_logo.png", 96, 96);
        constraints.gridx = 4;
        constraints.gridy = 0;
        topPanel.add(new JLabel(popcornIcon), constraints);

        //Tekst felt
        JTextField textField = maketextField(20);
        constraints.gridx = 6;
        constraints.gridy = 0;
        topPanel.add(textField, constraints);

        //FavoritListe knap
        JButton favoritListBut = makeButton("Favoritliste", 50, 25, 25, Color.RED);
        favoritListBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFavoritListe();
            }
        });
        constraints.gridx = 5;
        constraints.gridy = 0;
        topPanel.add(favoritListBut, constraints);

        //Tilføj en scrollpane til mainPanel inde i posterPanel
        mainPanel.add(makeScrollPane());

        //Tilføj alle plakater til posterPanel
        makeAllPosters(currentList);
    }

    void titleScreen() {
        //Fjern alle paneler fra frame og tilføj den nuværende
        frame.getContentPane().removeAll();
        frame.add(startPanel);

        //Sæt baggrunden til sort
        startPanel.setBackground(Color.black);

        //Titel tekst
        JLabel title = makeLabel("Popkorn Tid", 40, Color.gray);
        startPanel.add(title);

        //Billede af logo
        ImageIcon popcornIcon = makeImageIcon("/Popcorn_Time_logo.png", 256, 256);
        startPanel.add(new JLabel(popcornIcon));

        //Lav en timer som skifter hen til en ny scene efter 3 sekunder
        Timer t = new Timer(3000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainScreen();
            }
        });
        t.setRepeats(false);
        t.start();
    }

    void mediaScene(Medier medier){

        //Fjerner alle paneler fra Frame, tilføjer mediaPanel og opdaterer.
        frame.getContentPane().removeAll();
        mediaPanel.removeAll();
        mediaPanel.revalidate();
        mediaPanel.repaint();
        frame.add(mediaPanel);

        //Opretter panelerne og sætter layout
        JPanel topPanel = new JPanel(new GridBagLayout());
        JPanel leftPanel = new JPanel(new BorderLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        mediaPanel.add(topPanel,BorderLayout.NORTH);
        mediaPanel.add(leftPanel,BorderLayout.WEST);
        mediaPanel.add(rightPanel,BorderLayout.CENTER);
        topPanel.setBackground(Color.BLACK);
        leftPanel.setBackground(Color.BLACK);
        rightPanel.setBackground(Color.BLACK);

        //Sætter constraints op:
        constraints.anchor = GridBagConstraints.NORTHWEST;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weighty = 0.5;
        constraints.weightx = 1;
        constraints.insets = new Insets(5, 5, 5, 5);

        //Komponenter for topPanel

        //Laver hjem knap
        ImageIcon homeIcon = makeImageIcon("/home.png", 51, 51);
        constraints.gridx = 0;
        JButton homeBut = new JButton(homeIcon);
        homeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMainScreen();
            }
        });
        topPanel.add(homeBut, constraints);

        //Laver titel tekst
        JLabel title = makeLabel("Popkorn Tid",25,Color.gray);
        constraints.gridx = 1;
        constraints.gridy = 0;
        topPanel.add(title,constraints);

        //Laver billede logo
        ImageIcon popcornIcon = makeImageIcon("/Popcorn_Time_logo.png",96,96);
        constraints.gridx = 2;
        topPanel.add(new JLabel(popcornIcon),constraints);


        //Laver favoritliste knap
        JButton favoritListeBut = makeButton("Favoritliste", 25, 50, 25, Color.RED);
        favoritListeBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFavoritListe();
            }
        });
        constraints.gridx = 3;
        topPanel.add(favoritListeBut, constraints);

        //leftPanel komponenter:


        //Billedeplakat
        ImageIcon poster = makeImageIcon("/" + medier.getName() + ".jpg", 420, 627);
        leftPanel.add(new JLabel(poster), BorderLayout.CENTER);


        //Komponenter af rightPanel:


        int fontSize = 25;
        if (medier instanceof Film){
            makeDummyLabel(1, rightPanel);

            JLabel movieName = makeLabel("Movie name: " + medier.getName(), fontSize, Color.gray);
            rightPanel.add(movieName);

            makeDummyLabel(2, rightPanel);

            JLabel movieYear = makeLabel("The movie is from: " + medier.getYear(), fontSize, Color.gray);
            rightPanel.add(movieYear);

            makeDummyLabel(2, rightPanel);

            String genres = "";
            if (medier.getGenre().length < 2) {
                genres = "Genre: ";
            } else {
                genres = "Genres: ";
            }
            for (int i = 0; i < medier.getGenre().length; i++) {
                genres += medier.getGenre()[i];
                if (medier.getGenre().length > i+1) {
                    genres += ",";
                }
            }
            JLabel movieGenres = makeLabel(genres, fontSize, Color.gray);
            rightPanel.add(movieGenres);

            makeDummyLabel(2, rightPanel);

            JLabel movieRating = makeLabel("Rating: " + medier.getRating(), fontSize, Color.gray);
            rightPanel.add(movieRating);

            makeDummyLabel(6, rightPanel);


        } else if (medier instanceof Serie) {
            makeDummyLabel(1, rightPanel);

            JLabel seriesName = makeLabel("Series name: " + medier.getName(), fontSize, Color.gray);
            rightPanel.add(seriesName);

            makeDummyLabel(2, rightPanel);

            JLabel seriesYear = makeLabel("The series is from: " + medier.getYear(), fontSize, Color.gray);
            rightPanel.add(seriesYear);
            makeDummyLabel(2, rightPanel);

            String genres = "";
            if (medier.getGenre().length < 2) {
                genres = "Genre: ";
            } else {
                genres = "Genres: ";
            }
            for (int i = 0; i < medier.getGenre().length; i++) {
                genres += medier.getGenre()[i];
                if (medier.getGenre().length > i+1) {
                    genres += ",";
                }
            }
            JLabel serieGenres = makeLabel(genres, fontSize, Color.gray);
            rightPanel.add(serieGenres);
            makeDummyLabel(2, rightPanel);

            JLabel serieRating = makeLabel("Rating: " + medier.getRating(), fontSize, Color.gray);
            rightPanel.add(serieRating);
            makeDummyLabel(2, rightPanel);

            JLabel serieEpisodes = makeLabel("Episodes: " + medier.getEpisode(), fontSize, Color.gray);
            rightPanel.add(serieEpisodes);
            makeDummyLabel(3, rightPanel);
        }

        //Tilføjer knap med tilføj til favoritliste
        JButton favoriteButton = makeButton("Tilføj til favoritliste", 100, 100, 25, Color.black);
        favoriteButton.setName(medier.getName());

        for (int i = 0; i < favoritListe.size(); i++) {
            if (favoritListe.get(i).getName().equals(medier.getName())) {
                favoriteButton.setText("Fjern fra din favoritliste");
            }
        }

        favoriteButton.setContentAreaFilled(true);
        favoriteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                String movieName = button.getName();
                boolean existsInFavoritList = favoritListe.stream().anyMatch(med -> med.getName().equals(movieName));
                if (existsInFavoritList) {
                    favoritListe.removeIf(med -> med.getName().equals(movieName));
                    button.setText("Tilføj til favoritliste");
                } else {
                    favoritListe.add(medier);
                    button.setText("Fjern fra favoritliste");
                }
                media.saveFavoritList(favoritListe);
            }
        });


        rightPanel.add(favoriteButton);
        makeDummyLabel(1, rightPanel);


        //Tilføjer "play" knap
        JButton playButton = makeButton("Play", 100, 100, 25, Color.BLACK);
        playButton.setContentAreaFilled(true);
        playButton.setBackground(Color.RED);
        played = 1;

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton)e.getSource();
                if (played == 0) {
                    button.setBackground(Color.red);
                    played++;
                } else {
                    button.setBackground(Color.green);
                    played--;
                }
            }
        });
        rightPanel.add(playButton);
    }

    void showTitleScreen() {
        titleScreen();
        frame.setVisible(true);
    }

    void showMainScreen() {
        homeScreen();
        frame.setVisible(true);
    }
    void showMediaScene(Medier medier){
        mediaScene(medier);
        frame.setVisible(true);
    }

    void showFavoritListe() {
        homeScreen();
        makeAllPosters(favoritListe);
        sp.getViewport().setViewPosition(new Point(0, 0));
        frame.setVisible(true);
    }
}

