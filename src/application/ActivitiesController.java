package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Ellipse;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActivitiesController implements Initializable{
@FXML
private ComboBox<String> activitySelect;
@FXML
private ComboBox<String> categorySelect;
@FXML
private ComboBox<String> activityRemove;

@FXML
private TextArea customAct;
@FXML
private Text actTitle;
@FXML
private Label delMsg;
@FXML
private Label addMsg;
@FXML
private Label comment;

@FXML
private ListView<String> activityList;
@FXML
private ImageView parrot;
@FXML
private ImageView categoryImg;
@FXML
private ImageView textBubble;

@FXML
private Button add;
@FXML
private Button remove;
@FXML
private Button clear;
@FXML
private Button homebtn;


/*Image files and corresponding images*/
Image diningImg = new Image(new File("src/application/city.jpg").toURI().toString());
Image sightImg = new Image(new File("src/application/beach_2.jpg").toURI().toString());
Image sportImg = new Image(new File("src/application/resort.jpg").toURI().toString());
Image relaxImg = new Image(new File("src/application/camp.jpg").toURI().toString());
Image parrotImg = new Image(new File("src/application/parrot.png").toURI().toString());

ObservableList<String> categories = 
FXCollections.observableArrayList("dining",
		"sightsee",
		"sports",
		"relax",
		"other"
		);
ObservableList<String> dining= 
FXCollections.observableArrayList("bowling",
		"skateboarding",
		"basketball",
		"try street food"
		);
ObservableList<String> sightSeeing = 
FXCollections.observableArrayList("volleyball",
		"surfing",
		"jet skiing"
		);
ObservableList<String> sports = 
FXCollections.observableArrayList("fishing",
		"canoeing",
		"hiking"
		);
ObservableList<String> relax = 
FXCollections.observableArrayList("table tennis",
		"minigolf",
		"shuffle boarding"
		);
ObservableList<String> allAct= 
FXCollections.observableArrayList(
		"bowling",
		"skateboarding",
		"basketball",
		"try street food",
		"volleyball",
		"surfing",
		"jet skiing",
		"fishing",
		"cannoing",
		"hiking",
		"table tennis",
		"minigolf",
		"shuffle boarding",
		"golfing",
		"archery",
		"frisbee"
		);


/*initialize values of drop down menus*/
@SuppressWarnings("unchecked")
public void initialize(URL url, ResourceBundle rb) {
	ArrayList<String> arrlist2 = new ArrayList<String>();
	parrot.setImage(parrotImg);
	textBubble.setVisible(false);
	customAct.setVisible(false);
    categorySelect.setValue("Select a Category");
    activitySelect.setValue("Select an Activity");
    activityRemove.setValue("Select an Activity");
    categorySelect.setItems(categories);
    
    	try {
            FileInputStream input = new FileInputStream("src/application/ActivityList.txt");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
            String line = bufReader.readLine();
            while (line != null) {
               
                arrlist2.add(line);
                line = bufReader.readLine();

            }
            bufReader.close();
            int arrarySize = arrlist2.size();
            for (int i = 0; i < arrarySize; i++) {
                activityList.getItems().add(arrlist2.get(i));
            	}
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        } catch (IOException e) {
            e.printStackTrace();
        }
    	//activityRemove.setItems((ObservableList<String>) arrlist2);
    	ObservableList<String> fullList = FXCollections.observableList(arrlist2);
    	activityRemove.setItems(fullList);
    	
    }
@FXML
public String update()
{
	categoryImg.setFitHeight(347);
	categoryImg.setFitWidth(313);
	delMsg.setText("");
	addMsg.setText("");
	if(categorySelect.getValue()=="dining"){
	      comment.setText("Good I'm starving");
	      textBubble.setVisible(true);
	      actTitle.setText("Select an Activity");
	      categoryImg.setImage(diningImg);
	      categoryImg.setVisible(true);
	      activitySelect.setItems(dining);
	      activitySelect.setVisible(true);
	      customAct.setVisible(false);
	}
	if(categorySelect.getValue()=="sightsee"){
	      comment.setText("Let's go bird watching :)");
	      textBubble.setVisible(true);
	      actTitle.setText("Select an Activity");
	      categoryImg.setImage(sightImg);
	      categoryImg.setVisible(true);
	      activitySelect.setItems(sightSeeing);
	      activitySelect.setVisible(true);
	      customAct.setVisible(false);
	}
	if(categorySelect.getValue()=="sports"){
	      comment.setText("Gotta stretch out these\n old feathers");
	      textBubble.setVisible(true);
	      actTitle.setText("Select an Activity");
	      categoryImg.setImage(sportImg);
	      categoryImg.setVisible(true);
	      activitySelect.setItems(sports);
	      activitySelect.setVisible(true);
	      customAct.setVisible(false);
	}
	if(categorySelect.getValue()=="relax"){
	      comment.setText("Let's take squak");
	      textBubble.setVisible(true);
	      actTitle.setText("Select an Activity");
	      categoryImg.setImage(relaxImg);
	      categoryImg.setVisible(true);
	      activitySelect.setItems(relax);
	      activitySelect.setVisible(true);
	      customAct.setVisible(false);
	}
	if(categorySelect.getValue()=="other"){
	      comment.setText("Oh do tell!");
	      textBubble.setVisible(true);
	      actTitle.setText("Enter an Activity");
	      activitySelect.setItems(allAct);
	      categoryImg.setVisible(false);
	      activitySelect.setVisible(false);
	      customAct.setVisible(true);
	      activitySelect.setValue("Select an Activity");
	}
	
	return categorySelect.getValue().toString();
}
@FXML
public void save() throws IOException
{
	  Alert a = new Alert(AlertType.NONE);
      a.setAlertType(AlertType.ERROR);
      a.setTitle("Activity Exist");
      
      
	   File file = new File("src/application/ActivityList.txt");
	   ArrayList<String> arrlist = new ArrayList<String>();
	   String value;
	   String customVal = customAct.getText().trim();
	   value= activitySelect.getValue().toString();
	   FileInputStream input = new FileInputStream(file);
       
       FileWriter fileWriter = new FileWriter(file, true);
       delMsg.setText("");
       try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
			String line = bufReader.readLine();
			while (line != null) {
				arrlist.add(line);
				line = bufReader.readLine();
				
			}
			bufReader.close();
			if(!arrlist.contains(value))
			{
				if(value != "Select an Activity")
				{
				arrlist.add(value);
				fileWriter.write(value+"\n");
				if(value != "Select an Activity" && value != " "&&!activityList.getItems().contains(value))
			   	{
			   		activityList.getItems().add(value);
			   		addMsg.setText("added \""+value+"\" to list");
			   	}
				}
			}
			else
			{
				a.setContentText("You already added this to your activity list");
				a.show();
				addMsg.setText("");
			}
			if(!arrlist.contains(customVal))
			{
				
				if(!customVal.equals("")&&value == "Select an Activity")
				{
					arrlist.add(customVal);
					fileWriter.write(customVal+"\n");
					if(value != " "&&!activityList.getItems().contains(customVal))
				   	{
				   		activityList.getItems().add(customVal);
				   		addMsg.setText("added \""+customVal+"\" to list");
				   	}
				}
			}
			else
			{
				a.setContentText("You already added this to your activity list");
				a.show();
				addMsg.setText("");
			}
			fileWriter.close();
			activitySelect.setValue("Select an Activity");
			customAct.clear();
			ObservableList<String> fullList = FXCollections.observableList(arrlist);
	    	activityRemove.setItems(fullList);
		} catch (IOException e) {
			e.printStackTrace();
		}	
}
@FXML
void remove(ActionEvent event) throws IOException
{
	Alert a = new Alert(AlertType.NONE);
    a.setAlertType(AlertType.ERROR);
    a.setTitle("Activity Exist");
    
     
	   File file = new File("src/application/ActivityList.txt");
	   ArrayList<String> arrlist = new ArrayList<String>();
	   String value = "";
	   value= activityRemove.getValue().toString();
	   FileInputStream input = new FileInputStream(file);
	   try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
			String line = bufReader.readLine();
			while (line != null) {
				arrlist.add(line);
				line = bufReader.readLine();
				
			}
			bufReader.close();
			if(arrlist.contains(value))
			{
				if(value != "Select an Activity")
				{
				arrlist.remove(value);
				//fileWriter.write(value+"\n");
				if(value != "Select an Activity" && value != " "&&activityList.getItems().contains(value))
			   	{
			   		activityList.getItems().remove(value);
			   		delMsg.setText("removed \""+value+"\" from list");
			   		addMsg.setText("");
			   	}
				}
			}
			FileWriter writer = new FileWriter(file); 
			for(String str: arrlist) {
			  writer.write(str + System.lineSeparator());
			}
			writer.close();
			ObservableList<String> fullList = FXCollections.observableList(arrlist);
	    	activityRemove.setItems(fullList);
	   } catch (IOException e) {
			e.printStackTrace();
		}
	
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
@FXML
void clear(ActionEvent event) throws IOException {
	activitySelect.setValue("Select an Activity");
	customAct.clear();
	addMsg.setText("");
	delMsg.setText("");
}





}
