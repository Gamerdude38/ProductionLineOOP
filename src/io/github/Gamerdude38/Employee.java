package io.github.Gamerdude38;

/**
 * Represents an employee working for the company.
 *
 * @author John Maurer
 */
public class Employee {
  // create a class and tab named Employee that will allow the user to input their full name and
  // password
  /** A <code>StringBuilder</code> representing the name of the employee. */
  private StringBuilder name;
  /** A <code>String</code> representing the username of the employee. */
  private String username;
  /** A <code>String</code> representing the password of the employee. */
  private String password;
  /** A <code>String</code> representing the email of the employee. */
  private String email;

  /**
   * Constructs an <code>Employee</code> representing a real employee of the company. The employee's
   * username and email are determined by the <code>Name</code> parameter passed in. If the name or
   * password is in an invalid format, each field is set to default values.
   *
   * @param name the name of the employee.
   * @param password the password of the employee.
   */
  public Employee(String name, String password) {
    this.name = new StringBuilder(name);
    if (checkName(name)) {
      setUsername(name);
      setEmail(name);
    } else {
      username = "default";
      email = "user@oracleacademy.Test";
    }

    if (isValidPassword(password)) {
      this.password = password;
    } else {
      this.password = "pw";
    }
  }

  /**
   * Sets the username of the employee using the name of the employee.
   *
   * @param name the name of the employee.
   */
  private void setUsername(String name) {
    int spaceLocation = name.indexOf(" ");
    username = (name.substring(0, 1) + name.substring(spaceLocation + 1)).toLowerCase();
  }

  /**
   * Checks to make sure the user has entered a valid name. A valid name contains a first and last
   * name.
   *
   * @param name the name of the employee.
   * @return true if the name is valid, false otherwise.
   */
  private boolean checkName(String name) {
    return name.contains(" ");
  }

  /**
   * Sets the email of the employee using the name of the employee.
   *
   * @param name the name of the employee.
   */
  private void setEmail(String name) {
    int spaceLocation = name.indexOf(" ");
    email =
        (name.substring(0, spaceLocation) + "." + name.substring(spaceLocation + 1)).toLowerCase()
            + "@oracleacademy.Test";
  }

  /**
   * Checks to see if the password provided is valid. A valid password has a lowercase letter, an
   * uppercase letter, and a special character.
   *
   * @param password the password to check.
   * @return true if the password is valid, false otherwise.
   */
  private boolean isValidPassword(String password) {
    return password.matches(".*[a-z].*")
        && password.matches(".*[A-Z].*")
        && password.matches(".*\\W.*");
  }

  /**
   * Formats a <code>String</code> to display the name, username, email, and password on separate
   * lines.
   *
   * @return a printable <code>String</code> of an <code>Employee</code>.
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return "Employee Details\nName : "
        + name.toString()
        + "\nUsername : "
        + username
        + "\nEmail : "
        + email
        + "\nInitial Password : "
        + password;
  }
}
