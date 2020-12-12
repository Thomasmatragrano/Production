
import java.util.Date;

/**
 * Represents a production record of Products.
 * Gets
 * @author Thomas Matragrano
 */
public class ProductionRecord {
    int productionNumber;
    private final int productID;
    private final String serialNumber;
    private Date dateProduced;

    /**
     * Sets this ProductionRecord's Product information.
     * @param productID This ProductionRecord's product ID.
     */
    public ProductionRecord(int productID) {
        this.productID = productID;
        productionNumber = getProductionNum();
        serialNumber = getSerialNum();
        dateProduced = getProdDate();
    }

    /**
     * Gets this ProductionRecord's production number.
     * @return this ProductionRecord's production number.
     */
    public int getProductionNum() { return productionNumber; }

    /**
     * Gets this ProductionRecord's serial number.
     * @return this ProductionRecord's serial number.
     */
    public String getSerialNum() { return serialNumber; }

    /**
     * Gets this ProductionRecord's production date.
     * @return this ProductionRecord's production date.
     */
    public Date getProdDate() {
        dateProduced = java.sql.Date.valueOf(java.time.LocalDate.now());
        return dateProduced;
    }

    /**
     * Prints this ProductionRecord's information.
     * @return a concatenated String of ProductionRecord members.
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

