package io.github.Gamerdude38;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * A class to provide the functionality of the entire program.
 *
 * @author John Maurer
 */
public class Controller implements Initializable {
  // In IntelliJ, Under the Analyze tab, Inspect Code. Get rid of all suspicious code. Also, Run
  // FindBugs
  // Include JavaDoc comments, and enforce the GoogleStyle (Ctrl + Alt + L). Run CheckStyle with
  // Google Checks

  /** Represents the "Add Product" <code>Button</code>, its functionality, and its properties. */
  @FXML private Button btnAddProduct;
  /**
   * Represents the "Record Production" <code>Button</code>, its functionality, and its properties.
   */
  @FXML private Button btnRecordProduction;
  /**
   * Represents the "Product Name" <code>TextField</code>, its functionality, and its properties.
   */
  @FXML private TextField txtProductName;
  /**
   * Represents the "Manufacturer" <code>TextField</code>, its functionality, and its properties.
   */
  @FXML private TextField txtManufacturer;
  /** Represents the "Item Type" <code>ChoiceBox</code>, its functionality, and its properties. */
  @FXML private ChoiceBox cboItemType;
  /**
   * Represents the "Choose Quantity" <code>ComboBox</code>, its functionality, and its properties.
   */
  @FXML private ComboBox cboChooseQuantity;
  /**
   * Represents the "Choose Product" <code>ListView</code>, its functionality, and its properties.
   */
  @FXML private ListView lstChooseProduct;
  /**
   * Represents the "Existing Products" <code>TableView</code>, its functionality, and its
   * properties.
   */
  @FXML private TableView tbvExistingProducts;
  /**
   * Represents the "Production Log" <code>TextArea</code>, its functionality, and its properties.
   */
  @FXML private TextArea txaProductionLog;
  /** Represents all of the products that can be recorded for production. */
  private ObservableList<Product> productLine = FXCollections.observableArrayList();
  /** Represents all of the production records on file. */
  private ArrayList<ProductionRecord> productionLog = new ArrayList<ProductionRecord>();
  /** A constant <code>String</code> defining the JDBC Driver to be used. */
  private static final String JDBC_DRIVER = "org.h2.Driver";
  /** A constant <code>String</code> defining the URL of the local database. */
  private static final String DB_URL = "jdbc:h2:./res/ProductionDB";
  /** A constant <code>String</code> defining the username of the local database. */
  private static final String USER = "Gamerdude38";
  /** A constant <code>String</code> defining the password of the local database. */
  private static String pass = "ohhimark";
  /** Used to display Message Boxes to the user in the event they input incorrect data. */
  private Alert errorDialog = new Alert(Alert.AlertType.ERROR);

  /**
   * Initializes elements like the "Choose Quantity" <code>ComboBox</code> once the program starts
   * up.
   *
   * @param location a <code>URL</code> object required by the interface <code>Initializable</code>.
   * @param resources a <code>ResourceBundle</code> object required by the interface <code>
   *     Initializable</code>.
   */
  @FXML
  public void initialize(URL location, ResourceBundle resources) {
    // Since the setItems method of a ComboBox requires an ObservableList, we're populating it with
    // the data we want it to start with.

    ObservableList<ItemType> items = FXCollections.observableArrayList();

    items.addAll(Arrays.asList(ItemType.values()));

    // Set the data for Product Type
    cboItemType.setItems(items);
    cboItemType.getSelectionModel().selectFirst();

    ObservableList<Integer> numbers =
        FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // Set the data for Choose Quantity and change some properties of the ComboBox.
    cboChooseQuantity.setItems(numbers);
    cboChooseQuantity.setEditable(true);
    cboChooseQuantity.getSelectionModel().selectFirst();

    // Set up the Product Line
    setupProductLineTable();

    // Add the productLine ObservableList to the TableView
    tbvExistingProducts.setItems(productLine);

    // Add the productLine ObservableList to the ListView & make it so it only selects 1 item at a
    // time
    lstChooseProduct.setItems(productLine);
    lstChooseProduct.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    // In the event the table shows no rows, say there's no products added
    tbvExistingProducts.setPlaceholder(new Label("No products have been added."));

    // Acquire database password
    /*
    try{
      Properties prop = new Properties();

      prop.load(new FileInputStream(new File("/data.properties").getAbsolutePath()));

      pass = prop.getProperty("password");
    }catch (IOException ex){
      ex.printStackTrace();
      errorDialog
        .setContentText("Could not read properties file! Check the 'res' folder for mistakes.");
      errorDialog.show();
      return;
    };
    */

    // Load the product list
    loadProductList();

    // Load the production log
    loadProductionLog();
  }

  /**
   * Handles a button click on the "Add Product" button. Takes in user input from the text fields
   * and choice box from the "Product Line" tab and adds the information to the database.
   *
   * @param event an <code>ActionEvent</code> object that is fired when the button is clicked.
   */
  @FXML
  protected void handleAddProductAction(ActionEvent event) {
    // Acquire data from user input
    String productName = txtProductName.getText();
    String manufacturer = txtManufacturer.getText();
    ItemType itemType = (ItemType) cboItemType.getValue();

    // Check user input before continuing
    if (productName.equals("")) {
      errorDialog.setContentText("Please enter the name of the product.");
      errorDialog.show();
      return;
    }

    if (manufacturer.equals("")) {
      errorDialog.setContentText("Please enter the name of the manufacturer.");
      errorDialog.show();
      return;
    }

    if (itemType == null) {
      errorDialog.setContentText("Please select an item type.");
      errorDialog.show();
      return;
    }

    // Prepare to send data to the database
    PreparedStatement pstmtAddProduct;

    // Try to connect and add data to the database
    try {
      // Establish driver
      Class.forName(JDBC_DRIVER);

      // Establish connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, pass);

      // Create a prepared statement for ease of use
      pstmtAddProduct =
          conn.prepareStatement(
              "insert into Product(type, manufacturer, name) values ( ?, ?, ? );");

      // Populate the prepared statement
      pstmtAddProduct.setString(1, itemType.getCode());
      pstmtAddProduct.setString(2, manufacturer);
      pstmtAddProduct.setString(3, productName);

      // Send the populated statement over with the data
      pstmtAddProduct.executeUpdate();

      // Close the statement and connection
      pstmtAddProduct.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    // Update the product list
    loadProductList();
  }

  /**
   * Handles a button click on the "Record Production" button. Takes in user input from the listview
   * and combo box from the "Record Production" tab and stores it in the production log.
   *
   * @param event an <code>ActionEvent</code> object that is fired when the button is clicked.
   */
  @FXML
  protected void handleRecordProductionAction(ActionEvent event) {
    // Record Production button should:
    //
    // Create an ArrayList of ProductionRecord objects named productionRun.
    // Send the productionRun to an addToProductionDB method. (Tip: use a TimeStamp object for the
    // date)

    // Get the selected product and the quantity.
    Product productToRecord = (Product) lstChooseProduct.getSelectionModel().getSelectedItem();

    // Check for input errors before continuing.
    if (productToRecord == null) {
      errorDialog.setContentText("Please select a product from the table above.");
      errorDialog.show();
      return;
    }

    // Define an integer for quantity.
    int quantity;

    // Make sure the combo box has an integer.
    try {
      quantity =
          Integer.parseInt(cboChooseQuantity.getSelectionModel().getSelectedItem().toString());
    } catch (NumberFormatException ex) {
      ex.printStackTrace();
      errorDialog.setContentText("Please enter a whole number for the quantity.");
      errorDialog.show();
      return;
    }

    // Add the products to the database
    addToProductionDB(productToRecord, quantity);

    // Load the production log
    loadProductionLog();
  }

  /** Demonstrates the classes that implement <code>MultimediaControl</code> in the console. */
  public static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /**
   * Reverses a <code>String</code> password. For example, on input "12345Abc", the function returns
   * "cbA54321". This is to be used to reverse the password of an employee for security purposes.
   *
   * @param pw a <code>String</code> password to be reversed.
   * @return the reversed <code>String</code> of the password.
   */
  public String reverseString(String pw) {
    if (pw.length() == 1) {
      return pw;
    } else {
      return (pw.substring(pw.length() - 1) + reverseString(pw.substring(0, pw.length() - 1)));
    }
  }

  /** Sets up <code>tbvExistingProducts</code> during the initialization phase. */
  public void setupProductLineTable() {
    // Initialize the TableView by adding in columns
    TableColumn<String, Product> productNameColumn = new TableColumn<>("Name");
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<String, Product> productManufacturerColumn = new TableColumn<>("Manufacturer");
    productManufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

    TableColumn<String, Product> productTypeColumn = new TableColumn<>("Type");
    productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Widen the columns to fit the table size (solution suggested by Stack Overflow:
    // https://stackoverflow.com/questions/10152828/javafx-2-automatic-column-width )
    productNameColumn.prefWidthProperty().bind(tbvExistingProducts.widthProperty().divide(3));
    productManufacturerColumn
        .prefWidthProperty()
        .bind(tbvExistingProducts.widthProperty().divide(3));
    productTypeColumn.prefWidthProperty().bind(tbvExistingProducts.widthProperty().divide(3));

    // Add the columns to the rows
    tbvExistingProducts.getColumns().add(productNameColumn);
    tbvExistingProducts.getColumns().add(productManufacturerColumn);
    tbvExistingProducts.getColumns().add(productTypeColumn);
  }

  /**
   * Loads the product list from the database, populating the product line <code>ObservableList
   * </code>.
   */
  public void loadProductList() {
    try {
      // Establish driver
      Class.forName(JDBC_DRIVER);

      // Clear the ObservableList and clear UI elements
      productLine.removeAll();
      tbvExistingProducts.getItems().clear();
      lstChooseProduct.getItems().clear();

      // Establish connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, pass);

      // Create statement
      Statement stmt = conn.createStatement();

      // Obtain ResultSet from database
      ResultSet results = stmt.executeQuery("SELECT * FROM PRODUCT");

      // Loop through result set and pull data, making objects with it
      while (results.next()) {
        String name = results.getString(2);
        String code = results.getString(3);
        String manufacturer = results.getString(4);

        // Add the product info into the ObservableList
        if (code.equals("AU") || code.equals("AM")) {
          productLine.add(
              new AudioPlayer(name, manufacturer, "[Audio formats]", "[Playlist formats]"));
        } else {
          productLine.add(
              new MoviePlayer(
                  name, manufacturer, new Screen("[Resolution]", 1, 1), MonitorType.LCD));
        }
      }

      // Close the statement and connection
      stmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /** Loads the production log, populating the production log <code>ArrayList</code>. */
  public void loadProductionLog() {
    try {
      // Establish driver
      Class.forName(JDBC_DRIVER);

      // Establish connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, pass);

      // Create statement
      Statement stmt = conn.createStatement();

      // Obtain ResultSet from database
      ResultSet results = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");

      // Clear the production log
      txaProductionLog.setText("");

      // Loop through result set and pull data, making objects with it
      while (results.next()) {
        int prodNum = results.getInt(1);
        int prodID = results.getInt(2);
        String serial = results.getString(3);
        Timestamp date = results.getTimestamp(4);

        // Add to the ArrayList
        productionLog.add(new ProductionRecord(prodNum, prodID, serial, date));
      }

      // Close the statement and connection
      stmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    showProduction();
  }

  /** Displays the production log to <code>txaProductionLog</code>. */
  public void showProduction() {
    for (ProductionRecord productionRecord : productionLog) {
      txaProductionLog.appendText(productionRecord.toString() + "\n");
    }
  }

  /**
   * Adds the product to the Production Record database.
   *
   * @param product the <code>Product</code> to be added to the database.
   * @param quantity the amount of products to add to the database.
   */
  public void addToProductionDB(Product product, int quantity) {
    // The addToProductionDB method should:
    //
    // Insert productionRecord object information into the ProductionRecord database table.
    PreparedStatement pstmt;

    try {
      // Establish driver
      Class.forName(JDBC_DRIVER);

      // Establish connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, pass);

      String sql = "insert into PRODUCTIONRECORD(PRODUCT_ID, SERIAL_";
      sql += "NUM, DATE_PRODUCED) values ( ?, ?, ? );";
      // Create a prepared statement for ease of use
      pstmt = conn.prepareStatement(sql);

      // Loop through all the records
      for (int i = 0; i < quantity; i++) {
        ProductionRecord record = new ProductionRecord(product, i);

        // Populate the prepared statement
        pstmt.setInt(1, record.getProductID());
        pstmt.setString(2, record.getSerialNumber());
        pstmt.setTimestamp(3, new Timestamp(record.getDateProduced().getTime()));

        // Send the populated statement over with the data
        pstmt.executeUpdate();
      }

      // Close the statement and connection
      pstmt.close();
      conn.close();

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }
}
