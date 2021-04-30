//Authors:	name				GitHub usr
//			William Wells		wiwells
//			Samuel Pilato		sampleauto
//			Jacob Forney		jakexclone
//			Ravinder Chaupain	vnm303
//			Jasmine Beale		jnbeale
//Class: Application Programming 3443
//Section: 001
package application;

import java.io.IOException;

import application.model.MVCPlanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransportationController {

    @FXML
    private Button costname;

    @FXML
    private Button submit;
    
    @FXML
    private Button submit2;
    
    @FXML
    private Button submit3;
    
    @FXML
    private ComboBox<String> meansofTravel;
    
    @FXML
    private ComboBox<String> initialTravel;

    @FXML
    private TextField costText;

    @FXML
    private Button home;
    
    @FXML
    private Button clearbutton;
    
    @FXML
    private ListView<String> getTherelist;
    
    @FXML
    private ListView<String> meansList;
    
    @FXML
    private ListView<String> moneyList;
    
    @FXML
    private ListView<String> infoView;
    
    
    public void initialize() {
    	MVCPlanner.initTransportation(initialTravel, meansofTravel, getTherelist);
    }
    
    @FXML
    void sendHome(ActionEvent event) throws IOException {
    	try {
            AnchorPane homeParent = (AnchorPane) FXMLLoader.load(getClass().getResource("VPMain.fxml"));
            Scene homeScene = new Scene(homeParent, 750, 750);
            homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
            //Get stage info
            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(homeScene);
            //set the title 
            window.setTitle("Vacation Planner");
            //display the scene
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    public void submitButton() throws IOException {
    	MVCPlanner.submitTransit(initialTravel, getTherelist, infoView);
    }
    
    @FXML
    public void submitButton2() throws IOException {
        MVCPlanner.submitVehicle(meansofTravel, meansList, infoView);
    }
    
    @FXML
    void submitButton3(ActionEvent event) throws IOException {
    	MVCPlanner.submitCost(costText, moneyList);
    }
    
    @FXML
    void clearProcess(ActionEvent event) throws IOException {
    	MVCPlanner.clearTransportation(getTherelist, moneyList, meansList, infoView);
    }
    	
}
