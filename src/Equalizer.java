import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Graphics;

/**
 * it's a class which model a linear graphical equalizer for musics
 *
 * @author kowsar shams
 */

public class Equalizer extends JPanel implements ActionListener {


    private Timer timer = new Timer(30, this);

    /**
     * it paints lines with differnt lengths each time to give it an animation effect
     * @param g
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i=0;i<5;i++) {
            Random random = new Random();

            for (int j = 0; j < 200; j++)
                g.fill3DRect(10 * j, 0, 5, (int) (Math.random()*100),true );
        }
        timer.start();
    }

    /**
     * draw new model of lines with different sizez
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
            repaint();
        }

    }

