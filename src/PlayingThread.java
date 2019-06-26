import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class PlayingThread extends Thread {

    Music m ;
    FileInputStream fis;

    public PlayingThread(Music m,FileInputStream fileInputStream) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {

        this.m=m;
        this.m.setInput(fileInputStream);
        fis=fileInputStream;
        System.out.println("999999999999999999999"+fileInputStream.available());

    }

    @Override
    public void run() {
        long t = System.currentTimeMillis();
        try {
            System.out.println("*******************"+m.getInput().available());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            m.setPlayer(new Player(fis));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        }
        int a = m.
                player
                .getPosition();
        int b = m.
                player
                .getPosition();
        int au=0;
        while (true) {
            b = m.player.getPosition();
            if (HomePage.changed){
                au=HomePage.ctime;
            HomePage.changed=false;
                System.out.println("popopopopoppopoppopoppopo"+au);
            }


//HomePage.changed=false;


            if (b-a>=100) {
            }

            if (b-a>=1000) {
                System.out.println(m.timetoString(a / 1000+au));
                try {
                    HomePage.musicSlider.setValue((int) ((double)(b)/m.getTime())+au*1000/m.getTime());


                    System.out.println(((double)(b)/m.getTime())+au*1000/m.getTime());
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
                HomePage.jLabel2.setText(m.timetoString(a / 1000+au));
                a = b;
            }


            try {
                if (!m.player.play(1)) break;
            } catch (JavaLayerException e) {
                e.printStackTrace();

            }
            ;

//
//        try {
////            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        }
    }
    public void ccc(){}

}