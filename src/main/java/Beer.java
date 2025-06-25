public class Beer extends Product{
    private double alcohol;
    private String producer, country, packingType;

    public Beer(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice,
                String product, String country, String packingType, double alcohol) {
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        this.alcohol = alcohol;
        this.producer = product;
        this.country = country;
        this.packingType = packingType;
    }

    public Beer(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice) {
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
    }

    // Getter for the super class attributes are already defined,
    // Defining getters for the subclass attributes

    public double getAlcohol() {return this.alcohol;}
    public String getProducer() {return this.producer;}
    public String getCountry() {return this.country;}
    public String getPackingType() {return this.packingType;}

    // Note: No setters will be defined
    @Override
    public String display() {
        String result = "Product N° " +getBarcodeNumber() + "\n";
        result += "Name: " + getName() + "\n";
        result += "Brand: " + getBrand() + "\n";
        result += "Volume: " + getVolume() + "ml\n";
        result += "Cost Price: " + getCostPrice() + "€\n";
        result += "Selling Price: " + sellingPrice + "€";

        return result;
    }

    @Override
    public String display(int stock) {
        String result = super.display(stock);
        result += "\nAlcohol: " + this.alcohol+ "%";
        result += "\nProducer: " + this.producer;
        result += "\nCountry: " + this.country;
        result += "\nPacking Type: " + this.packingType;
        result += "\nStock: " + stock + " stocked";
        result += "\n---------------------------";

        return result;
    }

    @Override
    public void saveSpecificAttributes(bitRepeated rep) {
        rep.addBeer(this);
    }
}
