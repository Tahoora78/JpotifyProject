import java.util.ArrayList;

public class User {
    private String name;
    private int ID;
    private int IP;
    private int Password;
    private ArrayList<User> friends;
    private PlayList playList=new PlayList("Shared PlayList");
    private Long time;
    private Music music;
    boolean playing=false;

    public PlayList getPlayList() {
        return playList;
    }

    public void setPlayList(PlayList playList) {
        this.playList = playList;
    }

    public Long getTime() {
        return System.currentTimeMillis();
    }

    public String Difference(User user){
        long userTime=user.getTime();
        long dif=System.currentTimeMillis()-userTime;
        int diff= (int) (dif/10000);
        if(dif <=user.getMusic().getTime()*1000){
            playing=true;
            return "Playing...";
        }

        playing=false;
        int day=(int) (diff
                / 86400);
        int hour= (int) (dif/3600000);
        int min= (int) (dif/60000);
        int sec= (int) (dif/1000);

        if(day==0){
            if(hour==0){
                if (min==0){
                    return ""+sec;
                }
                else return ""+min;
            }
            else return ""+hour;

        }
        else return ""+day;


    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    public void setTime(Long time) {
        this.time = time;
    }

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

    public String giveActivity(User user){
        if (this.friends.contains(user)){
            if (user.getPlayList().getSongs().contains(user.getMusic())){
                if (user.isPlaying())
                return user.getName()+"\n"+"Playing..."+"\n"+user.getMusic().getTitle();

                else return user.getName()+"\n"+Difference(user)+" ago" +"\n"+user.getMusic().getTitle();
            }
        }


        return null;
    }

    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
