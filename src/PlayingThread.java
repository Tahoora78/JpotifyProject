import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.File;
import java.io.IOException;

public class PlayingThread extends Thread {

    Music m ;

    public PlayingThread(Music m) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {

        this.m=m;

    }

    @Override
    public void run() {
        long t = System.currentTimeMillis();
        System.out.println("*******************"+m.player);

        int a = m.
                player
                .getPosition();
        int b = m.
                player
                .getPosition();

        while (true) {

            if (b-a>=1000) {
                System.out.println(m.timetoString(a / 1000));
                try {
                    HomePage.musicSlider.setValue(a/m.getTime()*100);
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
                a = b;
            }
            b = m.player.getPosition();

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

}