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
            if (HomeFrame.changed){au=HomeFrame.ctime;
            HomeFrame.changed=false;
                System.out.println("popopopopoppopoppopoppopo"+au);
            }


//HomePage.changed=false;


            if (b-a>=100) {
            }

            if (b-a>=1000) {
                System.out.println(m.timetoString(a / 1000+au));
                try {
<<<<<<< HEAD
                    HomePage.musicSlider.setValue((int) ((double)(b)/m.getTime())+au*1000/m.getTime());

//                    System.out.println("1-"+);




                    System.out.println();
=======
                    HomeFrame.musicSlider.setValue((int) ((double)b/m.getTime())+au);
>>>>>>> 0ce23d17551ee2cae0df892c588eb1b09fe75f03
                } catch (InvalidDataException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (UnsupportedTagException e) {
                    e.printStackTrace();
                }
<<<<<<< HEAD
                HomePage.passTimeLabel.setText(m.timetoString(a / 1000+au));
=======
                HomeFrame.passTimeLabel.setText(m.timetoString(a / 1000+au));
>>>>>>> 0ce23d17551ee2cae0df892c588eb1b09fe75f03
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