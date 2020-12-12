/**
 * Sets product type constants to be implemented.
 * ItemType takes a type and a code.
 *
 * @author Thomas Matragrano
 */
public enum ItemType {
  AUDIO("Audio", "AU"),
  VISUAL("Visual", "VI"),
  AUDIO_MOBILE("AudioMobile", "AM"),
  VISUAL_MOBILE("VisualMobile", "VM"),
  ;
  protected String code;
  protected String type;

  /**
   * Sets This ItemType's type and code for the enum constants.
   *
   * @param type This ItemType's full typing.
   * @param code This ItemType's two letter code.
   */
  ItemType(String type, String code) {
    this.code = code;
    this.type = type;
  }
}