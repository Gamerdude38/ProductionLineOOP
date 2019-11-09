package io.github.Gamerdude38;

import java.net.URL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
  @FXML private ComboBox<Integer> cboChooseQuantity;
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
  /** A constant <code>String</code> defining the JDBC Driver to be used. */
  private static final String JDBC_DRIVER = "org.h2.Driver";
  /** A constant <code>String</code> defining the URL of the local database. */
  private static final String DB_URL = "jdbc:h2:./res/ProductionDB";
  /** A constant <code>String</code> defining the username of the local database. */
  private static final String USER = "Gamerdude38";
  /** A constant <code>String</code> defining the password of the local database. */
  private static final String PASS = "12345";

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

    for (ItemType item : ItemType.values()) {
      items.add(item);
    }

    // Set the data for Product Type
    cboItemType.setItems(items);
    cboItemType.getSelectionModel().selectFirst();

    ObservableList<Integer> numbers =
        FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    // Set the data for Choose Quantity and change some properties of the ComboBox.
    cboChooseQuantity.setItems(numbers);
    cboChooseQuantity.setEditable(true);
    cboChooseQuantity.getSelectionModel().selectFirst();

    // Initialize the TableView by adding in columns
    TableColumn<String, Product> productNameColumn = new TableColumn<>("Name");
    productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

    TableColumn<String, Product> productManufacturerColumn = new TableColumn<>("Manuf.");
    productManufacturerColumn.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));

    TableColumn<String, Product> productTypeColumn = new TableColumn<>("Type");
    productTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

    // Add the columns to the rows
    tbvExistingProducts.getColumns().add(productNameColumn);
    tbvExistingProducts.getColumns().add(productManufacturerColumn);
    tbvExistingProducts.getColumns().add(productTypeColumn);

    // Add the productLine ObservableList to the TableView
    tbvExistingProducts.setItems(productLine);

    // Add the productLine ObservableList to the ListView & make it so it only selects 1 item at a
    // time
    lstChooseProduct.setItems(productLine);
    lstChooseProduct.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    // In the event the table shows no rows, say there's no products added
    tbvExistingProducts.setPlaceholder(new Label("No products have been added."));

    // Test the MultimediaControl classes
    testMultimedia();
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

    // Add the product info into the ObservableList
    if (itemType.equals(ItemType.AUDIO)) {
      productLine.add(
          new AudioPlayer(productName, manufacturer, "[Audio formats]", "[Playlist formats]"));
    } else {
      productLine.add(
          new MoviePlayer(
              productName, manufacturer, new Screen("[Resolution]", 1, 1), MonitorType.LCD));
    }

    // Prepare to send data to the database
    PreparedStatement pstmtAddProduct;

    // Try to connect and add data to the database
    try {
      // Establish driver
      Class.forName(JDBC_DRIVER);

      // Establish connection
      Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

      // Create a prepared statement for ease of use
      pstmtAddProduct =
          conn.prepareStatement(
              "insert into Product(type, manufacturer, name) values ( ?, ?, ? );");

      // Populate the prepared statement
      pstmtAddProduct.setObject(1, itemType);
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
  }

  /**
   * Handles a button click on the "Record Production" button. Takes in user input from the listview
   * and combo box from the "Record Production" tab and stores it in the production log.
   *
   * @param event an <code>ActionEvent</code> object that is fired when the button is clicked.
   */
  @FXML
  protected void handleRecordProductionAction(ActionEvent event) {
    txaProductionLog.appendText(
        lstChooseProduct.getSelectionModel().getSelectedItem().toString()
            + " Quantity: "
            + cboChooseQuantity.getSelectionModel().getSelectedItem());
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
}
