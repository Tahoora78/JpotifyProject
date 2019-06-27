import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {




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

    public static void main(String[] args) throws IOException {
       ServerSocket serverSocket = new ServerSocket(15000);
        Socket sockst = serverSocket.accept();
        OutputStream out = sockst.getOutputStream();
        Socket sock = serverSocket.accept();
        InputStream is = sock.getInputStream();
        InputStreamReader in = new InputStreamReader(is);
        OutputStreamWriter on = new OutputStreamWriter(out);
        BufferedReader br = new BufferedReader(in);
        BufferedWriter write = new BufferedWriter(on);

        String number;
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });

        boolean send = false;
        while(true) {
            if(send==false){
         //   number = br.readLine();
             //   if(number.equals("connect")){
                    transferFromClientToServer("123.mp3");
               // }
            }

            if(send==true){
                write.write("connect");
                write.flush();
                transferfromserverToClient("clientFile.mp3");
            }


        }



    }

    }

