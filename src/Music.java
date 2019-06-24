
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Music is a class which model a song in program
 * It is used in almost every classes
 *
 * @author kowsar-shams
 */


public class Music implements Serializable{
    private transient Mp3File mp3File;
    private String Title;
    private String album;
    private String Artist;
    private File music;
    private int streamLength;
    private long stime;
    private int bytepersec;
    private int framepersec;
    private boolean isplaying=false;
    private transient AdvancedPlayer advancedPlayer;
    private transient FileInputStream input=null;
    private transient Player player=null;
    private Thread t;
    private int count;
    private int passedTime;
    private int remainTime;
    private final String songLyricsURL = "http://www.songlyrics.com";
    private boolean liked=false;

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public long getStime() {
        return stime;
    }

    public void setStime(long stime) {
        this.stime = stime;
    }

    /**
     * construct a single music by getting a file
     * @param music file which has been made and pass to make song
     * @throws IOException if it can't make an stream
     * @throws InvalidDataException if file format doesn't be .mp3
     * @throws UnsupportedTagException if music doesn't support ID3V2
     */



    
    public Music(File music) throws IOException, InvalidDataException, UnsupportedTagException, JavaLayerException {
        this.music = music;
        this.metaData();
        byte[] b=new byte[123];
        this.input=new FileInputStream(music);
        streamLength=input.available();
        this.mp3File=new Mp3File(music.getAbsolutePath());
        this.count=mp3File.getFrameCount();
        this.advancedPlayer=new AdvancedPlayer(input);
        metaData();
        bytepersec=input.available()/getTime();
        framepersec=mp3File.getFrameCount()/getTime();

    }


    /**
     * this class get music data from last 128 byte of the song
     * it also sets title,artist and Album class fields
     * @throws IOException if there be a problem while using with file or streams
     */

    private void metaData() throws IOException {


        byte[] total=new byte[(int) music.length()];
        byte[] TAG=new byte[3];
        byte[] title=new byte[30];
        byte[] artist=new byte[30];
        byte[] album=new byte[30];

        FileInputStream temp=new FileInputStream(music);


        temp.read(total);
        int counter=0;
        for (int i=total.length-128;i<total.length-125;i++){
            TAG[counter]=total[i];
            counter++;
        }

        counter=0;
        for (int i=total.length-125;i<total.length-95;i++){
            title[counter]=total[i];
            counter++;
        }
        counter=0;
        for (int i=total.length-95;i<total.length-65;i++) {
            artist[counter] = total[i];
            counter++;
        }

        counter=0;
        for (int i=total.length-65;i<total.length-35;i++){
            album[counter]=total[i];
            counter++;
        }
        this.album = new String(album);
        this.Title=new String(title);
        this.Artist=new String(artist);

    }


    /**
     *
     * @return field title
     */
    public String getTitle() {
        return Title;
    }

    /**
     *
     * @return album field
     */
    public String getAlbum() {
        return album;
    }

    /**
     *
     * @return filed artist
     */
    public String getArtist() {
        return Artist;
    }


    public AdvancedPlayer getAdvancedPlayer() {
        return advancedPlayer;
    }

    public FileInputStream getInput() {
        return input;
    }

    public void setInput(FileInputStream input) {
        this.input = input;
    }

    public void setAdvancedPlayer(AdvancedPlayer advancedPlayer) {
        this.advancedPlayer = advancedPlayer;
    }

    public boolean isIsplaying() {
        return isplaying;
    }

    public void setIsplaying(boolean isplaying) {
        this.isplaying = isplaying;
    }


    /**
     *
     * @return whole time of a music
     */
    public int getTime() {
        return ((int)mp3File.getLengthInMilliseconds())/1000;
    }


    /**
     * it changes given time to a valid structure
     * @param time its an int which is not in second/minute format
     * @return string which is a valid format of given time
     */
    public String timetoString(int time){
        String min=""+time/60;
        String sec=""+time%60;
        if (min.length()==1)
            min="0"+min;
        if (sec.length()==1)
            sec="0"+sec;
        return min+":"+sec;
    }

    /**
     * ir get music artwork from its bytes
     * @return image an Image which is resized into a smaller size
     */
    public Image getArtWork(){
        byte[] bytes;
        try {

            bytes = mp3File.
                    getId3v2Tag().
                    getAlbumImage();
            ImageIcon image=new ImageIcon(bytes);
            return getScaledImage(image.getImage(),100,100);

        }
        catch (NullPointerException e){
            ImageIcon imageIcon=new ImageIcon("baseMusicArtwork.jpeg");
            return getScaledImage(imageIcon.getImage(),500,500);
        }

    }

    public File getMusic() {
        return music;
    }

    public void setMusic(File music) {
        this.music = music;
    }

    /**
     * it resize an Image
     * @param srcImg source image which we want to resize it
     * @param w width which we want new image to be
     * @param h height which we want new image to be
     * @return image which is in needed size
     */
    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    /**
     * it makes a connection to "http://www.songlyrics.com" and get music by its artist ,title and by using tag p.songLyricsV14 int it's Javascript Format
     *
     * @param band
     * @param songTitle
     * @return
     * @throws IOException
     */
    public List<String> getSongLyrics(String band, String songTitle) throws IOException {
        List<String> lyrics= new ArrayList<String>();

        Document doc = Jsoup.connect(this.songLyricsURL+ "/"+band.replace(" ", "-").toLowerCase()+"/"+songTitle.replace(" ", "-").toLowerCase()+"-lyrics/").get();
        String title = doc.title();
        System.out.println(title);
        Element p = doc.select("p.songLyricsV14").get(0);
        for (Node e: p.childNodes()) {
            if (e instanceof TextNode) {
                lyrics.add(((TextNode)e).getWholeText());
            }
        }
        return lyrics;
    }

    /**
     *
     * @return a number which showes how many frame is in each second of this music
     */
    public int getFramePerCount(){
        return  mp3File.getFrameCount()/getTime();

    }

    public String getPath(){
        return this.music.getAbsolutePath();
    }

    void pause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}