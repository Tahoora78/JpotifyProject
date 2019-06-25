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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Home pc
 */
public class HomePage extends javax.swing.JDialog {

    private Thread thread;
    private boolean liked = false;
    long pause;
    PlayingThread pt;
    public static boolean flag=false;
    public static boolean flag2=false;
    public static String needed;
    public static String fileName;
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
            }

        }
        JLabel j1 = new JLabel();
        j1.setVisible(true);
//        j1.setIcon(new ImageIcon(music.getArtWork()));
//        setImage2(i1, (music.getArtWork()));

        user.setSongNum(user.getSongs().size());
        playList = new DefaultListModel();
        //playList.addElement("favorite list");
        //playList.addElement("shared playlist"000);
        playList.removeAllElements();
        System.out.println("sizePlayList" + user.getPlayLists().size());
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            System.out.println(user.getPlayLists().get(i).getTitle());
        }
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            playList.addElement(user.getPlayLists().get(i).getTitle());
        }
        buttonAndLabel();
        playListList.setModel(playList);
        playListList.getSelectionModel().addListSelectionListener(e -> {
                    //write action listener here;
                    String pname = playListList.getSelectedValue();
                    playerSelected = pname;
                    PlayList pl = null;
                    for (int i = 0; i < user.getPlayLists().size(); i++) {
                        if (user.getPlayLists().get(i).getTitle().equals(pname)) {
                            pl = user.getPlayLists().get(i);
                            System.out.println("((((((((((((((((((((((((((((((("+user.getPlayLists().get(i).getTitle());
                            break;
                        }
                    }

//                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&"+pl.getTitle());

                    for (int i = 0; i < buttons.size(); i++) {
                        buttons.get(i).removeNotify();
                        buttons.get(i).setVisible(false);
                        labels.get(i).setVisible(false);
                    }
                    Image image;
                    for (int i = 0; i < pl.getSongs().size(); i++) {
                        buttons.get(i).addNotify();
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

    public void labelAndTextAndButton(){
        playerLabels.add(namePlayer);
        playerLabels.add(namePlayer1);
        playerLabels.add(namePlayer2);
        playerLabels.add(namePlayer3);
        playerLabels.add(namePlayer4);
        playingTexts.add(playingText);
        playingTexts.add(playingText1);
        playingTexts.add(playingText2);
        playingTexts.add(playingText3);
        playingTexts.add(playingText4);
        timeTexts.add(timeText);
        timeTexts.add(timeText1);
        timeTexts.add(timeText2);
        timeTexts.add(timeText3);
        timeTexts.add(timeText4);
        AddSongButton.add(addFriendSong);
        AddSongButton.add(addFriendSong1);
        AddSongButton.add(addFriendSong2);
        AddSongButton.add(addFriendSong3);
        AddSongButton.add(addFriendSong4);
    }

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
        displayPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        i25 = new javax.swing.JButton();
        i26 = new javax.swing.JButton();
        i27 = new javax.swing.JButton();
        i28 = new javax.swing.JButton();
        i29 = new javax.swing.JButton();
        i30 = new javax.swing.JButton();
        i31 = new javax.swing.JButton();
        i32 = new javax.swing.JButton();
        i33 = new javax.swing.JButton();
        i34 = new javax.swing.JButton();
        i35 = new javax.swing.JButton();
        i36 = new javax.swing.JButton();
        i37 = new javax.swing.JButton();
        i38 = new javax.swing.JButton();
        i39 = new javax.swing.JButton();
        i40 = new javax.swing.JButton();
        i41 = new javax.swing.JButton();
        i42 = new javax.swing.JButton();
        i43 = new javax.swing.JButton();
        i44 = new javax.swing.JButton();
        t25 = new javax.swing.JLabel();
        t26 = new javax.swing.JLabel();
        t27 = new javax.swing.JLabel();
        t28 = new javax.swing.JLabel();
        t29 = new javax.swing.JLabel();
        l7 = new javax.swing.JLabel();
        t30 = new javax.swing.JLabel();
        t31 = new javax.swing.JLabel();
        t32 = new javax.swing.JLabel();
        t33 = new javax.swing.JLabel();
        t34 = new javax.swing.JLabel();
        t35 = new javax.swing.JLabel();
        t36 = new javax.swing.JLabel();
        t37 = new javax.swing.JLabel();
        l16 = new javax.swing.JLabel();
        t38 = new javax.swing.JLabel();
        t39 = new javax.swing.JLabel();
        i45 = new javax.swing.JButton();
        i46 = new javax.swing.JButton();
        i47 = new javax.swing.JButton();
        i48 = new javax.swing.JButton();
        t40 = new javax.swing.JLabel();
        t41 = new javax.swing.JLabel();
        t42 = new javax.swing.JLabel();
        t43 = new javax.swing.JLabel();
        t44 = new javax.swing.JLabel();
        t45 = new javax.swing.JLabel();
        t46 = new javax.swing.JLabel();
        t47 = new javax.swing.JLabel();
        t48 = new javax.swing.JLabel();
        displayPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        i49 = new javax.swing.JButton();
        i50 = new javax.swing.JButton();
        i51 = new javax.swing.JButton();
        i52 = new javax.swing.JButton();
        i53 = new javax.swing.JButton();
        i54 = new javax.swing.JButton();
        i55 = new javax.swing.JButton();
        i56 = new javax.swing.JButton();
        i57 = new javax.swing.JButton();
        i58 = new javax.swing.JButton();
        i59 = new javax.swing.JButton();
        i60 = new javax.swing.JButton();
        i61 = new javax.swing.JButton();
        i62 = new javax.swing.JButton();
        i63 = new javax.swing.JButton();
        i64 = new javax.swing.JButton();
        i65 = new javax.swing.JButton();
        i66 = new javax.swing.JButton();
        i67 = new javax.swing.JButton();
        i68 = new javax.swing.JButton();
        t49 = new javax.swing.JLabel();
        t50 = new javax.swing.JLabel();
        t51 = new javax.swing.JLabel();
        t52 = new javax.swing.JLabel();
        t53 = new javax.swing.JLabel();
        l8 = new javax.swing.JLabel();
        t54 = new javax.swing.JLabel();
        t55 = new javax.swing.JLabel();
        t56 = new javax.swing.JLabel();
        t57 = new javax.swing.JLabel();
        t58 = new javax.swing.JLabel();
        t59 = new javax.swing.JLabel();
        t60 = new javax.swing.JLabel();
        t61 = new javax.swing.JLabel();
        l17 = new javax.swing.JLabel();
        t62 = new javax.swing.JLabel();
        t63 = new javax.swing.JLabel();
        i69 = new javax.swing.JButton();
        i70 = new javax.swing.JButton();
        i71 = new javax.swing.JButton();
        i72 = new javax.swing.JButton();
        t64 = new javax.swing.JLabel();
        t65 = new javax.swing.JLabel();
        t66 = new javax.swing.JLabel();
        t67 = new javax.swing.JLabel();
        t68 = new javax.swing.JLabel();
        t69 = new javax.swing.JLabel();
        t70 = new javax.swing.JLabel();
        t71 = new javax.swing.JLabel();
        t72 = new javax.swing.JLabel();
        displayPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        i73 = new javax.swing.JButton();
        i74 = new javax.swing.JButton();
        i75 = new javax.swing.JButton();
        i76 = new javax.swing.JButton();
        i77 = new javax.swing.JButton();
        i78 = new javax.swing.JButton();
        i79 = new javax.swing.JButton();
        i80 = new javax.swing.JButton();
        i81 = new javax.swing.JButton();
        i82 = new javax.swing.JButton();
        i83 = new javax.swing.JButton();
        i84 = new javax.swing.JButton();
        i85 = new javax.swing.JButton();
        i86 = new javax.swing.JButton();
        i87 = new javax.swing.JButton();
        i88 = new javax.swing.JButton();
        i89 = new javax.swing.JButton();
        i90 = new javax.swing.JButton();
        i91 = new javax.swing.JButton();
        i92 = new javax.swing.JButton();
        t73 = new javax.swing.JLabel();
        t74 = new javax.swing.JLabel();
        t75 = new javax.swing.JLabel();
        t76 = new javax.swing.JLabel();
        t77 = new javax.swing.JLabel();
        l9 = new javax.swing.JLabel();
        t78 = new javax.swing.JLabel();
        t79 = new javax.swing.JLabel();
        t80 = new javax.swing.JLabel();
        t81 = new javax.swing.JLabel();
        t82 = new javax.swing.JLabel();
        t83 = new javax.swing.JLabel();
        t84 = new javax.swing.JLabel();
        t85 = new javax.swing.JLabel();
        l18 = new javax.swing.JLabel();
        t86 = new javax.swing.JLabel();
        t87 = new javax.swing.JLabel();
        i93 = new javax.swing.JButton();
        i94 = new javax.swing.JButton();
        i95 = new javax.swing.JButton();
        i96 = new javax.swing.JButton();
        t88 = new javax.swing.JLabel();
        t89 = new javax.swing.JLabel();
        t90 = new javax.swing.JLabel();
        t91 = new javax.swing.JLabel();
        t92 = new javax.swing.JLabel();
        t93 = new javax.swing.JLabel();
        t94 = new javax.swing.JLabel();
        t95 = new javax.swing.JLabel();
        t96 = new javax.swing.JLabel();
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

        editsPlayList.setLabel("delete");
        editsPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editsPlayListActionPerformed(evt);
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
                musicSliderAncestorMoved(evt);
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
                playButtonActionPerformed(evt);
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
                searchButtonActionPerformed(evt);
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

        jLabel1.setText("jLabel1");

        i1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i1ActionPerformed(evt);
            }
        });

        jLabel4.setText("jLabel1");

        i48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i48ActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel1");

        i72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i72ActionPerformed(evt);
            }
        });

        jLabel7.setText("jLabel1");

        i96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                i96ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayPanel3Layout = new javax.swing.GroupLayout(displayPanel3);
        displayPanel3.setLayout(displayPanel3Layout);
        displayPanel3Layout.setHorizontalGroup(
            displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i96, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i94, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t90, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t87, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t86, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i93, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t95, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i95, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel3Layout.createSequentialGroup()
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(i84, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(i79, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(t88, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(t94, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel3Layout.createSequentialGroup()
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t89, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i85, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)))
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t83, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t82, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i86, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i78, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t96, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i80, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i74, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t85, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t84, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i77, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i87, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i73, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t81, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t74, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t77, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i90, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i92, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i81, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i76, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t79, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t93, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t76, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t80, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i89, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i88, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i82, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t73, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addComponent(t75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t92, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t78, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i83, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58))
            .addGroup(displayPanel3Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displayPanel3Layout.setVerticalGroup(
            displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i74, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i88, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i92, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t79, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t81, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t85, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t92, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i80, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i96, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t90, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t91, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(displayPanel3Layout.createSequentialGroup()
                                .addComponent(l18, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addComponent(i95, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t87, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addComponent(i84, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t93, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t88, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t78, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(displayPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i77, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i78, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i90, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i83, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(t82, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i79, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i86, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i94, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i87, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(t74, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i89, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t76, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t86, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t94, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t83, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t77, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanel3Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l9, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addComponent(i93, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t95, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(displayPanel3Layout.createSequentialGroup()
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(i91, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(i85, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i73, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i76, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(t84, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(91, Short.MAX_VALUE))
                                    .addGroup(displayPanel3Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t80, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(displayPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(t89, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t96, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(t75, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(displayPanel3Layout.createSequentialGroup()
                        .addComponent(t73, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );

        javax.swing.GroupLayout displayPanel2Layout = new javax.swing.GroupLayout(displayPanel2);
        displayPanel2.setLayout(displayPanel2Layout);
        displayPanel2Layout.setHorizontalGroup(
            displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i70, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i69, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel2Layout.createSequentialGroup()
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(i60, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(i55, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(t64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(t70, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel2Layout.createSequentialGroup()
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i61, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)))
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t59, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t58, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i56, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t61, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t60, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i68, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i52, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t55, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t69, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t52, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t56, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i58, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t49, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addComponent(t51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t68, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i59, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58))
            .addGroup(displayPanel2Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        displayPanel2Layout.setVerticalGroup(
            displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i50, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i68, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i51, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t55, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t57, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t61, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t68, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i56, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t66, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t67, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(displayPanel2Layout.createSequentialGroup()
                                .addComponent(l17, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addComponent(i71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t63, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addComponent(i60, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t69, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t64, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t54, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(displayPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i59, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(t58, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i55, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i62, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i70, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i63, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(t50, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t52, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t62, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t70, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t59, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t53, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanel2Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l8, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addComponent(i69, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t71, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(displayPanel2Layout.createSequentialGroup()
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(i67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(i61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i49, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i58, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(t60, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(91, Short.MAX_VALUE))
                                    .addGroup(displayPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t56, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(t65, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t72, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(t51, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(displayPanel2Layout.createSequentialGroup()
                        .addComponent(t49, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(displayPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout displayPanel1Layout = new javax.swing.GroupLayout(displayPanel1);
        displayPanel1.setLayout(displayPanel1Layout);
        displayPanel1Layout.setHorizontalGroup(
            displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel1Layout.createSequentialGroup()
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(i36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(i31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(t40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(t46, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanel1Layout.createSequentialGroup()
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(t41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(63, 63, 63)))
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i38, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(56, 56, 56))
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(t43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(64, 64, 64)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t37, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i39, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i33, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i41, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i34, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addComponent(t27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(t44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(58, 58, 58))
            .addGroup(displayPanel1Layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        displayPanel1Layout.setVerticalGroup(
            displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i40, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i44, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t31, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t33, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t37, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t44, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i48, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t42, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t43, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(displayPanel1Layout.createSequentialGroup()
                                .addComponent(l16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addComponent(i47, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t39, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addComponent(i36, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t40, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(displayPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(i29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i42, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i35, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(t34, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)))
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(i31, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i38, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i39, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGap(176, 176, 176)
                        .addComponent(t26, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(i33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(i41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t28, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t38, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t35, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t29, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(displayPanel1Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(l7, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addComponent(i45, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(t47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
                            .addGroup(displayPanel1Layout.createSequentialGroup()
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addGap(34, 34, 34)
                                        .addComponent(i43, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(i37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(i34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addGap(13, 13, 13)
                                        .addComponent(t36, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(91, Short.MAX_VALUE))
                                    .addGroup(displayPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(t32, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(t41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(t48, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(t27, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))))
                    .addGroup(displayPanel1Layout.createSequentialGroup()
                        .addComponent(t25, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(displayPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

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
            .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
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
            .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(displayPanelLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(displayPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jScrollPane3.setViewportView(displayPanel);

        addToLibraryButton.setText("add to library");
        addToLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToLibraryButtonActionPerformed(evt);
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
                sortButtonActionPerformed(evt);
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
                                .addGap(30, 30, 30)
                                .addComponent(displayMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sortButton, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(53, 53, 53)
                                .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(soundBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(41, Short.MAX_VALUE))
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
                                .addComponent(searchButton)
                                .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(sortButton)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
     * @param songNamePlaying
     * @param time
     * @param time
     */
    public void updateFriendActivity(String songNamePlaying,String time,String friendName){
        namePlayer.setText(friendName);
        playingText.setText(songNamePlaying);
        timeText.setText(time);
    }


    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, IOException, InterruptedException, InvalidDataException, UnsupportedTagException {//GEN-FIRST:event_forwardButtonActionPerformed
        if (this.mode == 0)
            return;
        else if (mode == 1) {
            ArrayList<Music> songs = user.getCurrenPlayList().getSongs();
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

    public void buttonAndLabel() {
        buttons.add(i1);
        buttons.add(i2);
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
        labels.add(t17);labels.add(t18);
        labels.add(t19);
        labels.add(t20);
        labels.add(t21);
        labels.add(t22);
        labels.add(t23);
        labels.add(t24);

    }

    private void songsButtonActionPerformed(java.awt.event.ActionEvent evt) throws InvalidDataException, IOException, UnsupportedTagException, JavaLayerException {//GEN-FIRST:event_songsButtonActionPerformed
        buttonAndLabel();
        sortSongs();
        itsAlbum=false;
        System.out.println("size" + user.getSongs().size());

        for (int i = 0; i < buttons.size()&&i<labels.size(); i++) {
            buttons.get(i).setVisible(false);

            buttons.get(i).removeNotify();
            labels.get(i).setVisible(false);


        }


        Image image;
        for (int i = 0; i < user.getSongs().size(); i++) {
            buttons.get(i).addNotify();

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
            buttons.get(i).addActionListener(acsong);

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
        //try {
        //   Files.write(Paths.get(path), filename.getBytes(), StandardOpenOption.APPEND);
        //}catch (IOException e2) {
        //}

        // try {
        //   Files.write(Paths.get("UsersFolder/filename.txt"), "\r\n".getBytes(), StandardOpenOption.APPEND);
        // }catch (IOException e2) {
        // }

        ArrayList<Music> son=user.getSongs();
        if(!son.contains(new Music(f)))
            son.add(new Music(f));

        ArrayList<String> alnames=new ArrayList<>();
        for(int i=0;i<son.size();i++){
            if (!alnames.contains(son.get(i).getAlbum()))
                alnames.add(son.get(i).getAlbum());
        }

        System.out.println("8888888888888888888888888888888888888"+alnames.size());


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
//        this.jl1.setText("Title :"+music.getTitle());
//        this.jl2.setText("Artist :"+music.getArtist());
//        this.jl3.setText("Album :"+music.getAlbum());
//        this.jLabel3.setText(music.timetoString(music.getTime()));
//        this.jLabel5.setIcon(new ImageIcon(music.getArtWork()));
//        setImage(favoriteButton,"empty.png");
//        user.setLastMusic(music);
//        user.setLtime(System.currentTimeMillis());
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


    private void editsPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editsPlayListActionPerformed


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
    }//GEN-LAST:event_editsPlayListActionPerformed

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

//        System.out.println(user.getAlbums().size()+"PPPPPPPPPPPPPPPPPPPPPPPPPP");

        for (int i = 0; i <user.getAlbums().size() ; i++) {

            System.out.println(user.getAlbums().get(i).getTitle()+"PPPPPPPPPPPPPPPPPPPPPPPPPP");



        }




        System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO"+user.getAlbums().size());
        itsAlbum=true;
        for (int i = 0; i < buttons.size()&&i<labels.size(); i++) {
            buttons.get(i).setVisible(false);
            labels.get(i).setVisible(false);
            buttons.get(i).removeNotify();
            buttons.get(i).removeActionListener(acsong);
        }

        for (int i = 0; i < user.getAlbums().size(); i++) {
            buttons.get(i).addNotify();
            labels.get(i).setText(user.getAlbums().get(i).getTitle());
            labels.get(i).setVisible(true);

            Image image;
            if (user.getAlbums().get(i)!=null) {
                Music mm = user.getAlbums()
                        .get(i).
                                getSongs().
                                get(0);
                Mp3File mp3 = new Mp3File(mm.getMusic().getAbsoluteFile());
                byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                try {

                    bb = mp3.
                            getId3v2Tag().
                            getAlbumImage();
                    ImageIcon image1 = new ImageIcon(bb);
                    image = Music.getScaledImage(image1.getImage(), 100, 100);

                } catch (NullPointerException e) {
                    ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                    image = Music.getScaledImage(imageIcon.getImage(), 100, 100);
                }

                setImage2(buttons.get(i), image);
                buttons.get(i).setVisible(true);
                Album al = user.getAlbums().get(i);
                JLabel jl = labels.get(i);
//            buttons.get(i).addActionListener(e -> jl.setText("LLLLLL"));
                buttons.get(i).addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mode = 2;
                        user.setCurrentAlbum(al);
                        for (int i = 0; i < buttons.size() && i < labels.size(); i++) {

                            buttons.get(i).setVisible(false);
                            labels.get(i).setVisible(false);
                            buttons.get(i).removeNotify();
//                        e.consume();
                        }
                        for (int i = 0; i < al.getSongs().size(); i++) {
                            buttons.get(i).addNotify();
                            labels.get(i).setText(al.getSongs().get(i).getTitle());
                            labels.get(i).setVisible(true);
                            buttons.get(i).setIcon(new ImageIcon(al.getSongs().get(i).getArtWork()));
                            buttons.get(i).setVisible(true);
                            Music s = al.getSongs().get(i);
                            buttons.get(i).addActionListener(e1 -> {
                                try {


                                    setMusic(s);
//                                music.play();
                                } catch (JavaLayerException e2) {
                                    e2.printStackTrace();
                                } catch (FileNotFoundException e2) {
                                    e2.printStackTrace();
                                } catch (InterruptedException e2) {
                                    e2.printStackTrace();
                                } catch (InvalidDataException e2) {
                                    e2.printStackTrace();
                                } catch (IOException e2) {
                                    e2.printStackTrace();
                                } catch (UnsupportedTagException e2) {
                                    e2.printStackTrace();
                                }
                            });


                        }

                    }
                });
            }}//GEN-LAST:event_albumButtonActionPerformed
    }
    private void newPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlayListActionPerformed
        NPlayLists n = new NPlayLists(user);
        n.setVisible(true);
    }//GEN-LAST:event_newPlayListActionPerformed

    private void updateButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButonActionPerformed
        playList.removeAllElements();
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


                buttonAndLabel();
                for (int i = 0; i < buttons.size(); i++) {

                    buttons.get(i).removeNotify();
                    buttons.get(i).setVisible(false);
                    buttons.get(i).setVisible(false);

                }

                for (int i = 0; i < sortedMusic.size(); i++) {

                    buttons.get(i).addNotify();
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

                buttonAndLabel();
                for (int i = 0; i < buttons.size(); i++) {

                    buttons.get(i).removeNotify();
                    buttons.get(i).setVisible(false);
                    buttons.get(i).setVisible(false);

                }

                for (int i = 0; i < sorted.size(); i++) {

                    buttons.get(i).addNotify();
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

                    buttons.get(i).removeNotify();
                    labels.get(i).setVisible(false);


                }


                Image image;
                for (int i = 0; i < user.getSongs().size(); i++) {
                    buttons.get(i).addNotify();

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

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed


        String wanted=searchText.getText();
        ArrayList<Music> searched=new ArrayList<>();


    }//GEN-LAST:event_searchButtonActionPerformed

    private void editPlayListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editPlayListButtonActionPerformed
        playerSelected = playListList.getSelectedValue();
        changinOrderOfSongsOfPlaylist edit = new changinOrderOfSongsOfPlaylist(user,playerSelected);

        edit.setVisible(true);
    }//GEN-LAST:event_editPlayListButtonActionPerformed

    private void musicSliderMouseReleased(java.awt.event.MouseEvent evt) throws IOException, UnsupportedTagException, InvalidDataException, JavaLayerException {//GEN-FIRST:event_musicSliderMouseReleased
        pt.suspend();
        JSlider source = (JSlider) evt.getSource();
        System.out.println("Here");

//        System.out.println(music.getInput().available());
        FileInputStream newInput=pt.m.getInput();

        System.out.println(newInput.available());
        System.out.println(source.getValue());
        try{pt.stop();}finally {


            System.out.println("here1finally");
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

    private void i48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i48ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i48ActionPerformed

    private void i72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i72ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i72ActionPerformed

    private void i96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_i96ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_i96ActionPerformed




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
    private javax.swing.JPanel displayPanel1;
    private javax.swing.JPanel displayPanel2;
    private javax.swing.JPanel displayPanel3;
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
    private javax.swing.JButton i25;
    private javax.swing.JButton i26;
    private javax.swing.JButton i27;
    private javax.swing.JButton i28;
    private javax.swing.JButton i29;
    private javax.swing.JButton i3;
    private javax.swing.JButton i30;
    private javax.swing.JButton i31;
    private javax.swing.JButton i32;
    private javax.swing.JButton i33;
    private javax.swing.JButton i34;
    private javax.swing.JButton i35;
    private javax.swing.JButton i36;
    private javax.swing.JButton i37;
    private javax.swing.JButton i38;
    private javax.swing.JButton i39;
    private javax.swing.JButton i4;
    private javax.swing.JButton i40;
    private javax.swing.JButton i41;
    private javax.swing.JButton i42;
    private javax.swing.JButton i43;
    private javax.swing.JButton i44;
    private javax.swing.JButton i45;
    private javax.swing.JButton i46;
    private javax.swing.JButton i47;
    private javax.swing.JButton i48;
    private javax.swing.JButton i49;
    private javax.swing.JButton i5;
    private javax.swing.JButton i50;
    private javax.swing.JButton i51;
    private javax.swing.JButton i52;
    private javax.swing.JButton i53;
    private javax.swing.JButton i54;
    private javax.swing.JButton i55;
    private javax.swing.JButton i56;
    private javax.swing.JButton i57;
    private javax.swing.JButton i58;
    private javax.swing.JButton i59;
    private javax.swing.JButton i6;
    private javax.swing.JButton i60;
    private javax.swing.JButton i61;
    private javax.swing.JButton i62;
    private javax.swing.JButton i63;
    private javax.swing.JButton i64;
    private javax.swing.JButton i65;
    private javax.swing.JButton i66;
    private javax.swing.JButton i67;
    private javax.swing.JButton i68;
    private javax.swing.JButton i69;
    private javax.swing.JButton i7;
    private javax.swing.JButton i70;
    private javax.swing.JButton i71;
    private javax.swing.JButton i72;
    private javax.swing.JButton i73;
    private javax.swing.JButton i74;
    private javax.swing.JButton i75;
    private javax.swing.JButton i76;
    private javax.swing.JButton i77;
    private javax.swing.JButton i78;
    private javax.swing.JButton i79;
    private javax.swing.JButton i8;
    private javax.swing.JButton i80;
    private javax.swing.JButton i81;
    private javax.swing.JButton i82;
    private javax.swing.JButton i83;
    private javax.swing.JButton i84;
    private javax.swing.JButton i85;
    private javax.swing.JButton i86;
    private javax.swing.JButton i87;
    private javax.swing.JButton i88;
    private javax.swing.JButton i89;
    private javax.swing.JButton i9;
    private javax.swing.JButton i90;
    private javax.swing.JButton i91;
    private javax.swing.JButton i92;
    private javax.swing.JButton i93;
    private javax.swing.JButton i94;
    private javax.swing.JButton i95;
    private javax.swing.JButton i96;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
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
    private javax.swing.JLabel l16;
    private javax.swing.JLabel l17;
    private javax.swing.JLabel l18;
    private javax.swing.JLabel l6;
    private javax.swing.JLabel l7;
    private javax.swing.JLabel l8;
    private javax.swing.JLabel l9;
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
    private javax.swing.JLabel t25;
    private javax.swing.JLabel t26;
    private javax.swing.JLabel t27;
    private javax.swing.JLabel t28;
    private javax.swing.JLabel t29;
    private javax.swing.JLabel t3;
    private javax.swing.JLabel t30;
    private javax.swing.JLabel t31;
    private javax.swing.JLabel t32;
    private javax.swing.JLabel t33;
    private javax.swing.JLabel t34;
    private javax.swing.JLabel t35;
    private javax.swing.JLabel t36;
    private javax.swing.JLabel t37;
    private javax.swing.JLabel t38;
    private javax.swing.JLabel t39;
    private javax.swing.JLabel t4;
    private javax.swing.JLabel t40;
    private javax.swing.JLabel t41;
    private javax.swing.JLabel t42;
    private javax.swing.JLabel t43;
    private javax.swing.JLabel t44;
    private javax.swing.JLabel t45;
    private javax.swing.JLabel t46;
    private javax.swing.JLabel t47;
    private javax.swing.JLabel t48;
    private javax.swing.JLabel t49;
    private javax.swing.JLabel t5;
    private javax.swing.JLabel t50;
    private javax.swing.JLabel t51;
    private javax.swing.JLabel t52;
    private javax.swing.JLabel t53;
    private javax.swing.JLabel t54;
    private javax.swing.JLabel t55;
    private javax.swing.JLabel t56;
    private javax.swing.JLabel t57;
    private javax.swing.JLabel t58;
    private javax.swing.JLabel t59;
    private javax.swing.JLabel t6;
    private javax.swing.JLabel t60;
    private javax.swing.JLabel t61;
    private javax.swing.JLabel t62;
    private javax.swing.JLabel t63;
    private javax.swing.JLabel t64;
    private javax.swing.JLabel t65;
    private javax.swing.JLabel t66;
    private javax.swing.JLabel t67;
    private javax.swing.JLabel t68;
    private javax.swing.JLabel t69;
    private javax.swing.JLabel t7;
    private javax.swing.JLabel t70;
    private javax.swing.JLabel t71;
    private javax.swing.JLabel t72;
    private javax.swing.JLabel t73;
    private javax.swing.JLabel t74;
    private javax.swing.JLabel t75;
    private javax.swing.JLabel t76;
    private javax.swing.JLabel t77;
    private javax.swing.JLabel t78;
    private javax.swing.JLabel t79;
    private javax.swing.JLabel t8;
    private javax.swing.JLabel t80;
    private javax.swing.JLabel t81;
    private javax.swing.JLabel t82;
    private javax.swing.JLabel t83;
    private javax.swing.JLabel t84;
    private javax.swing.JLabel t85;
    private javax.swing.JLabel t86;
    private javax.swing.JLabel t87;
    private javax.swing.JLabel t88;
    private javax.swing.JLabel t89;
    private javax.swing.JLabel t9;
    private javax.swing.JLabel t90;
    private javax.swing.JLabel t91;
    private javax.swing.JLabel t92;
    private javax.swing.JLabel t93;
    private javax.swing.JLabel t94;
    private javax.swing.JLabel t95;
    private javax.swing.JLabel t96;
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


