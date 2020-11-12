import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

/**
 * JavaFX event handling and input registry.
 * Establishes Database Connectivity.
 *
 * @author Thomas Matragrano
 */
public class Controller {
    ObservableList<Product> productLine = FXCollections.observableArrayList();
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
    private TextArea taOutput;
    @FXML
    private ComboBox<String> quantityComboBox;
    @FXML
    private ChoiceBox<String> itemTypeChoiceBox;
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement ps = null;

    /**
     * Establishes Database Connectivity.
     *
     * @param
     * @return void
     */
    public void initializeDB() {
        //  Database credentials
        final String JDBC_DRIVER = "org.h2.Driver";
        final String DB_URL = "jdbc:h2:./resources/ProductionDB";
        final String USER = "";
        final String PASS = "";
        try {
            //Register JDBC driver
            Class.forName(JDBC_DRIVER);
            //Open a connection
            conn = DriverManager.getConnection(DB_URL);
            stmt = conn.createStatement();
            //Display Full list of products in the tableview and listview
            String fullList = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                productLine.add(new Product(rs.getString("Name"), ItemType.valueOf(rs.getString("Type")), rs.getString("Manufacturer")) {
                });
                chooseProductLV.getItems().addAll(rs.getString("Name"));
            }
            //Display Full list of Production Record in the textarea
            fullList = "SELECT * FROM PRODUCTIONRECORD";
            rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                productionTA.appendText("Prod. Num: " + rs.getInt("production_num")+" Product ID: " + rs.getInt("product_id")+" Serial Num: "+rs.getNString("serial_num")+" Date:" + rs.getDate("date_produced")+"\n");
            }
        } catch (SQLException e) {
            taOutput.appendText(e.toString());
        } catch (ClassNotFoundException e) {
            taOutput.appendText(e.toString());
        }
    }

    /**
     * Test Method for Multimedia Functionality
     *
     * @param
     * @return void
     */
    public static void testMultimedia() {
        AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
        Screen newScreen = new Screen("720x480", 40, 22);
        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
        ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
        productList.add(newAudioProduct);
        productList.add(newMovieProduct);
        for (MultimediaControl p : productList) {
            System.out.println(p);
            p.play();
            p.stop();
            p.next();
            p.previous();
        }
    }

    /**
     * Calls to establish Database Connectivity.
     * Populates JavaFX ChoiceBox and ComboBox Components.
     *
     * @param
     * @return void
     */
    public void initialize() {
        initializeDB();
        //Populates the Item Type ChoiceBox
        for (ItemType I : ItemType.values())
            itemTypeChoiceBox.getItems().addAll(String.valueOf(I));
        itemTypeChoiceBox.getSelectionModel().selectFirst();
        //Populates the Quantity ComboBox
        quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        quantityComboBox.getSelectionModel().selectFirst();
        quantityComboBox.setEditable(true);
        //Setup Product Line Table
        tableViewName.setCellValueFactory(new PropertyValueFactory("Name"));
        tableViewType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Type.type));
        tableVIewManufacturer.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
        tableView.setItems(productLine);
    }

    /**
     * Control for "Record Production" Button Click.
     *Inserts ProductionRecord objects into the PRODUCTIONRECORD Database
     * @param actionEvent
     * @return void Prints to the console
     */
    public void recordProduction(ActionEvent actionEvent) {
        // Product p = new Product(name,type,manufacturer);
        int id = 0;
        ProductionRecord pr = new ProductionRecord(id);
        java.util.Date D = pr.getProdDate();
        String serialNum = pr.getSerialNum();
        id = pr.getProductID();
        int prodNum = pr.getProductionNum();

        try {
            final String sql = "INSERT INTO ProductionRecord (production_num, product_id, serial_num,date_produced) VALUES ( ?, ?, ?,?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, prodNum);
            ps.setInt(2, id);
            ps.setString(3, serialNum);
            ps.setDate(4, java.sql.Date.valueOf(java.time.LocalDate.now()));
            ps.executeUpdate();
            //Prints Full List of Production Record to the Console
            stmt = conn.createStatement();
            String fullList = "SELECT * FROM PRODUCTIONRECORD";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                System.out.println("Prod. Num: " + rs.getString(1) + "\nID: " + rs.getString(2) + "\nSerial Num: " + rs.getString(3) + "\nDate: " + rs.getString(4));
               // productionTA.appendText(pr.toString());
            }
            //clean up environment
            //ps.close();
            //stmt.close();
            //conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        productionTA.appendText(pr.toString());
        //productionTA.appendText(chooseProductLV.getSelectionModel().getSelectedItem() + " Num Produced: " + quantityComboBox.getValue()+"\n");
    }

    /**
     * Functionality for "Add Product" Button Click.
     * Inserts GUI input into Database.
     * Prints full list of Products to the Console.
     *
     * @param actionEvent
     * @return void Prints to the console
     */
    public void addProduct(ActionEvent actionEvent) {
ItemType it = ItemType.AUDIO;
        String name = lblNameOutput.getText();
        String manufacturer = lblManufacturerOutput.getText();
        final String type = itemTypeChoiceBox.getSelectionModel().getSelectedItem();
        try {
            //Inserts variables name, manufacturer, type into ProductionDB using a prepared statement
            final String sql = "INSERT INTO Product (name, type, manufacturer) VALUES ( ?, ?, ? );";
            ps = conn.prepareStatement(sql);
            ps.setNString(1, name);
            ps.setNString(2, type);
            ps.setNString(3, manufacturer);
            ps.executeUpdate();
            //Prints updated Full List of Products to the tableview and listview
            stmt = conn.createStatement();
            String fullList = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                productLine.add(new Product(rs.getString("Name"), ItemType.valueOf(rs.getString("Type")), rs.getString("Manufacturer")) {
                });
                tableView.setItems(productLine);
                chooseProductLV.getItems().addAll(rs.getString("Name"));
            }
            //clean up environment
            // ps.close();
            // stmt.close();
            // conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
