import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Type;
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
            String fullList = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                productLine.add(new Product(rs.getString("Name"), ItemType.valueOf(rs.getString("Type")), rs.getString("Manufacturer")) {
                });
                chooseProductLV.getItems().addAll(rs.getString("Name"));
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

        AudioPlayer newAudioProduct = new AudioPlayer("DP-X1A", "Onkyo",

                "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");

        Screen newScreen = new Screen("720x480", 40, 22);

        MoviePlayer newMovieProduct = new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen,

                MonitorType.LCD);

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
        //Populates the Item Type ChoiceBox
        for (ItemType I : ItemType.values())
            itemTypeChoiceBox.getItems().addAll(String.valueOf(I));
        itemTypeChoiceBox.getSelectionModel().selectFirst();
        //Populates the Quantity ComboBox
        quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        quantityComboBox.getSelectionModel().selectFirst();
        quantityComboBox.setEditable(true);



        tableViewName.setCellValueFactory(new PropertyValueFactory("Name"));
        tableViewType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().Type.type));
        tableVIewManufacturer.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
        tableView.setItems(productLine);

        initializeDB();

    }


    /**
     * Control for "Record Production" Button Click.
     *
     * @param actionEvent
     * @return void Prints to the console
     */
    public void recordProduction(ActionEvent actionEvent) {
        /*
        initializeDB();

        int productID;
        String serialNumber;
        Date dateProduced;
        try {
            final String sql = "INSERT INTO ProductionRecord (name, type, manufacturer) VALUES ( ?, ?, ?,?);";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, productID);
            ps.setNString(2, serialNumber);
            ps.setDate(3, dateProduced);
            ps.executeUpdate();
            //Prints Full List of Products to the Console
            stmt = conn.createStatement();
            String fullList = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {
                System.out.println("ID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nType " + rs.getString(3) + "\nManufacturer " + rs.getString(4));
                //productionTA.setText("ID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nType " + rs.getString(3) + "\nManufacturer " + rs.getString(4));
            }
            //clean up environment
            ps.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

         */
        //productionTA.appendText(chooseProductLV.getSelectionModel().getSelectedItem() + "\n");

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
            //Prints Full List of Products to the Console
            stmt = conn.createStatement();

            String fullList = "SELECT * FROM PRODUCT";
            ResultSet rs = stmt.executeQuery(fullList);
            while (rs.next()) {


                System.out.println("ID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nType " + rs.getString(3) + "\nManufacturer " + rs.getString(4));
                //productionTA.setText("ID: " + rs.getString(1) + "\nName: " + rs.getString(2) + "\nType " + rs.getString(3) + "\nManufacturer " + rs.getString(4));
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
