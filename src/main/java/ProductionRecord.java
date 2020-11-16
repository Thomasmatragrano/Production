
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Thomas Matragrano
 * @brief creates a production record with a production number, ID, serial number, and date produced
 */
public class ProductionRecord {
    int productionNumber;
    private int productID;
    private String serialNumber;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private Date dateProduced = java.sql.Date.valueOf(java.time.LocalDate.now());

    /**
     * This constructor sets the product ID, product number, serial number, and date Produced.
     *
     * @param productID
     */
    public ProductionRecord(int productID) {
        this.productID = productID;
        productionNumber = getProductionNum();
        serialNumber = getSerialNum();
        dateProduced = getProdDate();

    }

    /**
     * This constructor sets the product ID, product number, serial number, and date Produced.
     *
     * @param dateProduced
     * @param productID
     * @param productionNumber
     * @param serialNumber
     */
    public ProductionRecord(
            int productionNumber, int productID, String serialNumber, Date dateProduced) {
        this.productID = productID;
        this.productionNumber = productionNumber;
        //this.serialNumber = serialNumber;
        dateProduced = new Date();
        this.dateProduced = dateProduced;
    }

    public int getProductionNum() {
        return productionNumber;
    }

    public void setProductionNum(int productionNumber) {
        this.productionNumber = productionNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getSerialNum() {
        return serialNumber;
    }

    public void setSerialNum(String serialNumber) {
        this.serialNumber = serialNumber;

    }

    public Date getProdDate() {
        return dateProduced;
    }

    public void setProdDate(Date dateProduced) {
        this.dateProduced = dateProduced;
    }

    /**
     * This constructor creates a unique serial number
     *
     * @param itemCount
     * @param P
     */
    public ProductionRecord(Product P, int itemCount) {
        serialNumber = P.manufacturer.substring(0, 3) + P.Type.code + "0000" + itemCount;
//setSerialNum(serialNumber);
    }

    /**
     * This String method displays the production record information.
     *
     * @return a concatenated String
     */
    public String toString() {
        return "Prod. Num: "
                + productionNumber
                + " Product ID: "
                + productID
                + " Serial Num: "
                + serialNumber
                + " Date: "
                + dateProduced + "\n";
    }
}

