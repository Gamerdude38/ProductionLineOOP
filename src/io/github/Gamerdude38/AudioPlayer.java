package io.github.Gamerdude38;

/**
 * Represents a physical audio player.
 *
 * @author John Maurer
 */
public class AudioPlayer extends Product implements MultimediaControl {

  /** A <code>String</code> representing a list of supported audio formats. */
  private String supportedAudioFormats;

  /** A <code>String</code> representing a list of supported playlist formats. */
  private String supportedPlaylistFormats;

  /**
   * Constructs an <code>AudioPlayer</code> representing a real audio player. An <code>AudioPlayer
   * </code> has the properties of a <code>Product</code> object with the addition of two strings
   * representing audio formats and playlist formats.
   *
   * @param name the name of the audio player.
   * @param manufacturer the manufacturer of the audio player.
   * @param supportedAudioFormats a list of audio formats supported by the audio player.
   * @param supportedPlaylistFormats a list of playlist formats supported by the audio player.
   */
  public AudioPlayer(
      String name,
      String manufacturer,
      String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(name, manufacturer, ItemType.AUDIO);
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * Plays the current track. Verification that the track is "playing" is displayed in the console.
   */
  @Override
  public void play() {
    System.out.println("Playing audio");
  }

  /**
   * Stops the current track. Verification that the track has "stopped" is displayed in the console.
   */
  @Override
  public void stop() {
    System.out.println("Stopping audio");
  }

  /**
   * Sets the current track to the previous track. Verification that the previous track has been
   * "selected" is displayed in the console.
   */
  @Override
  public void previous() {
    System.out.println("Previous audio");
  }

  /**
   * Sets the current track to the next track. Verification that the next track has been "selected"
   * is displayed in the console.
   */
  @Override
  public void next() {
    System.out.println("Next audio");
  }

  /**
   * Formats a <code>String</code> to display the name, manufacturer, type, supported audio formats,
   * and supported playlist formats on separate lines.
   *
   * @return a printable <code>String</code> of an <code>AudioPlayer</code>.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return super.toString()
        + "\nSupported Audio Formats: "
        + supportedAudioFormats
        + "\nSupported Playlist Formats: "
        + supportedPlaylistFormats;
  }
}
