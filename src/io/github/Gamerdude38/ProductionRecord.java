package io.github.Gamerdude38;

import java.util.Date;

/**
 * Represents an instance of a product record, which includes the production number, a product ID, a
 * serial number, and a date it was produced.
 *
 * @author John Maurer
 */
public class ProductionRecord {
  /** An integer representing a product's production number. */
  private int productionNumber;
  /** An integer representing a product's ID. */
  private int productID;
  /** A <code>String</code> representing a product's serial number. */
  private String serialNumber;
  /** A <code>Date</code> representing when the product was produced. */
  private Date dateProduced;
  /**
   * An integer accessible to all objects of <code>ProductionRecord</code> that keeps track of the
   * serial number for all products.
   */
  private static int serialIncrement = 0;

  /**
   * Constructs a <code>ProductionRecord</code> using only a product ID. The production number and
   * serial number are set to zero, and the date is set to the date on the user's computer at the
   * time of the call.
   *
   * @param productID the ID of the product.
   */
  public ProductionRecord(int productID) {
    productionNumber = 0;
    this.productID = productID;
    serialNumber = "0";
    dateProduced = new Date();
  }

  /**
   * Constructs a <code>ProductionRecord</code> using a <code>Product</code> and a count. Using the
   * information in the <code>Product</code> object, the program creates <code>count</code>
   * production records for the same product.
   *
   * @param product a <code>Product</code> representing the entire properties of the product.
   * @param count the number of products to be added to the production record.
   */
  public ProductionRecord(Product product, int count) {
    productionNumber = 0;
    productID = product.getId();
    serialNumber =
        product.getManufacturer().substring(0, 3)
            + product.getType().getCode()
            + String.format("%05d", serialIncrement);
    dateProduced = new Date();

    incrementSerialNum();
  }

  public static void incrementSerialNum(){
    serialIncrement++;
  }

  /**
   * Constructs a <code>ProductionRecord</code> with a specified production number, product ID,
   * serial number, and date.
   *
   * @param productionNumber the production number of the product.
   * @param productID the ID of the product.
   * @param serialNumber the serial number of the product.
   * @param dateProduced the date the product was produced.
   */
  public ProductionRecord(
      int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;

    // To truly make "dateProduced" a private variable, we need to make a defensive copy.
    // To do so, we instantiate a new Date by constructing it with the same data as the old date.
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Gets the value stored in <code>productionNumber</code>.
   *
   * @return the production number of the product.
   */
  public int getProductionNumber() {
    return productionNumber;
  }

  /**
   * Gets the value stored in <code>productID</code>.
   *
   * @return the product ID of the product.
   */
  public int getProductID() {
    return productID;
  }

  /**
   * Gets the value stored in <code>serialNumber</code>.
   *
   * @return the serial number of the product.
   */
  public String getSerialNumber() {
    return serialNumber;
  }

  /**
   * Gets the value stored in <code>dateProduced</code>.
   *
   * @return the date the product was produced.
   */
  public Date getDateProduced() {
    return new Date(dateProduced.getTime());
  }

  /**
   * Sets the <code>productionNumber</code> field to the value specified.
   *
   * @param productionNumber the number to set the <code>productionNumber</code> to.
   */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * Sets the <code>productID</code> field to the value specified.
   *
   * @param productID the number to set the <code>productID</code> to.
   */
  public void setProductID(int productID) {
    this.productID = productID;
  }

  /**
   * Sets the <code>serialNumber</code> field to the <code>String</code> specified.
   *
   * @param serialNumber the <code>String</code> to set the <code>serialNumber</code> to.
   */
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * Sets the <code>dateProduced</code> field to the <code>Date</code> specified.
   *
   * @param dateProduced the <code>Date</code> to set the <code>dateProduced</code> to.
   */
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = new Date(dateProduced.getTime());
  }

  /**
   * Formats a <code>String</code> to display the production number, the product ID, the serial
   * number, and the date the product was produced on the same line.
   *
   * @return a printable <code>String</code> of a production record.
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "Prod. Num: "
        + productionNumber
        + " Product ID: "
        + productID
        + " Serial Num: "
        + serialNumber
        + " Date: "
        + dateProduced.toString();
  }
}
