
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
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
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Music is a class which model a song in program
 * It is used in almost every classes
 *
 * @author kowsar-shams
 */


public class Music {
    private Mp3File mp3File;
    private String Title;
    private String album;
    private String Artist;
    private File music;
    private FileInputStream input=null;
    private Player player=null;
    private Thread t;
    private int count;
    private int passedTime;
    private int remainTime;
    private int time;
    private AdvancedPlayer advancedPlayer=null;
    private final String songLyricsURL = "http://www.songlyrics.com";

    /**
     * costruct a single music by getting a file
     * @param music file which has been made and pass to make song
     * @throws IOException if it can't make an stream
     * @throws InvalidDataException if file format doesn't be .mp3
     * @throws UnsupportedTagException if music doesnt support ID3V2
     */

    public Music(File music) throws IOException, InvalidDataException, UnsupportedTagException {
        this.music = music;
        this.metaData();
        this.input=new FileInputStream(music);
        this.mp3File=new Mp3File(music.getAbsolutePath());
        this.count=mp3File.getFrameCount();
        metaData();

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


    /**
     * it play a song by starting a thread
     * @param a it's a number which show starting frame which we want song to start from
     * @throws JavaLayerException if it face any problem while playing a file by advanceplayer or creating a mp3 file
     */

    public void play(int a) throws JavaLayerException {
        advancedPlayer=new AdvancedPlayer(input);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if (a>getTime()){
                        System.out.println("PAUSE");
                        return;}
                    advancedPlayer.play((a*count/getTime())%getTime(),count);
                    System.out.println(mp3File.getFrameCount());
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }




    /**
     * it play a song by starting a thread
     * @throws JavaLayerException if it face any problem while playing a file by player or creating a mp3 file
     */
    public void play() throws JavaLayerException {
        player=new Player(input);
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    player.play();
                    System.out.println(mp3File.getFrameCount());

                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

    }


    /**
     * it pause a song  by make the thread which started playing music sleep
     * @throws JavaLayerException
     */
    public void pause() throws JavaLayerException {

        t.stop();

    }

    /**
     *
     * @return it return the time of played music which had been played
     */

    public int getPassedTime() {
        return player.getPosition()/1000;
    }

    /**
     *
     * @return it return the time that have remained till end of music
     */
    public int getRemainTime() {
        return (getTime()-this.getPassedTime())/1000;
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
            return getScaledImage(imageIcon.getImage(),100,100);
        }

    }

    /**
     * it resize an Image
     * @param srcImg source image which we want to resize it
     * @param w width which we want new image to be
     * @param h height which we want new image to be
     * @return image which is in needed size
     */
    private Image getScaledImage(Image srcImg, int w, int h){
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
}