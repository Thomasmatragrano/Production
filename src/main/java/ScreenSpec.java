/**
 * @author Thomas Matragrano
 * @brief Interface that declares the methods for products that require a screen
 */
public interface ScreenSpec {

    public String getResolution();

    public int getRefreshRate();

    public int getResponseTime();
}
