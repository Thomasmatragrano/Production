
/**
 * Class extension of Product used for audio players.
 * An AudioPlayer implements Multimedia Controls.
 *
 * @author Thomas Matragrano
 */
public class AudioPlayer extends Product implements MultimediaControl {
  private final String supportedAudioFormats;
  private final String supportedPlaylistFormats;

  /**
   * Gets Strings from Product constructor.
   * Sets AudioPlayer product information.
   *
   * @param name                     this Product's name.
   * @param manufacturer             this Product's manufacturer.
   * @param supportedAudioFormats    this Product's supported Audio Formats.
   * @param supportedPlaylistFormats this Product's supported Playlist Formats.
   */
  public AudioPlayer(String name, String manufacturer, String supportedAudioFormats,
                     String supportedPlaylistFormats) {
    super(name, ItemType.valueOf(supportedAudioFormats), manufacturer);
    this.manufacturer = manufacturer;
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
    type = ItemType.AUDIO;
  }

  /**
   * This void method shows the user is playing audio.
   */
  @Override
  public void play() {
    System.out.println("Playing");
  }

  /**
   * This void method shows the user stopped playing audio.
   */
  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  /**
   * This void method shows the user is selecting the previous track.
   */
  @Override
  public void previous() {
    System.out.println("Previous");
  }

  /**
   * This void method shows the user is selecting the next track.
   */
  @Override
  public void next() {
    System.out.println("Next");
  }

  /**
   * Formats Audio Player information to be printed.
   *
   * @return Returns the string value for an Audio Player.
   */
  public String toString() {
    return super.toString()
            + "\n"
            + "Supported Audio Formats: "
            + supportedAudioFormats
            + "\n"
            + "Supported Playlist Formats: "
            + supportedPlaylistFormats;
  }
}
