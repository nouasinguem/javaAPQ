
# Project Title

Beverage Stock Management


## Description

The system will help employees and administrators in the process of selling, buying, tracking and controlling stock levels of drinks in a business to ensure optimal supply and prevent wastage.
## Features

- User registration subject to admin approval
- User login
- Buy or Sell product
- Great filtering and searching capabilities
- Export filtered product data to a non-existing file 
- Manage (view) all employees and transactions for admin use only
- Manage (view and update) the profile for employees and admin
- Multilingual, the application can be used in English and French
- Accessible, Usable and Inclusive User Interface
- Error Handling and Data Validation
- Role based access control
- Secured database


## Technologies

- Java: framework used to code GUIs (using Swing) and backend
- MySQL database (RDBMS): used to store, manage, and access data.
- JDBC: allowing our java programs to connect to the database
- JUnit: our unit testing framework used when testing our methods
- IntelliJ (IDE): environment for our code development written in java (developed by JetBrains)
## Installation

- Download and install all the necessary tools (Java 17, MySQL Workbench 8.0 CE, a java IDE like IntelliJ, Visual Studio Code)
- Import the project as it is from the zip file to your IDE
- Setup the database using a username or a password of your choice
- In the class "bitRepeated", update the database credentials with the ones you choose at the earlier stage
- Setup the database as shown on the following database schema following the next step
- Run the application from the "Main" class 
## Setting up the database

To set up the database, open MySQL Workbench 8.0 and on a query sheet, execute in the same order the following commands:

- CREATE TABLE Product (
    barcodeNumber VARCHAR(255) PRIMARY KEY,
    volume INT,
    name VARCHAR(255),
    brand VARCHAR(255),
    costPrice FLOAT,
    sellingPrice FLOAT,
    stock INT,
    type VARCHAR(255),
    image BLOB
); 

- CREATE TABLE Juice (
    barcodenumberJ VARCHAR(255) PRIMARY KEY,
    fruit VARCHAR(255),
    sugar INT,
    barcodenumber VARCHAR(255),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber)
);

- CREATE TABLE ESDrinks (
    barcodenumberE VARCHAR(255) PRIMARY KEY,
    flavor VARCHAR(255),
    sugar INT,
    caffeine INT,
    taurine INT,
    barcodenumber VARCHAR(255),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber)
);

- CREATE TABLE Beer (
    barcodenumberB VARCHAR(255) PRIMARY KEY,
    alcohol FLOAT,
    producer VARCHAR(255),
    country VARCHAR(255),
    packingType VARCHAR(255),
    barcodenumber VARCHAR(255),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber)
);

- CREATE TABLE Wine (
    barcodenumberW VARCHAR(255) PRIMARY KEY,
    alcohol FLOAT,
    region VARCHAR(255),
    grape VARCHAR(255),
    year INT,
    barcodenumber VARCHAR(255),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber)
);

- CREATE TABLE Spirit (
    barcodenumberS VARCHAR(255) PRIMARY KEY,
    alcohol FLOAT,
    type VARCHAR(255),
    producer VARCHAR(255),
    barcodenumber VARCHAR(255),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber)
);

- CREATE TABLE Workers (
    username VARCHAR(255) PRIMARY KEY,
    firstName VARCHAR(255),
    lastName VARCHAR(255),
    password VARCHAR(255),
    employeeID VARCHAR(255),
    isAdmin INT,
    isActive INT
);

- CREATE TABLE transaction (
    transactionID INT PRIMARY KEY,
    transactionType VARCHAR(255),
    quantity INT,
    price FLOAT,
    username VARCHAR(255),
    FOREIGN KEY (username) REFERENCES Workers(username)
);

- CREATE TABLE TransactionProduct (
    barcodenumber VARCHAR(255),
    transactionID INT,
    quantity INT,
    price FLOAT,
    PRIMARY KEY (barcodenumber, transactionID),
    FOREIGN KEY (barcodenumber) REFERENCES Product(barcodeNumber),
    FOREIGN KEY (transactionID) REFERENCES transaction(transactionID)
);
## Running Tests

Note: To test, you need to import testing libraries to your IDE running: import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
--
- Method: 
public int authentification(String username, String password) {

        Connection conn = null;
        int flag = 0;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement"; //Connection string
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select password, isActive from stockmanagement.workers where username=?";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            //Execute query
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                //Checking if the password is correct
                if (password.equals(rs.getString("password"))) {
                    //Checking if the account is active
                    if (rs.getInt("isActive") == 1) {
                        flag = 1;
                    } else {
                        flag = 2;
                    }
                }
            }

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (conn != null) {
                    conn.close();
                }
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }

To run tests, run the following command in a java class:
@Test
    void authentificationTest1() {//Wrong credentials
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("", "");

        Assertions.assertEquals(1, authen); //Test failed
    }


    @Test
    void authentificationTest2() {//Valid credentials and no admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("01717173667", "Maxime");
        Assertions.assertEquals(1, authen); //Test failed
    }

    @Test
    void authentificationTest3() {//Valid credential and no admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */
        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("01717173667", "Maxime");

        Assertions.assertEquals(2, authen); //Test passed
    }

    @Test
    void authentificationTest4() {//Valid credential and  admin approval
        /* authentication returns:
        1 if you're authenticated with right credential and with admin approval,
        2 if your credentials are right, but you request admin approval,
        and 0 if your credentials are wrong */

        bitRepeated rep = new bitRepeated();
        int authen = rep.authentification("25", "Maxime");

        Assertions.assertEquals(1, authen); //Test  passed
}

---

- Method: 
public boolean isValidPhoneNumber(String phoneNumber) {// 11 digits starting with 07
        return phoneNumber.matches("07\\d{9}$");
}

To run tests, run the following command in a java class:

@Test
    void validPhoneNumberTest2(){//Valid landline phone number
        /* This function test the phone number and returns:
         true if it's a UK mobile phone number and false if it's not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.isValidPhoneNumber("01256786999");
        Assertions.assertTrue(result); //Test failed
    }

    @Test
    void validPhoneNumberTest3(){//Valid landline phone number
        /* This function test the phone number and returns:
         true if it's a UK mobile phone number and false if it's not */

        bitRepeated rep = new bitRepeated();
        boolean result = rep.isValidPhoneNumber("07455609778");
        Assertions.assertTrue(result); //Test passed
    }




## How to Use

- Registration: Make sure all fields are filled in and valid with a UK mobile phone number
- Login: Make sure you have enter the right credentials and you have admin approval
- Sell a product: Enter a barcode in the database, a valid selling stock and confirm the transaction
- Buy a product: Enter a barcode, a valid stock (>0) and confirm. If the barcode is in the database, the stock with by updated and the transaction done. If not, you will be redirected to a new page to enter the product information (image, name, brands.. and specific attributes). Make sure you enter valid information and confirm the transaction. The product will be created in the database with its stock and information.
- Information on a single product: Enter a valid barcode which is contained in the database and confirm to see the information directly in the GUI or enter the file path and click on print to export product information to the actual file.
- All product info: Select a filter to view corresponding product in the GUI then file in the file path and click on print if you want to export the information to a file.
- Search Product: Select a product type, a filter (name, brand cost, volume..) and click on search to filter products and have the result in GUI.
- Low Stock Product: Select a product type and all the products of that type under the normal treshold with be displayed in the GUI.
- Manage employees: select an employee type and all employees of that type will be displayed
- View Transaction: Select a transaction type and all transactions of that type will be displayed in the GUI. 
- Profile: Enter a valid First Name, Last Name or Password and click on apply to change it. It also allow users to view their password.
- Click on logout to end you session.
## Authors

- Developed by: ###2409991

