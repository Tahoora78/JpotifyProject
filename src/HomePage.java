/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package jpotify;


import javazoom.jl.decoder.JavaLayerException;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Home pc
 */
public class homePage extends javax.swing.JDialog {

    private boolean liked=false;
    private Music music;
    private PlayList playlist=new PlayList("AAA");
    private Album album=new Album();
    private int mode;
    private PlayList FSongs=new PlayList("Favorit Songs");
    private PlayList Shared=new PlayList("Shared PlayList");
    private ArrayList<Album> albums=new ArrayList<>();
    private ArrayList<PlayList> playlists=new ArrayList<>();
    private HashMap<Integer,Music> songs=new HashMap();
    private int songNum=0;

    /**
     * Creates new form homePage
     */

    public homePage(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.playlists.add(FSongs);
        this.playlists.add(Shared);
        int count=0;
        for(int i=0;i<albums.size();i++){
            Album a=albums.get(i);
            ArrayList<Music> songs=a.getSongs();
            for(int j=0;j<songs.size();j++){
                songs.add(count,songs.get(j));
                songNum++;
                count++;
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        displayPanel = new java.awt.Panel();
        SoundBar = new java.awt.Panel();
        musicSlider = new javax.swing.JSlider();
        playButton = new javax.swing.JButton();
        pauseButton = new javax.swing.JButton();
        forwardButton = new javax.swing.JButton();
        backwardButton = new javax.swing.JButton();
        favoriteButton = new javax.swing.JButton();
        shufflebutton = new javax.swing.JButton();
        soundLabel = new javax.swing.JLabel();
        soundSlider = new javax.swing.JSlider();
        friendActivityScroll = new javax.swing.JScrollPane();
        panel1 = new java.awt.Panel();
        addToLibraryButton = new java.awt.Button();
        songsButton = new javax.swing.JButton();
        albumsButton = new javax.swing.JButton();
        artistButton = new javax.swing.JButton();
        fovoriteSongButton = new javax.swing.JButton();
        playListButton = new javax.swing.JButton();
        favoriteSongsPanel = new javax.swing.JPanel();
        libraryIconLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        friendActivityPanel = new javax.swing.JPanel();
        friendAxtivityLabel = new javax.swing.JLabel();
        newUserButton = new javax.swing.JButton();
        userNameLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout displayPanelLayout = new javax.swing.GroupLayout(displayPanel);
        displayPanel.setLayout(displayPanelLayout);
        displayPanelLayout.setHorizontalGroup(
                displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 386, Short.MAX_VALUE)
        );
        displayPanelLayout.setVerticalGroup(
                displayPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

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

        pauseButton.setText("jButton1");
        pauseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    pauseButtonActionPerformed(evt);
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
                }
            }
        });

        backwardButton.setText("jButton1");

        favoriteButton.setText("jButton1");
        favoriteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    favoriteButtonActionPerformed(evt);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        shufflebutton.setText("jButton1");

        soundLabel.setText("sound");

        javax.swing.GroupLayout SoundBarLayout = new javax.swing.GroupLayout(SoundBar);
        SoundBar.setLayout(SoundBarLayout);
        SoundBarLayout.setHorizontalGroup(
                SoundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SoundBarLayout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(shufflebutton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(backwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pauseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(forwardButton, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(favoriteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(303, 303, 303))
                        .addGroup(SoundBarLayout.createSequentialGroup()
                                .addGap(218, 218, 218)
                                .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(108, 108, 108)
                                .addComponent(soundLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(soundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        SoundBarLayout.setVerticalGroup(
                SoundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SoundBarLayout.createSequentialGroup()
                                .addContainerGap(15, Short.MAX_VALUE)
                                .addGroup(SoundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(playButton)
                                        .addComponent(pauseButton)
                                        .addComponent(forwardButton)
                                        .addComponent(backwardButton)
                                        .addComponent(favoriteButton)
                                        .addComponent(shufflebutton))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(SoundBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(musicSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(soundLabel)
                                        .addComponent(soundSlider, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36))
        );

        addToLibraryButton.setLabel("add to library");
        addToLibraryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToLibraryButtonActionPerformed(evt);
            }
        });

        songsButton.setText("Songs");

        albumsButton.setText("Albums");
        albumsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                albumsButtonActionPerformed(evt);
            }
        });

        artistButton.setText("artistButton");
        artistButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                artistButtonActionPerformed(evt);
            }
        });

        fovoriteSongButton.setText("favoriteSongButton");

        playListButton.setText("playList");

        javax.swing.GroupLayout favoriteSongsPanelLayout = new javax.swing.GroupLayout(favoriteSongsPanel);
        favoriteSongsPanel.setLayout(favoriteSongsPanelLayout);
        favoriteSongsPanelLayout.setHorizontalGroup(
                favoriteSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        favoriteSongsPanelLayout.setVerticalGroup(
                favoriteSongsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(albumsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(artistButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(fovoriteSongButton, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                                                        .addComponent(playListButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(favoriteSongsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addComponent(songsButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(addToLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
        );
        panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panel1Layout.createSequentialGroup()
                                .addGroup(panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panel1Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(addToLibraryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(74, 74, 74))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(songsButton)
                                                .addGap(12, 12, 12)
                                                .addComponent(albumsButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addComponent(artistButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fovoriteSongButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(playListButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(favoriteSongsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(121, Short.MAX_VALUE))
        );

        friendActivityScroll.setViewportView(panel1);

        libraryIconLabel.setText("library");

        javax.swing.GroupLayout friendActivityPanelLayout = new javax.swing.GroupLayout(friendActivityPanel);
        friendActivityPanel.setLayout(friendActivityPanelLayout);
        friendActivityPanelLayout.setHorizontalGroup(
                friendActivityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 195, Short.MAX_VALUE)
        );
        friendActivityPanelLayout.setVerticalGroup(
                friendActivityPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 399, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(friendActivityPanel);

        friendAxtivityLabel.setText("friendActivity");

        newUserButton.setText("new user");

        userNameLabel.setText("user");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(friendActivityScroll, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                                        .addComponent(libraryIconLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(20, 20, 20)
                                                .addComponent(displayPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addComponent(jScrollPane1)
                                                                        .addComponent(friendAxtivityLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                .addGap(0, 37, Short.MAX_VALUE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(userNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(newUserButton)
                                                                .addContainerGap())))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(SoundBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(displayPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(libraryIconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(friendActivityScroll, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(newUserButton)
                                                        .addComponent(userNameLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(friendAxtivityLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(2, 2, 2)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SoundBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addToLibraryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToLibraryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addToLibraryButtonActionPerformed

    private void artistButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_artistButtonActionPerformed



        displayPanel.setLayout(new FlowLayout());


    }//GEN-LAST:event_artistButtonActionPerformed

    private void albumsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_albumsButtonActionPerformed
        for(int i=0;i<albums.size();i++){
            Album a=albums.get(i);
            Image image=a.getImage();
//            tahoora
//            set image to a button
            Button button=new Button();
            this.displayPanel.add(button);
//            add action listener to each button
        }
    }//GEN-LAST:event_albumsButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {//GEN-FIRST:event_playButtonActionPerformed
        this.music.play();
    }//GEN-LAST:event_playButtonActionPerformed

    private void pauseButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {//GEN-FIRST:event_pauseButtonActionPerformed
        this.music.pause();
    }//GEN-LAST:event_pauseButtonActionPerformed

    private void forwardButtonActionPerformed(java.awt.event.ActionEvent evt) throws JavaLayerException {//GEN-FIRST:event_forwardButtonActionPerformed
        if (this.mode==0)
            return;
        else if(mode==1){
            ArrayList<Music> songs=this.playlist.getSongs();
            int a=songs.indexOf(music);
            music.pause();
            this.music=songs.get(a+1);
            music.play();
        }
        else{
            ArrayList<Music> songs=this.album.getSongs();
            int a=songs.indexOf(music);
            music.pause();
            this.music=songs.get(a+1);
            music.play();
        }

    }//GEN-LAST:event_forwardButtonActionPerformed


    private void favoriteButtonActionPerformed(java.awt.event.ActionEvent evt) throws IOException {//GEN-FIRST:event_favoriteButtonActionPerformed
        if(liked==false){
            setImage(favoriteButton,"fulLove.jpg");
            liked = true;
            this.FSongs.addSong(this.music);
        }
        else if(liked == true){
            setImage(favoriteButton,"love.jpg");
            liked = false;
            this.FSongs.removeSong(this.music);
        }
    }//GEN-LAST:event_favoriteButtonActionPerformed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(homePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(homePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(homePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(homePage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                homePage dialog = new homePage(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Panel SoundBar;
    private java.awt.Button addToLibraryButton;
    private javax.swing.JButton albumsButton;
    private javax.swing.JButton artistButton;
    private javax.swing.JButton backwardButton;
    private java.awt.Panel displayPanel;
    private javax.swing.JButton favoriteButton;
    private javax.swing.JPanel favoriteSongsPanel;
    private javax.swing.JButton forwardButton;
    private javax.swing.JButton fovoriteSongButton;
    private javax.swing.JPanel friendActivityPanel;
    private javax.swing.JScrollPane friendActivityScroll;
    private javax.swing.JLabel friendAxtivityLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel libraryIconLabel;
    private javax.swing.JSlider musicSlider;
    private javax.swing.JButton newUserButton;
    private java.awt.Panel panel1;
    private javax.swing.JButton pauseButton;
    private javax.swing.JButton playButton;
    private javax.swing.JButton playListButton;
    private javax.swing.JButton shufflebutton;
    private javax.swing.JButton songsButton;
    private javax.swing.JLabel soundLabel;
    private javax.swing.JSlider soundSlider;
    private javax.swing.JLabel userNameLabel;
    // End of variables declaration//GEN-END:variables
}
