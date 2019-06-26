import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{
    private String name;
    private int ID;
    private int IP;
    private int Password;
    private ArrayList<User> friends;
    private PlayList sharedPlayList=new PlayList("Shared PlayList");
    private Long Ltime;
    private Music lastMusic;
    private PlayList currenPlayList;
    private Album currentAlbum;
    boolean playing=false;
    private PlayList favoritePlayList = new PlayList("favorite PLayList");
    private ArrayList<PlayList> playLists  = new ArrayList<>();
    private ArrayList<Music> songs = new ArrayList<>();
    private ArrayList<Album> albums =new ArrayList<>();
    public static ArrayList<String> friandsName;
    

    private int songNum;

    

    public void addFriend(User friendName){
        friends.add(friendName);
    
    }
    
    public ArrayList<User> getFriends(){
        return friends;
    }
    
    public PlayList getCurrenPlayList() {
        return currenPlayList;
    }

    public void setCurrenPlayList(PlayList currenPlayList) {
        this.currenPlayList = currenPlayList;
    }

    public Album getCurrentAlbum() {
        return currentAlbum;
    }

    public void setCurrentAlbum(Album currentAlbum) {
        this.currentAlbum = currentAlbum;
    }

    public ArrayList<Music> getSongs() {
        return songs;
    }

    public int getSongNum() {
        return songNum;
    }

    public void setSongNum(int songNum) {
        this.songNum = songNum;
    }

    public PlayList getFavoritePlayList() {
        return favoritePlayList;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

  public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }

    public PlayList getSharedPlayList() {
        return sharedPlayList;
    }
    
    public ArrayList<PlayList> getPlayLists() {
        return playLists;
    }
    
    public void setFavoritePlaylist(PlayList favoritePlayList){
        this.favoritePlayList = favoritePlayList;
    }

    public void setSharedPlaylist(PlayList sharedPlayList){
        this.sharedPlayList = sharedPlayList;
    }
    
    public void setPlaylists(ArrayList playLists){
        this.playLists = playLists;
    }
    
    
    public Long getTime() {
        return System.currentTimeMillis();
    }

    public String Difference(User user) throws InvalidDataException, IOException, UnsupportedTagException {
        long userTime=user.getTime();
        long dif=System.currentTimeMillis()-userTime;
        int diff= (int) (dif/10000);
        if(dif <=user.getLastMusic().getTime()*1000){
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

    public Music getLastMusic() {
        return lastMusic;
    }

    public void setLastMusic(Music lastMusic) {
        this.lastMusic = lastMusic;
    }

    public void setSharedPlayList(PlayList sharedPlayList) {
        this.sharedPlayList = sharedPlayList;
    }

    public Long getLtime() {
        return Ltime;
    }

    public void setLtime(Long ltime) {
        Ltime = ltime;
    }

    public void setSongs(ArrayList<Music> songs) {
        this.songs = songs;
    }



    public User(String name) {
        this.playLists.add(favoritePlayList);
        this.playLists.add(sharedPlayList);
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

    
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

 
    public void addMusicToFavoritePlaylist(Music music){
        this.favoritePlayList.addSong(music);
    }

    public void addMusicToSharedPlayList(Music music){
        this.sharedPlayList.addSong(music);
    }
    
    public void addMusicToPlaylists(Music music,String name){


        for (PlayList pl:playLists){
            if (pl.getTitle().equals(name))
                pl.addSong(music);
        }
    }
    
    
    public String giveActivity(User user){
 /*
        if (this.friends.contains(user)){
            if (user.getp.getPlayList().getSongs().contains(user.getMusic())){
                if (user.isPlaying())
                return user.getName()+"\n"+"Playing..."+"\n"+user.getMusic().getTitle();

                else return user.getName()+"\n"+Difference(user)+" ago" +"\n"+user.getMusic().getTitle();
            }
        }


        return null;
    }
*/
 
    String activity = user.getName()+"/n"+"Playing..."+"\n"+user.lastMusic.getTitle()+"time";
    
 return activity;
 
 
    }
    public boolean isPlaying() {
        return playing;
    }

    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
}
