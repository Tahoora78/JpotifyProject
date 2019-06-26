/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package jpotify;


import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Home pc
 */
public class HomePage extends javax.swing.JDialog {

    private PlayList currentPlayList;
    ArrayList<JButton> addFriendSongs=new ArrayList<>();
    private Thread thread;
    private boolean liked = false;
    long pause;
    PlayingThread pt;
    public static boolean flag=false;
    public static boolean flag2=false;
    public static String needed;
    public static String fileName;
    public static boolean changed;
    public static int ctime;
    public boolean isFlag() {
        return flag;
    }

    public static void addingNewFile(File file){


//        adding needed to songs
//        File file1=new File()





















    }
    public File fileToTransfer;

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setAddFriendSongs(){

        addFriendSongs.add(addFriendSong10);
        addFriendSongs.add(addFriendSong11);
        addFriendSongs.add(addFriendSong12);
        addFriendSongs.add(addFriendSong13);

    }


//    public void setActionListenerOfAddFriendSongs(){
//        String nameFriend =
//        Friends friend = new Friends();
//
//
//        }
//
//
//
//
//    }

    //Thread playThread=new Thread(new PlayRunnable());
    private Music music;
    public static PlayList playlist = new PlayList("AAA");
    private Album album = new Album();
    private int mode;
    private Soundcontroller soundcontroller = new Soundcontroller();
    private PlayList FSongs = new PlayList("Favorit Songs");
    private PlayList Shared = new PlayList("Shared PlayList");
    private ArrayList<Album> albums = new ArrayList<>();
    private ArrayList<PlayList> playlists = new ArrayList<>();
    private ArrayList<Music> songs = new ArrayList<>();
    private Album currentAlbum;
    private int songNum = 0;
    String username;
    public static String playerSelected;
    private int nextSong;
    JButton jb = new JButton("LOLOOLOLOO");
    DefaultListModel playList;
    ArrayList<JButton> buttons = new ArrayList<>();
    ArrayList<JButton> Abuttons = new ArrayList<>();
    ArrayList<JButton> Asbuttons = new ArrayList<>();
    ArrayList<JLabel> playerLabels = new ArrayList<>();
    ArrayList<JTextField> playingTexts = new ArrayList<>();
    ArrayList<JTextField> timeTexts = new ArrayList<>();
    ArrayList<JButton> AddSongButton = new ArrayList<>();

    ArrayList<JLabel> labels = new ArrayList<>();
    String fileNameSerialize;
    public static User user;

    /**
     * Creates new form homePage
     */

    private boolean itsSong = false;
    private boolean itsSongInAlbum = false;


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
    /*
    public User deserialiseUser(User Myuser) {
        User users = null;
        System.out.println("%%%%%%%%%%%%%%%%%%%%"+user.getName());
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
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;"+users.getAlbums().size());
        return users;
    }
*/
    //**
    //this method serialize the user and save it
    //**
    public static void updateUser(User user) {
        System.out.println("update start");
        new File(user.getName().concat(".bin")).delete();
        try {
            String g = user.getName().concat(".bin");
            System.out.println("g" + g);
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(g));
            os.writeObject(user);
            os.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("update finished");
    }

    public static User deserializeUser(String name) throws FileNotFoundException {

        User userd = null;
        System.out.println("name" + name);
        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(name.concat(".bin")));
            userd = (User) is.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("finish deserializing");
        System.out.println(";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;" + userd.getAlbums().size());
        return userd;
    }

    public HomePage(String name) throws IOException, InvalidDataException, InvalidDataException, UnsupportedTagException, UnsupportedTagException, UnsupportedTagException, JavaLayerException, InterruptedException {
        initComponents();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
        user = deserializeUser(name);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                serializeUser(user);
                e.getWindow().dispose();
            }
        });
//        user.setLastMusic(new Music(new File("k.mp3")));
        System.out.println("*********************&&&*****");
        for (int i = 0; i < user.getSongs().size(); i++) {
            System.out.println(user.getSongs().get(i).getTitle());
        }
        System.out.println("**********************************&&&&&****");
        System.out.println("finish deserializing");
//        music = new Music(new File("k.mp3"));
//        music.play();

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
        setImage(addCurrentToButton,"add.jpg");

//        setImageLabel(musicLabel,"sound.jfif");
//        user.getPlayLists().add(FSongs);
//        user.getPlayLists().add(Shared);
        int count = 0;
        for (int i = 0; i < user.getAlbums().size(); i++) {
            Album a = user.getAlbums().get(i);
            ArrayList<Music> songss = a.getSongs();
            for (int j = 0; j < songss.size(); j++) {
                ArrayList<Music> abc = user.getSongs();
                if(!abc.contains(songss.get(j)))
                    abc.add(songss.get(j));
                user.setSongs(abc);
                this.songs=abc;
            }

        }
        JLabel j1 = new JLabel();
        j1.setVisible(true);


        user.setSongNum(user.getSongs().size());
        playList = new DefaultListModel();
        playList.removeAllElements();
        System.out.println("sizePlayList" + user.getPlayLists().size());
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            System.out.println(user.getPlayLists().get(i).getTitle());
        }
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            playList.addElement(user.getPlayLists().get(i).getTitle());
        }
        buttonAndLabelp1();
        playListList.setModel(playList);
        playListList.getSelectionModel().addListSelectionListener(e -> {
                    //write action listener here;
                    String pname = playListList.getSelectedValue();
                    playerSelected = pname;
                    PlayList pl = null;
                    for (int i = 0; i < user.getPlayLists().size(); i++) {
                        if (user.getPlayLists().get(i).getTitle().equals(pname)) {
                            pl = user.getPlayLists().get(i);
                            System.out.println("(((((((((((11111111111111111111111111111(((((((((((((((((((("+user.getPlayLists().get(i).getTitle());
                            break;
                        }
                    }

                    currentPlayList=pl;
                    user.setCurrenPlayList(pl);
//                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&"+pl.getTitle());

                    for (int i = 0; i < buttons.size(); i++) {
//                        buttons.get(i).removeNotify();

                        ActionListener[] acts=buttons.get(i).getActionListeners();
                        for (int j = 0; j <acts.length ; j++) {
                            buttons.get(i).removeActionListener(acts[j]);
                        }
                        buttons.get(i).setVisible(false);
                        labels.get(i).setVisible(false);
                    }
                    Image image;
                    if (pl.getSongs().size()!=0)
                    for (int i = 0; i < pl.getSongs().size(); i++) {
//                        buttons.get(i).addNotify();
                        Music mm = null;
                        try {
                            mm = new Music(pl.getSongs().get(i).getMusic());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        } catch (JavaLayerException e1) {
                            e1.printStackTrace();
                        }

                        Mp3File mp3 = null;

                        try {
                            mp3 = new Mp3File(mm.getMusic().getAbsoluteFile());
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        } catch (UnsupportedTagException e1) {
                            e1.printStackTrace();
                        } catch (InvalidDataException e1) {
                            e1.printStackTrace();
                        }

                        byte[] bb;
                        try {

                            bb = mp3.
                                    getId3v2Tag().
                                    getAlbumImage();
                            ImageIcon image1 = new ImageIcon(bb);
                            image = Music.getScaledImage(image1.getImage(), 100, 100);

                        } catch (NullPointerException e1) {
                            ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                            image = Music.getScaledImage(imageIcon.getImage(), 100, 100);
                        }


                        setImage2(buttons.get(i), image);
                        labels.get(i).setText(pl.getSongs().get(i).getTitle());
                        buttons.get(i).setVisible(true);
                        labels.get(i).setVisible(true);
                        Music s = pl.getSongs().get(i);
                        buttons.get(i).addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                mode=1;
                                try {
                                    setMusic(s);
                                } catch (JavaLayerException e1) {
                                    e1.printStackTrace();
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (InvalidDataException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedTagException e1) {
                                    e1.printStackTrace();
                                } catch (InterruptedException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        });


                    }
                }
        );
    }



    //updateUser(user);

//    public void labelAndTextAndButton(){
//        playerLabels.add(namePlayer);
//        playerLabels.add(namePlayer1);
//        playerLabels.add(namePlayer2);
//        playerLabels.add(namePlayer3);
//        playerLabels.add(namePlayer4);
//        playingTexts.add(playingText);
//        playingTexts.add(playingText1);
//        playingTexts.add(playingText2);
//        playingTexts.add(playingText3);
//        playingTexts.add(playingText4);
//        timeTexts.add(timeText);
//        timeTexts.add(timeText1);
//        timeTexts.add(timeText2);
//        timeTexts.add(timeText3);
//        timeTexts.add(timeText4);
//        AddSongButton.add(addFriendSong);
//        AddSongButton.add(addFriendSong1);
//        AddSongButton.add(addFriendSong2);
//        AddSongButton.add(addFriendSong3);
//        AddSongButton.add(addFriendSong4);
//    }

    //    public void
    public void updatePlayListList(String namePlayList){
        playerSelected = namePlayList;
        playList.addElement(namePlayList);
        playListList.setModel(playList);
        playListList.getSelectionModel().addListSelectionListener(e -> {
            //write action listener here;

        });

    }





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
        updateButon = new javax.swing.JButton();
        editPlayListButton = new javax.swing.JButton();
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
        addCurrentToButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jl1 = new javax.swing.JLabel();
        jl3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        friendActivityLabel = new javax.swing.JLabel();
        friendActivityPanel2 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        addFriendSong10 = new javax.swing.JButton();
        friendsLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        addFriendSong11 = new javax.swing.JButton();
        friendsLabel2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        addFriendSong12 = new javax.swing.JButton();
        friendsLabel3 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        addFriendSong13 = new javax.swing.JButton();
        friendsLabel4 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        addFriendSong14 = new javax.swing.JButton();
        friendsLabel5 = new javax.swing.JLabel();
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
        i11 = new javax.swing.JButton();
        i12 = new javax.swing.JButton();
        i14 = new javax.swing.JButton();
        i2 = new javax.swing.JButton();
        i8 = new javax.swing.JButton();
        i24 = new javax.swing.JButton();
        i9 = new javax.swing.JButton();
        i17 = new javax.swing.JButton();
        i20 = new javax.swing.JButton();
        i15 = new javax.swing.JButton();
        i16 = new javax.swing.JButton();
        i6 = new javax.swing.JButton();
        i7 = new javax.swing.JButton();
        i10 = new javax.swing.JButton();
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
        i18 = new javax.swing.JButton();
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
        sortComboBox = new javax.swing.JComboBox<>();
        sortButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 204, 255));

        libraryPanel.setBackground(new java.awt.Color(255, 102, 255));

        albumButton.setBackground(new java.awt.Color(255, 51, 204));
        albumButton.setText("album");
        albumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    albumButtonActionPerformed(evt);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        songsButton.setBackground(new java.awt.Color(255, 51, 204));
        songsButton.setText("Songs");
        songsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    songsButtonActionPerformed(evt);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
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

        editsPlayList.setLabel("delete");
        editsPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayListActionPerformed(evt);
            }
        });

        updateButon.setText("update");
        updateButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButonActionPerformed(evt);
            }
        });

        editPlayListButton.setText("edit");
        editPlayListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayListButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout libraryPanelLayout = new javax.swing.GroupLayout(libraryPanel);
        libraryPanel.setLayout(libraryPanelLayout);
        libraryPanelLayout.setHorizontalGroup(
            libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(libraryPanelLayout.createSequentialGroup()
                .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(92, 92, 92)
                        .addComponent(songsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(albumButton, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                                .addComponent(songsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(libraryPanelLayout.createSequentialGroup()
                                .addComponent(playListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editPlayListButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(updateButon, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(editsPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(204, 204, 204)
                        .addComponent(editPlayList)
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
                    .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(playListLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButon)
                        .addComponent(editPlayListButton))
                    .addComponent(editsPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(libraryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(songsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(libraryPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 493, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 13, Short.MAX_VALUE)))
                .addContainerGap())
        );

        libraryScroll.setViewportView(libraryPanel);

        soundBar.setBackground(new java.awt.Color(0, 0, 255));

        musicSlider.setBackground(new java.awt.Color(0, 0, 102));
        musicSlider.setMaximum(1000);
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
                try {
                    musicSliderStateChanged(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        musicSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                try {
                    musicSliderMouseReleased(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        musicLabel.setText("jLabel1");

        volumeSlider.setValue(100);
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
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    forwardButtonActionPerformed(evt);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
            }
        });

        favoriteButton.setText("jButton2");
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("jButton1");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    pauseButtonActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        backwardButton.setText("jButton1");
        backwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardButtonActionPerformed(evt);
            }
        });

        shuffleButton.setText("jButton1");
        shuffleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleButtonActionPerformed(evt);
            }
        });

        addCurrentToButton.setText("jButton1");
        addCurrentToButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCurrentToButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsPanelLayout = new javax.swing.GroupLayout(buttonsPanel);
        buttonsPanel.setLayout(buttonsPanelLayout);
        buttonsPanelLayout.setHorizontalGroup(
            buttonsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsPanelLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(addCurrentToButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(shuffleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
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
                    .addComponent(shuffleButton)
                    .addComponent(addCurrentToButton))
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
                        .addGap(315, 315, 315)
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

        jPanel12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addFriendSong10.setText("jButton1");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(addFriendSong10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(addFriendSong10)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addFriendSong11.setText("jButton1");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(addFriendSong11, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addComponent(addFriendSong11)
                .addContainerGap(17, Short.MAX_VALUE))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendsLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addFriendSong12.setText("jButton1");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(friendsLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addFriendSong12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(friendsLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(addFriendSong12)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addFriendSong13.setText("jButton1");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(friendsLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addFriendSong13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(friendsLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel15Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(addFriendSong13)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        addFriendSong14.setText("jButton1");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(friendsLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addFriendSong14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(friendsLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel16Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(addFriendSong14)))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout friendActivityPanel2Layout = new javax.swing.GroupLayout(friendActivityPanel2);
        friendActivityPanel2.setLayout(friendActivityPanel2Layout);
        friendActivityPanel2Layout.setHorizontalGroup(
            friendActivityPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        friendActivityPanel2Layout.setVerticalGroup(
            friendActivityPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(friendActivityPanel2Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(92, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendActivityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(friendActivityPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendActivityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(friendActivityPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel1);

        searchButton.setText("search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    searchButtonActionPerformed(evt);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        searchText.setText("search for...");
        searchText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTextActionPerformed(evt);
            }
        });

        newPlayList.setBackground(new java.awt.Color(255, 153, 153));
        newPlayList.setText("new playlist");
        newPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlayListActionPerformed(evt);
            }
        });

        displayPanel.setDoubleBuffered(false);
        displayPanel.setEnabled(false);
        displayPanel.setFocusable(false);

        jLabel1.setText("jLabel1");

        i11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i11ActionPerformed(evt);
            }
        });

        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    i1ActionPerformed(evt);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
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
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(i20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(i14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(i17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(displayPanelLayout.createSequentialGroup()
                                    .addComponent(i15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(i16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(128, 128, 128))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                                    .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(i22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(142, 142, 142)))
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)))
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 38, Short.MAX_VALUE)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(l6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(58, 58, 58))))
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(i13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(673, 673, 673))
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
                            .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addComponent(l15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i16, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(displayPanelLayout.createSequentialGroup()
                                .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, displayPanelLayout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                                    .addComponent(i23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanelLayout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(113, Short.MAX_VALUE))
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
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        displayMusicPanel.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout displayMusicPanelLayout = new javax.swing.GroupLayout(displayMusicPanel);
        displayMusicPanel.setLayout(displayMusicPanelLayout);
        displayMusicPanelLayout.setHorizontalGroup(
            displayMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1069, Short.MAX_VALUE)
        );
        displayMusicPanelLayout.setVerticalGroup(
            displayMusicPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 101, Short.MAX_VALUE)
        );

        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "alphabet", "artist", "time" }));

        sortButton.setText("sort");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    sortButtonActionPerformed(evt);
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

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
                                    .addComponent(addToLibraryButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(libraryScroll, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(newPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(168, 168, 168)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(30, 30, 30)
                                        .addComponent(displayMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(soundBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(17, 17, 17)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(addToLibraryButton)
                                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sortButton)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(searchButton))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(libraryScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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

    /**
     *
     //     * @param songNamePlaying
     //     * @param time
     //     * @param time
     */

//    tahoora
//    public void updateFriendActivity(String songNamePlaying,String time,String friendName){
//        namePlayer.setText(friendName);
//        playingText.setText(songNamePlaying);
//        timeText.setText(time);
//    }


    boolean shuffle=false;
    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {//GEN-FIRST:event_forwardButtonActionPerformed
        System.out.println("true in forward");


        if (!shuffle){
        if (this.mode == 0)
            return;
        else if (mode == 1) {
            ArrayList<Music> songs = currentPlayList.getSongs();
            int a = songs.indexOf(user.getLastMusic());
            user.setLastMusic(songs.get((a + 1)%songs.size()));
            setMusic(songs.get((a + 1)%songs.size()));


        }
        else {
            ArrayList<Music> songs1 = user.getCurrentAlbum().getSongs();
            int a = songs1.indexOf(user.getLastMusic());
            user.setLastMusic(songs1.get((a + 1)%songs1.size()));
            setMusic(songs1.get((a + 1)%songs1.size()));
        }
        }


        else {

            Random rand=new Random();

            if (this.mode == 0)
                return;
            else if (mode == 1) {
                ArrayList<Music> songs = currentPlayList.getSongs();
                int a = songs.indexOf(user.getLastMusic());
                int b=a+rand.nextInt();
                user.setLastMusic(songs.get((b)%songs.size()));
                setMusic(songs.get((b)%songs.size()));


            }
            else {
                ArrayList<Music> songs1 = user.getCurrentAlbum().getSongs();
                int a = songs1.indexOf(user.getLastMusic());
                int b=a+rand.nextInt();
                user.setLastMusic(songs1.get((b)%songs1.size()));
                setMusic(songs1.get((b)%songs1.size()));
            }





        }

    }//GEN-LAST:event_forwardButtonActionPerformed

    private void favoriteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_favoriteButtonActionPerformed
        if (liked == false) {
            setImage(favoriteButton, "full.png");
            liked = true;
            user.getFavoritePlayList().addSong(music);
            // ArrayList<Music> abc = user.getFavoritePlayList().getSongs();
            //abc.add(user.getMusic());
            //user.getFavoritePlayList().setSongs(abc);

//        this.FSongs.addSong(this.music);
        } else if (liked == true) {
            setImage(favoriteButton, "empty.png");
            liked = false;
            user.getFavoritePlayList().removeSong(music.getTitle());
            //ArrayList<Music> abc = user.getFavoritePlayList().getSongs();
            // abc.remove(user.getMusic());
            // user.getFavoritePlayList().setSongs(abc);

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

    private void musicSliderStateChanged(ChangeEvent evt) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {

//GEN-FIRST:event_musicSliderStateChanged
//        JSlider source = (JSlider) evt.getSource();
//        System.out.println("Here");
//
//        System.out.println(music.getInput().available());
//        FileInputStream newInput=new FileInputStream(music.getPath());
//
//        System.out.println(newInput.available());
//        System.out.println(source.getValue());
//        try{pt.stop();}finally {
//
//
//            System.out.println("here1finally");
//            newInput.skip((long) ((double) source.getValue() / 1000 * music.getStreamLength()));
//            System.out.println("Here");
//            music.setInput(newInput);
//            pt = new PlayingThread(music);
//            pt.start();
//        pt=new

    }//GEN-LAST:event_musicSliderStateChanged

    private void musicSliderAncestorMoved(javax.swing.event.AncestorEvent evt) throws InterruptedException {//GEN-FIRST:event_musicSliderAncestorMoved


    }//GEN-LAST:event_musicSliderAncestorMoved

    private void editPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayListActionPerformed


    }//GEN-LAST:event_editPlayListActionPerformed

    public void buttonAndLabelp1() {
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
        buttons.add(i9);
        buttons.add(i13);
        buttons.add(i14);
        buttons.add(i15);
        buttons.add(i16);
        buttons.add(i8);
        buttons.add(i7);
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
        labels.add(t17);labels.add(t18);
        labels.add(t19);
        labels.add(t20);
        labels.add(t21);
        labels.add(t22);
        labels.add(t23);
        labels.add(t24);
        for (int i=0;i<labels.size();i++){
            labels.get(i).setVisible(false);
        }

        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setVisible(false);}

    }


    ArrayList<JButton> buttons2=new ArrayList<>();
    ArrayList<JLabel> labels2=new ArrayList<>();

//    public void buttonAndLabelp2() {
//        buttons2.add(i25);
//        buttons2.add(i26);
//        buttons2.add(i27);
//        buttons2.add(i28);
//        buttons2.add(i29);
//        buttons2.add(i30);
//        buttons2.add(i31);
//        buttons2.add(i32);
//        buttons2.add(i33);
//        buttons2.add(i34);
//        buttons2.add(i35);
//        buttons2.add(i36);
//        buttons2.add(i37);
//        buttons2.add(i38);
//        buttons2.add(i39);
//        buttons2.add(i40);
//        buttons2.add(i41);
//        buttons2.add(i42);
//        buttons2.add(i43);
//        buttons2.add(i44);
//        buttons2.add(i45);
//        buttons2.add(i46);
//        buttons2.add(i47);
//        buttons2.add(i48);
//        buttons2.add(i49);
//        labels2.add(t25);
//        labels2.add(t26);
//        labels2.add(t27);
//        labels2.add(t28);
//        labels2.add(t29);
//        labels2.add(t30);
//        labels2.add(t31);
//        labels2.add(t32);
//        labels2.add(t33);
//        labels2.add(t34);
//        labels2.add(t35);
//        labels2.add(t36);
//        labels2.add(t37);
//        labels2.add(t38);
//        labels2.add(t39);
//        labels2.add(t40);
//        labels2.add(t41);
//        labels2.add(t42);
//        labels2.add(t43);
//        labels2.add(t44);
//        labels2.add(t45);
//        labels2.add(t46);
//        labels2.add(t47);
//        labels2.add(t48);
//        for (int i=0;i<labels2.size();i++){
//            labels2.get(i).setVisible(false);
//        }
//
//        for (int i=0;i<buttons2.size();i++) {
//            buttons2.get(i).setVisible(false);
//        }
//    }

    ArrayList<JButton> buttons3=new ArrayList<>();
    ArrayList<JLabel> labels3=new ArrayList<>();

//    public void buttonAndLabelp3() {
//        buttons3.add(i49);
//        buttons3.add(i50);
//        buttons3.add(i51);
//        buttons3.add(i52);
//        buttons3.add(i53);
//        buttons3.add(i54);
//        buttons3.add(i55);
//        buttons3.add(i56);
//        buttons3.add(i57);
//        buttons3.add(i58);
//        buttons3.add(i59);
//        buttons3.add(i60);
//        buttons3.add(i61);
//        buttons3.add(i62);
//        buttons3.add(i63);
//        buttons3.add(i64);
//        buttons3.add(i65);
//        buttons3.add(i66);
//        buttons3.add(i67);
//        buttons3.add(i68);
//        buttons3.add(i69);
//        buttons3.add(i70);
//        buttons3.add(i71);
//        buttons3.add(i72);
//        buttons3.add(i74);
//        labels3.add(t49);
//        labels3.add(t50);
//        labels3.add(t51);
//        labels3.add(t52);
//        labels3.add(t53);
//        labels3.add(t54);
//        labels3.add(t55);
//        labels3.add(t56);
//        labels3.add(t57);
//        labels3.add(t58);
//        labels3.add(t59);
//        labels3.add(t60);
//        labels3.add(t61);
//        labels3.add(t62);
//        labels3.add(t63);
//        labels3.add(t64);
//        labels3.add(t65);
//        labels3.add(t66);
//        labels3.add(t67);
//        labels3.add(t68);
//        labels3.add(t69);
//        labels3.add(t70);
//        labels3.add(t71);
//        labels3.add(t72);
//        for (int i=0;i<labels3.size();i++){
//            labels3.get(i).setVisible(false);
//        }
//
//        for (int i=0;i<buttons3.size();i++){
//            buttons3.get(i).setVisible(false);
//
//        }
//
//    }



    ArrayList<JButton> buttons4=new ArrayList<>();
    ArrayList<JLabel> labels4=new ArrayList<>();
//
//    public void buttonAndLabelp4() {
//        buttons4.add(i73);
//        buttons4.add(i74);
//        buttons4.add(i75);
//        buttons4.add(i76);
//        buttons4.add(i77);
//        buttons4.add(i78);
//        buttons4.add(i79);
//        buttons4.add(i80);
//        buttons4.add(i81);
//        buttons4.add(i82);
//        buttons4.add(i83);
//        buttons4.add(i84);
//        buttons4.add(i85);
//        buttons4.add(i86);
//        buttons4.add(i87);
//        buttons4.add(i88);
//        buttons4.add(i89);
//        buttons4.add(i90);
//        buttons4.add(i91);
//        buttons4.add(i92);
//        buttons4.add(i93);
//        buttons4.add(i94);
//        buttons4.add(i95);
//        buttons4.add(i96);
//        labels4.add(t73);
//        labels4.add(t74);
//        labels4.add(t75);
//        labels4.add(t76);
//        labels4.add(t77);
//        labels4.add(t78);
//        labels4.add(t79);
//        labels4.add(t80);
//        labels4.add(t81);
//        labels4.add(t82);
//        labels4.add(t83);
//        labels4.add(t84);
//        labels4.add(t85);
//        labels4.add(t86);
//        labels4.add(t87);
//        labels4.add(t88);
//        labels4.add(t89);
//        labels4.add(t90);
//        labels4.add(t91);
//        labels4.add(t92);
//        labels4.add(t93);
//        labels4.add(t94);
//        labels4.add(t95);
//        labels4.add(t96);
//        for (int i=0;i<labels4.size();i++){
//            labels4.get(i).setVisible(false);
//        }
//
//        for (int i=0;i<buttons4.size();i++){
//            buttons4.get(i).setVisible(false);
//
//        }
//
//    }
//
//
//


    private void songsButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {//GEN-FIRST:event_songsButtonActionPerformed
        buttonAndLabelp1();
//        buttonAndLabelp2();
//        buttonAndLabelp3();
//        buttonAndLabelp4();

        displayPanel.setVisible(true);
//        displayPanel1.setVisible(false);
//        displayPanel2.setVisible(false);
//        displayPanel3.setVisible(false);
        sortSongs();
        for (int i=0;i<buttons.size()&&i<labels.size();i++){

            ActionListener[] action=buttons.get(i).getActionListeners();
            for (int j = 0; j < action.length; j++) {
                buttons.get(i).removeActionListener(action[j]);

            }

        }


        Image image;
        System.out.println("||||||||||||||||");

        for (int i = 0; i < user.getSongs().size(); i++) {
            System.out.println(user.getSongs().get(i).getTitle());

        }
        System.out.println("||||||||||||||||");

        for (int i = 0; i < user.getSongs().size(); i++) {

            Music mm = new Music(user.getSongs().get(i).getMusic());
            Mp3File mp3 = new Mp3File(mm.getMusic().getAbsolutePath());
            byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
            try {
                bb = mp3.
                        getId3v2Tag().
                        getAlbumImage();
                ImageIcon image1 = new ImageIcon(bb);
                image = Music.getScaledImage(image1.getImage(), 200, 200);

            } catch (NullPointerException e) {
                ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                image = Music.getScaledImage(imageIcon.getImage(), 200, 200);
            }


            setImage2(buttons.get(i), image);
            labels.get(i).setText(mm.getTitle());
            buttons.get(i).setVisible(true);
            labels.get(i).setVisible(true);

            Music m1=(user.getSongs().get(i));

            ActionListener acsong=new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mode = 0;
                    try {
                        setMusic(m1);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }


                }

            };

            buttons.get(i).addActionListener(acsong);
//            buttons.get(i).removeActionListener(acsong);

            //   updateUser(user);
        }
    }//GEN-LAST:event_songsButtonActionPerformed

    private void addToLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {//GEN-FIRST:event_addToLibraryButtonActionPerformed

        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(this);
        File f = chooser.getSelectedFile();
        String filename = f.getAbsolutePath();
        String path = "UsersFolder/".concat(username).concat(".txt");
        System.out.println(path);

        ArrayList<Music> son=user.getSongs();
        if(!son.contains(new Music(f)))
            son.add(new Music(f));

        ArrayList<String> alnames=new ArrayList<>();
        for(int i=0;i<son.size();i++){
            if (!alnames.contains(son.get(i).getAlbum()))
                alnames.add(son.get(i).getAlbum());
        }


        ArrayList<Album> als=user.getAlbums();
        for(int i=0;i<alnames.size();i++){
            als.add(new Album());
        }

        for (int i=0;i<son.size();i++) {
            for (int j = 0; j < alnames.size(); j++) {
                if (son.get(i).getAlbum().equals(alnames.get(j))&&(!als.get(j).getSongs().contains(son.get(i)))) {
                    als.get(j).addSong(son.get(i));
                    break;
                }
            }
        }
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&"+als.size());
        user.setAlbums(als);
        updateUser(user);
//        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&"+als.size());



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
        System.out.println("88888888888888888888888888888888888888888888888888888888888888888888888888888");
    }



    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) throws JavaLayerException, IOException, InvalidDataException, UnsupportedTagException, InterruptedException {

        if (pt!=null)
            pt.stop();
        this.music=new Music(new File(
                music.
                        getMusic().getAbsolutePath()
        ));
        this.pt=new PlayingThread(music,music.getInput());
        this.jl1.setText("Title :"+music.getTitle());
        this.jl2.setText("Artist :"+music.getArtist());
        this.jl3.setText("Album :"+music.getAlbum());
        this.jLabel3.setText(music.timetoString(music.getTime()));
        this.jLabel5.setIcon(new ImageIcon(music.getArtWork()));
        setImage(favoriteButton,"empty.png");
        user.setLastMusic(music);
        user.setLtime(System.currentTimeMillis());
        music.setStime(System.currentTimeMillis());
        System.out.println("Start");
        pt.start();
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


    private void deletPlayListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletPlayListButtonActionPerformed


//?????????????????????????????????????????????????????????????????????????
        String musicName = playListList.getSelectedValue();
        playerSelected = musicName;
        int value = playListList.getSelectedIndex();
        if (playList.getSize() != 0 && value != 0 && value != 1) {
            playList.remove(value);
        }
        playListList.setModel(playList);
        for(int i=0;i<user.getPlayLists().size();i++){
            if(user.getPlayLists().get(i).getTitle().equals(musicName)){
                user.getPlayLists().remove(i);
                break;
            }
        }
        // updateUser(user);
    }//GEN-LAST:event_deletPlayListButtonActionPerformed

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, IOException, InterruptedException, UnsupportedTagException, InvalidDataException {//GEN-FIRST:event_i1ActionPerformed
        for (int i = 0; i < user.getSongs().size(); i++) {
            if (user.getSongs().get(i).getTitle().equals(i1.getText())) {
                setMusic(user.getSongs().get(i));
                break;
            }
        }
    }//GEN-LAST:event_i1ActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {


        if (pt.getState().equals(Thread.State.NEW))
            pt.start();
        else pt.resume();
        displayMusicPanel.setVisible(true);

    }

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_pauseButtonActionPerformed

        pt.suspend();
        displayMusicPanel.setVisible(false);


    }//GEN-LAST:event_pauseButtonActionPerformed

    private boolean itsAlbum=false;
    private void albumButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {//GEN-FIRST:event_albumButtonActionPerformed

        for (int i = 0; i < buttons.size() && i < labels.size(); i++) {

            ActionListener[] action = buttons.get(i).getActionListeners();
            for (int j = 0; j < action.length; j++) {
                buttons.get(i).removeActionListener(action[j]);

            }

        }

//        displayPanel1.show();
        ArrayList<Music> son = user.getSongs();
        ArrayList<String> albs = new ArrayList<>();
        ArrayList<Album> ualbs = new ArrayList<>();
        sortSongs();
        for (int i = 0; i < son.size(); i++) {

            String name = son.get(i).getAlbum();
            if (!albs.contains(name)) {

                albs.add(name);

            }

        }


        for (int i = 0; i < albs.size(); i++) {

            Album a = new Album();
            for (int j = 0; j < son.size(); j++) {
                if (son.get(j).getAlbum().equals(albs.get(i)))
                    a.addSong(son.get(j));
            }
            if (a.getSongs().size() != 0) {
                ualbs.add(a);
            }
//            System.out.println(a.getTitle()+"------------->"+a.getSongs().size());

        }

        user.setAlbums(ualbs);
        this.albums = ualbs;
        for (int i = 0; i < ualbs.size(); i++) {

            System.out.println(user.getAlbums().get(i).getTitle());


        }
        System.out.println("3333333333333333333333333333333333333");


        for (int i = 0; i < ualbs.size(); i++) {


            System.out.println(ualbs.size());
        }


        for (int i = 0; i < ualbs.size(); i++) {
//            buttons.get(i).addNotify();
            labels.get(i).setText(ualbs.get(i).getTitle());
            labels.get(i).setVisible(true);
            buttons.get(i).setVisible(true);

            Image image;
            if (ualbs.get(i) != null) {
                Music mm = ualbs
                        .get(i).
                                getSongs().
                                get(0);
                Mp3File mp3 = new Mp3File(mm.getMusic());
                byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                try {

                    bb = mp3.
                            getId3v2Tag().
                            getAlbumImage();
                    ImageIcon image1 = new ImageIcon(bb);
                    image = Music.getScaledImage(image1.getImage(), 200, 200);

                } catch (NullPointerException e) {
                    ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                    image = Music.getScaledImage(imageIcon.getImage(), 200, 200);
                }

                setImage2(buttons2.get(i), image);
                buttons.get(i).setVisible(true);
                Album al = ualbs.get(i);
//            buttons.get(i).addActionListener(e -> jl.setText("LLLLLL"));

                buttons.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        currentAlbum=al;
                        user.setCurrentAlbum(al);

                        for (int i = 0; i < buttons.size() && i < labels.size(); i++) {

                            ActionListener[] action = buttons.get(i).getActionListeners();
                            for (int j = 0; j < action.length; j++) {
                                buttons.get(i).removeActionListener(action[j]);

                            }


                            buttons.get(i).setVisible(false);
                            labels.get(i).setVisible(false);
                        }
                        for (int i = 0; i < al.getSongs().size(); i++) {
//                            buttons.get(i).addNotify();
                            labels.get(i).setText(al.getSongs().get(i).getTitle());
                            labels.get(i).setVisible(true);
                            setImage2(buttons.get(i), al.getSongs().get(i).getArtWork());
                            buttons.get(i).setVisible(true);
                            Music s = al.getSongs().get(i);
                            buttons.get(i).addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    try {
                                        setMusic(s);
                                    } catch (JavaLayerException e1) {
                                        e1.printStackTrace();
                                    } catch (IOException e1) {
                                        e1.printStackTrace();
                                    } catch (InvalidDataException e1) {
                                        e1.printStackTrace();
                                    } catch (UnsupportedTagException e1) {
                                        e1.printStackTrace();
                                    } catch (InterruptedException e1) {
                                        e1.printStackTrace();
                                    }
                                }
                            });


                        }


                    }
                });

            }
        }
    }
//GEN-LAST:event_albumButtonActionPerformed

    private void newPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlayListActionPerformed
        NPlayLists n = new NPlayLists(user);
        n.setVisible(true);
    }//GEN-LAST:event_newPlayListActionPerformed

    private void updateButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButonActionPerformed
        if (playList.size()!=0)playList.removeAllElements();
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            playList.addElement(user.getPlayLists().get(i).getTitle());
        }
        playListList.setModel(playList);
    }//GEN-LAST:event_updateButonActionPerformed

    private void addCurrentToButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCurrentToButtonActionPerformed
        String playName = JOptionPane.showInputDialog("write the name of playList you want to add this song to");
        for(int i=0;i<user.getPlayLists().size();i++){
            if(user.getPlayLists().get(i).getTitle().equals(playName)){

                user.getPlayLists().get(i).addSong(music);
                break;
            }
        }
    }//GEN-LAST:event_addCurrentToButtonActionPerformed

    private void searchTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTextActionPerformed

//        String wanted=searchText.getText();









    }//GEN-LAST:event_searchTextActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {//GEN-FIRST:event_sortButtonActionPerformed
        String sort = (String) sortComboBox.getSelectedItem();
        switch(sort){
            case("alphabet"):

                ArrayList<String> names=new ArrayList<>();
                ArrayList<Music> songlist=user.getSongs();
                for (int i = 0; i < songlist.size(); i++) {

                    names.add(songlist.get(i).getTitle());
                }

                Collections.sort(names);
                ArrayList<Music> sortedMusic=new ArrayList<>();
                for (int i = 0; i < names.size(); i++) {
                    for (int j = 0; j < songlist.size(); j++) {
                        if(names.get(i).equals(songlist.get(j).getTitle())) {

                            sortedMusic.add(songlist.get(j));
                            break;

                        }
                    }

                }


                buttonAndLabelp1();
                for (int i = 0; i < buttons.size(); i++) {

//                    buttons.get(i).removeNotify();
                    buttons.get(i).setVisible(false);
                    buttons.get(i).setVisible(false);

                }

                for (int i = 0; i < sortedMusic.size(); i++) {

//                    buttons.get(i).addNotify();
                    labels.get(i).setText(sortedMusic.get(i).getTitle());

                    Image image;
                    Music mm=new Music(sortedMusic.get(i).getMusic());
                    Mp3File mp3=new Mp3File(mm.getMusic().getAbsoluteFile());
                    byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                    try {

                        bb = mp3.
                                getId3v2Tag().
                                getAlbumImage();
                        ImageIcon image1=new ImageIcon(bb);
                        image= Music.getScaledImage(image1.getImage(),100,100);

                    }
                    catch (NullPointerException e){
                        ImageIcon imageIcon=new ImageIcon("baseMusicArtwork.jpeg");
                        image=Music.getScaledImage(imageIcon.getImage(),100,100);
                    }


                    setImage2(buttons.get(i), image);
                    buttons.get(i).setVisible(true);
                    labels.get(i).setVisible(true);



                    Music s=sortedMusic.get(i);

                    buttons.get(i).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                setMusic(s);
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });



                }


                break;
            case("artist"):

                ArrayList<String> artists=new ArrayList<>();

                ArrayList<Music> songs=user.getSongs();

                for (int i = 0; i <songs.size() ; i++) {

                    if (!artists.contains(songs.get(i).getArtist()))
                        artists.add(songs.get(i).getArtist());


                }

                Collections.sort(artists);

                ArrayList<Music> sorted=new ArrayList<>();

                for (int i = 0; i < artists.size(); i++) {

                    for (int j = 0; j <songs.size(); j++) {

                        if (songs.get(j).getArtist().equals(artists.get(i))&&(!sorted.contains(songs.get(j)))) {


                            sorted.add(songs.get(j));




                        }

                    }
                }

                buttonAndLabelp1();
                for (int i = 0; i < buttons.size(); i++) {

//                    buttons.get(i).removeNotify();
                    buttons.get(i).setVisible(false);
                    buttons.get(i).setVisible(false);

                }

                for (int i = 0; i < sorted.size(); i++) {

//                    buttons.get(i).addNotify();
                    labels.get(i).setText(sorted.get(i).getTitle());

                    Image image;
                    Music mm=new Music(sorted.get(i).getMusic());
                    Mp3File mp3=new Mp3File(mm.getMusic().getAbsoluteFile());
                    byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                    try {

                        bb = mp3.
                                getId3v2Tag().
                                getAlbumImage();
                        ImageIcon image1=new ImageIcon(bb);
                        image= Music.getScaledImage(image1.getImage(),100,100);

                    }
                    catch (NullPointerException e){
                        ImageIcon imageIcon=new ImageIcon("baseMusicArtwork.jpeg");
                        image=Music.getScaledImage(imageIcon.getImage(),100,100);
                    }


                    setImage2(buttons.get(i), image);
                    buttons.get(i).setVisible(true);
                    labels.get(i).setVisible(true);



                    Music s=sorted.get(i);

                    buttons.get(i).addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                setMusic(s);
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            }
                        }
                    });



                }


                break;







            case("time"):
                sortSongs();

                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setVisible(false);

//                    buttons.get(i).removeNotify();
                    labels.get(i).setVisible(false);


                }


                Image image;
                for (int i = 0; i < user.getSongs().size(); i++) {
//                    buttons.get(i).addNotify();

                    Music mm=new Music(user.getSongs().get(i).getMusic());
                    Mp3File mp3=new Mp3File(mm.getMusic().getAbsoluteFile());
                    byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                    try {

                        bb = mp3.
                                getId3v2Tag().
                                getAlbumImage();
                        ImageIcon image1=new ImageIcon(bb);
                        image= Music.getScaledImage(image1.getImage(),100,100);

                    }
                    catch (NullPointerException e){
                        ImageIcon imageIcon=new ImageIcon("baseMusicArtwork.jpeg");
                        image=Music.getScaledImage(imageIcon.getImage(),100,100);
                    }








                    setImage2(buttons.get(i), image);
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
                            if (music.isIsplaying()) {
                                try {
                                    music.pause();
                                } catch (JavaLayerException e1) {
                                    e1.printStackTrace();
                                }
                            }
                            try {
                                setMusic(s);
                            } catch (JavaLayerException e1) {
                                e1.printStackTrace();
                            } catch (FileNotFoundException e1) {
                                e1.printStackTrace();
                            } catch (InterruptedException e1) {
                                e1.printStackTrace();
                            } catch (InvalidDataException e1) {
                                e1.printStackTrace();
                            } catch (IOException e1) {
                                e1.printStackTrace();
                            } catch (UnsupportedTagException e1) {
                                e1.printStackTrace();
                            }


                        }}
                    });
                    //   updateUser(user);
                }

                break;

        }



    }//GEN-LAST:event_sortButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {//GEN-FIRST:event_searchButtonActionPerformed


        String wanted=searchText.getText();
        System.out.println("In search"+wanted);
        ArrayList<Music> searched=new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {

            System.out.println(songs.get(i).getArtist());
            if (songs.get(i).getAlbum().contains(wanted))
                if (!searched.contains(songs.get(i))){
                    searched.add(songs.get(i));
                }
            if(songs.get(i).getArtist().contains(wanted))
                if (!searched.contains(songs.get(i))){
                    searched.add(songs.get(i));
                }
            if (songs.get(i).getTitle().contains(wanted)){
                if (!searched.contains(songs.get(i))){
                    searched.add(songs.get(i));
                }
            }

        }
        System.out.println("searched size"+searched.size());


        for (int i = 0; i <buttons.size() ; i++) {
            buttons.get(i).setVisible(false);
            labels.get(i).setVisible(false);
            ActionListener[] acts=buttons.get(i).getActionListeners();
            for (int j = 0; j <acts.length ; j++) {

                buttons.get(i).removeActionListener(acts[j]);

            }
        }


        for (int i = 0; i <searched.size() ; i++) {


            labels.get(i).setText(searched.get(i).getTitle());

            labels.get(i).setVisible(true);
            buttons.get(i).setVisible(true);
            Music mm=new Music(new File(searched.get(i).getPath()));
            Image image;
            Mp3File mp3 = new Mp3File(mm.getMusic().getAbsolutePath());
            byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
            try {
                bb = mp3.
                        getId3v2Tag().
                        getAlbumImage();
                ImageIcon image1 = new ImageIcon(bb);
                image = Music.getScaledImage(image1.getImage(), 200, 200);

            } catch (NullPointerException e) {
                ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                image = Music.getScaledImage(imageIcon.getImage(), 200, 200);
            }


            setImage2(buttons.get(i), image);
            Music s=searched.get(i);

            buttons.get(i).addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        setMusic(s);
                    } catch (JavaLayerException e1) {
                        e1.printStackTrace();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (InvalidDataException e1) {
                        e1.printStackTrace();
                    } catch (UnsupportedTagException e1) {
                        e1.printStackTrace();
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
            });







        }



    }//GEN-LAST:event_searchButtonActionPerformed

    private void editPlayListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayListButtonActionPerformed
        playerSelected = playListList.getSelectedValue();
        changinOrderOfSongsOfPlaylist edit = new changinOrderOfSongsOfPlaylist(user,playerSelected);

        edit.setVisible(true);
    }//GEN-LAST:event_editPlayListButtonActionPerformed


    public static int samount;
    private void musicSliderMouseReleased(java.awt.event.MouseEvent evt) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {//GEN-FIRST:event_musicSliderMouseReleased

        int totaltime=pt.m.getTime();
        pt.suspend();
        JSlider source = (JSlider) evt.getSource();
        System.out.println("Here");

//        System.out.println(music.getInput().available());
        FileInputStream newInput=pt.m.getInput();

        System.out.println(newInput.available());
        System.out.println(source.getValue());
        try{pt.stop();}finally {


            samount=source.getValue();
            System.out.println("here1finally");
            ctime= (int) ((double)  source.getValue() / 1000* totaltime);
            System.out.println("))))))))))))))))))))))))))))"+pt.m.timetoString(ctime));
            changed=true;
            System.out.println(source.getValue());
            newInput.skip((long) ((double)  source.getValue() / 1000* pt.m.getStreamLength()));
            System.out.println((
                    double) pt.m.getStreamLength());
            System.out.println(((double)  source.getValue() / 1000* pt.m.getStreamLength())+"))))))))))))))))))");
            System.out.println(newInput.available());
            Music mm=new Music(pt.m.getMusic());
            mm.setInput(newInput);
            pt = new PlayingThread(mm,newInput);
            System.out.println(pt.m.getInput().available()+"+++++++++++++++++++++++++++++++");
            pt.start();


        }

    }//GEN-LAST:event_musicSliderMouseReleased

    private void addFriendSong3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addFriendSong3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addFriendSong3ActionPerformed

    private void shuffleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleButtonActionPerformed
        
        
        if (this.shuffle==false)
        this.shuffle=true;

        else this.shuffle=false;
        
        
        
        
    }//GEN-LAST:event_shuffleButtonActionPerformed

    private void backwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardButtonActionPerformed
       
        if (!shuffle){
        if (this.mode == 0)
            return;
        else if (mode == 1) {
            ArrayList<Music> songs = currentPlayList.getSongs();
            int a = songs.indexOf(user.getLastMusic());
            user.setLastMusic(songs.get((a - 1)%songs.size()));
            try {
                setMusic(songs.get((a -1)%songs.size()));
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        else {
            ArrayList<Music> songs1 = user.getCurrentAlbum().getSongs();
            int a = songs1.indexOf(user.getLastMusic());
            user.setLastMusic(songs1.get((a - 1)%songs1.size()));
            try {
                setMusic(songs1.get((a -1)%songs1.size()));
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }


        else {

            Random rand=new Random();

            if (this.mode == 0)
                return;
            else if (mode == 1) {
                ArrayList<Music> songs = currentPlayList.getSongs();
                int a = songs.indexOf(user.getLastMusic());
                int b=a+rand.nextInt();
                user.setLastMusic(songs.get((b)%songs.size()));
                try {
                    setMusic(songs.get((b)%songs.size()));
                } catch (JavaLayerException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }


            }
            else {
                ArrayList<Music> songs1 = user.getCurrentAlbum().getSongs();
                int a = songs1.indexOf(user.getLastMusic());
                int b=a+rand.nextInt();
                user.setLastMusic(songs1.get((b)%songs1.size()));
                try {
                    setMusic(songs1.get((b)%songs1.size()));
                } catch (JavaLayerException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomePage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }





        }
    }//GEN-LAST:event_backwardButtonActionPerformed

    private void i11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i11ActionPerformed




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
    private javax.swing.JButton addCurrentToButton;
    private javax.swing.JButton addFriendSong10;
    private javax.swing.JButton addFriendSong11;
    private javax.swing.JButton addFriendSong12;
    private javax.swing.JButton addFriendSong13;
    private javax.swing.JButton addFriendSong14;
    private javax.swing.JButton addToLibraryButton;
    private javax.swing.JButton albumButton;
    private javax.swing.JButton backwardButton;
    private javax.swing.JPanel buttonsPanel;
    private javax.swing.JPanel displayMusicPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton editPlayList;
    private javax.swing.JButton editPlayListButton;
    private java.awt.Button editsPlayList;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel friendActivityLabel;
    private javax.swing.JPanel friendActivityPanel2;
    private javax.swing.JLabel friendsLabel;
    private javax.swing.JLabel friendsLabel2;
    private javax.swing.JLabel friendsLabel3;
    private javax.swing.JLabel friendsLabel4;
    private javax.swing.JLabel friendsLabel5;
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
    public static javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
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
    public static javax.swing.JSlider musicSlider;
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
    private javax.swing.JButton sortButton;
    private javax.swing.JComboBox<String> sortComboBox;
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
    private javax.swing.JButton updateButon;
    private javax.swing.JTextField userNameLabel;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
    FileInputStream fileInputStream;
    BufferedInputStream bufferedInputStream;
    Player player;
    long totalLength;
    Runnable runnableResume=new Runnable() {
        @Override
        public void run() {
            try {
                //code for resume button
                fileInputStream=new FileInputStream(music.getPath());
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                fileInputStream.skip(totalLength-pause);
                player.play();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    Runnable runnablePlay=new Runnable() {
        @Override
        public void run() {
            try {
                //code for play button
                fileInputStream=new FileInputStream(music.getPath());
                bufferedInputStream=new BufferedInputStream(fileInputStream);
                player=new Player(bufferedInputStream);
                totalLength=fileInputStream.available();
                player.play();//starting music
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (JavaLayerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };


    ActionListener acsong=new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            mode=0;
            try {
                setMusic(music);
            } catch (JavaLayerException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (InvalidDataException e1) {
                e1.printStackTrace();
            } catch (UnsupportedTagException e1) {
                e1.printStackTrace();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }


        }
    };
    public void bbb(){}
//       updateUser(user);
}
//


