import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class bitRepeated extends JFrame {
    public bitRepeated() {

    }

    public void header(Container c, JLabel title) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel.add(title);
        title.setFont(new Font("Impact", Font.BOLD, 36));
        panel.setBackground(Color.RED);
        c.add(panel);
    }

    public void Dashboardfooter(Container c, JButton logout) {
        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel5.add(logout);
        logout.setPreferredSize(new Dimension(120, 30));
        logout.setBackground(Color.WHITE);
        panel5.setBackground(Color.RED);
        c.add(panel5);
    }

    //Authentification after credentials entered
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

    //Check if admin or employee return true if admin and false if employee
     public int checkAdminRight (String username){
        Connection conn = null;
        int flag = 0;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement"; //Connection string
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.workers where username=?";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            //Execute query
            ResultSet rs = statement.executeQuery();

            //Checking if the account is active

                if (rs.next()) {
                    //Checking if it's an admin or employee account
                    if (rs.getInt("isAdmin") == 1) {
                        flag = 1;
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

    public String createWorkerID(String username, String firstName, String lastName) {//Defines how we are allocating IDs to workers
        return firstName + lastName  + username;
    }

    //Check if admin or employee return true if admin and false if employee
    public int addWorker (Worker w){
        Connection conn = null;
        int flag = 0;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.workers where username=?";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, w.getPhone());

            //Execute query
            ResultSet rs = statement.executeQuery();

            //Checking if the  username exists already

            if (!rs.next()) {//No such username found
                statement.close();
                //inserting the new user into the database
                String sql1 = "insert into stockmanagement.workers (username, firstName, lastName, password, employeeID, isAdmin, isActive) " +
                        "values(?, ?, ?, ?, ?, ?, ?)";

                PreparedStatement ps = conn.prepareStatement(sql1);

                ps.setString(1, w.getPhone());
                ps.setString(2, w.getFirstName());
                ps.setString(3, w.getLastName());
                ps.setString(4, w.getPassword());
                ps.setString(5, w.getUserID());
                ps.setInt(6, w.getIsAdmin());//Initialise to 0 in the body
                ps.setInt(7, w.getIsActive());//Initialise to 0 in the body

                ps.executeUpdate();

                flag = 1;

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

    public boolean isValidPhoneNumber(String phoneNumber) {// 11 digits starting with 07
        return phoneNumber.matches("07\\d{9}$");
    }

    public Worker workerInfo (String username){//Get all data from a specific username from the database
        Connection conn = null;
        Worker flag = new Worker();// Considered as null in our case

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.workers where username=?";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, username);

            //Execute query
            ResultSet rs = statement.executeQuery();

            //Checking if the  username exists already

            if (!rs.next()) {//No such username found
                return flag;
            }
            String fn = rs.getString("firstName");
            String ln = rs.getString("lastName");
            String pss = rs.getString("password");
            String emID = rs.getString("employeeID");
            Integer isAdmin = rs.getInt("isAdmin");
            Integer isActive = rs.getInt("isActive");

            flag = new Worker(emID, username, fn, ln, pss, isAdmin, isActive);

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

    //Updating allows only to change the password, firstname and lastname
    public void updateInfo(String user, String fn, String ln, String pss){
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "update stockmanagement.workers " +
                "set firstName = ?, lastName = ?, password = ? " +
                "where username = ? ";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, fn);
            statement.setString(2, ln);
            statement.setString(3, pss);
            statement.setString(4, user);
            statement.executeUpdate();
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
    }

    public boolean isValidInt(String a){//Checks if an int is valid and > 0
        try{
            int c= Integer.parseInt(a);
            if (c>0) return true;
            else return false;
        }catch (Exception e) {
            return false;
        }
    }

    public boolean isValidDouble(String a){//Check if a double is valid and > 0
        try{
            Double c = Double.parseDouble(a);
            if(c>0) return true;
            else return false;
        } catch (Exception e) {
            return false;
        }
    }
    // This function checks with a barcode if a product is existing in the database or not
    public boolean checkExistingProduct(int barcodenumber){
        boolean flag = false;
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.product where barcodenumber=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, barcodenumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                flag = true;
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

    public int stockNumber(int barcodenumber){
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.product where barcodenumber=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, barcodenumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
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
        return 0;
    }

    public boolean isValidLong (String a){
        Long c = Long.parseLong(a);
        try {
            return c > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String firstName(String user){
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.workers where username=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("firstName");
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
        return "";
    }

    public void updateStock (int barcodenumber, int quantity){//Function to update the stock in case the product is already in the database or selling

        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "update stockmanagement.product set stock = stock+? where barcodenumber=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, quantity);
            statement.setInt(2, barcodenumber);
            statement.executeUpdate();

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
    }

    public boolean addProduct (Product p, int stock, String typ, File file){//Returns 1 if the product has been added and 0 if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.product " +
                "(barcodenumber, volume, name, brand, costPrice, sellingPrice, stock, type, image) values(?,?,?,?,?,?,?,?,?)";

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, p.getBarcodeNumber());
            statement.setInt(2, p.getVolume());
            statement.setString(3, p.getName());
            statement.setString(4, p.getBrand());
            statement.setDouble(5, p.getCostPrice());
            statement.setDouble(6, p.getSellingPrice());
            statement.setInt(7, stock);
            statement.setString(8, typ);
            statement.setBinaryStream(9, fis, (int) file.length());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addBeer (Beer beer){//Returns true if the product has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.beer " +
                "(barcodenumberB, alcohol, producer, country, packingType, barcodenumber) values(?,?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, beer.getBarcodeNumber());
            statement.setDouble(2, beer.getAlcohol());
            statement.setString(3, beer.getProducer());
            statement.setString(4, beer.getCountry());
            statement.setString(5, beer.getPackingType());
            statement.setInt(6, beer.getBarcodeNumber());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addJuice (Juice juice){//Returns true if the product has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.juice " +
                "(barcodenumberJ, fruit, sugar, barcodenumber) values(?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, juice.getBarcodeNumber());
            statement.setString(2, juice.getFruit());
            statement.setInt(3, juice.getSugar());
            statement.setInt(4, juice.getBarcodeNumber());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addESDrink (ESDrinks energy){//Returns true if the product has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.esdrinks " +
                "(barcodenumberE, flavor, sugar, caffeine, taurine,  barcodenumber) values(?,?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, energy.getBarcodeNumber());
            statement.setString(2, energy.getFlavor());
            statement.setInt(3, energy.getSugarContent());
            statement.setInt(4, energy.getCaffeine());
            statement.setInt(5, energy.getTaurine());
            statement.setInt(6, energy.getBarcodeNumber());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addWine (Wine wine){//Returns true if the product has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.wine " +
                "(barcodenumberW, alcohol, region, grape, year, barcodenumber) values(?,?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, wine.getBarcodeNumber());
            statement.setDouble(2, wine.getAlcohol());
            statement.setString(3, wine.getRegion());
            statement.setString(4, wine.getGrape());
            statement.setInt(5, wine.getYear());
            statement.setInt(6, wine.getBarcodeNumber());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addSpirit (Spirit spirit){//Returns true if the product has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.spirit " +
                "(barcodenumberS, alcohol, type, producer, barcodenumber) values(?,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, spirit.getBarcodeNumber());
            statement.setDouble(2, spirit.getAlcohol());
            statement.setString(3, spirit.getType());
            statement.setString(4, spirit.getProducer());
            statement.setInt(5, spirit.getBarcodeNumber());

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public boolean addTransaction (String transactionName, int number, double prix, String user ){//Returns true if the transaction has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.transaction " +
                "(transactionType, quantity, price, username) values(?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, transactionName);
            statement.setInt(2, number);
            statement.setDouble(3, prix);
            statement.setString(4, user);

            //Execute query
            statement.executeUpdate();
            flag = true;
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

    public double getPrice(int barcodenumber, String priceType){// This function will get the selling or the cost price of a product
        double flag =0;
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select costPrice, sellingPrice from stockmanagement.product where barcodenumber=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, barcodenumber);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (priceType.equals("sellingPrice")) {
                    flag = rs.getDouble("sellingPrice");
                }
                if (priceType.equals("costPrice")) {
                    flag = rs.getDouble("costPrice");
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

    public String singleProduct(int barcodenumber){// This function will get the selling or the cost price of a product
        Connection conn = null;
        String result ="";

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select type from stockmanagement.product where barcodenumber=?";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, barcodenumber);
            ResultSet rs = statement.executeQuery();
            String sql1 = "";
            if (rs.next()) {//Getting in the right table depending on the product type
                String typeValue = rs.getString("type");
                switch (typeValue){
                    case "Juice":
                        sql1 = "select * from stockmanagement.product p join stockmanagement.juice j on p.barcodenumber=j.barcodenumberJ where p.barcodenumber=?";
                        break;
                    case "Energetic":
                        sql1 = "select * from stockmanagement.product p join stockmanagement.esdrinks j on p.barcodenumber=j.barcodenumberE where p.barcodenumber=?";
                        break;
                    case "Beer":
                        sql1 = "select * from stockmanagement.product p join stockmanagement.beer j on p.barcodenumber=j.barcodenumberB where p.barcodenumber=?";
                        break;
                    case "Wine":
                        sql1 = "select * from stockmanagement.product p join stockmanagement.wine j on p.barcodenumber=j.barcodenumberW where p.barcodenumber=?";
                        break;
                    case "Spirit":
                        sql1 = "select * from stockmanagement.product p join stockmanagement.spirit j on p.barcodenumber=j.barcodenumberS where p.barcodenumber=?";
                        break;
                }
                statement.close();
                PreparedStatement statement1 = conn.prepareStatement(sql1);
                statement1.setInt(1, barcodenumber);
                rs = statement1.executeQuery();
                Product p= null;
                if (rs.next()) {//Getting in the right table depending on the product type
                    switch (typeValue){
                        case "Juice":
                            p = new Juice(barcodenumber, rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                                rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("fruit"), rs.getInt("sugar") );
                            break;
                        case "Energetic":
                            p = new ESDrinks(barcodenumber, rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                                    rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("flavor"),
                                    rs.getInt("sugar"), rs.getInt("caffeine"), rs.getInt("taurine"));
                            break;
                        case "Beer":
                            p = new Beer(barcodenumber, rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                                    rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("producer"),
                                    rs.getString("country"), rs.getString("packingType"), rs.getDouble("alcohol"));
                            break;
                        case "Wine":
                            p = new Wine(barcodenumber, rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                                  rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getDouble("alcohol"),
                                  rs.getString("region"), rs.getString("grape"), rs.getInt("year"));
                            break;
                        case "Spirit":
                            p = new Spirit(barcodenumber, rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                                    rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getDouble("alcohol"),
                                    rs.getString("type"), rs.getString("producer"));
                            break;
                    }
                    result = p.display(rs.getInt("stock"));
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
        return result;
    }

    //This function displays all element beer table if any existing depending on the special
    public String displayAllBeer(String special){
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql = "";
        if (special.equals("All")) {
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                "p.stock, p.type, b.alcohol, b.producer, b.country, b.packingType from stockmanagement.product p join stockmanagement.beer b " +
                "on p.barcodeNumber = b.barcodenumber";
        }
        if (special.equals("Low")) {
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.alcohol, b.producer, b.country, b.packingType from stockmanagement.product p join stockmanagement.beer b " +
                    "on p.barcodeNumber = b.barcodenumber where p.stock <= ?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Low")) {
                statement.setInt(1, 10); //Setting up the treshold
            }

            ResultSet rs = statement.executeQuery();

            result += "\n";
            while (rs.next()) {
                    Product b = new Beer(rs.getInt("barcodeNumber"), rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                            rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("producer"),
                            rs.getString("country"), rs.getString("packingType"), rs.getDouble("alcohol"));
                    result += b.display(rs.getInt("stock"));
                //}
                result += "\n";
            }
            result+="\n";

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
        return result;
    }

    //The following function will display all Juice product
    public String displayAllJuice(String special) {
        String result = "\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql ="";
        if (special.equals("All")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.fruit, b.sugar from stockmanagement.product p join stockmanagement.juice b " +
                    "on p.barcodeNumber = b.barcodenumber";
        }
        if(special.equals("Low")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.fruit, b.sugar from stockmanagement.product p join stockmanagement.juice b " +
                    "on p.barcodeNumber = b.barcodenumber where p.stock <= ?";
        }

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Low")) {
                statement.setInt(1, 10); //Setting up the treshold
            }

            ResultSet rs = statement.executeQuery();

            result += "";
            while (rs.next()) {
                Product j = new Juice(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("fruit"), rs.getInt("sugar"));
                result += j.display(rs.getInt("stock"));
                result += "\n";
            }
            result += "\n";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Display all the energetic drinks
    public String displayAllEDrink(String special) {
        String result = "\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";
        if (special.equals("All")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.flavor, b.sugar,b.caffeine, b.taurine from stockmanagement.product p join stockmanagement.esdrinks b " +
                    "on p.barcodeNumber = b.barcodenumber";
        }
        if(special.equals("Low")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.flavor, b.sugar,b.caffeine, b.taurine from stockmanagement.product p join stockmanagement.esdrinks b " +
                    "on p.barcodeNumber = b.barcodenumber where p.stock <= ?";
        }

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Low")) {
                statement.setInt(1, 10); //Setting up the treshold
            }
            ResultSet rs = statement.executeQuery();

            result += "\n";
            while (rs.next()) {
                Product e = new ESDrinks(rs.getInt("barcodenumber"), rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getString("flavor"),
                        rs.getInt("sugar"), rs.getInt("caffeine"), rs.getInt("taurine"));
                result += e.display(rs.getInt("stock"));
                result += "\n";
            }
            result += "\n";

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //Display all the wine product
    public String displayAllWine(String special){
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";

        if (special.equals("All")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.alcohol, b.region, b.grape, b.year from stockmanagement.product p join stockmanagement.wine b " +
                    "on p.barcodeNumber = b.barcodenumber";
        }
        if(special.equals("Low")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.alcohol, b.region, b.grape, b.year from stockmanagement.product p join stockmanagement.wine b " +
                    "on p.barcodeNumber = b.barcodenumber where p.stock <= ?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Low")) {
                statement.setInt(1, 10); //Setting up the treshold
            }

            ResultSet rs = statement.executeQuery();

            result += "\n";
            while (rs.next()) {
                Product w = new Wine(rs.getInt("barcodeNumber"), rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getDouble("alcohol"),
                        rs.getString("region"), rs.getString("grape"), rs.getInt("year"));
                result += w.display(rs.getInt("stock"));
                //}
                result += "\n";
            }
            result+="\n";

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
        return result;
    }

    public String displayAllSpirit(String special){
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";
        if (special.equals("All")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.alcohol, b.type, b.producer from stockmanagement.product p join stockmanagement.spirit b " +
                    "on p.barcodeNumber = b.barcodenumber";
        }
        if(special.equals("Low")){
            sql = "select p.barcodeNumber, p.volume, p.name, p.brand, p.costPrice, p.sellingPrice, " +
                    "p.stock, p.type, b.alcohol, b.type, b.producer from stockmanagement.product p join stockmanagement.spirit b " +
                    "on p.barcodeNumber = b.barcodenumber where p.stock <= ?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Low")) {
                statement.setInt(1, 10);
            }
            ResultSet rs = statement.executeQuery();

            result += "\n";
            while (rs.next()) {
                Product s = new Spirit(rs.getInt("barcodeNumber"), rs.getInt("volume"),rs.getString("name"), rs.getString("brand"),
                        rs.getDouble("costPrice"), rs.getDouble("sellingPrice"), rs.getDouble("alcohol"),
                        rs.getString("type"), rs.getString("producer"));
                result += s.display(rs.getInt("stock"));
                //}
                result += "\n";
            }
            result+="\n";

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
        return result;
    }

    //This function will return an array list fo 2 double  ( Total Cost Price and Selling price)
    public List <Double> prices(String typ){
        List <Double> result=new ArrayList<>();
        Connection conn = null;

        Double totalCost=0.0;//Total of cost price
        Double totalSale=0.0;//Total of selling price

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "";

        try{
            conn = DriverManager.getConnection(url, db, pwd);
            PreparedStatement statement = null;
            ResultSet rs = null;

            //Getting the right sql command depending on the product type
            if(typ.equals("All")){
                sql = "select costPrice, sellingPrice, stock from stockmanagement.product";
                statement = conn.prepareStatement(sql);
                rs = statement.executeQuery();
            }else{
                sql = "select costPrice, sellingPrice, stock from stockmanagement.product where type =?";
                statement = conn.prepareStatement(sql);
                statement.setString(1, typ);
                rs = statement.executeQuery();
            }

            while (rs.next()) {
                totalCost += rs.getDouble("costPrice")*rs.getDouble("stock");
                totalSale += rs.getDouble("sellingPrice")*rs.getDouble("stock");
            }
            result.add(Math.floor(totalCost));
            result.add(Math.floor(totalSale));

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
        return result;
    }

    //This function checks if any product is in low stock
    public boolean checkLowStock(){
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select * from stockmanagement.product";

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                if (rs.getInt("stock")<=10) return true;
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
        return false;
    }

    //The next function while return false if any JTextField is empty
    public boolean isValidStringTextfields (List<JTextField> liste){
        for (int i = 0; i < liste.size(); i++) {
            if(liste.get(i).getText().isEmpty()) return false;
        }
        return true;
    }

    //The function checks if the Data passed on the list are valid Integer
    public boolean isValidIntTextfields (List<JTextField> liste){
        for (int i = 0; i < liste.size(); i++) {
            if(!isValidInt(liste.get(i).getText())) return false;
        }
        return true;
    }

    //The function checks if the Data passed on the list are valid Integer
    public boolean isValidDoubleTextfields (List<JTextField> liste){
        for (int i = 0; i < liste.size(); i++) {
            if(!isValidDouble(liste.get(i).getText())) return false;
        }
        return true;
    }

    //This function is used to direct users to the right dashboard (admin or employee)
    public void dashoardDirection(String username){
        if(checkAdminRight(username)==1){
            new AdminDashboard(username);
            dispose();
        }else{//Opening an employee dashboard if not an Admin
            new employeeDashboard(username);
            dispose();
        }
    }

    //This function will handle to manage employees
    public String displayWorker(String special){
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";
        if (special.equals("All")){
            sql = "select * from stockmanagement.workers";
        }
        if(special.equals("Admin")){
            sql = "select * from stockmanagement.workers where isAdmin =?";
        }
        if(special.equals("Active")){
            sql = "select * from stockmanagement.workers where isActive=?";
        }
        if(special.equals("Inactive")){
            sql = "select * from stockmanagement.workers where isActive=?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Admin")) {
                statement.setInt(1, 1);//if it's admin
            }
            if (special.equals("Active")) {
                statement.setInt(1, 1);//If active
            }
            if (special.equals("Inactive")) {
                statement.setInt(1, 0);//If inactive
            }
            ResultSet rs = statement.executeQuery();

            result += "\n";
            while (rs.next()) {
                String un= rs.getString("username");
                String fn= rs.getString("firstName");
                String ln= rs.getString("lastName");
                String pss= rs.getString("password");
                int ad= rs.getInt("isAdmin");
                int act= rs.getInt("isActive");
                Worker w = new Worker(createWorkerID(un, fn, ln), un, fn, ln, pss, act, ad);
                result += w.display();
                result += "\n";
            }
            result+="\n";

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
        return result;
    }


    //This function is used to search products by names, brands..
    public String searchProducts(String specialType, String specialAttribute, String searchText, boolean isNumber){
        //SpecialType is the productType if choose, specialAttribute is the productAttribute, and searchtext the word to search
        //isNumber defines if special attribute is an int or a string
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";

        if (specialType.equals("All")){
            sql = "select * from stockmanagement.product where "+specialAttribute+" = ?";
        }
        else{
            sql = "select * from stockmanagement.product where "+specialAttribute+" = ? and type = ?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if(isNumber){
                if(isValidDouble(searchText)){
                    statement.setDouble(1, Double.parseDouble(searchText));
                }else if(isValidInt(searchText)){
                    statement.setInt(1, Integer.parseInt(searchText));
                }
            }else{
                statement.setString(1, searchText);
            }
            if(!specialType.equals("All")){
                statement.setString(2, specialType);
            }
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                String type = rs.getString("type");

                Product p= null;
                switch(type){
                    case "Beer":
                        p = new Beer(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"),
                                rs.getString("brand"), rs.getDouble("costPrice"), rs.getDouble("sellingPrice"));
                        break;

                    case "Juice":
                        p = new Juice(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"),
                                rs.getString("brand"), rs.getDouble("costPrice"), rs.getDouble("sellingPrice"));
                        break;
                    case "Spirit":
                        p = new Spirit(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"),
                                rs.getString("brand"), rs.getDouble("costPrice"), rs.getDouble("sellingPrice"));
                        break;
                    case "Wine":
                        p = new Wine(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"),
                                rs.getString("brand"), rs.getDouble("costPrice"), rs.getDouble("sellingPrice"));
                        break;
                    case "Energetic":
                        p = new Beer(rs.getInt("barcodeNumber"), rs.getInt("volume"), rs.getString("name"),
                                rs.getString("brand"), rs.getDouble("costPrice"), rs.getDouble("sellingPrice"));
                        break;
                }
                if(specialType.equals("All")){
                    result += "Type: " + rs.getString("type");
                    result+= "\n";
                }
                result += p.display();
                result += "\nStock: " + rs.getInt("stock") +" stocked\n";
                result += "----------------------------\n";
            }
            result+="\n";

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
        return result;
    }

    //This function will be used to count employees follwing a certain criteria
    public int countWorkers(String typ){
        int result=0;
        Connection conn = null;


        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "";
        //Describing the sql command
        if(typ.equals("All")){
            sql = "select * from stockmanagement.workers";
        }else if(typ.equals("Inactive") || typ.equals("Active")){
            sql = "select * from stockmanagement.workers where isActive =?";
        } else{
            sql = "select * from stockmanagement.workers where isAdmin =?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);
            PreparedStatement statement = conn.prepareStatement(sql);
            if (typ.equals("Inactive")) {
                statement.setInt(1, 0);
            }else if (typ.equals("Active")) {
                statement.setInt(1, 1);
            }else if (typ.equals("Admin")) {
                statement.setInt(1, 1);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                result++;
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
        return result;
    }

    public boolean updateTransactionProduct (int barcode, int transaction){//Returns true if the transaction has been added and false if not
        Connection conn = null;
        boolean flag = false;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "insert into stockmanagement.transactionproduct " +
                "(barcodenumber, transactionID, quantity, price) values(?,?,?,?)";
        //retrieve data from transaction table
        String sql1 = "select * from stockmanagement.transaction where transactionID = ?";
        //Data from product table
        String sql2 = "select * from stockmanagement.product where barcodenumber = ?";

        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement st1 = conn.prepareStatement(sql1);
            st1.setInt(1, transaction);
            PreparedStatement st2 = conn.prepareStatement(sql2);
            st2.setInt(1, barcode);
            ResultSet rs1 = st1.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            PreparedStatement statement = null;
            if(rs1.next() && rs2.next()){
                statement = conn.prepareStatement(sql);
                statement.setInt(1, rs2.getInt("barcodenumber"));
                statement.setInt(2, rs1.getInt("transactionID"));
                statement.setInt(3, rs1.getInt("quantity"));
                statement.setDouble(4, rs1.getDouble("price"));

            }
            //Execute query
            statement.executeUpdate();
            flag = true;
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


    public int maxTrasactionID (){//Returns true if the transaction has been added and false if not
        Connection conn = null;
        int id =0;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";

        String sql = "select MAX(transactionID) AS maxime from stockmanagement.transaction";


        try {
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                id = rs.getInt("maxime");
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
        return id;
    }

    public String displayTransaction(String special){
        String result="\n";
        Connection conn = null;

        String url = "jdbc:mysql://127.0.0.1:3306/stockmanagement";
        String db = "root";
        String pwd = "Maxime11";
        String sql ="";

        if(special.equals("All")){
            sql = "SELECT tp.transactionID, tp.barcodenumber, p.name, p.type, tp.quantity, " +
                    "tp.price, t.transactionType, t.username FROM TransactionProduct tp JOIN Product p ON tp.barcodenumber = p.barcodenumber JOIN " +
                    "Transaction t ON tp.transactionID = t.transactionID";
        }else{
            sql = "SELECT tp.transactionID, tp.barcodenumber, p.name, p.type, tp.quantity, " +
                    "tp.price, t.transactionType, t.username FROM TransactionProduct tp JOIN Product p ON tp.barcodenumber = p.barcodenumber JOIN " +
                    "Transaction t ON tp.transactionID = t.transactionID WHERE t.transactionType = ?";
        }

        try{
            conn = DriverManager.getConnection(url, db, pwd);

            PreparedStatement statement = conn.prepareStatement(sql);
            if (special.equals("Sale")) {
                statement.setString(1, "Sale");
            }
            if (special.equals("Purchase")) {
                statement.setString(1, "Purchase");
            }
            result+="\n";
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int bc = rs.getInt("barcodenumber");
                int tx = rs.getInt("transactionID");
                String trType = rs.getString("transactionType");
                double price = rs.getDouble("price");
                String type = rs.getString("type");
                int qty = rs.getInt("quantity");
                String name = rs.getString("name");
                String un= rs.getString("username");

                transactionProduct x = new transactionProduct(bc, tx, name, type, un, trType, qty, price);

                result += x.display();
                result+="\n";
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
        return result;
    }
}

