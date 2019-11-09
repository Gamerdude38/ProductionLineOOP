package io.github.Gamerdude38;

/**
 * An interface that requires media players to have basic media player functionality.
 *
 * @author John Maurer
 */
public interface MultimediaControl {
  /**
   * Plays the current track. Verification that the track is "playing" is displayed in the console.
   */
  public void play();

  /**
   * Stops the current track. Verification that the track has "stopped" is displayed in the console.
   */
  public void stop();

  /**
   * Sets the current track to the previous track. Verification that the previous track has been
   * "selected" is displayed in the console.
   */
  public void previous();

  /**
   * Sets the current track to the next track. Verification that the next track has been "selected"
   * is displayed in the console.
   */
  public void next();
}
