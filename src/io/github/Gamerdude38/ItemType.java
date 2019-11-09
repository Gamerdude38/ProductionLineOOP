package io.github.Gamerdude38;

/**
 * A specifier for a type of item in production.
 *
 * @author John Maurer
 */
public enum ItemType {
  /** Represents an audio device. */
  AUDIO("AU"),
  /** Represents a visual device. */
  VISUAL("VI"),
  /** Represents a mobile audio device. */
  AUDIOMOBILE("AM"),
  /** Represents a mobile visual device. */
  VISUALMOBILE("VM");

  /** The 2 character code for the device. */
  private final String code;

  /**
   * Constructs an <code>ItemType</code> using the <code>String</code> specified to set the <code>
   * code</code> field.
   *
   * @param code the 2 character code for the device.
   */
  ItemType(String code) {
    this.code = code;
  }

  /**
   * Gets the value stored in <code>code</code>.
   *
   * @return the code of the item.
   */
  public String getCode() {
    return code;
  }
}
