import java.io.*;
import java.net.*;
import java.util.*;
import javax.net.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.*;
import javax.sound.sampled.*;
import java.util.jar.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.*;
 
public final class Servers {
    public static void readingAndSendingToClient(String name) throws IOException{
        ServerSocket s = new ServerSocket(15069); 
        Socket sr = s.accept();
        FileInputStream fr = new FileInputStream(name.concat(".txt"));
        byte b[] = new byte[9999999];
        fr.read(b,0,b.length);
        OutputStream os = sr.getOutputStream();
        sr.close();
    }
    
     public static void gettingFileFromServer(String name) throws IOException{
        byte[] b = new byte[9999999];
        Socket sr = new Socket("local host",15069);
        InputStream is = sr.getInputStream();
        FileOutputStream fr = new FileOutputStream(name.concat(".txt"));
        is.read(b,0,b.length);
        fr.write(b,0,b.length);
        sr.close();
    }
    
    
    public static void main(String[] args){
        try {
            readingAndSendingToClient("tahoora");
            System.out.println("tttttttt");
        } catch (IOException ex) {
            Logger.getLogger(Servers.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            System.out.println("88888888888888888888");
            gettingFileFromServer("mohamad");
        } catch (IOException ex) {
            Logger.getLogger(Servers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}