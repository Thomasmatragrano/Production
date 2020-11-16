import java.lang.reflect.Type;

/**
 * @author Thomas Matragrano
 * @brief Abstract, parent class that sets, gets, and displays general product features
 */
public abstract class Product implements Item {
    int id;
    ItemType Type;
    String manufacturer;
    String name;

    /**
     * This constructor sets the name manufacturer and audio type
     *
     * @param name
     */
    public Product(String name, ItemType T, String manufacturer) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.Type = T;
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
        return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + Type;
    }


    /**
     * @author Thomas Matragrano
     * @brief This widget class extends Product as to create objects from it to test.
     */
    public static class Widget extends Product {

        /**
         * This constructor takes parameters from product to instantiate Product objects.
         *
         * @param name
         * @param T
         * @param manufacturer
         */
        public Widget(String name, ItemType T, String manufacturer) {
            super(name, T, manufacturer);
            //   this.Type = T;
            //   this.manufacturer = manufacturer;


        }


    }

}
