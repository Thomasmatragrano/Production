/**
 * Represents a product registered for production.
 * Configures general Product features.
 * * @author Thomas Matragrano
 */
public abstract class Product implements Item {
    int id;
    ItemType Type;
    String manufacturer;
    String name;

    /**
     * Sets abstract Product information.
     * @param name This Product's name.
     * @param manufacturer This Product's manufacturer.
     * @param T This Product's Media Type.
     */
    public Product(String name, ItemType T, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.Type = T;
    }

    /**
     * Gets this Product's ID.
     * @return this Product's ID.
     */
    public int getId() { return id; }

    /**
     * Sets this Product's name.
     * @param s This Product's name.
     */
    public void setName(String s) { name = s; }

    /**
     * Gets this Product's name.
     * @return this Product's name.
     */
    public String getName() { return name; }

    /**
     * Sets this Product's manufacturer.
     * @param s This Product's manufacturer.
     */
    public void setManufacturer(String s) { manufacturer = s; }

    /**
     * Gets this Product's manufacturer.
     * @return this product's manufacturer.
     */
    public String getManufacturer() { return manufacturer; }

    /**
     * Sets and displays the Product details.
     * @return a String representing this Product's information.
     */
    public String toString() {
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + Type;
    }
}
