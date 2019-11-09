package io.github.Gamerdude38;

/**
 * Represents a physical movie player.
 *
 * @author John Maurer
 */
public class MoviePlayer extends Product implements MultimediaControl {

  /** A <code>Screen</code> object representing the specifications of the movie player's screen. */
  Screen screen;
  /** A <code>MonitorType</code> representing the type of monitor the movie player has. */
  MonitorType monitorType;

  /**
   * Constructs a <code>MoviePlayer</code> object representing a real movie player. A <code>
   * MoviePlayer</code> has the properties of a <code>Product</code> object with the addition of a
   * <code>Screen</code> object and a <code>MonitorType</code> enum representing the screen
   * specifications and monitor type of the movie player, respectfully.
   *
   * @param name the name of the movie player.
   * @param manufacturer the manufacturer of the movie player.
   * @param screen the screen specifications of the movie player.
   * @param monitorType the monitor type of the movie player.
   */
  public MoviePlayer(String name, String manufacturer, Screen screen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.screen = screen;
    this.monitorType = monitorType;
  }

  /**
   * Plays the current movie. Verification that the movie is "playing" is displayed in the console.
   */
  @Override
  public void play() {
    System.out.println("Playing movie");
  }

  /**
   * Stops the current movie. Verification that the movie has "stopped" is displayed in the console.
   */
  @Override
  public void stop() {
    System.out.println("Stopping movie");
  }

  /**
   * Sets the current movie to the previous movie. Verification that the previous movie has been
   * "selected" is displayed in the console.
   */
  @Override
  public void previous() {
    System.out.println("Previous movie");
  }

  /**
   * Sets the current movie to the next movie. Verification that the next movie has been "selected"
   * is displayed in the console.
   */
  @Override
  public void next() {
    System.out.println("Next movie");
  }

  /**
   * Formats a <code>String</code> to display the name, manufacturer, type, screen specifications,
   * and monitor type on separate lines.
   *
   * @return a printable <code>String</code> of a <code>MoviePlayer</code>.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return super.toString()
        + "\nScreen: "
        + screen.toString()
        + "\nMonitor type: "
        + monitorType.name();
  }
}
