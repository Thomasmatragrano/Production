/**
 * Represents the screen on a media device.
 * Used for subclasses of Product that require a screen.
 *
 * @author Thomas Matragrano
 */
public class Screen implements ScreenSpec {
  private final String resolution;
  private final int refreshRate;
  private final int responseTime;

  /**
   * Sets Screen specifications.
   *
   * @param resolution   This Screen's resolution.
   * @param refreshRate  This Screen's refreshRate.
   * @param responseTime This Screen's responseTime.
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * Gets this Screen's resolution.
   *
   * @return this Screen's resolution.
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * Gets this Screen's refresh rate.
   *
   * @return this Screen's refresh rate.
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * Gets this Screen's response time.
   *
   * @return this Screen's response time.
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Prints this Screen's information.
   *
   * @return a concatenated String holding Screen information.
   */
  public String toString() {
    return "Resolution: "
            + resolution
            + "\nRefresh Rate: "
            + refreshRate
            + "\nResponse Time: "
            + responseTime;
  }
}
