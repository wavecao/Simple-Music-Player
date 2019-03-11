package Homework.Controller.MainWindow;




import java.io.File;


import java.net.URL;

import javax.media.Manager;
import javax.media.Player;

/**
 * Package: cn.nc.ncu.softwareproject.caowei.Test
 * Author: caowei
 * Date: 15:05 2019/1/3
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */


public class SimpleAudioPlayer {
    Player player = null;
    public void PlayMusic(String string)throws Exception{
        File file = new File(string);
        URL url = file.toURL();
        player = Manager.createRealizedPlayer(url);
        player.start();
    }
    public void stop(){
        player.stop();
    }
    public void play(){
        player.start();
    }
}
