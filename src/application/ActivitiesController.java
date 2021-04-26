package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ActivitiesController {
@FXML
private Button toSelect;
@FXML
public ComboBox<String> environment_select;
@FXML
private ComboBox<String> activity_select;
@FXML Label comment1;
@FXML
private ImageView environment1;
/*Buttons*/
/*Add Buttons*/
@FXML
private Button add1;
private Button add2;
private Button add3;
private Button add4;
private Button add5;
@FXML
/*home button*/
private Button homebtn;
private Button savbtn;
private Stage myStage;
String type = "city";

/*file and image urls*/
File city_file = new File("src/application/city.jpg");
Image city_img = new Image(city_file.toURI().toString());
File beach_file = new File("src/application/beach_2.jpg");
Image beach_img = new Image(beach_file.toURI().toString());
File Resort_file = new File("src/application/resort.jpg");
Image Resort_img = new Image(Resort_file.toURI().toString());
File Camping_file = new File("src/application/camp.jpg");
Image Camping_img = new Image(Camping_file.toURI().toString());
File Cruise_file = new File("src/application/cruise.jpg");
Image Cruise_img = new Image(Cruise_file.toURI().toString());



/*settings for activities*/
ObservableList<String> settings = 
FXCollections.observableArrayList("City","Beach","Resort","Camping","Cruise","Other");
/*SPORTS
 * 
 * city_sports
 * beach_sports
 * camp_sports
 * cruise_sports
 * resort_sport
 * 
 * 
 * */
ObservableList<String> city_sports = 
FXCollections.observableArrayList("bowling","skateboarding","basketball");
ObservableList<String> beach_sports = 
FXCollections.observableArrayList("volleyball","surfing","jet skiing");
ObservableList<String> camp_sports = 
FXCollections.observableArrayList("fishing","cannoing","hiking");
ObservableList<String> cruise_sports = 
FXCollections.observableArrayList("table tennis","minigolf","shuffle boarding");
ObservableList<String> resort_sports = 
FXCollections.observableArrayList("golfing","archery","frisbee");


@FXML
/*initialize values of drop down menus*/
public void initialize() {
    	//combobox starting text
    	environment_select.setValue("Select a setting");
    	activity_select.setValue("Select a sport");
    	//display options as drop down
    	environment_select.setItems(settings);
    	//environment_select.getValue();
    	
    }
public String update()
{
	environment1.setFitHeight(500);
	environment1.setFitWidth(300);
	
	if(environment_select.getValue()=="City"){
	      comment1.setText("Nothing like a walk\nthrough the city");
	      environment1.setImage(city_img);
	      activity_select.setItems(city_sports);
	}
	if(environment_select.getValue()=="Beach"){
	      comment1.setText("Ooo let's make sand castles");
	      environment1.setImage(beach_img);
	      activity_select.setItems(beach_sports);
	}
	if(environment_select.getValue()=="Resort"){
	      comment1.setText("I could really go for a massage\nright about now...");
	      environment1.setImage(Resort_img);
	      activity_select.setItems(resort_sports);
	}
	if(environment_select.getValue()=="Camping"){
	      comment1.setText("Gotta love the great outdoors");
	      environment1.setImage(Camping_img);
	      activity_select.setItems(camp_sports);
	}
	if(environment_select.getValue()=="Road Trip"){
	      comment1.setText("Hope you have a good playlist");
	      environment1.setImage(beach_img);
	      activity_select.setItems(beach_sports);
	}
	if(environment_select.getValue()=="Cruise"){
	      comment1.setText("Love the sound of waves");
	      environment1.setImage(Cruise_img);
	      activity_select.setItems(cruise_sports);
	}
	if(environment_select.getValue()=="Other"){
	      comment1.setText("It's okay, I won't tell!");
	}
	
	return environment_select.getValue().toString();
}
@SuppressWarnings("resource")
@FXML
public List<String> save() throws IOException
{
	  Alert a = new Alert(AlertType.NONE);
      a.setAlertType(AlertType.ERROR);
      a.setTitle("Activity Exist");
      a.setContentText("You already added this to your activity list");
      
	   File file = new File("src/application/ActivityList.txt");
	   ArrayList<String> arrlist = new ArrayList<String>(5);
	   String value = activity_select.getValue().toString();
	   
	   int datList = arrlist.size();
	   Scanner s = new Scanner(file);
	   FileInputStream input = new FileInputStream(file);
       
       FileWriter fileWriter = new FileWriter(file, true);
       BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
       try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
			String line = bufReader.readLine();
			while (line != null) {
				// read next line
				arrlist.add(line);
				line = bufReader.readLine();
				
			}
			bufReader.close();
			if(!arrlist.contains(value))
			{
				arrlist.add(value);
				fileWriter.write(value+"\n");
			    fileWriter.close();
			}
			else
			{
				a.show();
				activity_select.setValue("Select a sport");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	   System.out.println(arrlist);
	return arrlist;
	
}
@FXML
void home(ActionEvent event) {
    try {
        AnchorPane homeParent = (AnchorPane) FXMLLoader.load(getClass().getResource("VPMain.fxml"));
        Scene homeScene = new Scene(homeParent, 750, 750);
        homeScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        //Get stage info
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(homeScene);
        //set the title 
        window.setTitle("Vocation Planner");
        //display the scene
        window.show();
    } catch (Exception e) {
        e.printStackTrace();
    }



}




}
