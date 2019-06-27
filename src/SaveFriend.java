/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Home pc
 */
public class SaveFriend {
    public static PlayList sharedPlayList;
    public static String nameOfLastMusic;
    public long  timePassed;
    public static String name;
    public static String Ip;
    public SaveFriend(PlayList sharedPlayList, String nameOfLastMusic, Long timePassed, String name, String Ip){
        this.name = name;
        this.nameOfLastMusic = nameOfLastMusic;
        this.timePassed = timePassed;
        this.sharedPlayList = sharedPlayList;
        this.Ip = Ip;
    }
    public static String getIp(){
    return Ip;
    }
    public static String getName(){
        return name;
    
    }
    
    
}
