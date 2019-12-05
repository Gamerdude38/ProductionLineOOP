package io.github.Gamerdude38;

/**
 * An abstract type that provides the basic functionality of all the items in a production line.
 *
 * @author John Maurer
 */
public abstract class Product implements Item {

  /** An integer representing the item's identification number. */
  private int id;

  /** A <code>String</code> representing the item's type. */
  private ItemType type;

  /** A <code>String</code> representing the manufacturer of the item. */
  private String manufacturer;

  /** A <code>String</code> representing the name of the item. */
  private String name;

  /**
   * Constructs a <code>Product</code> representing a real product with a name, manufacturer, and a
   * type.
   *
   * @param name the name of the product.
   * @param manufacturer the manufacturer of the product.
   * @param type the type of product, represented with the enum <code>ItemType</code>.
   */
  public Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Gets the value stored in <code>type</code>.
   *
   * @return the <code>ItemType</code> of the item.
   */
  public ItemType getType() {
    return type;
  }

  /**
   * Gets the value stored in <code>id</code>.
   *
   * @return the id of the item.
   */
  @Override
  public int getId() {
    return id;
  }

  /**
   * Gets the value stored in <code>name</code>.
   *
   * @return the name of the item.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Gets the value stored in <code>manufacturer</code>.
   *
   * @return the manufacturer of the item.
   */
  @Override
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * Sets the <code>name</code> field to the <code>String</code> specified.
   *
   * @param name the <code>String</code> to set the <code>name</code> field to.
   */
  @Override
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Sets the <code>manufacturer</code> field to the <code>String</code> specified.
   *
   * @param manufacturer the <code>String</code> to set the <code>manufacturer</code> field to.
   */
  @Override
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * Formats a <code>String</code> to display the name, manufacturer, and type of an item on
   * separate lines.
   *
   * @return a printable <code>String</code> of an item.
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "Name: " + name + "\nManufacturer: " + manufacturer + "\nType: " + type;
  }
}
