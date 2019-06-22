import java.awt.*;
import java.util.ArrayList;

/**
 * it's a class which model an Alnum in program
 * Each Album has title,Artist and Artwork which is same between all songs of an Album
 *
 * @author kowsar shams
 *
 *
 */
public class Album {
    private String Title;
    private int counter;
    private Image image;
    private String artist;
    private long ltime;

    public long getLtime() {

        long max=0;
        for (Music song:songs){

            if (song.getStime()>max){
                max=song.getStime();
            }
        }

        this.ltime=max;
        return max;
    }


    private ArrayList<Music> songs=new ArrayList<>();

    public Album() {

    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    /**
     * it addes given music to Album and set title,artist and Image(Artwork) which is a similar charactristic beyween all songs of an Album
     * @param music music which we want to add to Album
     */
    public void addSong(Music music){
        songs.add(music);
        this.setTitle(music.getAlbum());
        this.setImage(music.getArtWork());
        this.setArtist(music.getArtist());
        counter=songs.size();

    }

    public int getCounter() {
        return counter;
    }

    public ArrayList<Music> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Music> songs) {
        this.songs = songs;
    }
}
