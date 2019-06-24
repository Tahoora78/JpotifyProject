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

    public PlayingThread(Music m) throws JavaLayerException, UnsupportedTagException, InvalidDataException, IOException {

        this.m=m;

    }

    @Override
    public void run() {
        long t = System.currentTimeMillis();
        System.out.println("*******************"+m.player);

        try {
            m.setPlayer(new Player(new FileInputStream(m.getMusic())));
        } catch (JavaLayerException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int a = m.
                player
                .getPosition();
        int b = m.
                player
                .getPosition();

        while (true) {

            b = m.player.getPosition();
            if (b-a>=100) {
            }

            if (b-a>=1000) {
                System.out.println(m.timetoString(a / 1000));
                try {
                    HomePage.musicSlider.setValue((int) ((double)b/m.getTime()));
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
                HomePage.jLabel2.setText(m.timetoString(a / 1000));
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

}