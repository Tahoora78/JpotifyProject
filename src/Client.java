import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void transferFromClientToServer1(String addressFile) throws IOException{
        int port = 15064;
        Socket sr = new Socket("localhost",port);
        FileInputStream fr = new FileInputStream(addressFile);
        OutputStream out = sr.getOutputStream();

        byte[] buffer = new byte[64 * 1024];
        int bytesRead = 0;
        long totalSent = 0;

        while ((bytesRead = fr.read(buffer)) != -1) {
            if (bytesRead > 0) {
                out.write(buffer, 0, bytesRead);
                totalSent += bytesRead;
                System.out.println("sent " + totalSent);
            }
        }

        sr.close();

        /*
        byte[] b = new byte[9999999];
        fr.read(b,0,b.length);
        OutputStream os = sr.getOutputStream();
        os.write(b,0,b.length);
*/}


    public static void transferfromserverToClient1(String fileName) throws IOException {
        Socket sock = new Socket("localhost", 15064);
        InputStream in = sock.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        byte [] buffer = new byte[64*1024];
        int bytesRead = 0;

        while ( (bytesRead = in.read(buffer)) != -1)
            fileOutputStream.write(buffer, 0, bytesRead);
        sock.close();
        fileOutputStream.close();
    }

    public static void main(String[] args) {
       /*
        try {
            System.out.println("9(((((((((((((((((((((((((((((((((");
            transferfromserverToClient1("tahoora.mp3");
            System.out.println("10((((((((((((((((((((((((((((((((((");
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        try {
            transferFromClientToServer1("0.bin");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    }
