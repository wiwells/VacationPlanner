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

import application.model.MVCPlanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class VPController {
	
    @FXML
    void transportation(ActionEvent event) {
    	
    }

    @FXML
    void location(ActionEvent event) {
    	try {
            AnchorPane homeParent = (AnchorPane) FXMLLoader.load(getClass().getResource("Location.fxml"));
            Scene homeScene = new Scene(homeParent, 750, 750);
            homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //Get stage info
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            window.setResizable(false);
            //set the title 
            window.setTitle("Location");
            //display the scene
            window.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void checklist(ActionEvent event) {

    }

    @FXML
    void activities(ActionEvent event) {

    }
}
