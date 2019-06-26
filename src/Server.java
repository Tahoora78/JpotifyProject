import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
     static Socket acceptName;
    static Socket acceptIP;
    static Socket acceptFile;
   static ArrayList<File> DataBase;
    public static ArrayList<User> users;
   
    public static void transferFromClientToServer(String fileName) throws IOException {
        // ServerSocket servsock = new ServerSocket(15065);

        int port = 15064;
        byte[] b = new byte[9999999];
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("Server Started and listening to the port 25000");
        Socket sock = serverSocket.accept();
        InputStream is = sock.getInputStream();
        //address to save
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);
        byte [] buffer = new byte[64*1024];
        int bytesRead = 0;

        while ( (bytesRead = is.read(buffer)) != -1)
            fileOutputStream.write(buffer, 0, bytesRead);
        sock.close();
        fileOutputStream.close();

            /*

             FileOutputStream fr = new FileOutputStream(fileName);
             is.read(b,0,b.length);
             fr.write(b,0,b.length);
             */


    }
    public static void transferfromserverToClient(String name) throws IOException {
        ServerSocket servsock = new ServerSocket(15064);
        Socket sock = servsock.accept();
        long time = System.currentTimeMillis();

        OutputStream out = sock.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream(name);

        byte[] buffer = new byte[64 * 1024];
        int bytesRead = 0;
        long totalSent = 0;

        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            if (bytesRead > 0) {
                out.write(buffer, 0, bytesRead);
                totalSent += bytesRead;
                System.out.println("sent " + totalSent);
            }
        }

        sock.close();

        System.out.println("Sent " + totalSent + " bytes in "
                + (System.currentTimeMillis() - time) + "ms.");
    }
    public static void main(String[] args) throws IOException, InvalidDataException, JavaLayerException, UnsupportedTagException {
        LoginPage login = new LoginPage();
        login.setVisible(true);
       // IPs.add(InetAddress.getLocalHost().getHostAddress().toString());
       // usernames.add(HomeFrame.user.getName());
        ServerSocket sSocketIP = new ServerSocket();
        
        while (true) {
            acceptIP = sSocketIP.accept();
         //   acceptName = sSocketUserName.accept();
            BufferedReader clientName = new BufferedReader(new InputStreamReader(acceptName.getInputStream()));

            DataOutputStream outToClientname = new DataOutputStream(acceptName.getOutputStream());

            String name = clientName.readLine();
           // usernames.add(name);
            outToClientname.writeBytes("ok");


            BufferedReader clientIP = new BufferedReader(new InputStreamReader(acceptIP.getInputStream()));

            DataOutputStream outToClientIP = new DataOutputStream(acceptIP.getOutputStream());

            String IP = clientIP.readLine();
           // IPs.add(name);
            outToClientIP.writeBytes("ok");


            //acceptFile = sSocketFile.accept();
            BufferedReader clientFile = new BufferedReader(new InputStreamReader(acceptFile.getInputStream()));

            DataOutputStream outToClientFile = new DataOutputStream(acceptFile.getOutputStream());

            String FileName = clientName.readLine();
            File file = new File(FileName);

            for (File file1 : DataBase) {
                if ((new Music(file1)).getTitle().equals(FileName)) {
                    file = file1;
                    break;
                }
            }

            //transferfromserver(file);

            outToClientname.writeBytes("ok");
        }

    }

    }


