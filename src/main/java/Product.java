/**
 * @author Thomas Matragrano
 * @brief Abstract, parent class that sets, gets, and displays general product features
 */
public abstract class Product implements Item {
    int id;
    ItemType type;
    String manufacturer;
    String name;

    /**
     * This constructor sets the name and audio type
     *
     * @param name
     */
    public Product(String name) {

        this.name = name;
        type = ItemType.AUDIO;
    }

    public int getId() {
        return id;
    }

    public void setName(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }

    public void setManufacturer(String s) {
        manufacturer = s;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * This String method displays the Product details.
     *
     * @return a concatenated String
     */
    public String toString() {
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
    }

    /**
     * @author Thomas Matragrano
     * @brief This widget class extends Product as to create objects from it to test.
     */
    public static class Widget extends Product {

        /**
         * This constructor takes parameters from product to instantiate Product objects.
         *
         * @param manufacturer
         * @param name
         * @param T
         */
        public Widget(String name, String manufacturer, String T) {
            super(name);
            this.manufacturer = manufacturer;
            T = type.type;
        }
    }
}