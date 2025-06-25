public abstract class  Product {
    protected int barcodeNumber, volume; // volume in mL
    protected String name, brand;
    protected double costPrice, sellingPrice;

    public Product(int barcodeNumber, int volume, String name, String brand,
                   double costPrice, double sellingPrice) {
        this.barcodeNumber = barcodeNumber;
        this.volume = volume;
        this.name = name;
        this.brand = brand;
        this.costPrice = costPrice;
        this.sellingPrice = sellingPrice;
    }

    // Defining getters for accessing each attribute

    public int getBarcodeNumber() {return this.barcodeNumber;}
    public int getVolume() {return this.volume;}
    public String getName() {return this.name;}
    public String getBrand() {return this.brand;}
    public double getCostPrice() {return this.costPrice;}
    public double getSellingPrice() {return this.sellingPrice;}

    //Defining setters for setting each attribute if needed
    public void setName(String name) {this.name = name;}

    public abstract String display();
    public String display(int stock) {
        String result = "Product N° " +getBarcodeNumber() + "\n";
        result += "Name: " + getName() + "\n";
        result += "Brand: " + getBrand() + "\n";
        result += "Volume: " + getVolume() + "ml\n";
        result += "Cost Price: " + getCostPrice() + "€\n";
        result += "Selling Price: " + sellingPrice + "€";

        return result;
    }

    public abstract void saveSpecificAttributes(bitRepeated rep);
}

