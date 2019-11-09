package io.github.Gamerdude38;

/**
 * Represents a screen, which consists of a resolution, a refresh rate, and a response time.
 *
 * @author John Maurer
 */
public class Screen implements ScreenSpec {

  /**
   * A <code>String</code> representing the resolution of the screen, usually denoted by the
   * dimensions of the screen.
   */
  String resolution;
  /** An integer representing the refresh rate of the screen. */
  int refreshRate;
  /** An integer representing the response time of the screen. */
  int responseTime;

  /**
   * Constructs a <code>Screen</code> object and sets the values of the resolution, the refresh
   * rate, and the response time of the screen.
   *
   * @param resolution the resolution of the screen.
   * @param refreshRate the refresh rate of the screen.
   * @param responseTime the response time of the screen.
   */
  public Screen(String resolution, int refreshRate, int responseTime) {
    this.resolution = resolution;
    this.refreshRate = refreshRate;
    this.responseTime = responseTime;
  }

  /**
   * Gets the resolution of the screen.
   *
   * @return the resolution of the screen.
   */
  @Override
  public String getResolution() {
    return resolution;
  }

  /**
   * Gets the refresh rate of the screen.
   *
   * @return the refresh rate of the screen.
   */
  @Override
  public int getRefreshRate() {
    return refreshRate;
  }

  /**
   * Gets the response time of the screen.
   *
   * @return the response time of the screen.
   */
  @Override
  public int getResponseTime() {
    return responseTime;
  }

  /**
   * Formats a <code>String</code> to display the name, manufacturer, and type of an item on
   * separate lines.
   *
   * @return a printable <code>String</code> of a screen.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Resolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshRate
        + "\nResponse time: "
        + responseTime;
  }
}
