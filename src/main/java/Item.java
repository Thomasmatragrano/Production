/**
 * @author Thomas Matragrano
 * @brief Interface that declares methods which will be used by the product parent and child classes
 */
public interface Item {
    public int getId();

    public void setName(String s);

    public String getName();

    public void setManufacturer(String s);

    public String getManufacturer();
}
