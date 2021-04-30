package application.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class MVCPlanner {
	/***************  Checklist MVC  ******************/
	
	public static String checklistsubmit(boolean add,String item, String q)
	{
		int n = 0;
		if(item.isEmpty())
			return "Item name must not be empty.";
		else if(q.isEmpty())
			return "Quantity must not be empty.";
			try {
				n = Integer.parseInt(q);
			} catch(NumberFormatException e){
				return "Quantity must be an integer.";
			}
		if(n < 1)
			return "Quantity must be greater than 0.";
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> itemlist = new ArrayList<String>();
		ArrayList<String> quantitylist = new ArrayList<String>();
		try {
			list = readChecklistFile();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "File not found";
		}
		
		for(String current : list)
		{
			String currentsplit[];
			currentsplit = current.split(",");
			currentsplit[1] = currentsplit[1].replaceAll("\\s", "");
			itemlist.add(currentsplit[0]);
			quantitylist.add(currentsplit[1]);
		}
		if(add)
			addChecklistItem(itemlist,quantitylist,item,n);
		else
			if(itemlist.contains(item))
			{
				if(n <= Integer.parseInt(quantitylist.get(itemlist.indexOf(item))))
				{
					subtractChecklistItem(itemlist,quantitylist,item,n);
				}
				else
					return "Quantity given is greater than in checklist.";
			}
			else
				return "Item not found.";
		return "Success!";
	}
	public static void clearFile(String n) throws IOException {
		FileWriter clear = new FileWriter(n,false);
		PrintWriter clearer = new PrintWriter(clear,false);
		clearer.flush();
		clearer.close();
	}
	public static ArrayList<String> readChecklistFile() throws FileNotFoundException
	{
		String current;
		String currentsplit[];
		Scanner filereader = new Scanner (new File("Checklist.txt"));
		ArrayList<String> list = new ArrayList<String>();
		while(filereader.hasNextLine())
		{
			current = filereader.nextLine();
			currentsplit = current.split(",");
			currentsplit[1] = currentsplit[1].replaceAll("\\s", "");
			if(!currentsplit[1].contains("-") && !currentsplit[1].equals("0"))
			{
				list.add(current);
			}
		}
		return list;
	}
	public static void writeChecklistFile(ArrayList<String> list,ArrayList<String> listnums) throws IOException
	{
		//Function to rewrite to file with updated lists
		//Clear file
		clearFile("Checklist.txt");
		//Create writers
		BufferedWriter bw = new BufferedWriter(new FileWriter("Checklist.txt",true));
		PrintWriter pw = new PrintWriter(bw);
		for(int i=0;i<list.size();i++)
		{
			//If 0 or negative, do not write to list
			if(listnums.get(i).equals("0")||listnums.get(i).equals("-"))
				continue;
			//Otherwise, write to list
			pw.println(list.get(i)+", "+listnums.get(i));
		}
		bw.close();
	}
	public static void addChecklistItem(ArrayList<String> list,ArrayList<String> listnums, String n, int m) {
		//Give function to read both arraylists, print a message, and update inventory.txt
		int temp;
		//Test list to see if item is already in list
		if(list.contains(n))
		{
			temp = Integer.parseInt(listnums.get(list.indexOf(n)));
			listnums.set(list.indexOf(n),String.valueOf(temp+m));
		}
		else //Otherwise add new item to list
		{
			list.add(n);
			listnums.add(String.valueOf(m));
		}
		//Write lists to file
		try {
			writeChecklistFile(list,listnums);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void subtractChecklistItem(ArrayList<String> list,ArrayList<String> listnums,String n, int m)
	{
		int temp = Integer.parseInt(listnums.get(list.indexOf(n)));
		listnums.set(list.indexOf(n),String.valueOf(temp-m));
		//Rewrite items and nums to inventory.txt
		try {
			writeChecklistFile(list,listnums);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*******************  Activities  *********************/
	//Image files and corresponding images
    static Image diningImg = new Image(new File("src/application/dining.jpg").toURI().toString());
    static Image sightImg = new Image(new File("src/application/nature.jpg").toURI().toString());
    static Image sportImg = new Image(new File("src/application/skiing.jpg").toURI().toString());
    static Image relaxImg = new Image(new File("src/application/relax.jpg").toURI().toString());
    static Image parrotImg = new Image(new File("src/application/parrot.png").toURI().toString());
    static Image planImg = new Image(new File("src/application/schedule.jpg").toURI().toString());
	
  //observable list for categories and activities
    static ObservableList < String > categories = FXCollections.observableArrayList(
        "dining", "sightsee", "sports", "relax", "all","other"
    );
    static ObservableList < String > dining = FXCollections.observableArrayList(
        "visit cafe", "try street food",
        "go to buffet", "eat at restaurant"
    );
    static ObservableList < String > sightSeeing = FXCollections.observableArrayList(
        "whale watching", "bird watching",
        "nature walk", "walk through city"
    );
    static ObservableList < String > sports = FXCollections.observableArrayList(
        "volleyball", "minigolf",
        "skiing", "canoeing"
    );
    static ObservableList < String > relax =
        FXCollections.observableArrayList(
            "get a massage", "do some yoga",
            "go sunbathing", "go for a dip"
        );
    static ObservableList < String > all = FXCollections.observableArrayList(
        "visit cafe", "try street food",
        "go to buffet", "eat at resturant",
        "whale watching", "bird watching",
        "nature walk", "walk through city",
        "volleyball", "minigolf",
        "skiing", "canoeing",
        "get a massage", "do some yoga",
        "go sunbathing", "go for a dip"
    );
	
    /*Name of Function: initialize;
     * 
     * @activitySelect:display activities the user may add
     * @activityRemove:display activities the user may remove
     * @categorySelect:display categories
     * @arrlist: activities read in from ActivityList.txt
     * 
     * This function will:
     * -read values from file into array list
     * -add activities from the array list into the list view
     * -set the values of the 3 combo boxes
     * -set the items of the 3 combo boxes
     * 
     * 
     * 
     * 
     */
	public static void init(ListView<String> activityList, ComboBox<String> activitySelect, ComboBox<String> categorySelect, ComboBox<String> activityRemove, 
			ImageView categoryImg, ImageView parrot, ImageView textBubble, Label source, Label comment, TextArea customAct) {
		ArrayList < String > arrlist = readTextFile();
        ObservableList < String > fullList = FXCollections.observableList(arrlist);
        int arrarySize = arrlist.size();
        for (int i = 0; i < arrarySize; i++) {
            activityList.getItems().add(arrlist.get(i));
        }
       //set all current items into combo box
        setImageAndCom(String.valueOf(categorySelect), categoryImg, parrot, textBubble, source, comment);
        textBubble.setVisible(false);
        customAct.setVisible(false);
        //set values
        categorySelect.setValue("Select a Category");
        activitySelect.setValue("Select an Activity");
        activityRemove.setValue("Select an Activity");
        //set items
        categorySelect.setItems(categories);
        activitySelect.setItems(all);
        activityRemove.setItems(fullList);
	}
	
	static public void add(ComboBox<String> activitySelect, TextArea customAct, Label delMsg, Label addMsg, ListView<String> activityList, ComboBox<String> activityRemove) throws IOException{
		File file = new File("ActivityList.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        ArrayList < String > arrlist = readTextFile();
        String value = "";
        if (activitySelect.getValue() != null) {
            value = activitySelect.getValue().toString();
        }
        String customVal = customAct.getText().trim();
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.ERROR);
        a.setTitle("Activity Exist");
        delMsg.setText("");
        //base case
        if (customAct.isVisible() == false) {
            if (checkListforItem(arrlist, value) == true && value != "" && value != "Select an Activity") {
                a.setContentText("You already added \"" + value + "\" to your activity list");
                a.show();
                addMsg.setText("");
            }
        }
        if (activitySelect.isVisible() == false) {
            if (checkListforItem(arrlist, customVal) == true && customVal != "" && customVal != " " && customVal != "\n" && customAct.getText().trim().length() != 0) {
                a.setContentText("You already added \"" + customVal + "\" to your activity list");
                a.show();
                addMsg.setText("");
            }
        }
        //add value to file and listview
        if (checkListforItem(arrlist, value) == false && customAct.isVisible() == false) {
        	if(file.length()==0)
        	{
        	  fileWriter.write(value);
        	}
        	else
        	{

             fileWriter.write("\n"+value);
        	}
            arrlist.add(value);
            activityList.getItems().add(value);
            addMsg.setText("added \"" + value + "\" to list");

        }
        //add customVal to file and listview
        if (checkListforItem(arrlist, customVal) == false && activitySelect.isVisible() == false) {
        	if(file.length()==0)
        	{
        	  fileWriter.write(customVal);
        	}
        	else
        	{

             fileWriter.write("\n"+customVal);
        	}
            arrlist.add(customVal);
            activityList.getItems().add(customVal);
            addMsg.setText("added \"" + customVal + "\" to list");
        }
        fileWriter.close();
        activitySelect.setValue("Select an Activity");
        customAct.clear();
        ObservableList < String > fullList = FXCollections.observableList(arrlist);
        activityRemove.setItems(fullList);
	}
	
	
	/*Name of Function: remove;
     * 
     * Attached to: @remove button
     * 
     * This function will:
     * -display all activities added by the user via the activityRemove combo box
     * -remove selected value from @activityList, @activityRemove and ActivityList.txt
     * -display success message when item is removed
     * -update arrlist and write it to the file (replacing the out dated one)
     * 
     * 
     */
	static public void remove(ComboBox<String> activityRemove, ListView<String> activityList, Label delMsg, Label addMsg) throws IOException{
		File file = new File("ActivityList.txt");
        ArrayList < String > arrlist = readTextFile();
        String value = "";
        value = activityRemove.getValue().toString();
        if (checkListforItem(arrlist, value) == true && value != "Select an Activity") {
            arrlist.remove(value);
            activityList.getItems().remove(value);
            activityRemove.getItems().remove(value);
            delMsg.setText("removed \"" + value + "\" from list");
            addMsg.setText("");
        }
        FileWriter writer = new FileWriter(file);
        //write from arrlist to file
        for (String str: arrlist) {
            writer.write(str + System.lineSeparator());
        }
        writer.close();
        activityRemove.setValue("Select an Activity");
	}
	
	//Checks the users activityList for activities already selected
	static public boolean checkListforItem(ArrayList < String > arrlist, String value) throws IOException {
        Alert a = new Alert(AlertType.NONE);
        a.setAlertType(AlertType.ERROR);
        a.setTitle("Activity Exist");
        if (!arrlist.contains(value)) {
            if (value != "Select an Activity" && !value.equals("")) {
                return false;
            }

        }
        return true;
    }
	
	 //read text file
    static public ArrayList < String > readTextFile() {
        ArrayList < String > arrlist = new ArrayList < String > ();
        try {
            FileInputStream input = new FileInputStream("ActivityList.txt");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
            String line = bufReader.readLine();
            while (line != null&&line != " ") {
            	if(!line.isEmpty())
            	{
                arrlist.add(line);
            	}
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrlist;
    }
    
    public static void setImageAndCom(String category, ImageView categoryImg, 
    								ImageView parrot, ImageView textBubble, Label source, Label comment) {
    	categoryImg.setFitHeight(200);
        categoryImg.setFitWidth(200);
        categoryImg.setImage(planImg);
        parrot.setImage(parrotImg);
        textBubble.setVisible(true);
        source.setText("Photo by Eric Rothermel on Unsplash");
        if (category == "dining") {
            comment.setText("Good. I'm starving");
            categoryImg.setImage(diningImg);
            source.setText("Photo by Pawel Wojciechowski on Unsplash");
        }
        if (category == "sightsee") {
        	comment.setText("Let's go bird watching :)");
        	categoryImg.setImage(sightImg);
        	source.setText("Photo by Adam Kool on Unsplash");
        }
        if (category == "sports") {
        	comment.setText("Gotta stretch out these\n old feathers");
        	categoryImg.setImage(sportImg);
        	source.setText("Photo by William Ling on Unsplash");
        }
        if (category == "relax") {
        	comment.setText("Let's take squak");
            categoryImg.setImage(relaxImg);
            source.setText("Photo by Aaron Burden on Unsplash");
        }
        if (category == "all") {
        	comment.setText("Hmm can't decide?");
        }
        if (category == "other") {
        	comment.setText("Oh do tell!");
        }
    }
    
    //set activities depending on category
    public static void setAct(String category, Text actTitle, ComboBox<String> activitySelect, TextArea customAct) {
    	actTitle.setText("Select an Activity");
    	activitySelect.setVisible(true);
        customAct.setVisible(false);
    	if (category == "dining") {
    		activitySelect.setItems(dining);
        }
        if (category == "sightsee") {
        	activitySelect.setItems(sightSeeing);
        }
        if (category == "sports") {
        	activitySelect.setItems(sports);
        }
        if (category == "relax") {
        	activitySelect.setItems(relax);
        }
        if (category == "all") {
        	activitySelect.setItems(all);
        }
        if (category == "other") {
        	actTitle.setText("Enter an Activity");
        	activitySelect.setVisible(false);
            customAct.setVisible(true);
        }
    }
	
	/*******************  Locations  *********************/
    //Default Image
    static Image def = new Image(new File("src/application/default.png").toURI().toString());
    
	//List of countries and cities
	static ObservableList<String> catC = FXCollections.observableArrayList("India","Africa","Britain",
			"USA","Canada","Nepal","Australia","Germany","France","Spain","Portugal","UAE","NewZealand");
    static ObservableList<String> cat = FXCollections.observableArrayList("Delhi","CapeTown","London","NewYork", 
    		"Toronto","Kathmandu","Sydney","Berlin","Paris","Madrid","Lisbon","Dubai","Wellington");
	
	static public void initLocation(ImageView imageView, ComboBox<String> country, ComboBox<String> city, ListView<String> list){
		File file = new File("Location.txt");
  	    ArrayList<String> arrlist = new ArrayList<String>();
  	    imageView.setImage(def);
    	  country.setItems(catC);
    	  city.setItems(cat);
    	  
    	  city.setValue("City");
    	  country.setValue("Country");
    	  
    	  try {
    	  	FileInputStream input = new FileInputStream(file);
    	  	BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
    	  	String line = bufReader.readLine();
    	  	while (line != null) {
    	  		arrlist.add(line);
    	  		line = bufReader.readLine();
    	  	}							
    	  	bufReader.close();
    	  	int arrarySize = arrlist.size();
    	  	for (int i = 0; i < arrarySize; i++) {
    	  	list.getItems().add(arrlist.get(i));
         	}
    	  }catch (IOException e) {
    	  	e.printStackTrace();
    	 }
	}
	
	//add Location
	static public void addLocation(TextField user, TextField user1, ComboBox<String> country, ComboBox<String> city,
									ListView<String> list, ImageView Imageview) throws IOException {
		File file = new File("Location.txt");
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
				
				if(!arrlist.contains(value) && value!= "Country")
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
				if(!arrlist.contains(value1) && value1!="City")
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
		    		country.setValue("Country");
		    		city.setValue("City");
		    		
		    		Imageview.setVisible(false);
		    		Imageview.setImage(def);
		    		Imageview.setVisible(true);
		    		
				
				}
				
				fileWriter.close();
	       }catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	
	//Remove Location
	static public void removeLocation(TextField user, TextField user1, ComboBox<String> country, ComboBox<String> city,
			ListView<String> list, ImageView Imageview) throws IOException{
		File file = new File("Location.txt");
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
  			
  			if(!arrlist.contains(combined) && !arrlist.contains(combined1))
  			{
  				Alert mainError = new Alert(AlertType.ERROR);
         		mainError.setTitle("Error!!!");
         		mainError.setHeaderText("Please enter the Country and City.");
         		mainError.setContentText("Thank you!!...");
         		mainError.showAndWait();
         		
         		
  			}
  				
  			if(arrlist.contains(combined))
  			{
  				arrlist.remove(combined);
  				list.getItems().remove(combined);
  			}
	
  			if(arrlist.contains(combined1))
  			{
  				Alert mainError = new Alert(AlertType.ERROR);
         		mainError.setTitle("Heads Up!!!");
         		mainError.setHeaderText("Are you sure you want to remove?");
         		mainError.setContentText("You selected" + "--->"+ combined1);
         		mainError.showAndWait();
  				arrlist.remove(combined1);
  				list.getItems().remove(combined1);
  				country.setValue("Country");
  	    		city.setValue("City");
  	    		Imageview.setImage(def);
  				
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
	
	/*******************  Transportations  ******************/
	//lists for ComboBoxs
	static ObservableList<String> initial = 
    		FXCollections.observableArrayList("Airplane",
    				"Train",
    				"Bus",
    				"Ship",
    				"Car"
    				);
    
    static ObservableList<String> atDestination = 
    		FXCollections.observableArrayList("Rental Car",
    				"Taxi",
    				"Train",
    				"Bus",
    				"Ridesharing"
    				);
	
    static public void initTransportation(ComboBox<String> initialTravel, ComboBox<String> meansofTravel, ListView<String> getTherelist) {
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
    
    static public void submitTransit(ComboBox<String> initialTravel, ListView<String> getTherelist, 
    								ListView<String> infoView) throws IOException, FileNotFoundException{
       
    	
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
    
    //SubmitButton2 Personal Transportation vehicle
    static public void submitVehicle(ComboBox<String> meansofTravel, ListView<String> meansList, 
    								ListView<String> infoView) throws IOException, FileNotFoundException {
    	
    	
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
	
    
    //Submit Button3 Cost
    static public void submitCost(TextField costText, ListView<String> moneyList) {
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
    
    //Clearing Files
    static public void clearTransportation(ListView<String> getTherelist, ListView<String> moneyList, 
    									   ListView<String> meansList, ListView<String> infoView) throws IOException, FileNotFoundException {
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
    //EOF
}
