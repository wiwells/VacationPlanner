//Authors:	name				GitHub usr
//			William Wells		wiwells
//			Samuel Pilato		sampleauto
//			Jacob Forney		jakexclone
//			Ravinder Chaupain	vnm303
//			Jasmine Beale		jnbeale
//Class: Application Programming 3443
//Section: 001
//Date Created: 4/19/2021

package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.MVCPlanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXML;


public class VPController {
    
    @FXML
    void transportation(ActionEvent event) {
    	
    }

    @FXML
    void location(ActionEvent event) {

    }

    @FXML
    void checklist(ActionEvent event) throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("Checklist.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(scene);
		window.show();
    }

    @FXML
    void activities(ActionEvent event) {
            try {
                AnchorPane homeParent = (AnchorPane) FXMLLoader.load(getClass().getResource("ActivitiesMain.fxml"));
                Scene homeScene = new Scene(homeParent, 750, 750);
                homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
                //Get stage info
                Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
                window.setScene(homeScene);
                window.setResizable(false);
                //set the title 
                window.setTitle("Activity Select");
                //display the scene
                window.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        

    }
    
}
