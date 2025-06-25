public class transactionProduct {
    private String nameT, typeT, transactionTypeT, usernameT;
    private int barcodenumberT, transactioIDT, quantityT;
    private double priceT;

    public transactionProduct(int barcodenumber, int transactioID, String name, String type,
                              String username, String transactionTypeT, int quantity, double price) {
        this.barcodenumberT = barcodenumber;
        this.transactioIDT = transactioID;
        this.nameT = name;
        this.typeT = type;
        this.usernameT = username;
        this.transactionTypeT = transactionTypeT;
        this.quantityT = quantity;
        this.priceT = price;
    }
    public String getName() {return this.nameT;}
    public String getType() {return this.typeT;}
    public String getUsername() {return this.usernameT;}
    public int getBarcodenumber() {return this.barcodenumberT;}
    public int getTransactioID() {return this.transactioIDT;}
    public int getQuantity() {return this.quantityT;}
    public double getPrice() {return this.priceT;}
    public  String getTransactionType(){return this.transactionTypeT;}

    bitRepeated rep = new bitRepeated();

    public String display() {
        String r = "";
        r+= "=== Transaction ID: " + transactioIDT + ", Barcode Number: " + barcodenumberT + " ===\n\n";
        r+="Product Name     : " + nameT;
        r+="\nType             : " + typeT;
        r+="\nSeller         : " + usernameT+ ", " + rep.firstName(getUsername());
        r+="\nTransaction Type      : " + transactionTypeT;
        r+= "\nQuantity: " + quantityT;
        r+="\nPrice            : "+priceT;
        r+="\n------------------------------------";

        return r;
    }

}
