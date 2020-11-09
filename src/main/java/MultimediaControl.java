/**
 * @author Thomas Matragrano
 * @brief Interface that declares playback functions to be used on product subclasses
 */
public interface MultimediaControl {
    public void play();

    public void stop();

    public void previous();

    public void next();
}
