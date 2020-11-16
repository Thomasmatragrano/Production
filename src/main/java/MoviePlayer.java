import java.lang.reflect.Type;

/**
 * @author Thomas Matragrano
 * @brief Constructs an object for Movie Players. Overrides all methods implemented from Multimedia
 */
public class MoviePlayer extends Product implements MultimediaControl {

    private Screen myScreen;
    private MonitorType monType;


    /**
     * This constructor sets the name, manufacturer, Screen, and MonitorType
     *
     * @param name
     * @param manufacturer
     * @param monType
     * @param myScreen
     */
    public MoviePlayer(String name, String manufacturer, Screen myScreen, MonitorType monType) {
        super(name, ItemType.valueOf(String.valueOf(monType)), manufacturer);
        this.name = name;
        this.manufacturer = manufacturer;
        this.myScreen = myScreen;
        Type = ItemType.VISUAL;
    }

    /**
     * This void method invokes the movieplayer play action
     *
     * @return void prints to the console
     */
    @Override
    public void play() {
        System.out.println("Playing movie");
    }

    /**
     * This void method invokes the movieplayer stop action
     *
     * @return void prints to the console
     */
    @Override
    public void stop() {
        System.out.println("Stopping movie");
    }

    /**
     * This void method invokes the movieplayer next action
     *
     * @return void prints to the console
     */
    @Override
    public void next() {
        System.out.println("Next movie");
    }

    /**
     * This void method invokes the movieplayer previous action
     *
     * @return void prints to the console
     */
    @Override
    public void previous() {
        System.out.println("Previous movie");
    }

    /**
     * This String method displays the movieplayer information
     *
     * @return a concatenated String
     */
    public String toString() {
        return super.toString() + "\n" + "Screen: " + myScreen + "\n" + "Monitor Type: " + monType;
    }
}
