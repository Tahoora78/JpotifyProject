import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

    static ServerSocket sSocketUserName;

    static {
        try {
            sSocketUserName = new ServerSocket(12345);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ServerSocket sSocketIP;

    static {
        try {
            sSocketIP = new ServerSocket(12346);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static ServerSocket sSocketUser;

    static {
        try {
            sSocketUser = new ServerSocket(12347);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static Socket acceptName;
    static Socket acceptIP;
    static Socket acceptUser;
    ArrayList<File> DataBase;
    ArrayList<User> users;
    static ArrayList<InetAddress> IPs=new ArrayList<>();
    ArrayList<String> usernames=new ArrayList<>();

    private ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        LoginPage login=new LoginPage();
        login.setVisible(true);
        while (true){
            acceptIP=sSocketIP.accept();
            acceptName=sSocketUserName.accept();
            acceptIP=sSocketIP.accept();
            BufferedReader clientName = new BufferedReader(new
                    InputStreamReader(acceptName.getInputStream()));

        }


    }

}
