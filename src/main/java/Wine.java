public class Wine extends Product {
    private double alcohol; // In %
    private  String region, grape; // Grape is the type of grape
    private int year;

    public Wine(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice,
                double alcohol, String region, String grape, int year){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        this.alcohol = alcohol;
        this.region = region;
        this.grape = grape;
        this.year = year;
    }

    public Wine(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
    }

    // Getter for the super class attributes are already defined,
    // Defining getters for the subclass attributes

    public double getAlcohol() {return this.alcohol;}
    public String getRegion() {return this.region;}
    public String getGrape() {return this.grape;}
    public int getYear() {return this.year;}

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
    public String display(int stock){
        String result = super.display(stock);
        result += "\nAlcohol: " + this.getAlcohol();
        result += "\nRegion: " + this.getRegion();
        result += "\nGrape: " + this.getGrape();
        result += "\nYear: " + this.getYear();
        result += "\nStock: " + stock + " stocked";
        result += "\n----------------------------";
        return result;
    }

    @Override
    public void saveSpecificAttributes(bitRepeated rep) {
        rep.addWine(this);
    }
}
