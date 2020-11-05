import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.fxml.FXML;

import java.sql.*;

public class Controller {
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

    public void initialize() {
        initializeDB();
        //Populates the Item Type ChoiceBox
        itemTypeChoiceBox.getItems().addAll("AUDIO", "VIDEO");
        itemTypeChoiceBox.getSelectionModel().selectFirst();
        //Populates the Quantity ComboBox
        quantityComboBox.getItems().addAll("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");
        quantityComboBox.getSelectionModel().selectFirst();
        quantityComboBox.setEditable(true);
    }

    private void initializeDB() {
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
        } catch (SQLException e) {
            taOutput.appendText(e.toString());
        } catch (ClassNotFoundException e) {
            taOutput.appendText(e.toString());
        }
    }

    public void recordProduction(ActionEvent actionEvent) {
    }

    public void addProduct(ActionEvent actionEvent) {
        initialize();
        String name = lblNameOutput.getText();
        String manufacturer = lblManufacturerOutput.getText();
        String type = itemTypeChoiceBox.getSelectionModel().getSelectedItem();
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
    }
}
