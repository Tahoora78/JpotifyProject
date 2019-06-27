import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
    
     public static  Socket socket;
        public static ObjectOutputStream objectOutputStream;
        public static ObjectInputStream objectInputStream;
       OutputStream out ;
        OutputStreamWriter wr;
        BufferedWriter write ;
        public Socket getSocket(){
        return socket;
        }
   public static  void transferFromClientToServer1(String addressFile,String Ip) throws IOException{
        int port = 15000;
        Socket sr = new Socket(Ip,port);
        System.out.println("true");
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

     
}

    public static void transferfromserverToClient1(String fileName,String ip) throws IOException {
        Socket sock = new Socket(ip, 15000);
        InputStream in = sock.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        byte [] buffer = new byte[64*1024];
        int bytesRead = 0;

        while ( (bytesRead = in.read(buffer)) != -1)
            fileOutputStream.write(buffer, 0, bytesRead);
        sock.close();
        fileOutputStream.close();

    }

    public static SaveFriend deserializeFriend(String file){


        SaveFriend fr = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
            fr = (SaveFriend) is.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("finish deserializing");
        return fr;
    }

    public static void refreshRecieve(SaveFriend savFriend) throws IOException{
            InputStream in = socket.getInputStream();
         SaveFriend sf=savFriend;
         InputStreamReader rd = new InputStreamReader(in);
        BufferedReader brd = new BufferedReader(rd);
        String s1 = brd.readLine();
        if(s1.equals("connect")) {
            transferfromserverToClient1(savFriend.getName().concat(".bin"), savFriend.Ip);

            SaveFriend nsf = deserializeFriend(savFriend.getName().concat(".bin"));

            HomePage.user.addFriend(nsf);
        }
        }
       // public static void serializedFriend(String HomePage.getU){}
    public static void refreshSend(SaveFriend savFriend) throws IOException {
        OutputStream out = socket.getOutputStream();
        SaveFriend sf=savFriend;
        OutputStreamWriter wr = new OutputStreamWriter(out);
        BufferedWriter write = new BufferedWriter(wr);
        write.write("connect");
        write.flush();

        transferFromClientToServer1(savFriend.getName().concat(".bin"), savFriend.Ip);

        SaveFriend nsf = deserializeFriend(savFriend.getName().concat(".bin"));

        HomePage.user.addFriend(nsf);

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
        //while(true) {
        new Thread(){
                @Override
                public void run() {
                    try {
                        ServerSocket serverSocket = new ServerSocket(8080);
                        while (true){
                            LoginPage loginPage = new LoginPage();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }.start();


        //String number;
        //boolean send = true;
/*       

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
*/
    }

    }
