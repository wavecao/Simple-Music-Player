package Homework.Controller.SearchWindow;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Package: Homework.Controller.SearchWindow
 * Author: caowei
 * Date: 22:51 2018/12/27
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class Music_Model{
    private final IntegerProperty songid;
    private final StringProperty songname;
    private final StringProperty singername;
    private final StringProperty albumname;

    public Music_Model(){
        this(0,null,null,null);
    }
    public Music_Model(int songid,String songname, String singername, String albumname){
        this.songid = new SimpleIntegerProperty(songid);
        this.songname = new SimpleStringProperty(songname);
        this.singername = new SimpleStringProperty(singername);
        this.albumname = new SimpleStringProperty(albumname);
    }

    public int getSongid() {
        return songid.get();
    }

    public IntegerProperty songidProperty() {
        return songid;
    }

    public void setSongid(int songid) {
        this.songid.set(songid);
    }

    public String getSongname() {
        return songname.get();
    }

    public StringProperty songnameProperty() {
        return songname;
    }

    public void setSongname(String songname) {
        this.songname.set(songname);
    }

    public String getSingername() {
        return singername.get();
    }

    public StringProperty singernameProperty() {
        return singername;
    }

    public void setSingername(String singername) {
        this.singername.set(singername);
    }

    public String getAlbumname() {
        return albumname.get();
    }

    public StringProperty albumnameProperty() {
        return albumname;
    }

    public void setAlbumname(String albumname) {
        this.albumname.set(albumname);
    }
}
