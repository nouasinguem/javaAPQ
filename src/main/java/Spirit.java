public class Spirit extends Product{
    private double alcohol;
    private String type, producer;

    public Spirit(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice,
                  double alcohol, String type, String producer){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        this.alcohol = alcohol;
        this.type = type;
        this.producer = producer;
    }

    public Spirit(int barcodeNumber, int volume, String name, String brand, double costPrice, double sellingPrice){
        super(barcodeNumber, volume, name, brand, costPrice, sellingPrice);
        }
    // Getter for the super class attributes are already defined,
    // Defining getters for the subclass attributes

    public double getAlcohol(){return this.alcohol;}
    public String getType(){return this.type;}
    public String getProducer(){return this.producer;}

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
        result += "\nAlcohol: " + alcohol + "%\n";
        result += "Type: " + type + "\n";
        result += "Producer: " + producer + "\n";
        result += "Stock: " + stock + " stocked";
        result += "\n---------------------------";
        return result;
    }
    @Override
    public void saveSpecificAttributes(bitRepeated rep) {
        rep.addSpirit(this);
    }
}
