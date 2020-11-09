/**
 * @author Thomas Matragrano
 * @brief Class extension of Product used for audio players
 */
public class AudioPlayer extends Product implements MultimediaControl {

    private String audioSpecification;
    private String mediaType;

    /**
     * @brief Constructor for AudioPlayer that takes parameters for name, manufacturer,
     *     audioSpecification, and MediaType.
     */
    public AudioPlayer(
            String name, String manufacturer, String audioSpecification, String mediaType) {
        super(name);
        this.audioSpecification = audioSpecification;
        this.manufacturer = manufacturer;
        this.mediaType = mediaType;
        type = ItemType.AUDIO;
    }

    /** This void method shows the user is playing media. */
    @Override
    public void play() {
        System.out.println("Playing");
    }
    /** This void method shows the user stopped playing media. */
    @Override
    public void stop() {
        System.out.println("Stopping");
    }
    /** This void method shows the user is selecting previous media */
    @Override
    public void previous() {
        System.out.println("Previous");
    }
    /** This void method shows the user is selecting the next media */
    @Override
    public void next() {
        System.out.println("Next");
    }
    /** @return Returns a string value for audioplayer. */
    public String toString() {
        return super.toString()
                + "\n"
                + "Supported Audio Formats: "
                + audioSpecification
                + "\n"
                + "Supported Playlist Formats: "
                + mediaType;
    }
}
