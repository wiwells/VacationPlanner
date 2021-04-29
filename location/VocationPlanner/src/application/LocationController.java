package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class LocationController implements Initializable {
	  @FXML
	    private Button addbtn;
	  @FXML
	    private TextField user;
	  @FXML
	    private TextField user1;
	    @FXML
	    private ComboBox<String> country;

	    @FXML
	    private ComboBox<String> city;

	    @FXML
	    private Button clear;
	    @FXML
	    private Button remove;
	    @FXML
	    private ImageView Imageview;
	    @FXML
	    private ListView < String > list;
	Parent mainPane;
	Image cityImg = new Image(new File("src/application/India.png").toURI().toString());
	Image cityImg1 = new Image(new File("src/application/Africa.png").toURI().toString());
	Image cityImg2 = new Image(new File("src/application/Britain.png").toURI().toString());
	Image cityImg3 = new Image(new File("src/application/USA.png").toURI().toString());
	Image cityImg4 = new Image(new File("src/application/Canada.png").toURI().toString());
	Image cityImg5 = new Image(new File("src/application/Nepal.png").toURI().toString());
	Image cityImg6 = new Image(new File("src/application/Australia.png").toURI().toString());
	Image cityImg7 = new Image(new File("src/application/Germany.png").toURI().toString());
	Image cityImg8 = new Image(new File("src/application/France.png").toURI().toString());
	Image cityImg9 = new Image(new File("src/application/Spain.png").toURI().toString());
	Image cityImg10 = new Image(new File("src/application/Portugal.png").toURI().toString());
	Image cityImg11 = new Image(new File("src/application/UAE.png").toURI().toString());
	Image cityImg12 = new Image(new File("src/application/NewZealand.png").toURI().toString());
	
    @FXML
    private Button goHomeButton;
    ObservableList<String> categories = 
    		FXCollections.observableArrayList("India","Africa","Britain","USA","Canada","Nepal","Australia","Germany","France","Spain","Portugal","UAE","NewZealand");
    ObservableList<String> cat = 
    		FXCollections.observableArrayList("Delhi","CapeTown","London","NewYork","Toronto","Kathmandu","Sydney","Berlin","Paris","Madrid","Lisbon","Dubai","Wellington");
    public void initialize(URL url, ResourceBundle rb) {
    	File file = new File("src/application/Location.txt");
  	    ArrayList<String> arrlist = new ArrayList<String>();
		
    	  country.setItems(categories);
    	  city.setItems(cat);
    	  
    	  city.setValue("Select a city");
    	  country.setValue("Select a country");
    	  
    	  try {
    		  FileInputStream input = new FileInputStream(file);
   			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
   			String line = bufReader.readLine();
   			while (line != null) {
   				arrlist.add(line);
   				line = bufReader.readLine();
   				
   				
   			}													//list.getItems().add(value);
   			bufReader.close();
    	  int arrarySize = arrlist.size();
          for (int i = 0; i < arrarySize; i++) {
              list.getItems().add(arrlist.get(i));
              
          }
    	}catch (IOException e) {
 			e.printStackTrace();
    }
    
    }
    
    
    @FXML
	void goHome(ActionEvent event) throws IOException{
    	mainPane = FXMLLoader.load(getClass().getResource("VPMain.fxml"));
    	Scene scene = new Scene(mainPane);  //pane to be shown
    	Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow(); 
    	window.setScene(scene);
    	window.show();
    }
    	
    	
		@FXML
        void clearAllField(ActionEvent event) {
    		user.clear();
    		user1.clear();
    		country.setValue("Select a country");
    		city.setValue("Select a city");
    		
    		Imageview.setVisible(false);
    		
        }
		

        
        @FXML
        void remove(ActionEvent event) throws IOException {
        	
        	
        	File file = new File("src/application/Location.txt");
     	   ArrayList<String> arrlist = new ArrayList<String>();
     	   String value = "";
     	   String value1 = "";
     	   String combined = "";
     	   String combined1 = "";
     	   String usr = user.getText().trim();
     	   String usr1 = user1.getText().trim();
     	  FileInputStream input = new FileInputStream(file);
     	   if(country.getValue().toString()!= " ") {
     		   value= country.getValue().toString();
     	   }
     	   
     	   if(city.getValue().toString()!= " ") {
     		   value1= city.getValue().toString();
     	   }
     	 
     	  
     	  
            
            try {
     			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
     			String line = bufReader.readLine();
     			while (line != null) {
     				arrlist.add(line);
     				line = bufReader.readLine();
     				
     			}
     			bufReader.close();
     			combined = usr+","+usr1;
     			combined1 = value+","+value1;
     			if(arrlist.contains(combined))
     			{
     				arrlist.remove(combined);
     				list.getItems().remove(combined);
     			}
     			if(arrlist.contains(combined1))
     			{
     				arrlist.remove(combined1);
     				list.getItems().remove(combined1);
     			}
     			
     			FileWriter writer = new FileWriter(file);
                for (String str: arrlist) {
                    writer.write(str + System.lineSeparator());
                }
                writer.close();
            }catch (IOException e) {
     			e.printStackTrace();
     		}
        	

        }

        @FXML
        void update(ActionEvent event) {
        	if(country.getValue()=="India" || city.getValue()=="Delhi"){
                Imageview.setImage(cityImg);}
                else if(country.getValue()=="Africa" || city.getValue()=="CapeTown" )
                Imageview.setImage(cityImg1); 
                else if(country.getValue()=="Britain" || city.getValue()=="London" )
                Imageview.setImage(cityImg2);
                else if(country.getValue()=="USA" || city.getValue()=="NewYork")
                Imageview.setImage(cityImg3);
                else if(country.getValue()=="Canada" || city.getValue()=="Toronto")
                Imageview.setImage(cityImg4);
                else if(country.getValue()=="Nepal" || city.getValue()=="Kathmandu")
                Imageview.setImage(cityImg5);
                else if(country.getValue()=="Australia" || city.getValue()=="Sydney")
                Imageview.setImage(cityImg6);
                else if(country.getValue()=="Germany" || city.getValue()=="Berlin")
                Imageview.setImage(cityImg7);
                else if(country.getValue()=="France" || city.getValue()=="Paris")
                Imageview.setImage(cityImg8); 
                else if(country.getValue()=="Spain" || city.getValue()=="Madrid")
                Imageview.setImage(cityImg9);
                else if(country.getValue()=="Portugal" || city.getValue()=="Lisbon")
                Imageview.setImage(cityImg10);
                else if(country.getValue()=="UAE" || city.getValue()=="Dubai")
                Imageview.setImage(cityImg11);
                else if(country.getValue()=="NewZealand" || city.getValue()=="Wellington")
                Imageview.setImage(cityImg12);
        	Imageview.setVisible(true);
        }

      @FXML  
public void add() throws IOException
{
	  
      
	   File file = new File("src/application/Location.txt");
	   ArrayList<String> arrlist = new ArrayList<String>();
	   String value = " ";
	   String value1 = " ";
	   String usr = user.getText().trim();
	   if(country.getValue().toString()!= " ") {
		   value= country.getValue().toString();
	   }
	   
	   if(city.getValue().toString()!= " ") {
		   value1= city.getValue().toString();
	   }
	 String usr1 = user1.getText().trim();
	   FileInputStream input = new FileInputStream(file);
       
       FileWriter fileWriter = new FileWriter(file, true);
       try {
			BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
			String line = bufReader.readLine();
			while (line != null) {
				arrlist.add(line);
				
				line = bufReader.readLine();
				
			}
			bufReader.close();
			String comb = usr+","+usr1;
			String val = value+","+value1;
			
			if(!arrlist.contains(value) && value!= "Select a country")
			{
				arrlist.add(value);
				list.getItems().add(val);
				fileWriter.write(value+",");
			}
			if(!arrlist.contains(usr) && !usr.equals(""))
			{
				if(!usr.equals(""))
				{
					arrlist.add(usr);
					list.getItems().add(comb);
					fileWriter.write(usr+",");
				}
				
				
			
			
			}
			if(!arrlist.contains(value1) && value1!="Select a city")
			{
				arrlist.add(value1);
				
				fileWriter.write(value1+"\n");
			}
			if(!arrlist.contains(usr1) && !usr1.equals(""))
			{
				if(!usr1.equals(""))
				{
					arrlist.add(usr1);
					
					fileWriter.write(usr1+"\n");
				}
				
				
				user.clear();
	    		user1.clear();
	    		country.setValue("Select a country");
	    		city.setValue("Select a city");
	    		
	    		Imageview.setVisible(false);
	    		
			
			}
			
			fileWriter.close();
       }catch (IOException e) {
			e.printStackTrace();
		}
     
}
}
           



