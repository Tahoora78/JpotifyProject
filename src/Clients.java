import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.sound.sampled.*;
import java.util.jar.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;
 
public class Clients  {
    public static void gettingFileFromServer(String name) throws IOException{
        byte[] b = new byte[99999999];
        Socket sr = new Socket("local host",4999);
        InputStream is = sr.getInputStream();
        FileOutputStream fr = new FileOutputStream(name.concat(".txt"));
        is.read(b,0,b.length);
        fr.write(b,0,b.length);
    }
    
    public static void main(String[] args){
        try {
            gettingFileFromServer("mohamad");
        } catch (IOException ex) {
            Logger.getLogger(Clients.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
}
