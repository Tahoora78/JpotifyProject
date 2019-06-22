import java.awt.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * it's a class which model a playList in program
 * each playList has a list of songs and a title
 *
 * @author kowsar shams
 */

public class PlayList implements Serializable {
    private ArrayList<Music> songs = new ArrayList<>();
    private String title;
    private int number;
    
    //number of favorite playlist = 1000
    //number of favorite playlist = 1001
    
    public void setNumber(int num){
        number = num;
    }
    
    public int getNumber(){
        return number;
    }
    

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
     * it removes a song from PlayList
      * @param s it's name of the music in arrayList and if it has 2 music with same name it just remove last added one
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
     * it removes a song from PlayList
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

    public ArrayList<Music> getSongs() { return this.songs;
    }
}
