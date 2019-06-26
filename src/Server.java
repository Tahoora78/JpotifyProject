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
   
    public static void transfertoserver() throws IOException {
        ServerSocket servsock = new ServerSocket(15065);







    }
    public static void transferfromserver(File file) throws IOException {
        ServerSocket servsock = new ServerSocket(15064);
        Socket sock = servsock.accept();
        long time = System.currentTimeMillis();

        OutputStream out = sock.getOutputStream();
        FileInputStream fileInputStream = new FileInputStream("1234.mp4");

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

   
    static ArrayList<String> IPs=new ArrayList<>();
    public static ArrayList<String> usernames=new ArrayList<>();
 
    public ArrayList<String> getAllUserNames(){
        return usernames;
    }

    public static void main(String[] args) throws IOException, InvalidDataException, JavaLayerException, UnsupportedTagException {
        LoginPage login = new LoginPage();
        login.setVisible(true);
        IPs.add(InetAddress.getLocalHost().getHostAddress().toString());
        usernames.add(HomePage.user.getName());
        ServerSocket sSocketIP = new ServerSocket();
        
        while (true) {
            acceptIP = sSocketIP.accept();
//            acceptName = sSocketUserName.accept();
            BufferedReader clientName = new BufferedReader(new InputStreamReader(acceptName.getInputStream()));

            DataOutputStream outToClientname = new DataOutputStream(acceptName.getOutputStream());

            String name = clientName.readLine();
            usernames.add(name);
            outToClientname.writeBytes("ok");


            BufferedReader clientIP = new BufferedReader(new InputStreamReader(acceptIP.getInputStream()));

            DataOutputStream outToClientIP = new DataOutputStream(acceptIP.getOutputStream());

            String IP = clientIP.readLine();
            IPs.add(name);
            outToClientIP.writeBytes("ok");


//            acceptFile = sSocketFile.accept();
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

            transferfromserver(file);

            outToClientname.writeBytes("ok");
        }

    }

    }

