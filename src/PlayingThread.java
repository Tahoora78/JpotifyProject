import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class PlayingThread implements Runnable{
    private Music music;
    private AdvancedPlayer advancedPlayer;
    boolean isPaused=false;
    public PlayingThread(Music music) throws FileNotFoundException, JavaLayerException {
        this.music=music;
        this.advancedPlayer=new AdvancedPlayer(new FileInputStream(music.getMusic()));
    }
    public void resume() {
        this.isPaused = false;
        synchronized (advancedPlayer) {
            advancedPlayer.notifyAll();
        }
    }
    public void seekTo(int frame) throws JavaLayerException {
        synchronized (advancedPlayer) {
            advancedPlayer.close();
            advancedPlayer = new AdvancedPlayer(music.getInput());
            advancedPlayer.play(frame, frame + 1);
        }
    }

    @Override
    public void run() {

        while (true){
            try {
                if (!advancedPlayer.play(1)) break;
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
            if (this.isPaused==true){

                synchronized (advancedPlayer) {
                    try {
                        advancedPlayer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }





        }

    }


    public void pause(){
        this.isPaused=true;
    }
}
