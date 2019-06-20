import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * it's a class which model a playlist in program
 * each playlist has a list of songs and a title
 *
 * @author kowsar shams
 */

public class PlayList {
    private ArrayList<Music> songs = new ArrayList<>();
    private String title;

    public PlayList(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void addSong(Music m) {
        songs.add(m);

    }


    /**
     * it removes a song from Playlist
      * @param s it's name of the music in arraylist and if it has 2 music with same name it just remove last added one
     */
    public void removeSong(String s) {
        Iterator<Music> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Music music = iterator.next();
            if (music.getTitle().equals(s)) {
                songs.remove(music);
                break;
            }
        }
    }

    /**
     * it removes a song from Playlist
     * @param m it is the music file which we want to be deleted
     * @throws IOException if it face any problem while working with Music m
     */
    public void removeSong(Music m) throws IOException {
        Iterator<Music> iterator = songs.iterator();
        while (iterator.hasNext()) {
            Music music = iterator.next();
            if (music.equals(m)) {
                songs.remove(music);
            }
        }

    }


    public void rename(String s) {
        this.title = s;
    }

}
