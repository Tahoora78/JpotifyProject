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

    public static void main(String[] args) throws IOException {
       Socket sr = new Socket("169.254.156.61",15000);
        OutputStream out = sr.getOutputStream();
        // FileInputStream fr = new FileInputStream(addressFile);
        Socket sock = new Socket("169.254.156.61", 15000);
        InputStream in = sock.getInputStream();
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        OutputStreamWriter wr = new OutputStreamWriter(out);
        BufferedWriter write = new BufferedWriter(wr);
        while(true) {


        String number;
        boolean send = true;


            if(send==false) {
                    number = br.readLine();
                    System.out.println("recieving");
                transferfromserverToClient1("clientFile.mp3");

            }
            if(send==true){

                System.out.println("sending");
               // write.write("connect");
               // write.flush();
                transferFromClientToServer1("123.mp3");
                System.out.println("88888");
            }


        }
        transferFromClientToServer1("123.mp3");

    }

    }
