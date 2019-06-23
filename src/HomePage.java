/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package jpotify;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Home pc
 */
public class HomePage extends javax.swing.JDialog {

    private boolean liked = false;
    private Music music;
    private PlayList playlist = new PlayList("AAA");
    private Album album = new Album();
    private int mode;
    private Soundcontroller soundcontroller = new Soundcontroller();
    private PlayList FSongs = new PlayList("Favorit Songs");
    private PlayList Shared = new PlayList("Shared PlayList");
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<PlayList> playlists = new ArrayList<>();
    private ArrayList<Music> songs = new ArrayList<>();
    private int songNum = 0;
    String username;
    private int nextSong;
    JButton jb = new JButton("LOLOOLOLOO");
    DefaultListModel playList;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JButton> Abuttons = new ArrayList<>();
    ArrayList<JButton> Asbuttons = new ArrayList<>();

    ArrayList<JLabel> labels = new ArrayList<>();
    String fileNameSerialize;
    public static User user;

    /**
     * Creates new form homePage
     */

    private boolean itsSong=false;
    private boolean itsSongInAlbum=false;
    public void actButton(JButton jb,Album album,Music music1,Music music2){

        if (itsSong)
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        setMusic(music2);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        if (itsSongInAlbum){
            jb.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        setMusic(music1);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                }
            });
        }
        if (itsAlbum){jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    setMusic(music1);
                } catch (JavaLayerException e1) {
                    e1.printStackTrace();
                }
            }
        });


        }



    }
    public void setImage(JButton button, String path) {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(path)));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(pauseButton.getWidth(), pauseButton.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon i = new ImageIcon(img2);
        button.setIcon(i);


    }


    public void setImage2(JButton button, Image Image) {
        Image img = Image;
        Image newimg = img.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newimg);
        button.setIcon(image);
    }

    public void setImageLabel(JLabel label, String path) {
        ImageIcon image = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource(path)));
        Image img1 = image.getImage();
        Image img2 = img1.getScaledInstance(pauseButton.getWidth(), pauseButton.getHeight(), Image.SCALE_REPLICATE);
        ImageIcon i = new ImageIcon(img2);
        label.setIcon(i);


    }


    //**
    //@param : User user
    //this method deserialize the user
    //**
    public User deserialiseUser(User Myuser) {
        User users = null;
        System.out.println(user.getName());
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(Myuser.getName().concat(".bin")));
            users = (User) is.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("finish deserializing");
        System.out.println("name" + users.getName());
        return users;
    }

    //**
    //this method serialize the user and save it
    //**
      public static void updateUser(User user){
          System.out.println("update start");
          new File(user.getName().concat(".bin")).delete();
          try {
              String g = user.getName().concat(".bin");
              System.out.println("g"+g);
              ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(g));
              os.writeObject(user);
              os.close();
          }catch(FileNotFoundException e){
              e.printStackTrace();
          }catch(IOException e){
              e.printStackTrace();
          }
          System.out.println("update finished");
      }
      
      public static User deserializeUser(String name) throws FileNotFoundException{
        User userd = null;
        System.out.println("name"+name);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(name.concat(".bin")));
            userd = (User)is.readObject();
        }
        catch(FileNotFoundException e ){
            e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("finish deserializing");
     return userd;   
    }
      
    public HomePage(String name) throws IOException, InvalidDataException, InvalidDataException, UnsupportedTagException, UnsupportedTagException, UnsupportedTagException, JavaLayerException, InterruptedException {
        System.out.println("myuser name in home" + name);
        user = deserializeUser(name);
        user.setMusic(new Music(new File("k.mp3")));
        System.out.println("finish deserializing");
        music = new Music(new File("k.mp3"));
//        music.play();
        initComponents();
        username = name;
        fileNameSerialize = username.concat(".bin");
        // User user = new User(username);

        System.out.println("username" + user.getName());
        jb.setVisible(true);
        songsPanel.setVisible(true);
        songsPanel.add(jb);
        userNameLabel.setText(name);
        setImage(pauseButton, "pause.png");
        setImage(playButton, "play.png");
        setImage(forwardButton, "forward.png");
        setImage(backwardButton, "backward.png");
        setImage(shuffleButton, "shuffle.png");
        setImage(favoriteButton, "love.jpg");
//        setImageLabel(musicLabel,"sound.jfif");
//        user.getPlayLists().add(FSongs);
//        user.getPlayLists().add(Shared);
        int count = 0;
        for (int i = 0; i < user.getAlbums().size(); i++) {
            Album a = user.getAlbums().get(i);
            ArrayList<Music> songss = a.getSongs();
            for (int j = 0; j < songss.size(); j++) {
                ArrayList<Music> abc = user.getSongs();
                abc.add(songss.get(j));
                user.setSongs(abc);
            }

        }
        JLabel j1 = new JLabel();
        j1.setVisible(true);
        j1.setIcon(new ImageIcon(music.getArtWork()));
        setImage2(i1, (music.getArtWork()));

        user.setSongNum(user.getSongs().size());

//        ????????????????????????????????????????????????????????????????????

        playList = new DefaultListModel();
        playList.addElement("favorite list");
        playList.addElement("shared playlist");
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            playList.addElement(user.getPlayLists().get(i).getTitle());
        }
        playListList.setModel(playList);
        //updateUser(user);
    }
//?????????????????????????????????????????????????????????????????

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        libraryScroll = new javax.swing.JScrollPane();
        libraryPanel = new javax.swing.JPanel();
        albumButton = new javax.swing.JButton();
        songsButton = new javax.swing.JButton();
        songsPanel = new javax.swing.JPanel();
        libraryLabel = new java.awt.Label();
        jScrollPane2 = new javax.swing.JScrollPane();
        playListList = new javax.swing.JList<>();
        editPlayList = new javax.swing.JButton();
        playListLabel = new javax.swing.JLabel();
        editsPlayList = new java.awt.Button();
        soundBar = new javax.swing.JPanel();
        musicSlider = new javax.swing.JSlider();
        musicLabel = new javax.swing.JLabel();
        volumeSlider = new javax.swing.JSlider();
        buttonsPanel = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        favoriteButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        backwardButton = new javax.swing.JButton();
        shuffleButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jl1 = new javax.swing.JLabel();
        jl3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        friendActivityLabel = new javax.swing.JLabel();
        friendActivityPanel = new javax.swing.JPanel();
        friendButton1 = new java.awt.Button();
        friendButton3 = new java.awt.Button();
        friendButton4 = new java.awt.Button();
        friendButton2 = new java.awt.Button();
        searchButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        newPlayList = new javax.swing.JButton();
        userNameLabel = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        displayPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        i22 = new javax.swing.JButton();
        i3 = new javax.swing.JButton();
        i4 = new javax.swing.JButton();
        i23 = new javax.swing.JButton();
        i10 = new javax.swing.JButton();
        i9 = new javax.swing.JButton();
        i14 = new javax.swing.JButton();
        i2 = new javax.swing.JButton();
        i17 = new javax.swing.JButton();
        i24 = new javax.swing.JButton();
        i12 = new javax.swing.JButton();
        i8 = new javax.swing.JButton();
        i20 = new javax.swing.JButton();
        i15 = new javax.swing.JButton();
        i16 = new javax.swing.JButton();
        i6 = new javax.swing.JButton();
        i18 = new javax.swing.JButton();
        i11 = new javax.swing.JButton();
        i21 = new javax.swing.JButton();
        i5 = new javax.swing.JButton();
        t18 = new javax.swing.JLabel();
        t10 = new javax.swing.JLabel();
        t24 = new javax.swing.JLabel();
        t17 = new javax.swing.JLabel();
        t16 = new javax.swing.JLabel();
        l6 = new javax.swing.JLabel();
        t12 = new javax.swing.JLabel();
        t5 = new javax.swing.JLabel();
        t23 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        t9 = new javax.swing.JLabel();
        t15 = new javax.swing.JLabel();
        t22 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        l15 = new javax.swing.JLabel();
        t13 = new javax.swing.JLabel();
        t7 = new javax.swing.JLabel();
        i19 = new javax.swing.JButton();
        i13 = new javax.swing.JButton();
        i7 = new javax.swing.JButton();
        i1 = new javax.swing.JButton();
        t8 = new javax.swing.JLabel();
        t20 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        t2 = new javax.swing.JLabel();
        t6 = new javax.swing.JLabel();
        t11 = new javax.swing.JLabel();
        t14 = new javax.swing.JLabel();
        t19 = new javax.swing.JLabel();
        t21 = new javax.swing.JLabel();
        addToLibraryButton = new javax.swing.JButton();
        displayMusicPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 204, 255));

        libraryPanel.setBackground(new java.awt.Color(255, 102, 255));

        albumButton.setBackground(new java.awt.Color(255, 51, 204));
        albumButton.setText("album");
        albumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumButtonActionPerformed(evt);
            }
        });

        songsButton.setBackground(new java.awt.Color(255, 51, 204));
        songsButton.setText("Songs");
        songsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                songsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout songsPanelLayout = new javax.swing.GroupLayout(songsPanel);
        songsPanel.setLayout(songsPanelLayout);
        songsPanelLayout.setHorizontalGroup(
            songsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        songsPanelLayout.setVerticalGroup(
            songsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 87, Short.MAX_VALUE)
        );

        libraryLabel.setBackground(new java.awt.Color(255, 204, 204));
        libraryLabel.setText("library");

        jScrollPane2.setViewportView(playListList);

        editPlayList.setText("edit");
        editPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayListActionPerformed(evt);
            }
        });

        playListLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        playListLabel.setText("playlist");

        editsPlayList.setLabel("edit");
        editsPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editsPlayListActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout libraryPanelLayout = new javax.swing.GroupLayout(libraryPanel);
        libraryPanel.setLayout(libraryPanelLayout);
        libraryPanelLayout.setHorizontalGroup(
            libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryPanelLayout.createSequentialGroup()
                .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(songsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(albumButton, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                .addComponent(songsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(libraryPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(playListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(editsPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(248, 248, 248)
                                .addComponent(editPlayList)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(libraryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libraryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        libraryPanelLayout.setVerticalGroup(
            libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(libraryLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(albumButton)
                .addGap(10, 10, 10)
                .addComponent(songsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(editPlayList)
                    .addComponent(playListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editsPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(songsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        libraryScroll.setViewportView(libraryPanel);

        soundBar.setBackground(new java.awt.Color(0, 0, 255));

        musicSlider.setBackground(new java.awt.Color(0, 0, 102));
        musicSlider.setValue(0);
        musicSlider.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
                try {
                    musicSliderAncestorMoved(evt);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        musicSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicSliderStateChanged(evt);
            }
        });

        musicLabel.setText("jLabel1");

        volumeSlider.setValue(0);
        volumeSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                volumeSliderStateChanged(evt);
            }
        });

        buttonsPanel.setBackground(new java.awt.Color(0, 0, 153));

        playButton.setText("jButton1");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    playButtonActionPerformed(evt);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        forwardButton.setText("jButton1");

        favoriteButton.setText("jButton2");
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("jButton1");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        backwardButton.setText("jButton1");

        shuffleButton.setText("jButton1");

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(shuffleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(backwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addComponent(favoriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );
        buttonsPanelLayout.setVerticalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonsPanelLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(playButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(forwardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(favoriteButton)
                    .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backwardButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(shuffleButton))
                .addContainerGap())
        );

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jl2.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jl1.setText("jLabel4");

        jl3.setText("jLabel4");

        javax.swing.GroupLayout soundBarLayout = new javax.swing.GroupLayout(soundBar);
        soundBar.setLayout(soundBarLayout);
        soundBarLayout.setHorizontalGroup(
            soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(soundBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(soundBarLayout.createSequentialGroup()
                        .addGroup(soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jl1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                            .addComponent(jl3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 777, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(364, 364, 364))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soundBarLayout.createSequentialGroup()
                        .addComponent(jl2, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addGap(355, 355, 355)
                        .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(285, 285, 285)
                        .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50)
                        .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142))))
        );
        soundBarLayout.setVerticalGroup(
            soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, soundBarLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel5)
                    .addGroup(soundBarLayout.createSequentialGroup()
                        .addGroup(soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(soundBarLayout.createSequentialGroup()
                                .addGap(51, 51, 51)
                                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(soundBarLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(buttonsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(soundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(musicSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, soundBarLayout.createSequentialGroup()
                        .addComponent(jl1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jl3, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
        );

        friendActivityLabel.setFont(new java.awt.Font("Traditional Arabic", 1, 18)); // NOI18N
        friendActivityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        friendActivityLabel.setText("friend activity");

        friendButton1.setLabel("button1");

        friendButton3.setLabel("button1");
        friendButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendButton3ActionPerformed(evt);
            }
        });

        friendButton4.setLabel("button1");
        friendButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendButton4ActionPerformed(evt);
            }
        });

        friendButton2.setLabel("button1");
        friendButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                friendButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout friendActivityPanelLayout = new javax.swing.GroupLayout(friendActivityPanel);
        friendActivityPanel.setLayout(friendActivityPanelLayout);
        friendActivityPanelLayout.setHorizontalGroup(
            friendActivityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(friendButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(friendButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(friendButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE)
            .addComponent(friendButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        friendActivityPanelLayout.setVerticalGroup(
            friendActivityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendActivityPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(friendButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(friendButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(friendButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(friendActivityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(friendActivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendActivityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendActivityPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(150, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        searchButton.setText("search");

        searchText.setText("search for...");

        newPlayList.setBackground(new java.awt.Color(255, 153, 153));
        newPlayList.setText("new playlist");
        newPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlayListActionPerformed(evt);
            }
        });

        jLabel1.setText("jLabel1");

        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    i1ActionPerformed(evt);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(i8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(i14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58))
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addComponent(i7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addComponent(i8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addComponent(i19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t19, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(i21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(i20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(91, Short.MAX_VALUE))
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addComponent(t18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        jScrollPane3.setViewportView(displayPanel);

        addToLibraryButton.setText("add to library");
        addToLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    addToLibraryButtonActionPerformed(evt);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
            }
        });

        displayMusicPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout displayMusicPanelLayout = new javax.swing.GroupLayout(displayMusicPanel);
        displayMusicPanel.setLayout(displayMusicPanelLayout);
        displayMusicPanelLayout.setHorizontalGroup(
            displayMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        displayMusicPanelLayout.setVerticalGroup(
            displayMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(addToLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(searchButton))
                                    .addComponent(libraryScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(newPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
                            .addComponent(displayMusicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(soundBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(67, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(addToLibraryButton)
                                    .addComponent(searchButton))
                                .addGap(18, 18, 18)
                                .addComponent(libraryScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 661, Short.MAX_VALUE))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(newPlayList, javax.swing.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                            .addComponent(displayMusicPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(soundBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void artistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistButtonActionPerformed


        displayPanel.setLayout(new FlowLayout());


    }//GEN-LAST:event_artistButtonActionPerformed


    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, IOException, InterruptedException {//GEN-FIRST:event_forwardButtonActionPerformed
        if (this.mode == 0)
            return;
        else if (mode == 1) {
            ArrayList<Music> songs = user.getCurrenPlayList().getSongs();
            int a = songs.indexOf(user.getMusic());
            user.getMusic().pause();
            user.setMusic(songs.get(a + 1));
            user.getMusic().play();
        } else {
            ArrayList<Music> songs = user.getCurrentAlbum().getSongs();
            int a = songs.indexOf(user.getMusic());
            user.getMusic().pause();
            user.setMusic(songs.get(a + 1));
            user.getMusic().play();
        }

    }//GEN-LAST:event_forwardButtonActionPerformed

    private void favoriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButtonActionPerformed
        if (liked == false) {
            setImage(favoriteButton, "full.png");
            liked = true;
            ArrayList<Music> abc = user.getFavoritePlayList().getSongs();
            abc.add(user.getMusic());
            user.getFavoritePlayList().setSongs(abc);
//        this.FSongs.addSong(this.music);
        } else if (liked == true) {
            setImage(favoriteButton, "empty.png");
            liked = false;

            ArrayList<Music> abc = user.getFavoritePlayList().getSongs();
            abc.remove(user.getMusic());
            user.getFavoritePlayList().setSongs(abc);

        }

        //try {
        //      Files.write(Paths.get(), "\r\n".getBytes(), StandardOpenOption.APPEND);
        // }catch (IOException e) {
        //exception handling left as an exercise for the reader
        // }

        // updateUser(user);
    }//GEN-LAST:event_favoriteButtonActionPerformed


    private void volumeSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_volumeSliderStateChanged
//         volumeSlider.setValue(soundcontroller.getValue());
        soundcontroller.setValue(volumeSlider.getValue());
    }//GEN-LAST:event_volumeSliderStateChanged

    private void musicSliderStateChanged(javax.swing.event.ChangeEvent evt) {

//GEN-FIRST:event_musicSliderStateChanged

        if (musicSlider.getValueIsAdjusting())
            try {
                music.play(musicSlider.getValue());
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
        
            }

//        jLLLLLLLLL

        
    }//GEN-LAST:event_musicSliderStateChanged

    private void musicSliderAncestorMoved(javax.swing.event.AncestorEvent evt) throws InterruptedException {//GEN-FIRST:event_musicSliderAncestorMoved


        Thread.sleep(100);
        musicSlider.setValue(musicSlider.getValue() + 1);


    }//GEN-LAST:event_musicSliderAncestorMoved

    private void editPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayListActionPerformed


    }//GEN-LAST:event_editPlayListActionPerformed

    public void buttonAndLabel() {
        buttons.add(i1);
        buttons.add(i2);
        buttons.add(i3);
        buttons.add(i4);
        buttons.add(i5);
        buttons.add(i6);
        buttons.add(i7);
        buttons.add(i8);
        buttons.add(i9);
        buttons.add(i10);
        buttons.add(i11);
        buttons.add(i12);
        buttons.add(i13);
        buttons.add(i14);
        buttons.add(i15);
        buttons.add(i16);
        buttons.add(i17);
        buttons.add(i18);
        buttons.add(i19);
        buttons.add(i20);
        buttons.add(i21);
        buttons.add(i22);
        buttons.add(i23);
        buttons.add(i24);
        labels.add(t1);
        labels.add(t2);
        labels.add(t3);
        labels.add(t4);
        labels.add(t5);
        labels.add(t6);
        labels.add(t7);
        labels.add(t8);
        labels.add(t9);
        labels.add(t10);
        labels.add(t11);
        labels.add(t12);
        labels.add(t13);
        labels.add(t14);
        labels.add(t15);
        labels.add(t16);
        labels.add(t17);
        labels.add(t18);
        labels.add(t19);
        labels.add(t20);
        labels.add(t21);
        labels.add(t22);
        labels.add(t23);
        labels.add(t24);

    }

    private void songsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_songsButtonActionPerformed
        buttonAndLabel();
        sortSongs();
        itsAlbum=false;
        System.out.println("size" + user.getSongs().size());

        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setVisible(false);

            buttons.get(i).removeNotify();
            labels.get(i).setVisible(false);


        }


        for (int i = 0; i < user.getSongs().size(); i++) {
            buttons.get(i).addNotify();
            setImage2(buttons.get(i), user.getSongs().get(i).getArtWork());
            labels.get(i).setText(user.getSongs().get(i).getTitle());
            System.out.println(labels.get(1).getText());
            System.out.println(labels.get(i).getText());

            buttons.get(i).setVisible(true);
            labels.get(i).setVisible(true);
            Music s = user.getSongs().get(i);
            if (i == 1)
                System.out.println(s.getTitle() + "+++++++++++++++++++++++++++++++++++++++++++++++++");
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {if (!itsAlbum){
                    try {
                        if (music.isIsplaying())
                            music.pause();
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }
                    try {
                        setMusic(s);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    }


                }}
            });
            //   updateUser(user);
        }
    }//GEN-LAST:event_songsButtonActionPerformed

    private void addToLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException {//GEN-FIRST:event_addToLibraryButtonActionPerformed
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        String path = "UsersFolder/".concat(username).concat(".txt");
        System.out.println(path);
        //try {
        //   Files.write(Paths.get(path), filename.getBytes(), StandardOpenOption.APPEND);
        //}catch (IOException e2) {
        //}

        // try {
        //   Files.write(Paths.get("UsersFolder/filename.txt"), "\r\n".getBytes(), StandardOpenOption.APPEND);
        // }catch (IOException e2) {
        // }


        ArrayList<Album> albums = user.getAlbums();
        boolean isAlone = true;
        for (Album a : albums) {
            if (a.getTitle().equals(new Music(f).getAlbum())) {
                a.addSong(new Music(f));
                isAlone = false;
            }
        }
        System.out.println("isAlone" + "::::::::::" + isAlone);
        if (isAlone) {
            Album newA = new Album();
            newA.addSong(new Music(f));
            albums.add(newA);
        }
        user.setAlbums(albums);
        System.out.println("}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}");
        for (Album album1 : user.getAlbums()) {
            System.out.println("*************************************************");
            System.out.println(album1.getTitle());
        }
        System.out.println(filename);
        try {
            ArrayList<Music> abc = user.getSongs();
            abc.add(new Music(f));
            user.setSongs(abc);
            user.setSongNum(user.getSongs().size());


//       this.songNum++;
//       boolean flag =false;
//
//       for(Album album:user.getAlbums()) {
//             try {
//                 if (new Music(f).getAlbum().equals(album.getTitle())) {
//                     try {
//                         album.addSong(new Music(f));
//                     } catch (IOException ex) {
//                         Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//                     } catch (InvalidDataException ex) {
//                         Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//                     } catch (UnsupportedTagException ex) {
//                         Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//                     }
//                     flag = true;
//                 } } catch (IOException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (InvalidDataException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (UnsupportedTagException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             }
//
//
//
//             System .out.print(album.getTitle());
//       }
//       if (!flag){
//           Album a=new Album();
//             try {
//                 a.addSong(new Music(f));
//             } catch (IOException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (InvalidDataException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             } catch (UnsupportedTagException ex) {
//                 Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
//             }
//             ArrayList<Album> cde=user.getAlbums();
//             cde.add(a);
//             user.setAlbums(cde);
//             System .out.print(album.getTitle());


//           albums.add(a);
        } catch (UnsupportedTagException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidDataException e) {
            e.printStackTrace();
        }
        //serializeUser(user);
    updateUser(user);
    
    }
//GEN-LAST:event_addToLibraryButtonActionPerformed

     public void serializeUser(User user){
       System.out.println("name"+user.getName());
       try{
           ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(user.getName().concat(".bin")));
           os.writeObject(user);
           os.close();
       }catch(FileNotFoundException e){
           System.out.println("nnnnnnnnnnnnnnnnnn");
           e.printStackTrace();
       }
       catch(IOException e){
           e.printStackTrace();
       }
       System.out.println("finish serializin");
   }
    
    

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) throws JavaLayerException {

        if (this.music.isIsplaying()){
            System.out.println(this.music.getTitle()+"is previous");
            this.music.pause();
        }
        this.music = music;
        jLabel3.setText(music.timetoString(music.getTime()));
        jl1.setText("Name:" + music.getTitle());
        jl2.setText("Artist: " + music.getArtist());
        jl3.setText("Album: " + music.getAlbum());
        jLabel5.setText("");
        jLabel5.setIcon(new ImageIcon(music.getArtWork()));
//        this.music.play();
        System.out.println(this.music.getTitle()+"is now");

        music.play();

    }

    public void whichAlbum() {

        ArrayList<Music> songss = user.getSongs();
        ArrayList<Album> albumss = user.getAlbums();

        for (Music m : songss) {

            for (Album a : albumss) {

                if (a.getTitle().equals(m.getAlbum())) {
                    ArrayList<Music> nsongs = user.getSongs();
                    nsongs.add(m);
                    user.setSongs(nsongs);
                    break;
                }
            }
        }


    }


    private void friendButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_friendButton3ActionPerformed

    private void friendButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendButton4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_friendButton4ActionPerformed

    private void friendButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_friendButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_friendButton2ActionPerformed

    private void editsPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editsPlayListActionPerformed


//?????????????????????????????????????????????????????????????????????????
        int value = playListList.getSelectedIndex();
        if (playList.getSize() != 0 && value != 0 && value != 1) {
            playList.remove(value);
        }
        playListList.setModel(playList);
        // updateUser(user);
    }//GEN-LAST:event_editsPlayListActionPerformed

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {//GEN-FIRST:event_i1ActionPerformed
        for (int i = 0; i < user.getSongs().size(); i++) {
            if (user.getSongs().get(i).getTitle().equals(i1.getText())) {
                setMusic(user.getSongs().get(i));
                break;
            }
        }
    }//GEN-LAST:event_i1ActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {


        if (!music.isIsplaying())//GEN-FIRST:event_playButtonActionPerformed

            try {
                music.play((int) ((float)musicSlider.getValue() * music.getTime() / 100));
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }


        if (music.getStime()==0)
            music.play();

    }//GEN-LAST:event_playButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed

        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEE");


        if (music.isIsplaying())

            try {
                music.pause();
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        System.out.println("EEEEEEEEEEEEEEEEEEEEEEEE");


    }//GEN-LAST:event_pauseButtonActionPerformed

    private boolean itsAlbum=false;
    private void albumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumButtonActionPerformed

        itsAlbum=true;
        for (int i = 0; i < buttons.size(); i++) {
            buttons.get(i).setVisible(false);
            labels.get(i).setVisible(false);
            buttons.get(i).removeNotify();
        }

        for (int i = 0; i < user.getAlbums().size(); i++) {
            buttons.get(i).addNotify();
            labels.get(i).setText(user.getAlbums().get(i).getTitle());
            labels.get(i).setVisible(true);
            setImage2(buttons.get(i),user.getAlbums().get(i).getImage());
            buttons.get(i).setVisible(true);
            Album al=user.getAlbums().get(i);
            JLabel jl=labels.get(i);
//            buttons.get(i).addActionListener(e -> jl.setText("LLLLLL"));
            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (int i = 0; i < buttons.size(); i++) {

                        buttons.get(i).setVisible(false);
                        labels.get(i).setVisible(false);
                        buttons.get(i).removeNotify();
                    }
                    for (int i=0;i<al.getSongs().size();i++){
                        buttons.get(i).addNotify();
                        labels.get(i).setText(al.getSongs().get(i).getTitle());
                        labels.get(i).setVisible(true);
                        buttons.get(i).setIcon(new ImageIcon(al.getSongs().get(i).getArtWork()));
                        buttons.get(i).setVisible(true);
                        Music s=al.getSongs().get(i);
                        buttons.get(i).addActionListener(e1 -> {
                            try {


                                setMusic(s);
                                music.play();
                            } catch (JavaLayerException e2) {
                                e2.printStackTrace();
                            }
                        });



                    }

                }
            });
//        buttons.get(i).addActionListener(new ActionListener() {

//                    @Override
////                    public void actionPerformed(ActionEvent e) {
////                        album = al;
////                        for (int i = 0; i < al.getSongs().size(); i++) {
////                            setImage2(buttons.get(i), user.getAlbums().get(i).getImage());
////                            labels.get(i).setText(user.getAlbums().get(i).getSongs().get(i).getTitle());
////                            buttons.get(i).setVisible(true);
////                            Music m = user.getAlbums().get(i).getSongs().get(i);
//////                            buttons.get(i).addActionListener(new ActionListener() {
//////                                @Override
//////                                public void actionPerformed(ActionEvent e) {
//////                                    try {
//////                                        if (music.isIsplaying())
//////                                            music.pause();
//////                                    } catch (JavaLayerException e1) {
//////                                        e1.printStackTrace();
//////                                    }
//////                                    music = m;
//////                                    try {
//////                                        music.play();
//////                                    } catch (JavaLayerException e1) {
//////                                        e1.printStackTrace();
//////                                    }
//////                                }
//////                            });
////                        }
////
////                    }
////                });
////            }
////        }
////
////
////
////
////
////
////
//            }
//        }
//
//    }//GEN-LAST:event_albumButtonActionPerformed
        }
    }
    private void newPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlayListActionPerformed
        newPlayListList n = new newPlayListList(user);
        n.setVisible(true);
    }//GEN-LAST:event_newPlayListActionPerformed
        
   


//????????????????????????????????????????????????????????????
    /**
     * @param args the command line arguments
     */
    public void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            //</editor-fold>

            /* Create and display the dialog */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    fileNameSerialize = username.concat(".bin");
                    HomePage homepage = null;
                    System.out.println("name" + fileNameSerialize);
              /*   try {
                     homepage = new HomePage(username);
                 } catch (IOException | JavaLayerException ex) {
                     Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (InvalidDataException ex) {
                     Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (UnsupportedTagException ex) {
                     Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                 }
                      homepage.setVisible(true);
                      System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
                
                      try{
                          ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fileNameSerialize));
                          os.writeObject(homepage);
                          os.close();
                      }catch(FileNotFoundException e){
                      e.printStackTrace();
                      }catch(IOException e ){
                      e.printStackTrace();
                      }

                      System.out.println("kkkkkkkkkkkkkkkkkkkkkkkk");
*/
                }
            });
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
        public void sortSongs() {


            ArrayList<Long> times = new ArrayList<>();
            int i = 0;
            for (Music music : user.getSongs()) {

                times.add(music.getStime());
            }

            Collections.sort(times);
            Collections.reverse(times);
            ArrayList<Music> sortedSongs = new ArrayList<>();

            for (int j = 0; j < times.size(); j++) {
                for (Music m : user.getSongs()) {
                    if (m.getStime() == times.get(j)) {

                        if(!sortedSongs.contains(m))
                        sortedSongs.add(m);

                    }
                }

            }
            user.setSongs(sortedSongs);

//            this.songs = sortedSongs;

        //updateUser(user);

        }


    public void sortAlbums() {


        ArrayList<Long> times = new ArrayList<>();
        int i = 0;
        for (Album album:user.getAlbums()) {

            times.add(album.getLtime());
        }

        Collections.sort(times);
        Collections.reverse(times);
        ArrayList<Album> sortedAlbums = new ArrayList<>();

        for (int j = 0; j < times.size(); j++) {
            for (Album album:albums) {
                if (album.getLtime()==times.get(j)) {

                    sortedAlbums.add(album);

                }
            }

        }

        user.setAlbums(sortedAlbums);

//        this.albums=sortedAlbums;

        //updateUser(user);

    }











    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addToLibraryButton;
    private javax.swing.JButton albumButton;
    private javax.swing.JButton backwardButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel displayMusicPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton editPlayList;
    private java.awt.Button editsPlayList;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel friendActivityLabel;
    private javax.swing.JPanel friendActivityPanel;
    private java.awt.Button friendButton1;
    private java.awt.Button friendButton2;
    private java.awt.Button friendButton3;
    private java.awt.Button friendButton4;
    private javax.swing.JButton i1;
    private javax.swing.JButton i10;
    private javax.swing.JButton i11;
    private javax.swing.JButton i12;
    private javax.swing.JButton i13;
    private javax.swing.JButton i14;
    private javax.swing.JButton i15;
    private javax.swing.JButton i16;
    private javax.swing.JButton i17;
    private javax.swing.JButton i18;
    private javax.swing.JButton i19;
    private javax.swing.JButton i2;
    private javax.swing.JButton i20;
    private javax.swing.JButton i21;
    private javax.swing.JButton i22;
    private javax.swing.JButton i23;
    private javax.swing.JButton i24;
    private javax.swing.JButton i3;
    private javax.swing.JButton i4;
    private javax.swing.JButton i5;
    private javax.swing.JButton i6;
    private javax.swing.JButton i7;
    private javax.swing.JButton i8;
    private javax.swing.JButton i9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel l15;
    private javax.swing.JLabel l6;
    private java.awt.Label libraryLabel;
    private javax.swing.JPanel libraryPanel;
    private javax.swing.JScrollPane libraryScroll;
    private javax.swing.JLabel musicLabel;
    private javax.swing.JSlider musicSlider;
    private javax.swing.JButton newPlayList;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JLabel playListLabel;
    private javax.swing.JList<String> playListList;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchText;
    private javax.swing.JButton shuffleButton;
    private javax.swing.JButton songsButton;
    private javax.swing.JPanel songsPanel;
    private javax.swing.JPanel soundBar;
    private javax.swing.JLabel t1;
    private javax.swing.JLabel t10;
    private javax.swing.JLabel t11;
    private javax.swing.JLabel t12;
    private javax.swing.JLabel t13;
    private javax.swing.JLabel t14;
    private javax.swing.JLabel t15;
    private javax.swing.JLabel t16;
    private javax.swing.JLabel t17;
    private javax.swing.JLabel t18;
    private javax.swing.JLabel t19;
    private javax.swing.JLabel t2;
    private javax.swing.JLabel t20;
    private javax.swing.JLabel t21;
    private javax.swing.JLabel t22;
    private javax.swing.JLabel t23;
    private javax.swing.JLabel t24;
    private javax.swing.JLabel t3;
    private javax.swing.JLabel t4;
    private javax.swing.JLabel t5;
    private javax.swing.JLabel t6;
    private javax.swing.JLabel t7;
    private javax.swing.JLabel t8;
    private javax.swing.JLabel t9;
    private javax.swing.JTextField userNameLabel;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
        }
