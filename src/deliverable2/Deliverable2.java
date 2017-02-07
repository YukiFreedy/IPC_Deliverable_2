/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable2;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author Yuki
 */
public class Deliverable2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader myLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml"));
        Parent root = (Parent) myLoader.load();
        MainWindowController dcCon = myLoader.<MainWindowController>getController();
        
        Scene scene = new Scene(root);
        dcCon.initData(dcCon);
        stage.setScene(scene);
        stage.setTitle("Mountain");
        stage.setMinWidth(860);
        stage.setMinHeight(622);
        stage.getIcons().add(new Image("/deliverable2/logo.png")); 
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
