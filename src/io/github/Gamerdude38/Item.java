package io.github.Gamerdude38;

/**
 * An interface that represents what an item in a production line should consist of.
 *
 * @author John Maurer
 */
public interface Item {

  /**
   * Gets the id of the item.
   *
   * @return the id of the item.
   */
  int getId();

  /**
   * Gets the name of the item.
   *
   * @return the name of the item.
   */
  String getName();

  /**
   * Gets the manufacturer of the item.
   *
   * @return the name of the manufacturer.
   */
  String getManufacturer();

  /**
   * Sets the name of the item using a <code>String</code>.
   *
   * @param name the <code>String</code> to set the name to.
   */
  void setName(String name);

  /**
   * Sets the name of the manufacturer using a <code>String</code>.
   *
   * @param manufacturer the <code>String</code> to set the manufacturer's name to.
   */
  void setManufacturer(String manufacturer);
}
