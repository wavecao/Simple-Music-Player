package Homework.Controller.MainWindow;

import Homework.Controller.JSON_Test.Internet_Search;
import Homework.Controller.JSON_Test.MusicContentList;
import Homework.Controller.SearchWindow.Search_Main;
import Homework.Controller.SearchWindow.Search_Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.*;


/**
 * Package: Homework.Controller
 * Author: caowei
 * Date: 9:00 2018/12/14
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class EventTest{

    /*记录要播放的歌曲的名字*/
    public static String flag = "";

    /*默认的音乐文件目录*/
    public static String path ="C://CloudMusic//";

    /*记录播放器的状态,用于控制播放和暂停*/
    private Boolean PlayFlag = false;

    /*文件选择器*/
    private FileChooser chooser = new FileChooser();

    /*搜索关键字*/
    public static String strSearch;

    private SimpleAudioPlayer simpleAudioPlayer = new SimpleAudioPlayer();

    public String getPath() {
        return path;
    }


    /*底栏栏的信息显示*/
    @FXML
            Label Infor;

    /*歌单的实现*/
    @FXML
            private ListView songlist;

    /*搜索框*/
    @FXML
            public  TextField SearchText;

    /*喜欢列表*/
    @FXML
            private ListView Lovelist;

    /**
     * 12.20:判断数组中是否有重复元素,此函数也许可以防止歌单重复
     * 12.22晚:现在看来他好像不行,虽然我也不知道怎么搞定的歌单重复问题...
     */
    public boolean isRepeat(String[] strings,String str){
        for (String s : strings){
            if (!(s.equals(str))){
                return true;
            }
        }
        return false;
    }

    /**
     * 从文件中读取喜欢列表,以防重复采用Set集合存储之后再传值给String数组
     * @return
     */
    public String[] ReadLoveList(){
        File ReadFile = new File("C:\\Users\\23537\\IdeaProjects\\Java Code\\src\\Homework\\Libs\\LoveList.txt");
        Set<String> arrayList = new TreeSet<String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(ReadFile));
            String str;
            while ((str = in.readLine()) != null) {
                arrayList.add(str);
            }
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        String[] likelist = new String[arrayList.size()];
        Iterator<String> iterator = arrayList.iterator();
        for (int i=0; i<arrayList.size(); i++){

            String s = iterator.next();
            likelist[i] = s;
        }
        return likelist;
    }

    /**
     * 将path中所有音乐的名字存入字符串数组
     * @param path
     * @return
     * @throws Exception
     */
    public String[] getmusic(String path){
        File rootDir = new File(path);
        String[] fileList = rootDir.list();
        if (!rootDir.isDirectory()){
            String type = "mp3";
            for (int i=0; i<fileList.length; i++){
                if (fileList[i].contains(type)) {
                    fileList[i] = fileList[i].substring(0,rootDir.getName().lastIndexOf(".")); //将文件名.MP3前面的部分存入filelist
                }else {
                    fileList[i] = null;
                }
            }
        }
        return fileList;
    }

    @FXML
    public void initialize(){
        ObservableList<String> dataList = FXCollections.observableArrayList();
        ObservableList<String> likeList = FXCollections.observableArrayList();
        try{
            String[] music = getmusic(this.path);
            String[] lovelist = ReadLoveList();
            for (int i=0; i<music.length; i++){
                if (this.isRepeat(music,music[i])){
                    dataList.add(music[i]);
                    songlist.setItems(dataList);
                }
            }
            for (int j=0; j<lovelist.length; j++){
                likeList.add(lovelist[j]);
                Lovelist.setItems(likeList);
            }

        }catch (Exception e1){
            System.out.println("刷新列表失败!");
        }
    }

    /**
     * 强制性结束程序
     * @param event
     */
    @FXML
    public void onWinClose(ActionEvent event){
        System.exit(0);
    }

    /**
     * 让用户通过弹出的窗口选择音乐来源
     *
     * @throws Exception
     */
    @FXML
    public void onDirChoose(ActionEvent event) throws Exception{
        File file2;
        try{
            chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("MP3文件","*.mp3"));
            file2=chooser.showOpenDialog(null);
            String flag = file2.getParent();
            System.out.println(flag);
            this.onDirRefresh(event);
            System.out.println(path);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 通过刷新来获取文件夹中新增加的歌曲
     */
    @FXML
    public void onDirRefresh(ActionEvent e){
        ObservableList<String> dataList = FXCollections.observableArrayList();
        ObservableList<String> likeList = FXCollections.observableArrayList();
        try{
            String[] music = getmusic(this.path);
            String[] lovelist = ReadLoveList();
            for (int i=0; i<music.length; i++){
                if (this.isRepeat(music,music[i])){
                    dataList.add(music[i]);
                    songlist.setItems(dataList);
                }
            }
            for (int j=0; j<lovelist.length; j++){
                likeList.add(lovelist[j]);
                Lovelist.setItems(likeList);
            }
        }catch (Exception e1){
            System.out.println("刷新列表失败!");
        }
    }

    /**
     * Listview的鼠标点击事件:双击播放(通过player_thread线程)
     * @param event
     * @throws Exception
     */
    @FXML
    public void MouseItem(MouseEvent event) throws Exception{
                if (event.getClickCount() == 2){
                    this.flag = songlist.getSelectionModel().getSelectedItems().toString();
                    this.flag = this.flag.substring(1,flag.length()-1);
                    Infor.setText("正在播放:"+flag);
                    if (PlayFlag == false){
                        simpleAudioPlayer.PlayMusic(getPath()+ "\\" + flag);
                        PlayFlag = true;
                    }else {
                        simpleAudioPlayer.stop();
                        simpleAudioPlayer.PlayMusic(getPath()+ "\\" + flag);
                        PlayFlag = true;
                    }

                }
            }
    /**
     * SimpleAudioPlayer类实现,通过判断全局flag实现暂停和播放
     * @param event
     */
    @FXML
    public void OnPauseClick(ActionEvent event){

        try{
            if (PlayFlag == true){
                simpleAudioPlayer.stop();
                PlayFlag = false;
                Infor.setText("已经暂停:"+flag);
            }else {
                simpleAudioPlayer.play();
                PlayFlag = true;
                Infor.setText("正在播放:"+flag);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * 根据用户输入的文本进行网络搜索,搜索结果在新窗口显示
     * @param e
     */
    @FXML
    public void OnSearchClick(ActionEvent e){
        strSearch = SearchText.getText();
        List<MusicContentList> lists = new ArrayList<>();
        Internet_Search internet_search = new Internet_Search(EventTest.strSearch);
        if ((lists = internet_search.ParseJsonWithGson(lists,internet_search.JsonAnalyze(EventTest.strSearch))).size() == 0){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("接口故障提醒");
            alert.setHeaderText("接口出现故障,请搜索其他关键词!");
            alert.setContentText("是否退出?");
            alert.showAndWait();
        }else {
            Search_Main search_main = new Search_Main();
            search_main.start(new Stage());
            Search_Controller search_controller = new Search_Controller();
        }
    }

    /**
     * 调用vbs脚本控制声音的加,vbs脚本目录过长将会导致异常,因此放在"文档"文件夹下
     * @param event
     * @throws Exception
     */
    @FXML
    public void onVolumeUp(ActionEvent event) throws Exception {
        File vbsFile = new File("C:\\Users\\23537\\Documents\\Volume_Up.vbs");
        String vbsPath ="cmd /c wscript " + vbsFile.getAbsolutePath();
        try{
            Process	process = Runtime.getRuntime().exec(vbsPath);
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 调用vbs脚本控制声音的加,vbs脚本目录过长将会导致异常,因此放在"文档"文件夹下
     * @param event
     * @throws Exception
     */
    @FXML
    public void onVolumeDown(ActionEvent event) throws Exception {
        File vbsFile = new File("C:\\Users\\23537\\Documents\\Volume_Down.vbs");
        String vbsPath ="cmd /c wscript " + vbsFile.getAbsolutePath();
        try{
            Process	process = Runtime.getRuntime().exec(vbsPath);
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 点击按钮即可把歌曲添加至喜欢的列表,列表内歌曲采用文件读写的方式存储
     * @param event
     */
    @FXML
    public void onAddToLove(ActionEvent event) throws Exception{
        File AddList = new File("C:\\Users\\23537\\IdeaProjects\\Java Code\\src\\Homework\\Libs\\LoveList.txt");
        if (AddList.exists()){
            String songname = this.flag;
            FileWriter out = new FileWriter(AddList,true);
            out.write(songname);
            out.write("\n");
            out.close();
        }else{
            try{
                AddList.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
                System.out.println("呦吼,文件创建失败了!");
            }

        }
    }

    /**
     * 切换上一首
     * @param event
     */
    @FXML
    public void onPreMusic(ActionEvent event) {
        String[] MusicList = getmusic(this.getPath());
        int index = 0;
        if (PlayFlag == true){
            for (int i=0; i<MusicList.length; i++){
                if (flag.equalsIgnoreCase(MusicList[i])){
                    if (i == 0){
                        index = MusicList.length-1;
                    }else {
                        index = i-1;
                    }
                }
            }
            String MusicPre = MusicList[index];
            flag = MusicPre;
            simpleAudioPlayer.stop();
            try{
                simpleAudioPlayer.PlayMusic(getPath()+MusicPre);
                PlayFlag = true;
                Infor.setText("正在播放:"+MusicPre);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("切换上一首出现故障!");
            }
        }else {
            return;
        }
    }

    /**
     * 切换下一首
     * @param event
     */
    @FXML
    public void onNextMusic(ActionEvent event) {
        String[] MusicList = getmusic(this.getPath());
        int index = 0;
        if (PlayFlag == true){
            for (int i=0; i<MusicList.length; i++){
                if (flag.equalsIgnoreCase(MusicList[i])){
                    if (i == MusicList.length-1){
                        index = 0;
                    }else {
                        index = i+1;
                    }
                }
            }
            String MusicNext = MusicList[index];
            flag = MusicNext;
            simpleAudioPlayer.stop();
            try{
                simpleAudioPlayer.PlayMusic(getPath()+MusicNext);
                PlayFlag = true;
                Infor.setText("正在播放:"+flag);
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("切换上一首出现故障!");
            }
        }else {
            return;
        }
    }
}
