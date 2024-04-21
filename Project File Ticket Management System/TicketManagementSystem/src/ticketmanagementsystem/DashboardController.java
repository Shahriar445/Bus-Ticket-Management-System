/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ticketmanagementsystem;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Shipon
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane main_form;
    @FXML
    private Button availableBuses_btn;

    @FXML
    private Button available_add_btn;

    @FXML
    private TextField available_busID;

    @FXML
    private TableView<busData> available_tableView;

    @FXML
    private TableColumn<busData, String> available_col_butID;

    @FXML
    private TableColumn<busData, String> available_col_date;

    @FXML
    private TableColumn<busData, String> available_col_location;

    @FXML
    private TableColumn<busData, String> available_col_price;
    @FXML
    private TableColumn<busData, String> available_col_status;

    @FXML
    private DatePicker available_date;

    @FXML
    private Button available_delete_btn;

    @FXML
    private AnchorPane available_form;

    @FXML
    private TextField available_location;

    @FXML
    private TextField available_price;

    @FXML
    private Button available_reset_btn;

    @FXML
    private TextField available_search;

    @FXML
    private ComboBox<?> available_status;

    @FXML
    private Button available_update_btn;

    @FXML
    private Button bookingTicket_btn;

    @FXML
    private ComboBox<?> bookingTicket_busID;

    @FXML
    private DatePicker bookingTicket_date;

    @FXML
    private TextField bookingTicket_firstname;

    @FXML
    private AnchorPane bookingTicket_form;

    @FXML
    private ComboBox<?> bookingTicket_gender;

    @FXML
    private TextField bookingTicket_lastname;

    @FXML
    private ComboBox<?> bookingTicket_location;

    @FXML
    private TextField bookingTicket_phone;

    @FXML
    private Button bookingTicket_reset_btn;

    @FXML
    private Label bookingTicket_sel_busID;

    @FXML
    private Label bookingTicket_sel_date;

    @FXML
    private Label bookingTicket_sel_firstname;

    @FXML
    private Label bookingTicket_sel_gender;

    @FXML
    private Label bookingTicket_sel_lastname;

    @FXML
    private Label bookingTicket_sel_location;

    @FXML
    private Button bookingTicket_sel_pay_btn;

    @FXML
    private Label bookingTicket_sel_phonenumber;

    @FXML
    private Button bookingTicket_sel_receipt_btn;

    @FXML
    private Label bookingTicket_sel_ticket;

    @FXML
    private Label bookingTicket_sel_total;

    @FXML
    private Label bookingTicket_sel_type;

    @FXML
    private Button bookingTicket_select_btn;

    @FXML
    private ComboBox<?> bookingTicket_ticket;

    @FXML
    private ComboBox<?> bookingTicket_type;

    @FXML
    private FontAwesomeIconView close;

    @FXML
    private TableColumn<?, ?> customers_Ticketname;

    @FXML
    private Button customers_btn;

    @FXML
    private TableColumn<?, ?> customers_customerName;

    @FXML
    private TableColumn<?, ?> customers_date;

    @FXML
    private TableColumn<?, ?> customers_firstname;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private TableColumn<?, ?> customers_gender;

    @FXML
    private TableColumn<?, ?> customers_lastname;

    @FXML
    private TableColumn<?, ?> customers_location;

    @FXML
    private TableColumn<?, ?> customers_phone;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<?> customers_tableView;

    @FXML
    private TableColumn<?, ?> customers_type;

    @FXML
    private Label dashboard_availableBus;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AreaChart<?, ?> dashboard_chart;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private Label dashboard_todayIncome;

    @FXML
    private Label dashboard_totalIncome;

    @FXML
    private Button minimize;

    @FXML
    private Button logOut_btn;

    @FXML
    private Label username;

    /*   ------------------ start implements code------------------     */
    // DATABASE TOOLS 
    private Connection connect;
    private PreparedStatement prepare;
    private ResultSet result;
    private Statement statement;

    //AVAILABLE BUSES FORM FIRST : 
    public void availableBusAdd() {

        String addData;
        addData = "INSERT INTO bus (bus_id, location, status, price,date ) VALUES(?,?,?,?,?) ";
        connect = Database.connectDb();

        try {

            Alert alert;

            //CHECK IF THE FIELDS ARE EMPTY
            if (available_busID.getText().isEmpty()
                    || available_location.getText().isEmpty()
                    || available_status.getSelectionModel().getSelectedItem() == null
                    || available_price.getText().isEmpty()
                    || available_date.getValue() == null) {
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            } else {

                //CHECK IF THE BUS ID IS ALREADY EXIST 
                String check = "SELECT bus_id FROM bus WHERE bus_id = '"
                        + available_busID.getText() + "'";
                statement = connect.createStatement();
                result = statement.executeQuery(check);

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Bus Id : " + available_busID.getText() + " was already exist ");
                    alert.showAndWait();
                } else {

                    prepare = connect.prepareStatement(addData);
                    prepare.setString(1, available_busID.getText());
                    prepare.setString(2, available_location.getText());
                    prepare.setString(3, (String) available_status.getSelectionModel().getSelectedItem());
                    prepare.setString(4, (String) available_price.getText());
                    prepare.setString(5, String.valueOf(available_date.getValue()));

                    prepare.executeUpdate();

                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();

                    // TO UPDATE YOUR TABLE VIEW ONCE THE DATA  IS SUCCESSFUL 
                    availableBShowBusData();
                    availableBusReset();
//CALLED AVAILABLE BUS RESET FUNCTION
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // UPDATE BUS DETAILS 

    public void availableBusUpdate() throws SQLException {
        String updateData = "UPDATE bus SET location ='"
                + available_location.getText() + "', status = '"
                + available_status.getSelectionModel().getSelectedItem()
                + "', price ='" + available_price.getText()
                + "', date = '" + available_date.getValue()
                + "' WHERE  bus_id = '" + available_busID.getText() + "'";
        connect = Database.connectDb();

        Alert alert;

        if (available_busID.getText().isEmpty()
                || available_location.getText().isEmpty()
                || available_status.getSelectionModel().getSelectedItem() == null
                || available_price.getText().isEmpty()
                || available_date.getValue() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Select the item first");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to UPDATE Bus Id: " + available_busID.getText() + "?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {
                prepare = connect.prepareStatement(updateData);
                prepare.executeUpdate();

                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update!");
                alert.showAndWait();

                availableBShowBusData();
                availableBusReset();
            } else {
                return;
            }
        }

    }

    public void availableBusDelete() throws SQLException {
        String deleteData = "DELETE FROM bus WHERE bus_id = '"
                + available_busID.getText() + "'";

        connect = Database.connectDb();
        Alert alert;

        if (available_busID.getText().isEmpty()
                || available_location.getText().isEmpty()
                || available_status.getSelectionModel().getSelectedItem() == null
                || available_price.getText().isEmpty()
                || available_date.getValue() == null) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error message");
            alert.setHeaderText(null);
            alert.setContentText("Select the item first");
            alert.showAndWait();
        } else {
            alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to delete Bus ID : " + available_busID.getText() + " ?");

            Optional<ButtonType> option = alert.showAndWait();
            if (option.get().equals(ButtonType.OK)) {

                statement = connect.createStatement();
                statement.executeUpdate(deleteData);
                
                alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Deleted!");
                alert.showAndWait();

                availableBShowBusData();
                availableBusReset();
            }
            else {return ;}
        }
    }

    public void availableBusReset() {
        available_busID.setText("");
        available_location.setText("");
        available_status.getSelectionModel().clearSelection();
        available_price.setText("");
        available_date.setValue(null);

    }

    
    // WORKING FOR STATUS LIST BUS AVAILABLE OR NOT 
    private final String[] statusList = {"Available", "Not Available"};

    public void comboBoxStatus() {
        List<String> listS = new ArrayList<>();

        for (String data : statusList) {
            listS.add(data);
        }
        ObservableList listStaus = FXCollections.observableArrayList(listS);
        available_status.setItems(listStaus);
    }

    public ObservableList<busData> availableBusBusData() throws SQLException {

        ObservableList<busData> busListData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM bus ";

        connect = Database.connectDb();

        try {

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            busData busD;

            while (result.next()) {
                busD = new busData(result.getInt("bus_id"),
                        result.getString("location"),
                        result.getString("status"),
                        result.getDouble("price"),
                        result.getDate("date"));
                busListData.add(busD);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busListData;
    }

    private ObservableList<busData> availableBBusListData;

    public void availableBShowBusData() throws SQLException {
        availableBBusListData = availableBusBusData();
        available_col_butID.setCellValueFactory(new PropertyValueFactory<>("busId"));
        available_col_location.setCellValueFactory(new PropertyValueFactory<>("location"));
        available_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        available_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        available_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        available_tableView.setItems(availableBBusListData);

    }

    @FXML
    public void avaialbleBSelectBusData() {
        busData busD = available_tableView.getSelectionModel().getSelectedItem();
        int num = available_tableView.getSelectionModel().getSelectedIndex();

        if ((num - 1) < -1) {
            return;
        }
        available_busID.setText(String.valueOf(busD.getBusId()));
        available_location.setText(busD.getLocation());
        available_price.setText(String.valueOf(busD.getPrice()));

        available_date.setValue(LocalDate.parse(String.valueOf(busD.getDate())));

    }
    
     public void availableSearch(){
        
        FilteredList<busData> filter = new FilteredList<>(availableBBusListData, e-> true);
        
        available_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            
            filter.setPredicate(predicateBusData ->{
                
                if(newValue.isEmpty() || newValue == null){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
//                NOTHING? THEN WE NEED TO DO THIS FIRST
                if(predicateBusData.getBusId().toString().contains(searchKey)){
//                    NOTE, IF INTEGER OR IF THE DATA TYPE IS NOT STRING, YOU MUST BE DO toString()
                    return true;
                }else if(predicateBusData.getLocation().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBusData.getStatus().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateBusData.getDate().toString().contains(searchKey)){
                    return true;
                }else if(predicateBusData.getPrice().toString().contains(searchKey)){
                    return true;
                }else return false;
                
            });
        });
        SortedList<busData> sortList = new SortedList<>(filter);
        
        sortList.comparatorProperty().bind(available_tableView.comparatorProperty());
        available_tableView.setItems(sortList);
     }
     
     
     public void busIdList(){
        
        String busD = "SELECT * FROM bus WHERE status = 'Available'";
        
        connect = Database.connectDb();
        
        try{
            prepare = connect.prepareStatement(busD);
            result = prepare.executeQuery();
            
            ObservableList listB = FXCollections.observableArrayList();
            
            while(result.next()){
                
                listB.add(result.getString("bus_id"));
                
                
            } bookingTicket_busID.setItems(listB);
           
            ticketNumList();
           
            
        }catch(Exception e){e.printStackTrace();}
        
        
        
        
    }
     
     
       public void LocationList(){
        
        String locationL = "SELECT * FROM bus WHERE status = 'Available'";
        
        connect = Database.connectDb();
        
        try{
            
            prepare = connect.prepareStatement(locationL);
            result = prepare.executeQuery();
            
            ObservableList listL = FXCollections.observableArrayList();
            
            while(result.next()){
                
                listL.add(result.getString("location"));
            }
            
            bookingTicket_location.setItems(listL);
            
        }catch(Exception e){e.printStackTrace();}
        
    }
       private String[] listT = {"First Class", "Economy Class"};
    
    public void typeList(){
        
        List<String> tList = new ArrayList<>();
        
        for(String data : listT){
            tList.add(data);
        }
        
        ObservableList listType = FXCollections.observableArrayList(tList);
        bookingTicket_type.setItems(listType);
        
    }
   public void ticketNumList() throws SQLException
   {
       List <String > listTicket = new ArrayList();
       for (int q=1 ;q<=40 ;q++)
       {
           listTicket.add(String.valueOf(q));
       }
       
      
       
      String removeSeat ="SELECT seatNum FROM customer WHERE bus_id='"
      + bookingTicket_busID.getSelectionModel().getSelectedItem()+"'";
      connect =Database.connectDb();
      try{
            prepare = connect.prepareStatement(removeSeat);
          result =prepare.executeQuery();
          while (result.next())
          {
              listTicket.remove(result.getString("seatNum"));
          }
          
         
          ObservableList listTi = FXCollections.observableArrayList(listTicket);
          bookingTicket_ticket.setItems(listTi);
      }catch (SQLException e){e.printStackTrace();}
       
       
       
       
       
       
   }
   
   // -----------------------FOR BOOKING TICKET (CUSTOMER) ------------------------------------
   private double priceData =0;
   private double totalP =0;
   public void bookingTicketSelect()
   {
       String firstName=bookingTicket_firstname.getText();
       String lastName=bookingTicket_lastname.getText();
       String gender =(String) bookingTicket_gender.getSelectionModel().getSelectedItem();
       String phoneNumber =bookingTicket_phone.getText();
       String date=String.valueOf(bookingTicket_date.getValue());
       
        String busId = (String)bookingTicket_busID.getSelectionModel().getSelectedItem();
        String location = (String)bookingTicket_location.getSelectionModel().getSelectedItem();
        String type = (String)bookingTicket_type.getSelectionModel().getSelectedItem();
        String seatnum = (String)bookingTicket_ticket.getSelectionModel().getSelectedItem();
       Alert alert;
        
        if(firstName == null || lastName == null 
                || gender == null || phoneNumber == null || date == null
                || busId == null || location == null
                || type == null || seatnum == null){
            
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText(" Fill all blank fields");
            alert.showAndWait();
            
        }else{
            
            String totalPrice = "SELECT price FROM bus WHERE location = '"
                    +location+"'";
            
            try{
                
                connect = Database.connectDb();
                
                prepare = connect.prepareStatement(totalPrice);
                result = prepare.executeQuery();
                
                if(result.next()){
                    priceData = result.getDouble("price");
                }
                
                if("First Class".equals(type)){
                    totalP = (priceData + 100);
                }else if("Economy Class".equals(type)){
                    totalP = priceData; 
                }
            }catch(SQLException e){e.printStackTrace();}
            
            bookingTicket_sel_total.setText("$"+String.valueOf(totalP));
            bookingTicket_sel_firstname.setText(firstName);
            bookingTicket_sel_lastname.setText(lastName);
            bookingTicket_sel_gender.setText(gender);
            bookingTicket_sel_phonenumber.setText(phoneNumber);
            bookingTicket_sel_date.setText(date);
            
            bookingTicket_sel_busID.setText(busId);
            bookingTicket_sel_location.setText(location);
            bookingTicket_sel_type.setText(type);
            bookingTicket_sel_ticket.setText(seatnum);
            
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Message");
            alert.setHeaderText(null);
            alert.setContentText("Successfully Selected!");
            alert.showAndWait();
        }
   }

     public void bookingTicketReset(){
        
        bookingTicket_firstname.setText("");
        bookingTicket_lastname.setText("");
        bookingTicket_gender.getSelectionModel().clearSelection();
        bookingTicket_phone.setText("");
        bookingTicket_date.setValue(null);
        
    }
   private String[] genderL={"Male","Female","Others"};
   public void genderList()
   {
       List<String > listG= new ArrayList<>();
       for (String data: genderL)
       {
           listG.add(data);
       }
       ObservableList gList = FXCollections.observableArrayList(listG);
       bookingTicket_gender.setItems(gList);
   

   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   // LEST WORK FOR PAY 
   
     private int countRow;
    public void bookingTicketPay(){
        
        
            
         String firstName=bookingTicket_firstname.getText();
       String lastName=bookingTicket_lastname.getText();
       String gender =(String) bookingTicket_gender.getSelectionModel().getSelectedItem();
       String phoneNumber =bookingTicket_phone.getText();
       String date=String.valueOf(bookingTicket_date.getValue());
       
        String busId = (String)bookingTicket_busID.getSelectionModel().getSelectedItem();
        String location = (String)bookingTicket_location.getSelectionModel().getSelectedItem();
        String type = (String)bookingTicket_type.getSelectionModel().getSelectedItem();
        String seatnum = (String)bookingTicket_ticket.getSelectionModel().getSelectedItem();
            
       
        
        String payData = "INSERT INTO customer (customer_id,firstName,lastName,gender,phoneNumber,bus_id,location,type,seatNum,total,date)"
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        
        connect = Database.connectDb();
        
        try{
            
            Alert alert;
            
            String countNum = "SELECT COUNT(id) FROM customer";
            statement = connect.createStatement();
            result = statement.executeQuery(countNum);
            
            while(result.next()){
                countRow = result.getInt("COUNT(id)");
            }
            
//            CHECK IF EMPTY
            if(bookingTicket_sel_firstname.getText().isEmpty()
                    ||bookingTicket_sel_lastname.getText().isEmpty()
                    || bookingTicket_sel_gender.getText().isEmpty()
                    || bookingTicket_sel_phonenumber.getText().isEmpty()
                    || bookingTicket_sel_date.getText().isEmpty()
                    || bookingTicket_sel_busID.getText().isEmpty()
                    || bookingTicket_sel_location.getText().isEmpty()
                    || bookingTicket_sel_type.getText().isEmpty()
                    ||  bookingTicket_sel_ticket.getText().isEmpty()
                    || totalP == 0){
                
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the information first");
                alert.showAndWait();
                
            }else{
            
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure?");
                alert.showAndWait();
//                WE NEED TO REMOVE THE SEAT# IF THE CUSTOMER IS ALREADY CHOSE THAT 
                prepare = connect.prepareStatement(payData);
                prepare.setString(1, String.valueOf(countRow+1));
                prepare.setString(2, firstName);
                prepare.setString(3, lastName);
                prepare.setString(4, gender);
                prepare.setString(5, phoneNumber);
                prepare.setString(6, busId);
                prepare.setString(7, location);
                prepare.setString(8, type);
                prepare.setString(9, seatnum);
                prepare.setString(10, String.valueOf(totalP));
                prepare.setString(11, date);
                
                prepare.executeUpdate();
                
                String receiptData="INSERT INTO customer_receipt(customer_id,total,date)VALUES(?,?,?)";
                prepare =connect.prepareStatement(receiptData);
                prepare.setString(1,String.valueOf(countRow+1));
                prepare.setString(2, String.valueOf(totalP));
                prepare.setString(3,date);
                
                prepare.executeUpdate();
                
                alert =new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successful!");
                alert.showAndWait();
                
                bookingTicket_sel_firstname.setText("");
                bookingTicket_sel_lastname.setText("");
                  bookingTicket_sel_gender.setText("");
              bookingTicket_sel_phonenumber.setText("");
               bookingTicket_sel_date.setText("");
                 bookingTicket_sel_busID.setText("");
                bookingTicket_sel_location.setText("");
                    bookingTicket_sel_type.setText("");
                    bookingTicket_sel_ticket.setText("");
                    bookingTicket_sel_total.setText("$0.0");
//                 INSERT THE DATA ON CUSTOMER_RECEIPT FOR OUR RECEIPT : ) 
           
            }
        }catch(Exception e){e.printStackTrace();}
    }
   
    
    
    
    
    
    
    
    
    
    
    private double x = 0;
    private double y = 0;

     // -----------------------FOR BOOKING TICKET (CUSTOMER) ------------------------------------
    public void logout() throws IOException {

        //SET ALERT FORM CONFARMATION MESSAGE
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Message");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to logout ?");

        Optional<ButtonType> option = alert.showAndWait();

        if (option.get().equals(ButtonType.OK)) {

            // LOGIN FORM 
            logOut_btn.getScene().getWindow().hide();

            Parent root = FXMLLoader.load(getClass().getResource("LoginPage.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });

            root.setOnMouseDragged((MouseEvent event) -> {
                stage.setX(event.getSceneX() - x);
                stage.setY(event.getSceneY() - y);

                stage.setOpacity(.8);
            });

            root.setOnMouseReleased((MouseEvent event) -> {
                stage.setOpacity(1);
            });
            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        } else {
            return;
        }

    }

    public void defaultBtn() // DEFAULT BUTTON COLOR DESIGN 
    {
        dashboard_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#f5c271,#2db05b)");
        availableBuses_btn.setStyle("-fx-background-color:transparent");
        bookingTicket_btn.setStyle("-fx-background-color:transparent");
        customers_btn.setStyle("-fx-background-color:transparent");
    }

    @FXML
    public void switchForm(ActionEvent event) throws SQLException {  //action when user click any from switch to another page 

        if (event.getSource() == dashboard_btn) {
            dashboard_form.setVisible(true);
            available_form.setVisible(false);
            bookingTicket_form.setVisible(false);
            customers_form.setVisible(false);

            // design button when click 
            dashboard_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#f5c271,#2db05b)");
            availableBuses_btn.setStyle("-fx-background-color:transparent");
            bookingTicket_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

        } else if (event.getSource() == availableBuses_btn) {
            available_form.setVisible(true);
            dashboard_form.setVisible(false);

            bookingTicket_form.setVisible(false);
            customers_form.setVisible(false);

            // design button when click 
            dashboard_btn.setStyle("-fx-background-color:transparent");
            availableBuses_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#f5c271,#2db05b)");
            bookingTicket_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color:transparent");

            //TO UPDATE THE FORM ONCE YOU CLICK THE AVAIALABLE BUSES BUTTON 
            availableBShowBusData();
            availableSearch();
            typeList();
            ticketNumList();
            genderList();

        } else if (event.getSource() == bookingTicket_btn) {
            bookingTicket_form.setVisible(true);
            dashboard_form.setVisible(false);
            available_form.setVisible(false);
            customers_form.setVisible(false);

            // design button when click 
            dashboard_btn.setStyle("-fx-background-color:transparent");
            availableBuses_btn.setStyle("-fx-background-color:transparent");
            bookingTicket_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#f5c271,#2db05b)");
            customers_btn.setStyle("-fx-background-color:transparent");
            
            busIdList();
            LocationList();
            
        } else if (event.getSource() == customers_btn) {
            customers_form.setVisible(true);
            dashboard_form.setVisible(false);
            available_form.setVisible(false);
            bookingTicket_form.setVisible(false);

            // design button when click 
            dashboard_btn.setStyle("-fx-background-color: transparent");
            availableBuses_btn.setStyle("-fx-background-color:transparent");
            bookingTicket_btn.setStyle("-fx-background-color:transparent");
            customers_btn.setStyle("-fx-background-color: linear-gradient(to bottom right,#f5c271,#2db05b)");

        }

    }

    @FXML
    public void close() // implementing close when user close click then program colse
    {
        System.exit(0);
    }

    @FXML
    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            defaultBtn(); //called defaultBtn function in initialization function

            comboBoxStatus();  // STATUS ADD FOR BUS FUCNTION CALLED

            availableBShowBusData(); // CALLED DATABASE AVAILABLE BUS DATA
             busIdList(); // CALLED THE BUS ID LIST FUNCTION 
             
             LocationList();
             typeList();
             ticketNumList();
             genderList();
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
