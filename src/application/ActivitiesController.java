package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ActivitiesController implements Initializable {

    @FXML
    private ComboBox < String > activitySelect;
    @FXML
    private ComboBox < String > categorySelect;
    @FXML
    private ComboBox < String > activityRemove;

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
    private Label source;

    @FXML
    private ListView < String > activityList;
    @FXML
    private ImageView parrot;
    @FXML
    private ImageView categoryImg;
    @FXML
    private ImageView textBubble;
    @FXML
    private Rectangle border;
    @FXML
    private Rectangle imgBorder;

    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button clear;
    @FXML
    private Button homebtn;


    //Image files and corresponding images
    Image diningImg = new Image(new File("src/application/dining.jpg").toURI().toString());
    Image sightImg = new Image(new File("src/application/nature.jpg").toURI().toString());
    Image sportImg = new Image(new File("src/application/skiing.jpg").toURI().toString());
    Image relaxImg = new Image(new File("src/application/relax.jpg").toURI().toString());
    Image parrotImg = new Image(new File("src/application/parrot.png").toURI().toString());
    Image planImg = new Image(new File("src/application/schedule.jpg").toURI().toString());

    //observable list for categories and activities
    ObservableList < String > categories = FXCollections.observableArrayList(
        "dining", "sightsee", "sports", "relax", "all","other"
    );
    ObservableList < String > dining = FXCollections.observableArrayList(
        "visit cafe", "try street food",
        "go to buffet", "eat at resturant"
    );
    ObservableList < String > sightSeeing = FXCollections.observableArrayList(
        "whale watching", "bird watching",
        "nature walk", "walk through city"
    );
    ObservableList < String > sports = FXCollections.observableArrayList(
        "volleyball", "minigolf",
        "skiing", "canoeing"
    );
    ObservableList < String > relax =
        FXCollections.observableArrayList(
            "get a massage", "do some yoga",
            "go sunbathing", "go for a dip"
        );
    ObservableList < String > all = FXCollections.observableArrayList(
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
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList < String > arrlist = readTextFile();
        ObservableList < String > fullList = FXCollections.observableList(arrlist);
        int arrarySize = arrlist.size();
        for (int i = 0; i < arrarySize; i++) {
            activityList.getItems().add(arrlist.get(i));
        }
       //set all current items into combo box
        setImageAndComment(categorySelect.getValue());
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
    
    /*Name of Function: add;
     * 
     * Attached to: @add button
     * 
     * This function will:
     * -get the user selection or user input value from combo box/text area 
     * -display an error message if the activity is in the list
     * -display a success message once activity is added
     * 
     * 
     * 
     */
    @FXML
    public void add() throws IOException {

        File file = new File("src/application/ActivityList.txt");
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
            fileWriter.write(value + "\n");
            arrlist.add(value);
            activityList.getItems().add(value);
            addMsg.setText("added \"" + value + "\" to list");

        }
        //add customVal to file and listview
        if (checkListforItem(arrlist, customVal) == false && activitySelect.isVisible() == false) {
            arrlist.add(customVal);
            fileWriter.write(customVal + "\n");
            activityList.getItems().add(customVal);
            addMsg.setText("added \"" + customVal + "\" to list");
        }
        fileWriter.close();
        activitySelect.setValue("Select an Activity");
        customAct.clear();
        ObservableList < String > fullList = FXCollections.observableList(arrlist);
        activityRemove.setItems(fullList);
    }
    public boolean checkListforItem(ArrayList < String > arrlist, String value) throws IOException {
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
    @FXML
    void remove(ActionEvent event) throws IOException {
        File file = new File("src/application/ActivityList.txt");
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
    //read text file
    public ArrayList < String > readTextFile() {
        ArrayList < String > arrlist = new ArrayList < String > ();
        try {
            FileInputStream input = new FileInputStream("src/application/ActivityList.txt");
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(input));
            String line = bufReader.readLine();
            while (line != null) {
                arrlist.add(line);
                line = bufReader.readLine();
            }
            bufReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrlist;
    }
    //update the scene
    @FXML
    public void updateUI() {
        setActivities(categorySelect.getValue());
        setImageAndComment(categorySelect.getValue());
        delMsg.setText("");
        addMsg.setText("");
    }
    //clear and/or reset fields
    @FXML
    void clearFields(ActionEvent event) throws IOException {
        activitySelect.setValue("Select an Activity");
        activityRemove.setValue("Select an Activity");
        customAct.clear();
        addMsg.setText("");
        delMsg.setText("");
    }
    //return to VPMain.fxml
    @FXML
    void returnToMain(ActionEvent event) {
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
    //set images depending on category
    public void setImageAndComment(String category)
    {
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
    public void setActivities(String category)
    {
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
}