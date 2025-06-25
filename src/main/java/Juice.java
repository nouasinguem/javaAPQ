public class Juice extends Product{
    private String fruit;
    private int sugar;
    private boolean pulp, conservative;

    // Initiating a juice object
    public Juice(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice,
                 String fruit, int sugar){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        this.fruit = fruit;
        this.sugar = sugar;
    }

    public Juice(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
    }

    // Get the specific attribute of the juice
    public String getFruit() {return this.fruit;}
    public int getSugar() {return this.sugar;}

    // Note: In our case, the specific attribute can not be edited once set

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
        result += "\nFruit: " + this.fruit;
        result += "\nSugar: " + this.sugar + " mg";
        result += "\nStock: " + stock + " stocked";
        result += "\n---------------------------";

        return result;
    }

    @Override
    public void saveSpecificAttributes(bitRepeated rep) {
        rep.addJuice(this);
    }
}
