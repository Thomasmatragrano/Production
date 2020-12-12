import java.sql.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * JavaFX event handling and input registry.
 * Establishes Database Connectivity.
 * Initializes JavaFX components.
 *
 * @author Thomas Matragrano
 */
public class Controller {
  @FXML
  private Label productLineLbl;
  @FXML
  private Label produceLbl;
  @FXML
  private Label signUpLbl;
  @FXML
  private TextArea newEmpTA;
  @FXML
  private TextField userField;
  @FXML
  private PasswordField passField;
  private final ObservableList<Product> productLine = FXCollections.observableArrayList();
  @FXML
  private TableColumn<Product, String> tableVIewManufacturer;
  @FXML
  private TableColumn<Product, String> tableViewType;
  @FXML
  private TableColumn<Product, String> tableViewName;
  @FXML
  private TableView<Product> tableView;
  @FXML
  private ListView<String> chooseProductLV;
  @FXML
  private TextArea productionTA;
  @FXML
  private TextField lblManufacturerOutput;
  @FXML
  private TextField lblNameOutput;
  @FXML
  TextArea taOutput;
  @FXML
  private ComboBox<String> quantityComboBox;
  @FXML
  private ChoiceBox<String> itemTypeChoiceBox;
  Connection conn = null;
  Statement stmt = null;
  PreparedStatement ps = null;

  /**
   * Test Method for Multimedia Functionality.
   */
  public static void testMultimedia() {

    AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",
            "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction",
            newScreen, MonitorType.LCD);
    MoviePlayer newMovieProduct2 = new MoviePlayer("Sony Player", "Sony",
            newScreen, MonitorType.LED);
    ArrayList<MultimediaControl> productList = new ArrayList<>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    productList.add(newMovieProduct2);
    //Looping through the product list
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /**
   * Establishes Database Connectivity.
   * Corrects JavaFX component population.
   */
  public void initializeDB() {
    //  Database credentials
    final String jdbc_driver = "org.h2.Driver";
    final String db_url = "jdbc:h2:./resources/ProductionDB";
    try {
      //Register JDBC driver
      Class.forName(jdbc_driver);
      //Open a connection
      conn = DriverManager.getConnection(db_url);
      stmt = conn.createStatement();
      //Display Full list of products in the tableview and listview
      String fullList = "select * from product";
      ResultSet rs = stmt.executeQuery(fullList);
      //Populating the product line with Product objects from the database using a ResultSet
      //Populating the RecordProduction ListView with the names of Products
      while (rs.next()) {
        productLine.addAll(new Product(rs.getString("Name"),
                ItemType.valueOf(rs.getString("Type")),
                rs.getString("Manufacturer")) {
        });
        chooseProductLV.getItems().addAll(rs.getString("Name"));
      }
      //Display Full list of Production Record in the TextArea
      fullList = "SELECT * FROM PRODUCTIONRECORD";
      rs = stmt.executeQuery(fullList);
      while (rs.next()) {
        productionTA.appendText("Prod. Num: " + rs.getInt("production_num") + " Product ID: "
                + rs.getInt("product_id") + " Serial Num: " + rs.getNString("serial_num")
                + " Date:" + rs.getDate("date_produced") + "\n");
      }
    } catch (SQLException e) {
      taOutput.appendText(e.toString());
    } catch (ClassNotFoundException e) {
      taOutput.appendText(e.toString());
    }
  }

  /**
   * Calls to establish Database Connectivity.
   * Populates JavaFX Components.
   * Initializes GUI.
   */
  public void initialize() {
    initializeDB();
    //Populates the Item Type ChoiceBox
    for (ItemType i : ItemType.values()) {
      itemTypeChoiceBox.getItems().addAll(String.valueOf(i));
    }
    itemTypeChoiceBox.getSelectionModel().selectFirst();
    //Populates the Quantity ComboBox
    quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
    quantityComboBox.getSelectionModel().selectFirst();
    quantityComboBox.setEditable(true);
    //Setup Product Line Table
    tableViewName.setCellValueFactory(new PropertyValueFactory<>("Name"));
    //Assigning new TableView cell factory using a lambda function
    tableViewType.setCellValueFactory(cellData ->
            new SimpleStringProperty(cellData.getValue().type.type));
    tableVIewManufacturer.setCellValueFactory(new PropertyValueFactory<>("Manufacturer"));
    tableView.setItems(productLine);
    chooseProductLV.getSelectionModel().selectFirst();
    Screen newScreen = new Screen("720x480", 40, 22);
    System.out.println(newScreen.getRefreshRate() + " " + newScreen.getResponseTime()
            + " " + newScreen.getResolution());
  }

  /**
   * Control for "Record Production" Button Click.
   * Records an already existing Product's production.
   * Displays error message for erroneous inputs.
   */
  public void recordProduction() {
    Pattern p = Pattern.compile("^[0-9]", Pattern.CASE_INSENSITIVE);
    Matcher m = p.matcher(quantityComboBox.getSelectionModel().getSelectedItem());
    boolean check = m.find();
    //Checks the Record Production quantity box for valid input and proceeds if true
    if (check) {
      try {
        final String productid = "SELECT id,name FROM PRODUCT";
        final String sql = "INSERT INTO "
                + "ProductionRecord (product_id,serial_num,date_produced) VALUES ( ?,?,?);";
        ps = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery(productid);
        //Looping through the Result Set to retrieve the original Product's ID.
        //Setting
        while (rs.next()) {
          if (rs.getNString("name")
                  .equals(String.valueOf(chooseProductLV.getSelectionModel().getSelectedItem()))) {
            int id = rs.getInt("id");
            ProductionRecord pr = new ProductionRecord(id);
            String serialNum = pr.getSerialNum();
            //Setting the PreparedStatement to insert into ProductionRecord Database
            ps.setInt(1, id);
            ps.setString(2, serialNum);
            ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
            productionTA.appendText(pr.toString());
          }
        }
        //Correct message
        produceLbl.setText("Production Recorded!");
      } catch (SQLException se) {
        se.printStackTrace();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.show();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else {
      //Error message
      produceLbl.setText("Please enter a valid Quantity.");
    }
  }

  /**
   * Functionality for "Add Product" Button Click.
   * Inserts GUI input into Database.
   * Prints full list of Products to the Console.
   */
  public void addProduct() {
    //Setting the Product name and manufacturer fields
    String name = lblNameOutput.getText();
    String manufacturer = lblManufacturerOutput.getText();
    final String type = itemTypeChoiceBox.getSelectionModel().getSelectedItem();
    //Checking if any fields are empty
    if (lblNameOutput.getText().isEmpty() || lblManufacturerOutput.getText().isEmpty()) {
      productLineLbl.setText("Please input a value for product name and manufacturer.");
    } else {
      try {
        //Inserts variables into ProductionDB Product table using a prepared statement
        final String sql = "INSERT INTO Product (name, type, manufacturer) VALUES ( ?, ?, ? );";
        ps = conn.prepareStatement(sql);
        ps.setNString(1, name);
        ps.setNString(2, type);
        ps.setNString(3, manufacturer);
        ps.executeUpdate();

        stmt = conn.createStatement();
        String fullList = "SELECT * FROM PRODUCT";
        ResultSet rs = stmt.executeQuery(fullList);
        //Clearing tableview and listview to be updated
        tableView.getItems().setAll();
        chooseProductLV.getItems().setAll();
        //Prints updated Full List of Products to the tableview and listview
        while (rs.next()) {
          productLine.addAll(new Product(rs.getString("Name"),
                  ItemType.valueOf(rs.getString("Type")), rs.getString("Manufacturer")) {
          });
          chooseProductLV.getItems().add(rs.getString("Name"));
        }
      } catch (SQLException se) {
        se.printStackTrace();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.show();
      } catch (Exception e) {
        e.printStackTrace();
      }
      lblManufacturerOutput.setText("");
      lblNameOutput.setText("");
      productLineLbl.setText("Product Added!");
    }
  }

  /**
   * An Employee signs into a database.
   * Controls Employee table insertion.
   * Displays Employee login credentials.
   */
  public void empSignUp() {
    String userName = userField.getText();
    String pass = passField.getText();
    //Checking if Employee Tab username and password fields are empty.
    if (userName.isEmpty() || pass.isEmpty()) {
      //Error message
      signUpLbl.setText("Please Enter an Input for Username and Password.");
    } else {
      Employee e1 = new Employee(userName, pass);
      //Correct message
      signUpLbl.setText("Registration Successful!");
      try {
        //Inserts this Employee's username, password into Employee table using a prepared statement
        final String sql = "INSERT INTO Employee (user_name, pass_word) VALUES ( ?, ?);";
        ps = conn.prepareStatement(sql);
        ps.setNString(1, e1.username);
        ps.setNString(2, e1.password);
        ps.executeUpdate();
      } catch (SQLException se) {
        se.printStackTrace();
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.show();
      } catch (Exception e) {
        e.printStackTrace();
      }
      userField.setText("");
      passField.setText("");
      newEmpTA.setText(e1.toString());
    }
  }
}