
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class userTest implements Serializable{
   private String name;
    private int ID;
    private int IP;
    private int Password;
    private ArrayList<User> friends;
    private PlayList sharedPlayList=new PlayList("Shared PlayList");
    private Long time;
    private Music music;
    private PlayList currenPlayList;
    private Album currentAlbum;
    boolean playing=false;
    private PlayList favoritePlayList = new PlayList("favorite PLayList");
    private ArrayList<PlayList> playLists  = new ArrayList<>();
    private ArrayList<Music> songs = new ArrayList<>();
    private ArrayList<Album> albums =new ArrayList<>();


    private int songNum;


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

    public void setSongs(ArrayList<Music> songs) {
        this.songs = songs;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public userTest(String name) {
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

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public void addFriend(User user){
        friends.add(user);
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
    
    
 /*   public String giveActivity(User user){
        if (this.friends.contains(user)){
            if (user.getPlayList().getSongs().contains(user.getMusic())){
                if (user.isPlaying())
                return user.getName()+"\n"+"Playing..."+"\n"+user.getMusic().getTitle();

                else return user.getName()+"\n"+Difference(user)+" ago" +"\n"+user.getMusic().getTitle();
            }
        }


        return null;
    }
*/
    public boolean isPlaying() {
        return playing;
    }
    
    
    
     public static void updateUser(userTest user){
          System.out.println("update start");
          new File(user.getName().concat(".bin")).delete();
          try {
              String g = user.getName().concat(".bin");
              System.out.println("g"+g);
              ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(g));
              os.writeObject(user);
              os.close();
          }catch(FileNotFoundException e){
              e.printStackTrace();
          }catch(IOException e){
              e.printStackTrace();
          }
          System.out.println("update finished");
      }

     
      public void serializeUser(userTest user){
       System.out.println("name"+user.getName());
       try{
           ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(user.getName().concat(".bin")));
           os.writeObject(user);
           os.close();
       }catch(FileNotFoundException e){
           System.out.println("nnnnnnnnnnnnnnnnnn");
           e.printStackTrace();
       }
       catch(IOException e){
           e.printStackTrace();
       }
   }
    
     
     public static userTest deserializeUser(String name) throws FileNotFoundException{
        userTest userd = null;
        System.out.println("name"+name);
        try{
            ObjectInputStream is = new ObjectInputStream(new FileInputStream(name.concat(".bin")));
            userd = (userTest)is.readObject();
        }
        catch(FileNotFoundException e ){
            e.printStackTrace();
        }catch(IOException e){
        e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        System.out.println("finish deserializing");
     return userd;   
    }
    
      
      
      
    public void setPlaying(boolean playing) {
        this.playing = playing;
    }
    public static void main(String []args){
       try {
           userTest t = new userTest("tahoors");
           t.setID(0);
           updateUser(t);
           System.out.println("kkkkkkkkkkkkkkkkk"+t.getName());
           t.Password = 123;
           t.songNum=8;
           
           userTest o = (userTest)deserializeUser("tahoors");
           System.out.println("kkkkkkkkkkkkkkkkk"+t.getName());
           System.out.println("id"+o.getID());
           o.setID(9);
           updateUser(o);
            userTest oy = (userTest)deserializeUser("tahoors");
             System.out.println("kkkkkkkkkkkkkkkkk"+oy.getID());
       } catch (FileNotFoundException ex) {
           Logger.getLogger(userTest.class.getName()).log(Level.SEVERE, null, ex);
       }
    
       
    }
    
    
    
    
    
    
}
    
















