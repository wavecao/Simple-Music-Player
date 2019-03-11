package Homework.Controller.SearchWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Package: Homework.Controller.JSON_Test
 * Author: caowei
 * Date: 7:52 2018/12/28
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class Search_Main extends Application {
    @Override
    public void start(final Stage primaryStage){
        try {
            Parent parent = FXMLLoader.load(getClass().getResource("../../vision/SearchInfo.fxml"));
            Scene scene = new Scene(parent,930,400);
            primaryStage.setTitle("乐趣音乐搜索结果");
            primaryStage.getIcons().add(new Image("Homework/vision/Image/Search-icon.jpg"));
            primaryStage.setScene(scene);
            primaryStage.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String []args){
        Application.launch(Search_Main.class,args);
    }
}
