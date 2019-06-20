import java.util.ArrayList;

public class User {
    private String name;
    private int ID;
    private int IP;
    private int Password;
    private ArrayList<User> friends;
    private PlayList playList=new PlayList("Shared PlayList");

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIP() {
        return IP;
    }

    public void setIP(int IP) {
        this.IP = IP;
    }

    public int getPassword() {
        return Password;
    }

    public void setPassword(int password) {
        Password = password;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user){
        friends.add(user);
    }
    public void addMusic(Music music){
        this.playList.addSong(music);
    }
}
