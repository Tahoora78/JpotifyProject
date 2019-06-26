import java.io.*;
import java.net.InetAddress;
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
        LoginPage login = new LoginPage();
        PlayList list = HomeFrame.user.getSharedPlayList();




        Socket sendName = new Socket("172.24.100.85", 12345);
        Socket sendIP = new Socket("172.24.100.85", 12346);
        OutputStream osname = sendName.getOutputStream();
        OutputStreamWriter oswname = new OutputStreamWriter(osname);
        BufferedWriter bwname = new BufferedWriter(oswname);
        bwname.write(HomeFrame.user.getName());
        bwname.flush();


        InputStream isname = sendName.getInputStream();
        InputStreamReader isrname = new InputStreamReader(isname);
        BufferedReader brname = new BufferedReader(isrname);
        String message = brname.readLine();
        if (message.equals("ok")) {
            sendName.close();
        }


        OutputStream osIP = sendName.getOutputStream();
        OutputStreamWriter oswIP = new OutputStreamWriter(osIP);
        BufferedWriter bwIP = new BufferedWriter(oswIP);
        bwname.write(InetAddress.getLocalHost().toString());
        bwname.flush();

        InputStream isIP = sendIP.getInputStream();
        InputStreamReader isrIP = new InputStreamReader(isIP);
        BufferedReader brIP = new BufferedReader(isrIP);
        String IP = brIP.readLine();
        if (IP.equals("ok")) {
            sendIP.close();
        }
    while(true){
        if (HomeFrame.flag) {

            // transfertoserver();
            HomeFrame.flag = false;

        }
        if (HomeFrame.flag2){

        //    transferfromserver(HomeFrame.needed);
            HomeFrame.flag2=false;

        }

        }




            }
    }



