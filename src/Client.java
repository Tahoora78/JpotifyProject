import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {



    public static void transferfromserver(String fileName) throws IOException {
        Socket sock = new Socket("172.24.100.85", 15064);
        InputStream in = sock.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        byte [] buffer = new byte[64*1024];
        int bytesRead = 0;

        while ( (bytesRead = in.read(buffer)) != -1)
            fileOutputStream.write(buffer, 0, bytesRead);
        sock.close();
        fileOutputStream.close();
    }




    public void transfertoserver(File file){}









    public static void main(String[] args) throws IOException {
        LoginPage login = new LoginPage();
        PlayList list = HomePage.user.getSharedPlayList();




        Socket sendName = new Socket("172.24.100.85", 12345);
        Socket sendIP = new Socket("172.24.100.85", 12346);
        OutputStream osname = sendName.getOutputStream();
        OutputStreamWriter oswname = new OutputStreamWriter(osname);
        BufferedWriter bwname = new BufferedWriter(oswname);
        bwname.write(HomePage.user.getName());
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
        if (HomePage.flag) {

//            transfertoserver();
            HomePage.flag = false;

        }
        if (HomePage.flag2){

            transferfromserver(HomePage.needed);
            HomePage.flag2=false;

        }

        }




            }
    }


