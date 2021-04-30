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
import java.io.FileNotFoundException;
//Imports
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import application.model.MVCPlanner;

public class ChecklistController {
	//Home button
	@FXML private Button homebutton;
	@FXML private Button submitbutton;
	@FXML private RadioButton rbadd;
	@FXML private RadioButton rbremove;
	@FXML private TextField itemfield;
	@FXML private TextField qfield;
	@FXML private ToggleGroup rbgroup;
	@FXML private ListView<String> Checklist;
	@FXML private Label emptyLabel;
	private Alert a = new Alert(AlertType.NONE);
	

	public void initialize()
	{
		try {
			File myObj = new File("Checklist.txt");
			if (myObj.createNewFile()) {
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			updateList();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//Goes to home screen on button press
	@FXML
    void home(ActionEvent event) throws IOException {
    	AnchorPane root = FXMLLoader.load(getClass().getResource("VPMain.fxml"));
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		//set the title 
        window.setTitle("Vacation Planner");
		window.setScene(scene);
		window.show();
    }
	@FXML
	void submit(ActionEvent event) throws FileNotFoundException
	{
		
		updateList();
		boolean bool = rbadd.isSelected();
		String item = itemfield.getText();
		String q = qfield.getText();
		String success = "";
		success = MVCPlanner.checklistsubmit(bool, item, q);
		
		if(success.equals("Success!"))
		{
			itemfield.clear();
			qfield.clear();
		}
		a.setAlertType(AlertType.CONFIRMATION);
		a.setContentText(success);
		a.show();
		
		updateList();
	}
	@FXML
	void updateList() throws FileNotFoundException
	{
		Checklist.getItems().clear();
		ArrayList<String> list = MVCPlanner.readChecklistFile();
		if(list.size() == 0)
			emptyLabel.setText("Your checklist is empty!");
		else
			emptyLabel.setText("");
		for(String fill : list)
		{
			Checklist.getItems().add(fill);
		}
	}
}
