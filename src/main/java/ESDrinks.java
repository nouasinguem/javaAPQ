public class ESDrinks extends Product {
    private String flavor;
    private int sugarContent, caffeine, taurine; // values in mg
    private boolean sweeteners, carbonated;

    public ESDrinks(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice,
                     String flavor, int sugarContent, int caffeine, int taurine) {
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        this.flavor = flavor;
        this.sugarContent = sugarContent;
        this.caffeine = caffeine;
        this.taurine = taurine;

    }

    public ESDrinks(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice) {
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
    }
    // Getter for the super class attributes are already defined,
    // Defining getters for the subclass attributes

    public String getFlavor() {return this.flavor;}
    public int getSugarContent() {return this.sugarContent;}
    public int getCaffeine() {return this.caffeine;}
    public int getTaurine() {return this.taurine;}


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
        String result =super.display(stock);
        result += "\nFlavor: " + this.flavor;
        result += "\nSugar Content: " + this.sugarContent+" mg";
        result += "\nCaffeine: " + this.caffeine +" mg";
        result += "\nTaurine: " + this.taurine + " mg";
        result += "\nStock: " + stock + " stocked";
        result += "\n---------------------------";
        return result;
    }

    @Override
    public void saveSpecificAttributes(bitRepeated rep) {
        rep.addESDrink(this);
    }
}
