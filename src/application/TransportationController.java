package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TransportationController implements Initializable {

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
    
    ObservableList<String> initial = 
    		FXCollections.observableArrayList("Airplane",
    				"Train",
    				"Bus",
    				"Ship",
    				"Car"
    				);
    
    ObservableList<String> atDestination = 
    		FXCollections.observableArrayList("Rental Car",
    				"Taxi",
    				"Train",
    				"Bus",
    				"Ridesharing"
    				);
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	ArrayList<String> items = new ArrayList<String>();
    	initialTravel.setItems(initial);
    	meansofTravel.setItems(atDestination);
    	initialTravel.setValue("Select Transportation");
    	meansofTravel.setValue("Select Transportation");
    	try {
    		FileInputStream input = new FileInputStream("initialTransportation.txt");
    		BufferedReader buffer = new BufferedReader(new InputStreamReader(input));
    		String line = buffer.readLine();
    		while(line != null) {
    			items.add(line);
    			line = buffer.readLine();
    		}
    		buffer.close();
    		
    		int arraySize = items.size();
    		for(int i=0; i<arraySize;i++) {
    			getTherelist.getItems().add(items.get(i));
    		}
    	} catch (FileNotFoundException ex) {
    		System.err.println(ex);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    @FXML
    void sendHome(ActionEvent event) throws IOException {
    	
    	AnchorPane Mainroot = FXMLLoader.load(getClass().getClassLoader().getResource("VPMain.fxml"));
    	Scene Mainscene = new Scene(Mainroot);
    	
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	
    	window.setScene(Mainscene);
    	window.show();
    }
    
    @FXML
    public void submitButton() throws IOException {
          
    	   File file = new File("initialTransportation.txt");
    	   ArrayList<String> arrlist = new ArrayList<String>();
    	   String value;
    	   value= initialTravel.getValue().toString();
    	   FileInputStream input = new FileInputStream(file);
           
           FileWriter writer = new FileWriter(file, false);
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
    				if(value != "Select Transportation")
    				{
    				arrlist.add(value);
    				writer.write("You will travel to your destination by " + value + ".");
    				if(value != "Select Transportation" && value != " "&&!getTherelist.getItems().contains(value))
    			   	{
    					getTherelist.getItems().clear();
    					getTherelist.getItems().add("You will travel to your destination by " + value + ".");
    			   		
    			   	}
    				}
    			}
    			writer.close();
    			initialTravel.setValue("Select Transportation");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
           if (value == "Airplane") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Airlines.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value == "Train") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Trains.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value == "Bus") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Bus.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value == "Ship") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("ship.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value == "Car") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Car.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
    }
    
    @FXML
    public void submitButton2() throws IOException {
          
    	   File file2 = new File("meansText.txt");
    	   ArrayList<String> arrlist2 = new ArrayList<String>();
    	   String value2;
    	   value2= meansofTravel.getValue().toString();
    	   FileInputStream input2 = new FileInputStream(file2);
           
           FileWriter writer2 = new FileWriter(file2, false);
           try {
    			BufferedReader bufReader2 = new BufferedReader(new InputStreamReader(input2));
    			String line2 = bufReader2.readLine();
    			while (line2 != null) {
    				arrlist2.add(line2);
    				line2 = bufReader2.readLine();
    				
    			}
    			bufReader2.close();
    			if(!arrlist2.contains(value2))
    			{
    				if(value2 != "Select Transportation")
    				{
    				arrlist2.add(value2);
    				writer2.write("You will get around your destination by " + value2 + ".");
    				if(value2 != "Select Transportation" && value2 != " "&&!meansList.getItems().contains(value2))
    			   	{
    					meansList.getItems().clear();
    					meansList.getItems().add("You will get around your destination by " + value2 + ".");
    			   		
    			   	}
    				}
    			}
    			writer2.close();
    			meansofTravel.setValue("Select Transportation");
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
           if (value2 == "Rental Car") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("rentalCar.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value2 == "Taxi") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Taxi.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value2 == "Train") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Trains.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value2 == "Bus") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("Bus.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else if (value2 == "Ridesharing") {
        	   ArrayList<String> ar = new ArrayList<String>();
        	   infoView.getItems().clear();
        	   try {
                   FileInputStream al = new FileInputStream("RideSharing.txt");
                   BufferedReader buf = new BufferedReader(new InputStreamReader(al));
                   String li = buf.readLine();
                   while (li != null) {
                      
                       ar.add(li);
                       li = buf.readLine();

                   }
                   buf.close();
                   int as = ar.size();
                   for (int i = 0; i < as; i++) {
                       infoView.getItems().add(ar.get(i));
                   	}
               } catch (FileNotFoundException ex) {
                   System.err.println(ex);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
    }
    
    @FXML
    void submitButton3(ActionEvent event) throws IOException {
    	Alert c = new Alert(AlertType.ERROR);
    	String cost = costText.getText();
    	
    	if(costText.getLength() == 0){
    		c.setHeaderText("You did not fill in how much your transportation will cost.");
        	costText.clear();
    		c.show();
    	}
		else {
    	try {
            FileWriter writer = new FileWriter("moneyText.txt", false);
            writer.write("Your total cost for transportation will be " + cost + " dollars ");
            moneyList.getItems().clear();
            moneyList.getItems().add("Your total cost for transportation will be " + cost + " dollars ");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    	costText.clear();
    	}
    	
    }
    
    @FXML
    void clearProcess(ActionEvent event) throws IOException {
    	Alert CLEAR = new Alert(AlertType.ERROR);
    	CLEAR.setHeaderText("You have cleared all fields.");
    	getTherelist.getItems().clear();
    	meansList.getItems().clear();
    	moneyList.getItems().clear();
    	infoView.getItems().clear();
    	FileWriter writers = new FileWriter("moneyText.txt", false);
    	FileWriter writerz = new FileWriter("meansText.txt", false);
    	FileWriter writerc = new FileWriter("initialTransportation.txt", false);
    	writers.close();
    	writerz.close();
    	writerc.close();
    	CLEAR.show();
    }
    	
}