import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Soundcontroller {

    private int value;

    public Soundcontroller() {

    }


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        Line.Info source = Port.Info.SPEAKER;
        //        source = Port.Info.LINE_OUT;
        //        source = Port.Info.HEADPHONE;

        if (AudioSystem.isLineSupported(source)) {
            try {
                Port outline = (Port) AudioSystem.getLine(source);
                outline.open();
                FloatControl volumeControl = (FloatControl) outline.getControl(FloatControl.Type.VOLUME);

                volumeControl.setValue((float) ((float) value / 100));
            } catch (LineUnavailableException ex) {
                System.err.println("source not supported");
                ex.printStackTrace();
            }
        }
    }
}