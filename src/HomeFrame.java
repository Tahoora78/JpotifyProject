
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javazoom.jl.decoder.JavaLayerException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home pc
 */
public class HomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form HomeFrame
     */
    
    
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
 ArrayList<JButton> buttons2=new ArrayList<>();
    ArrayList<JLabel> labels2=new ArrayList<>();
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

   
    
    public HomeFrame(String name) throws IOException, InvalidDataException, InvalidDataException, UnsupportedTagException, UnsupportedTagException, UnsupportedTagException, JavaLayerException, InterruptedException {
        initComponents();
        this.displayPanel.setMinimumSize(new Dimension(600,600));
       // this.setResizable(false);
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
        //songsPanel.setVisible(true);
        //songsPanel.add(jb);
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

//                    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&"+pl.getTitle());

                    for (int i = 0; i < buttons.size(); i++) {
//                        buttons.get(i).removeNotify();
                        buttons.get(i).setVisible(false);
                        labels.get(i).setVisible(false);
                    }
                    Image image;
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

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        playListList = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        AlbumButton = new javax.swing.JButton();
        SongsButton = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        editPlayListButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        deletePlayListButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        friendLabel = new javax.swing.JLabel();
        addFriendSong1 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        friendLabel3 = new javax.swing.JLabel();
        addFriendSong3 = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        friendLabel2 = new javax.swing.JLabel();
        addFriendSong2 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        displayPanel = new javax.swing.JPanel();
        i1 = new javax.swing.JButton();
        i2 = new javax.swing.JButton();
        i4 = new javax.swing.JButton();
        i5 = new javax.swing.JButton();
        i3 = new javax.swing.JButton();
        i6 = new javax.swing.JButton();
        t7 = new javax.swing.JLabel();
        t6 = new javax.swing.JLabel();
        t5 = new javax.swing.JLabel();
        t1 = new javax.swing.JLabel();
        t4 = new javax.swing.JLabel();
        i7 = new javax.swing.JButton();
        t2 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        i9 = new javax.swing.JButton();
        i10 = new javax.swing.JButton();
        i12 = new javax.swing.JButton();
        i13 = new javax.swing.JButton();
        i11 = new javax.swing.JButton();
        i14 = new javax.swing.JButton();
        t16 = new javax.swing.JLabel();
        t15 = new javax.swing.JLabel();
        t14 = new javax.swing.JLabel();
        t13 = new javax.swing.JLabel();
        t9 = new javax.swing.JLabel();
        t12 = new javax.swing.JLabel();
        i16 = new javax.swing.JButton();
        i15 = new javax.swing.JButton();
        t11 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        i17 = new javax.swing.JButton();
        i18 = new javax.swing.JButton();
        i20 = new javax.swing.JButton();
        i21 = new javax.swing.JButton();
        i19 = new javax.swing.JButton();
        i22 = new javax.swing.JButton();
        t24 = new javax.swing.JLabel();
        t23 = new javax.swing.JLabel();
        t22 = new javax.swing.JLabel();
        t21 = new javax.swing.JLabel();
        t17 = new javax.swing.JLabel();
        t20 = new javax.swing.JLabel();
        i24 = new javax.swing.JButton();
        i23 = new javax.swing.JButton();
        t19 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        i25 = new javax.swing.JButton();
        i26 = new javax.swing.JButton();
        i28 = new javax.swing.JButton();
        i29 = new javax.swing.JButton();
        i27 = new javax.swing.JButton();
        i30 = new javax.swing.JButton();
        t32 = new javax.swing.JLabel();
        t31 = new javax.swing.JLabel();
        t30 = new javax.swing.JLabel();
        t29 = new javax.swing.JLabel();
        t25 = new javax.swing.JLabel();
        t28 = new javax.swing.JLabel();
        i32 = new javax.swing.JButton();
        i31 = new javax.swing.JButton();
        t27 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jButton62 = new javax.swing.JButton();
        jButton63 = new javax.swing.JButton();
        jButton64 = new javax.swing.JButton();
        jButton65 = new javax.swing.JButton();
        jButton66 = new javax.swing.JButton();
        jButton67 = new javax.swing.JButton();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jButton68 = new javax.swing.JButton();
        jButton69 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jButton70 = new javax.swing.JButton();
        jButton71 = new javax.swing.JButton();
        jButton72 = new javax.swing.JButton();
        jButton73 = new javax.swing.JButton();
        jButton74 = new javax.swing.JButton();
        jButton75 = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jButton76 = new javax.swing.JButton();
        jButton77 = new javax.swing.JButton();
        jLabel65 = new javax.swing.JLabel();
        t26 = new javax.swing.JLabel();
        t18 = new javax.swing.JLabel();
        t10 = new javax.swing.JLabel();
        t3 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        newPlayList = new javax.swing.JButton();
        displayMusicPanel = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        musicSlider = new javax.swing.JSlider();
        jPanel12 = new javax.swing.JPanel();
        addCurrentToButton = new javax.swing.JButton();
        favoriteButton = new javax.swing.JButton();
        shuffleButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        playButton = new javax.swing.JButton();
        backwardButton = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        musicLabel = new javax.swing.JLabel();
        volumeSlider = new javax.swing.JSlider();
        timeLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jl1 = new javax.swing.JLabel();
        jl2 = new javax.swing.JLabel();
        jl3 = new javax.swing.JLabel();
        jl5 = new javax.swing.JLabel();
        passTimeLabel = new javax.swing.JLabel();
        userNameLabel = new javax.swing.JLabel();
        searchButton = new javax.swing.JButton();
        searchText = new javax.swing.JTextField();
        sortButton = new javax.swing.JButton();
        sortComboBox = new javax.swing.JComboBox<>();
        addToLibraryButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setPreferredSize(new java.awt.Dimension(1700, 1100));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("library");

        jScrollPane1.setViewportView(playListList);

        AlbumButton.setText("album");
        AlbumButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AlbumButtonActionPerformed(evt);
            }
        });

        SongsButton.setText("songs");
        SongsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SongsButtonActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("playList");

        editPlayListButton.setText("edit");
        editPlayListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editPlayListButtonActionPerformed(evt);
            }
        });

        updateButton.setText("update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        deletePlayListButton.setText("delete");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editPlayListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(updateButton)
                .addGap(31, 31, 31)
                .addComponent(deletePlayListButton)
                .addGap(20, 20, 20))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editPlayListButton)
                .addComponent(updateButton)
                .addComponent(deletePlayListButton))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AlbumButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SongsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(AlbumButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(SongsButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel2);

        jLabel2.setFont(new java.awt.Font("Tempus Sans ITC", 2, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("friend activity");

        friendLabel.setText("jLabel4");

        addFriendSong1.setText("jButton6");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addFriendSong1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addFriendSong1))
                    .addComponent(friendLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        friendLabel3.setText("jLabel4");

        addFriendSong3.setText("jButton6");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addFriendSong3, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addFriendSong3))
                    .addComponent(friendLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        friendLabel2.setText("jLabel4");

        addFriendSong2.setText("jButton6");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(friendLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addFriendSong2, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(addFriendSong2))
                    .addComponent(friendLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(716, 716, 716))
            .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel8Layout.createSequentialGroup()
                    .addGap(199, 199, 199)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel3);

        i1.setText("jButton1");

        i2.setText("jButton1");

        i4.setText("jButton1");

        i5.setText("jButton1");

        i3.setText("jButton1");

        i6.setText("jButton1");

        i7.setText("jButton1");

        i9.setText("jButton1");

        i10.setText("jButton1");

        i12.setText("jButton1");

        i13.setText("jButton1");

        i11.setText("jButton1");

        i14.setText("jButton1");

        i16.setText("jButton1");

        i15.setText("jButton1");

        i17.setText("jButton1");

        i18.setText("jButton1");

        i20.setText("jButton1");

        i21.setText("jButton1");

        i19.setText("jButton1");

        i22.setText("jButton1");

        i24.setText("jButton1");

        i23.setText("jButton1");

        i25.setText("jButton1");

        i26.setText("jButton1");

        i28.setText("jButton1");

        i29.setText("jButton1");

        i27.setText("jButton1");

        i30.setText("jButton1");

        i32.setText("jButton1");

        i31.setText("jButton1");

        jButton62.setText("jButton1");

        jButton63.setText("jButton1");

        jButton64.setText("jButton1");

        jButton65.setText("jButton1");

        jButton66.setText("jButton1");

        jButton67.setText("jButton1");

        jButton68.setText("jButton1");

        jButton69.setText("jButton1");

        jButton70.setText("jButton1");

        jButton71.setText("jButton1");

        jButton72.setText("jButton1");

        jButton73.setText("jButton1");

        jButton74.setText("jButton1");

        jButton75.setText("jButton1");

        jButton76.setText("jButton1");

        jButton77.setText("jButton1");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jButton77, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel59, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton76, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton70, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton71, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton72, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton73, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton75, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton74, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton77, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton76, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addComponent(jButton69, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton68, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton62, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton63, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton64, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton65, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton67, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton66, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton69, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton68, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel57, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                        .addComponent(i31, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)))
                .addGap(18, 18, 18)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(i32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addComponent(jPanel17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i25, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i26, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i28, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i29, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i30, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i27, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i31, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i32, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t32, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t31, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t29, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t28, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(i18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(i23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(t24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(i24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i17, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i18, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i20, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i21, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i19, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i23, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i24, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 144, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(i15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(i16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(t16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i9, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i10, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i13, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i14, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i11, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i16, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(i7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(displayPanelLayout.createSequentialGroup()
                        .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
            displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayPanelLayout.createSequentialGroup()
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(i1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i4, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(i7, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(t7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t4, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(t3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 928, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 139, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jScrollPane5.setViewportView(jPanel4);

        newPlayList.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        newPlayList.setText("new player");
        newPlayList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newPlayListActionPerformed(evt);
            }
        });

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

        musicSlider.setMaximum(1000);
        musicSlider.setToolTipText("");
        musicSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                musicSliderStateChanged(evt);
            }
        });
        musicSlider.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                musicSliderMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 649, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(380, 380, 380))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(musicSlider, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        addCurrentToButton.setText("jButton26");
        addCurrentToButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCurrentToButtonActionPerformed(evt);
            }
        });

        favoriteButton.setText("jButton26");
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                favoriteButtonActionPerformed(evt);
            }
        });

        shuffleButton.setText("jButton26");
        shuffleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                shuffleButtonActionPerformed(evt);
            }
        });

        pauseButton.setText("jButton26");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseButtonActionPerformed(evt);
            }
        });

        forwardButton.setText("jButton26");
        forwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                forwardButtonActionPerformed(evt);
            }
        });

        playButton.setText("jButton26");
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });

        backwardButton.setText("jButton26");
        backwardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backwardButtonActionPerformed(evt);
            }
        });

        musicLabel.setText("jLabel28");

        volumeSlider.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                volumeSliderAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });

        timeLabel.setText("jLabel9");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(121, 121, 121))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(42, 42, 42)
                        .addComponent(musicLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(volumeSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addComponent(timeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(addCurrentToButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(shuffleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(backwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(favoriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pauseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(playButton)
                        .addComponent(forwardButton)
                        .addComponent(favoriteButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(shuffleButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(backwardButton)
                        .addComponent(addCurrentToButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jl2.setText("jLabel9");

        jl3.setText("jLabel9");

        jl5.setText("jLabel9");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jl1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jl3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jl5, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(passTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jl1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jl5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jl2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(passTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jl3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 839, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        userNameLabel.setText("jLabel29");

        searchButton.setText("search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        searchText.setText("search for..");

        sortButton.setText("sort");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        sortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "alphabet", "artist", "time" }));

        addToLibraryButton.setText("add to library");
        addToLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToLibraryButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(addToLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(113, 113, 113)
                        .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(sortButton)
                        .addGap(41, 41, 41)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(searchButton)
                        .addGap(0, 0, 0)
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(displayMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(newPlayList, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 724, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(12, 12, 12)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton)
                        .addComponent(searchText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(sortButton)
                        .addComponent(sortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(addToLibraryButton)))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 502, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPlayList)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 221, Short.MAX_VALUE)
                .addComponent(displayMusicPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1525, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1087, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newPlayListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newPlayListActionPerformed
        
         NPlayLists n = new NPlayLists(user);
        n.setVisible(true);
        
    }//GEN-LAST:event_newPlayListActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_forwardButtonActionPerformed
        
        System.out.println("true in forward");


        if (this.mode == 0)
            return;
        else if (mode == 1) {
            try {
                ArrayList<Music> songs = user.getCurrenPlayList().getSongs();
                int a = songs.indexOf(user.getLastMusic());
                user.setLastMusic(songs.get((a + 1)%songs.size()));
                setMusic(songs.get((a + 1)%songs.size()));
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }


        }
        else {
            ArrayList<Music> songs1 = user.getCurrentAlbum().getSongs();
            int a = songs1.indexOf(user.getLastMusic());
            user.setLastMusic(songs1.get((a + 1)%songs1.size()));
            try {
                setMusic(songs1.get((a + 1)%songs1.size()));
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
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

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        
        
        if (pt.getState().equals(Thread.State.NEW))
            pt.start();
        else pt.resume();
        displayMusicPanel.setVisible(true);

        
        
    }//GEN-LAST:event_playButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseButtonActionPerformed
        
        
        pt.suspend();
        displayMusicPanel.setVisible(false);


        
        
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void backwardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backwardButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_backwardButtonActionPerformed

    private void shuffleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_shuffleButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_shuffleButtonActionPerformed

    private void addCurrentToButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCurrentToButtonActionPerformed
        
         String playName = JOptionPane.showInputDialog("write the name of playList you want to add this song to");
        for(int i=0;i<user.getPlayLists().size();i++){
            if(user.getPlayLists().get(i).getTitle().equals(playName)){

                user.getPlayLists().get(i).addSong(music);
                break;
            }
        }
        
        
    }//GEN-LAST:event_addCurrentToButtonActionPerformed

    private void volumeSliderAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_volumeSliderAncestorAdded
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
    }//GEN-LAST:event_volumeSliderAncestorAdded

    private void musicSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_musicSliderStateChanged
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
    }//GEN-LAST:event_musicSliderStateChanged

    private void SongsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SongsButtonActionPerformed
        buttonAndLabelp1();
        displayPanel.setVisible(true);
         
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

            try {
                
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
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
         
    }//GEN-LAST:event_SongsButtonActionPerformed

    private void addToLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToLibraryButtonActionPerformed
       
         try {
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
         } catch (IOException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (InvalidDataException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedTagException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (JavaLayerException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         }



    }//GEN-LAST:event_addToLibraryButtonActionPerformed

    private void editPlayListButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                   
        
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
    }                                             

    private void i1ActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException, IOException, InterruptedException, UnsupportedTagException, InvalidDataException {                                   
        for (int i = 0; i < user.getSongs().size(); i++) {
            if (user.getSongs().get(i).getTitle().equals(i1.getText())) {
                setMusic(user.getSongs().get(i));
                break;
            }
        }

        
    }                                                  

    private boolean itsAlbum=false;

    private void AlbumButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AlbumButtonActionPerformed
        
        
        for (int i = 0; i < buttons.size() && i < labels.size(); i++) {

            buttons.get(i).setVisible(false);
            labels.get(i).setVisible(false);
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
                Mp3File mp3 = null;
                try {
                    mp3 = new Mp3File(mm.getMusic());
                } catch (IOException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
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
                            buttons.get(i).setVisible(true);


                            Image image;
                            if (al.getSongs().get(i) != null) {
                                Music mm = al.getSongs().
                                        get(0);
                                Mp3File mp3 = null;
                                try {
                                    mp3 = new Mp3File(mm.getMusic());
                                } catch (IOException e1) {
                                    e1.printStackTrace();
                                } catch (UnsupportedTagException e1) {
                                    e1.printStackTrace();
                                } catch (InvalidDataException e1) {
                                    e1.printStackTrace();
                                }
                                byte[] bb;
//            ImageIcon image1=new ImageIcon(bb);
                                try {

                                    bb = mp3.
                                            getId3v2Tag().
                                            getAlbumImage();
                                    ImageIcon image1 = new ImageIcon(bb);
                                    image = Music.getScaledImage(image1.getImage(), 200, 200);

                                } catch (NullPointerException e1) {
                                    ImageIcon imageIcon = new ImageIcon("baseMusicArtwork.jpeg");
                                    image = Music.getScaledImage(imageIcon.getImage(), 200, 200);
                                }


                                setImage2(buttons.get(i), image);


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
                    }
                });

            }
        }

        
    }//GEN-LAST:event_AlbumButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        
         if (playList.size()!=0)playList.removeAllElements();
        for (int i = 0; i < user.getPlayLists().size(); i++) {
            playList.addElement(user.getPlayLists().get(i).getTitle());
        }
        playListList.setModel(playList);
        
    }//GEN-LAST:event_updateButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        
        String wanted=searchText.getText();
        System.out.println("In search"+wanted);
        ArrayList<Music> searched=new ArrayList<>();
        for (int i = 0; i < songs.size(); i++) {

            System.out.println(songs.get(i).getArtist());
            if (songs.get(i).getAlbum().indexOf(wanted)!=-1)
                if (!searched.contains(songs.get(i))){
                    searched.add(songs.get(i));
                }
                if(songs.get(i).getArtist().indexOf(wanted)!=-1)
                    if (!searched.contains(songs.get(i))){
                        searched.add(songs.get(i));
                    }
                    if (songs.get(i).getTitle().indexOf(wanted)!=-1){
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
            Music mm = null;
            try {
                mm = new Music(new File(searched.get(i).getPath()));
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image image;
            Mp3File mp3=null;
            try {
                mp3 = new Mp3File(mm.getMusic().getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
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

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
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

                    try {
                        
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
                    catch (IOException ex){
                        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }



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

                    try {
                        
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
                    catch (IOException ex){
                        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }



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
                    try {
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
                    catch (IOException ex){
                        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidDataException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedTagException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (JavaLayerException ex) {
                Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
                }

                break;

        }



    }//GEN-LAST:event_sortButtonActionPerformed
public static int samount;
    private void musicSliderMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_musicSliderMouseReleased
        
        
        try{                                          
            
            
            int totaltime=pt.m.getTime();
            pt.suspend();
            JSlider source = (JSlider) evt.getSource();
            System.out.println("Here");
            
//        System.out.println(music.getInput().available());
FileInputStream newInput=pt.m.getInput();

System.out.println(newInput.available());
System.out.println(source.getValue());
try{pt.stop();}finally {
    
    
    try {
        samount=source.getValue();
        System.out.println("here1finally");
        ctime= (int) ((double)  source.getValue() / 1000* totaltime);
        System.out.println("))))))))))))))))))))))))))))"+pt.m.timetoString(ctime));
        changed=true;
        newInput.skip((long) ((double)  source.getValue() / 1000* pt.m.getStreamLength()));
        System.out.println((
                double) pt.m.getStreamLength());
        System.out.println(((double)  source.getValue() / 1000* pt.m.getStreamLength())+"))))))))))))))))))");
        System.out.println(newInput.available());
        Music mm=new Music(pt.m.getMusic());
        mm.setInput(newInput);
        try {
            pt = new PlayingThread(mm,newInput);
        } catch (JavaLayerException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedTagException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidDataException ex) {
            Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(pt.m.getInput().available()+"+++++++++++++++++++++++++++++++");
        pt.start();
    } catch (IOException ex) {
        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (InvalidDataException ex) {
        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (UnsupportedTagException ex) {
        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
    } catch (JavaLayerException ex) {
        Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    
}



        }    catch (InvalidDataException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         } catch (UnsupportedTagException ex) {
             Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
         }


        
    }//GEN-LAST:event_musicSliderMouseReleased
                                                  
    
    
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
        this.jl5.setIcon(new ImageIcon(music.getArtWork()));
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


    
     public void buttonAndLabelp1() {
        buttons.add(i1);
        buttons.add(i2);
        buttons.add(i3);
        buttons.add(i4);
        buttons.add(i5);
        buttons.add(i6);
        buttons.add(i7);
        //buttons.add(i8);
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
        buttons.add(i25);
        buttons.add(i26);
        labels.add(t1);
        labels.add(t2);
        labels.add(t3);
        labels.add(t4);
        labels.add(t5);
        labels.add(t6);
        labels.add(t7);
//        labels.add(t8);
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
        labels.add(t25);
        labels.add(t26);
        for (int i=0;i<labels.size();i++){
            labels.get(i).setVisible(false);
        }

        for (int i=0;i<buttons.size();i++){
            buttons.get(i).setVisible(false);}

    }


   
    
    
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    
                    new HomeFrame(username).setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidDataException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedTagException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (JavaLayerException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InterruptedException ex) {
                    Logger.getLogger(HomeFrame.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                fileNameSerialize = username.concat(".bin");
                    HomeFrame homeframe = null;
                    System.out.println("name" + fileNameSerialize);
            }
        });
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
    private javax.swing.JButton AlbumButton;
    private javax.swing.JButton SongsButton;
    private javax.swing.JButton addCurrentToButton;
    private javax.swing.JButton addFriendSong1;
    private javax.swing.JButton addFriendSong2;
    private javax.swing.JButton addFriendSong3;
    private javax.swing.JButton addToLibraryButton;
    private javax.swing.JButton backwardButton;
    private javax.swing.JButton deletePlayListButton;
    private javax.swing.JPanel displayMusicPanel;
    private javax.swing.JPanel displayPanel;
    private javax.swing.JButton editPlayListButton;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JButton forwardButton;
    private javax.swing.JLabel friendLabel;
    private javax.swing.JLabel friendLabel2;
    private javax.swing.JLabel friendLabel3;
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
    private javax.swing.JButton i4;
    private javax.swing.JButton i5;
    private javax.swing.JButton i6;
    private javax.swing.JButton i7;
    private javax.swing.JButton i9;
    private javax.swing.JButton jButton62;
    private javax.swing.JButton jButton63;
    private javax.swing.JButton jButton64;
    private javax.swing.JButton jButton65;
    private javax.swing.JButton jButton66;
    private javax.swing.JButton jButton67;
    private javax.swing.JButton jButton68;
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton70;
    private javax.swing.JButton jButton71;
    private javax.swing.JButton jButton72;
    private javax.swing.JButton jButton73;
    private javax.swing.JButton jButton74;
    private javax.swing.JButton jButton75;
    private javax.swing.JButton jButton76;
    private javax.swing.JButton jButton77;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel jl1;
    private javax.swing.JLabel jl2;
    private javax.swing.JLabel jl3;
    private javax.swing.JLabel jl5;
    private javax.swing.JLabel musicLabel;
    private javax.swing.JSlider musicSlider;
    private javax.swing.JButton newPlayList;
    private javax.swing.JLabel passTimeLabel;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JList<String> playListList;
    private javax.swing.JButton searchButton;
    private javax.swing.JTextField searchText;
    private javax.swing.JButton shuffleButton;
    private javax.swing.JButton sortButton;
    private javax.swing.JComboBox<String> sortComboBox;
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
    private javax.swing.JLabel t4;
    private javax.swing.JLabel t5;
    private javax.swing.JLabel t6;
    private javax.swing.JLabel t7;
    private javax.swing.JLabel t9;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel title;
    private javax.swing.JButton updateButton;
    private javax.swing.JLabel userNameLabel;
    private javax.swing.JSlider volumeSlider;
    // End of variables declaration//GEN-END:variables
}
