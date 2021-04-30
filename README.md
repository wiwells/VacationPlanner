# VocationPlanner
Group Project for 3443 Application Programming course at the University of Texas at San Antonio.
Authors:
      William Wells     wiwells
      Jasmine Beale     jnbeale
      Ravinder Chaupain  vnm303
      Samuel Pilato     sampleauto
      Jacob Forney      jakexclone
      
# Cloning repository
  Clone working master file using this link https://github.com/wiwells/VacationPlanner.git
  Or in the repository (https://github.com/wiwells/VacationPlanner/tree/master),  download a zip file with 
   the workable project listed under the green "code" button.
   
  Double check that all text files are in the vacationplanner directory
    list of file names: 
          ActivityList.txt
          Airlines.txt
          Bus.txt
          Car.txt
          Checklist.txt
          Location.txt
          RideSharing.txt
          Taxi.txt
          Trains.txt
          initialTransportation.txt
          listView.txt
          meansText.txt
          moneyText.txt
          rentalCar.txt
          ship.txt
   
# Functionality of application
  The Vacation Planner Application is supposed to store a vacation plan the user would like to create
  prior to a trip they would like to take. Features include planning for transportation means, travel locations,
  supplies checklist, and daily activities.
 
# Functionality of Transportation Panel
  Works:
      The user can enter a means of Transportation to the destination of their choice. By submitting a travel means, the 
      application will provide the user with several company names and phone numbers that the user may find useful.
  Does not work:
      The Panel was intended to store the initial information of travel means to get to the desired destination, 
      travel means at the desired destination, and cost of travel. The Panel unfortunatly fails to record the two of the 
      intended fields.
      
      The Panel also has issues with two of the textfields under "Means of Transportation during Travel", where if the user
      selects "Rental Car" or "Ridesharing" under the ComboBox, the message in the textfields do not show do to them collapsing.

# Functionality of Locations Panel
  Works: 
       The user is able to add and remove travel destinations of their choice at anytime. 
       The user is required to enter or select a country and city to be able to add a destination to their list.
       The "Enter" fields are meant for the user to enter a destination not already in the "select" dropboxes.
       Destinations entered by the user will be recorded and displayed the next time the application is opened by the user.
  Does not work:
       If the user decides to enter a country and city using both methods of textfields and dropboxes, then the application will add all 
       four entered and selected items to the text file. Which will cause an issue in the file (Location.txt), where the user will
       then not be able to remove the entered travel destination unless they remove them straight from the text file (Location.txt).
   
# Functionality of Checklist Panel
  Works:
      The user is able to create (add and remove) items and quantities from their created lists.
      The items will be recorded for the next time they open the application.
      
# Functionality of Activity Panel
  Works:
      The user is able to utilize suggested activities from the application or enter their own activites.
      Removal of these activites also works.
      The application will record these activities the next time the user opens the application.
      
# Utilized Application
      -Eclipse IDE for Jave Developers 2020-12
                       -Utilizing Java jre 1.8.0_281
      -For FXML files, utilize JavaFX SceneBuilder 2.0
