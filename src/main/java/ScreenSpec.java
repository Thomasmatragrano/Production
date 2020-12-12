/**
 * Declares methods for products that require a screen.
 * @author Thomas Matragrano
 */
public interface ScreenSpec {

    /**
     * Get's this ScreenSpec's resolution.
     * @return  this ScreenSpec's resolution.
     */
    String getResolution();

    /**
     * Get's this ScreenSpec's refresh rate.
     * @return this ScreenSpec's refresh rate.
     */
     int getRefreshRate();

    /**
     * Get's this ScreenSpec's response time.
     * @return this ScreenSpec's response time.
     */
     int getResponseTime();
}
