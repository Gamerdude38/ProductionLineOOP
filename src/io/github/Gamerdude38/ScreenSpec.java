package io.github.Gamerdude38;

/**
 * An interface that represents what information a screen should have about itself.
 *
 * @author John Maurer
 */
public interface ScreenSpec {

  /**
   * Gets the resolution of the screen.
   *
   * @return the resolution of the screen.
   */
  public String getResolution();

  /**
   * Gets the refresh rate of the screen.
   *
   * @return the refresh rate of the screen.
   */
  public int getRefreshRate();

  /**
   * Gets the response time of the screen.
   *
   * @return the response time of the screen.
   */
  public int getResponseTime();
}
