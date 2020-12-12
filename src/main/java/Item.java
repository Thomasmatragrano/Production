/**
 * @author Thomas Matragrano
 * Interface that declares methods which will be used by the product parent and child classes
 */
public interface Item {

     /**
      * Gets the ID of a Product.
      * @return the ID of a Product.
      */
     int getId();

     /**
      * Sets a Product's name.
      * @param s The Product's name.
      */
     void setName(String s);

     /**
      * Gets the name of a Product.
      * @return the name of a Product.
      */
     String getName();

     /**
      * Sets a Product's manufacturer.
      * @param s the manufacturer of a Product.
      */
     void setManufacturer(String s);

     /**
      * Gets the manufacturer of a Product.
      * @return the manufacturer of a Product.
      */
     String getManufacturer();
}
