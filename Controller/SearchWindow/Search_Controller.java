package Homework.Controller.SearchWindow;

import Homework.Controller.JSON_Test.Internet_Search;
import Homework.Controller.JSON_Test.MusicContentList;
import Homework.Controller.MainWindow.EventTest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Package: Homework.Controller.SearchWindow
 * Author: caowei
 * Date: 23:05 2018/12/27
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class Search_Controller {
    @FXML
    TableView<Music_Model> MusicTable;

    @FXML
    TableColumn<Music_Model,Integer> idColumn;

    @FXML
    TableColumn<Music_Model,String> songnameColumn;

    @FXML
    TableColumn<Music_Model,String> singernameColumn;

    @FXML
    TableColumn<Music_Model,String> albumnameColumn;

    @FXML
    Button Close = new Button();

    /*存储数据*/
    private ObservableList<Music_Model> MusicData = FXCollections.observableArrayList();


    /**
     * 在FXML运行时自动运行初始化函数
     */
    @FXML
    public void initialize(){
        List<MusicContentList> lists = new ArrayList<>();
        Internet_Search internet_search = new Internet_Search(EventTest.strSearch);
        lists = internet_search.ParseJsonWithGson(lists,internet_search.JsonAnalyze(EventTest.strSearch));
        /*指定每一列数据的来源*/
        idColumn.setCellValueFactory(cellData->cellData.getValue().songidProperty().asObject());
        songnameColumn.setCellValueFactory(cellData->cellData.getValue().songnameProperty());
        singernameColumn.setCellValueFactory(cellData->cellData.getValue().singernameProperty());
        albumnameColumn.setCellValueFactory(cellData->cellData.getValue().albumnameProperty());
        /*将数据和TableView绑定*/
        MusicTable.setItems(MusicData);
        /*循环为MusicData添加数据*/
        for(int i=0; i<20; i++){
            MusicData.add((new Music_Model(i+1,lists.get(i).getSongname(),lists.get(i).getSingername(),lists.get(i).getAlbumname())));
        }
    }
    //https://y.qq.com/portal/search.html#page=1&searchid=1&remoteplace=txt.yqq.top&t=song&w=
    @FXML
    public void onClose(javafx.event.ActionEvent event) {
        Stage stage = (Stage)Close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void onInternetRes(ActionEvent event) {
        try{
            Desktop.getDesktop().browse(new URI("https://y.qq.com/portal/search.html#page=1&searchid=1&remoteplace=txt.yqq.top&t=song&w=" + EventTest.strSearch));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
