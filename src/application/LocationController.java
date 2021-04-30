//Authors:	name				GitHub usr
//			William Wells		wiwells
//			Samuel Pilato		sampleauto
//			Jacob Forney		jakexclone
//			Ravinder Chaupain	vnm303
//			Jasmine Beale		jnbeale
//Class: Application Programming 3443
//Section: 001
package application;

import java.io.File;
import java.io.IOException;

import application.model.MVCPlanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class LocationController {
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
	Image def = new Image(new File("src/application/default.png").toURI().toString());
	
    @FXML
    private Button goHomeButton;
    
    
    public void initialize() {
    	MVCPlanner.initLocation(Imageview, country, city, list);
    }
    
    
    @FXML
	void goHome(ActionEvent event) throws IOException{
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
        void clearAllField(ActionEvent event) {
    		user.clear();
    		user1.clear();
    		country.setValue("Country");
    		city.setValue("City");
    		
    		Imageview.setVisible(false);
    		Imageview.setImage(def);
    		Imageview.setVisible(true);
        }
		

        
        @FXML
        void remove(ActionEvent event) throws IOException {
        	MVCPlanner.removeLocation(user, user1, country, city, list, Imageview);
		clearAllField(event);
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
      public void add() throws IOException {
    	  MVCPlanner.addLocation(user, user1, country, city, list, Imageview);
      }
}
