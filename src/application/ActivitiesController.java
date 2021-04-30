package application;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import application.model.*;

public class ActivitiesController {

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
    private AnchorPane activitiesBgImg;

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
    public void initialize() {
    	MVCPlanner.init(activityList, activitySelect, categorySelect, activityRemove, categoryImg, 
    					parrot, textBubble, source, comment, customAct);

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
    		MVCPlanner.add(activitySelect, customAct, delMsg, addMsg, activityList, activityRemove);
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
        MVCPlanner.remove(activityRemove, activityList, delMsg, addMsg);
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
            window.setTitle("Vacation Planner");
            //display the scene
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //set images depending on category
    public void setImageAndComment(String category)
    {
    	MVCPlanner.setImageAndCom(category, categoryImg, parrot, textBubble, source, comment);
    }
    //set activities depending on category
    public void setActivities(String category)
    {
    	MVCPlanner.setAct(category, actTitle, activitySelect, customAct);
    }
}
