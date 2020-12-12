/**
 * Provides common playback functions to be implemented.
 *
 * @author Thomas Matragrano
 */
public interface MultimediaControl {

  /**
   * This void method shows the user is playing media.
   */
  void play();

  /**
   * This void method shows the user is stopping media.
   */
  void stop();

  /**
   * This void method shows the user is selecting the previous option.
   */
  void previous();

  /**
   * This void method shows the user is selecting the next option.
   */
  void next();
}
