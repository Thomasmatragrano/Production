
/**
 * Represents a movie player available for production.
 * A movie player is a product.
 *
 * @author Thomas Matragrano
 */
public class MoviePlayer extends Product implements MultimediaControl {
  private final Screen myScreen;
  private final MonitorType monType;

  /**
   * This constructor sets the name, manufacturer, Screen, and MonitorType.
   *
   * @param name         This MoviePlayer's name.
   * @param manufacturer This MoviePlayer's manufacturer.
   * @param monType      This MoviePlayer's monitor type.
   * @param myScreen     This MoviePlayer's Screen specifications.
   */
  public MoviePlayer(String name, String manufacturer, Screen myScreen, MonitorType monType) {
    super(name, ItemType.valueOf(String.valueOf(monType)), manufacturer);
    this.name = name;
    this.manufacturer = manufacturer;
    this.myScreen = myScreen;
    this.monType = monType;
    type = ItemType.VISUAL;
  }

  /**
   * This void method shows the user is watching a movie.
   */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * This void method shows the user is stopping a movie.
   */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * This void method shows the user is selecting the next movie.
   */
  @Override
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * This void method shows the user is selecting the previous movie.
   */
  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * Prints this MoviePlayer's information.
   *
   * @return a concatenated String with this MoviePlayer's information.
   */
  public String toString() {
    return super.toString() + "\n" + "Screen: " + myScreen + "\n" + "Monitor Type: " + monType;
  }
}
