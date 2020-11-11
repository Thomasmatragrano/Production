/**
 * @author Thomas Matragrano
 * @brief Sets product type constants to be implemented.
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
     * This constructor sets values for the enum constants.
     *
     * @param Type,Code
     */
    ItemType(String Type, String Code) {
        code = Code;
        type = Type;
    }
    public String getItemType(){
        return type;
    }
}