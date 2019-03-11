package Homework.Controller.MainWindow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Package: Homework.vision.Controller
 * Author: caowei
 * Date: 10:52 2018/12/13
 * CopyRight: NanChang University Software Project School caowei
 * Description:
 */
public class MyPlayer extends Application{

    @Override
    public void start(final Stage primaryStage) throws Exception{
        Parent parent = FXMLLoader.load(getClass().getResource("../../vision/music.fxml"));
        Scene scene = new Scene(parent,974,602);
        //去除系统原有标题栏
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.getIcons().add(new Image("Homework/vision/Image/Search-icon.jpg"));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String args[]){
        Application.launch(MyPlayer.class,args);
    }
}


