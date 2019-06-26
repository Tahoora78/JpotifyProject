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
    public  PlayList sharedPlayList;
    public String nameOfLastMusic;
    public int timePassed;
    public String name;
    public SaveFriend(PlayList sharedPlayList,String nameOfLastMusic,int timePassed,String name){
        this.name = name;
        this.nameOfLastMusic = nameOfLastMusic;
        this.timePassed = timePassed;
        this.sharedPlayList = sharedPlayList;
        
    }
    
    
}
