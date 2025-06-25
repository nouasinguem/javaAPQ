import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class BuyProduct extends JFrame {
    private String username;
    private int barcodenumber, stock;
    public BuyProduct(String username, int barcodenumber, int stock) {
        super("Add the product information");
        this.username = username;
        this.barcodenumber = barcodenumber;
        this.stock = stock;
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public String getUsername() { return this.username; }
    public int getBarcodenumber() { return this.barcodenumber; }
    public int getStock() { return this.stock; }

    public void design() {
        Container c = new Container();
        c.setLayout(new BoxLayout( c, BoxLayout.Y_AXIS));
        c.setBackground(Color.RED);
        Container c1 = this.getContentPane();
        c1.setBackground(Color.BLUE);
        c1.setLayout(new CardLayout(5,5));
        c1.add(c);
        bitRepeated rep = new bitRepeated();//To use functions
        FileInputStream fis = null; //Final form of the image to be stored in the database BLOB

        //To select the image to go with the product
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("Images", "jpg", "jpeg", "png");
        fileChooser.setFileFilter(extension);
        c.add(fileChooser);
        int result = fileChooser.showOpenDialog(null);
            final File selectedFile = fileChooser.getSelectedFile();
            //Checking the image extension
            if(!extension.accept(selectedFile)){
                JOptionPane.showMessageDialog(null, "Please select an image file\nSelectionnez une image");
                rep.dashoardDirection(getUsername());
                dispose();
            }


        //Setting the menu
        JPanel top = new JPanel(new GridLayout(1,2));
        JMenuBar language = new JMenuBar();
        JMenuItem french = new JMenuItem("Français");
        JMenuItem english = new JMenuItem("English");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JMenu menu = new JMenu("EN");
        menu.add(english);
        menu.add(french);
        language.add(menu);
        panel.add(language);

        // display the user logged in
        JLabel loggedUser = new JLabel("Logged in as, "+ rep.firstName(getUsername()));
        JPanel p = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loggedUser.setFont(new Font("Serif", Font.ITALIC, 13));
        p.add(loggedUser);

        top.add(p);
        top.add(panel);
        c.add(top);
        //Declaring elements of the page
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");

        //Setting the page title
        JLabel title = new JLabel("Product N° "+getBarcodenumber());
        rep.header(c, title);

        //Defining radio button to give the user the choice to select the product type

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        buttons.setSize(new Dimension(500, 25));
        ButtonGroup productType = new ButtonGroup();
        JRadioButton Juice = new JRadioButton("Juice");
        JRadioButton ESDrinks = new JRadioButton("Energetic");
        JRadioButton Beer = new JRadioButton("Beer");
        JRadioButton Wine = new JRadioButton("Wine");
        JRadioButton Spirit = new JRadioButton("Spirit");
        //Adding the elements in the group
        productType.add(Juice);
        productType.add(ESDrinks);
        productType.add(Beer);
        productType.add(Wine);
        productType.add(Spirit);
        //Adding elements on the panel
        buttons.add(Juice);
        buttons.add(ESDrinks);
        buttons.add(Beer);
        buttons.add(Wine);
        buttons.add(Spirit);
        buttons.setBackground(Color.GREEN);
        c.add(buttons);

        //Declaring the common bit of the page (product attributes)

        //This is the elements appearing (common)
        JPanel body = new JPanel(new GridLayout(2,1));//Body panel for all product's attributes
        JLabel typeLabel = new JLabel("Type: ");
        JLabel name = new JLabel("Name");
        JLabel brand = new JLabel("Brand");
        JLabel volume = new JLabel("Volume (mL)");
        JLabel costPrice = new JLabel("Cost Price");
        JLabel sellingPrice = new JLabel("Selling Price");
        JTextField typevalue = new JTextField(10);
        JTextField nameField = new JTextField(10);
        JTextField brandField = new JTextField(10);
        JTextField volumeField = new JTextField(10);
        JTextField costPriceField = new JTextField(10);
        JTextField sellingPriceField = new JTextField(10);

        //Specific attributes (Labels and Textfields

        JLabel alcohol = new JLabel("Alcohol (%)");
        JLabel producer = new JLabel("Producer");
        JLabel country = new JLabel("Country");
        JLabel packingType = new JLabel("Packing Type");
        JLabel flavor = new JLabel("Flavor");
        JLabel sugar = new JLabel("Sugar (per 100mL)");
        JLabel caffeine = new JLabel("Caffeine (mg)");
        JLabel taurine = new JLabel("Taurine (mg)");
        JLabel grape = new JLabel("Grape");
        JLabel year = new JLabel("Year");
        JLabel type = new JLabel("Type");
        JTextField alcoholField = new JTextField(10);
        JTextField producerField = new JTextField(10);
        JTextField countryField = new JTextField(10);
        JTextField packingTypeField = new JTextField(10);
        JTextField flavorField = new JTextField(10);
        JTextField sugarField = new JTextField(10);
        JTextField caffeineField = new JTextField(10);
        JTextField taurineField = new JTextField(10);
        JTextField grapeField = new JTextField(10);
        JTextField yearField = new JTextField(10);
        JTextField typeField = new JTextField(10);


        JPanel inBody = new JPanel(new GridLayout(6,2, 30, 10)); // Panel for general product attributes
        JPanel inbody2 = new JPanel(new GridLayout(6,2, 30, 10));//Panel for specific product attributes

        inBody.add(typeLabel);
        inBody.add(typevalue);
        inBody.add(name);
        inBody.add(nameField);
        inBody.add(brand);
        inBody.add(brandField);
        inBody.add(volume);
        inBody.add(volumeField);
        inBody.add(costPrice);
        inBody.add(costPriceField);
        inBody.add(sellingPrice);
        inBody.add(sellingPriceField);

        body.add(inBody);
        body.add(inbody2);

        body.setBackground(Color.LIGHT_GRAY);
        inbody2.setBackground(Color.LIGHT_GRAY);
        inBody.setBackground(Color.LIGHT_GRAY);

        JScrollPane scroll = new JScrollPane(body);// For the body to be scrolling
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        c.add(scroll);
        scroll.setBackground(Color.GREEN);

        typevalue.setEditable(Boolean.FALSE);


        //Case of Juice
        //Adding special attributes for ESDrinks
        Juice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the radio button is selected
                if (Juice.isSelected()) {
                    inbody2.removeAll();//Remove all component of the panel for specific attributes to reEdit it

                    //Adding the specific attributes in the body
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    inbody2.add(flavor);
                    inbody2.add(flavorField);
                    inbody2.add(sugar);
                    inbody2.add(sugarField);
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    typevalue.setText(Juice.getText());
                    //Refreshing the page after the click
                    c.revalidate();
                    c.repaint();

                }
            }
        });

        //Adding special attributes for ESDrinks
        ESDrinks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the radio button is selected
                if (ESDrinks.isSelected()) {
                    inbody2.removeAll();//Remove all component of the panel for specific attributes to reEdit it

                    //Adding the specific attributes in the body
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    inbody2.add(flavor);
                    inbody2.add(flavorField);
                    inbody2.add(sugar);
                    inbody2.add(sugarField);
                    inbody2.add(caffeine);
                    inbody2.add(caffeineField);
                    inbody2.add(taurine);
                    inbody2.add(taurineField);
                    typevalue.setText(ESDrinks.getText());

                    //Refreshing the page after the click
                    c.revalidate();
                    c.repaint();

                }
            }
        });

        //Adding special attributes for Beer
        Beer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the radio button is selected
                if (Beer.isSelected()) {
                    inbody2.removeAll();//Remove all component of the panel for specific attributes to reEdit it

                    //Adding the specific attributes in the body
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    inbody2.add(alcohol);
                    inbody2.add(alcoholField);
                    inbody2.add(producer);
                    inbody2.add(producerField);
                    inbody2.add(country);
                    inbody2.add(countryField);
                    inbody2.add(packingType);
                    inbody2.add(packingTypeField);
                    typevalue.setText(Beer.getText());

                    //Refreshing the page after the click
                    c.revalidate();
                    c.repaint();

                }
            }
        });

        //Adding special attributes for Wine
        Wine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the radio button is selected
                if (Wine.isSelected()) {
                    inbody2.removeAll();//Remove all component of the panel for specific attributes to reEdit it

                    //Adding the specific attributes in the body
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    inbody2.add(alcohol);
                    inbody2.add(alcoholField);
                    inbody2.add(country);
                    inbody2.add(countryField);
                    inbody2.add(grape);
                    inbody2.add(grapeField);
                    inbody2.add(year);
                    inbody2.add(yearField);
                    typevalue.setText(Wine.getText());

                    //Refreshing the page after the click
                    c.revalidate();
                    c.repaint();

                }
            }
        });

        //Adding special attributes for ESDrinks
        Spirit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if the radio button is selected
                if (Spirit.isSelected()) {
                    inbody2.removeAll();//Remove all component of the panel for specific attributes to reEdit it

                    //Adding the specific attributes in the body
                    inbody2.add(new JLabel(""));
                    inbody2.add(new JLabel(""));
                    inbody2.add(alcohol);
                    inbody2.add(alcoholField);
                    inbody2.add(type);
                    inbody2.add(typeField);
                    inbody2.add(producer);
                    inbody2.add(producerField);
                    typevalue.setText(Spirit.getText());

                    //Refreshing the page after the click
                    c.revalidate();
                    c.repaint();

                }
            }
        });



        JPanel footer = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 5));
        footer.add(back);
        footer.add(confirm);
        back.setPreferredSize(new Dimension(100, 30));
        confirm.setPreferredSize(new Dimension(100, 30));
        footer.setBackground(Color.RED);
        c.add(footer);



        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                back.setText("Retour");
                confirm.setText("Confirmer");
                title.setText("Acheter des produits");
                loggedUser.setText("Connecté (e) comme, "+ rep.firstName(getUsername()));
                Juice.setText("Jus");
                ESDrinks.setText("Energetique");
                Beer.setText("Bière");
                Wine.setText("Vin");
                Spirit.setText("Whisky");
                name.setText("Nom");
                brand.setText("Marque");
                costPrice.setText("Prix d'achat");
                sellingPrice.setText("Prix de vente");
                alcohol.setText("Alcool (%)");
                producer.setText("Producteur");
                country.setText("Pays");
                packingType.setText("Emballage");
                flavor.setText("Goût");
                sugar.setText("Sucre (par 100mL)");
                grape.setText("Raisins");
                year.setText("Année");
            }
        });


        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new BuyProduct(getUsername(), getBarcodenumber(), getStock());
                dispose();

            }
        });

        //Going back to the dashboard onclick "Back"
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Check whether it's an Admin or an Employee logged in to open the right dashboard (Admin or Employee)
                rep.dashoardDirection(getUsername());
                dispose();
            }
        });

        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                boolean operationSuccess = false;
                //First, we are processing the data validation
                if(Juice.isSelected()==false && ESDrinks.isSelected()==false && Beer.isSelected()==false && Wine.isSelected()==false && Spirit.isSelected()==false){
                    //In case no product type has been selected
                    JOptionPane.showMessageDialog(null, "Please select a product type\nS'il vous plaît selectionnez un type de produit");
                    return;
                }
                //Product type TextFields validation
                if(!rep.isValidStringTextfields(new ArrayList<>(List.of(nameField, brandField, volumeField, costPriceField, sellingPriceField)))){
                    JOptionPane.showMessageDialog(null, "Please fill in all the fields\nS'il vous plaît remplissez tout les champs");
                    return;
                }//Validating the cost and selling fields
                else if(!rep.isValidDoubleTextfields(new ArrayList<>(List.of(costPriceField, sellingPriceField)))){
                    JOptionPane.showMessageDialog(null, "Selling or Cost price invalid\nPrix de vente ou d'achat invalide");
                    return;
                }

                //Checking the specific attributes of each product type
                if(Juice.isSelected()){
                    //Empty fields
                    if (!rep.isValidStringTextfields(new ArrayList<>(List.of(flavorField, sugarField)))){
                        JOptionPane.showMessageDialog(null, "All specific attribute need to be filled in\nTout les attrbut spécifiques doivent être remplis");
                        return;
                    }
                    //Int fields
                    if(!rep.isValidIntTextfields(new ArrayList<>(List.of(sugarField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                }else if (ESDrinks.isSelected()){
                    //Empty fields
                    if (!rep.isValidStringTextfields(new ArrayList<>(List.of(flavorField, sugarField,caffeineField, taurineField)))){
                        JOptionPane.showMessageDialog(null, "All specific attribute need to be filled in\nTout les attrbut spécifiques doivent être remplis");
                        return;
                    }
                    //Int fields
                    if(!rep.isValidIntTextfields(new ArrayList<>(List.of(sugarField, caffeineField, taurineField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                }else if (Beer.isSelected()){
                    //Empty fields
                    if (!rep.isValidStringTextfields(new ArrayList<>(List.of(alcoholField, producerField, countryField, packingTypeField)))){
                        JOptionPane.showMessageDialog(null, "All specific attribut need to be filled in\nTout les attrbut spécifiques doivent être remplis");
                        return;
                    }
                    //Double fields
                    if(!rep.isValidDoubleTextfields(new ArrayList<>(List.of(alcoholField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                }else if (Wine.isSelected()){
                    //Empty fields
                    if (!rep.isValidStringTextfields(new ArrayList<>(List.of(alcoholField, grapeField, countryField, yearField)))){
                        JOptionPane.showMessageDialog(null, "All specific attribut need to be filled in\nTout les attrbut spécifiques doivent être remplis");
                        return;
                    }
                    //Int fields
                    if(!rep.isValidIntTextfields(new ArrayList<>(List.of(yearField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                    //Double fields
                    if(!rep.isValidDoubleTextfields(new ArrayList<>(List.of(alcoholField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                }else if (Spirit.isSelected()){
                    //Empty fields
                    if (!rep.isValidStringTextfields(new ArrayList<>(List.of(alcoholField, producerField, typeField)))){
                        JOptionPane.showMessageDialog(null, "All specific attribut need to be filled in\nTout les attrbut spécifiques doivent être remplis");
                        return;
                    }
                    //Double fields
                    if(!rep.isValidDoubleTextfields(new ArrayList<>(List.of(alcoholField)))){
                        JOptionPane.showMessageDialog(null, "Wrong specific data type\nMauvais type de données spécique");
                        return;
                    }
                }


                        //Getting values on the form
                int volumeValue = Integer.parseInt(volumeField.getText());
                double costValue = Double.parseDouble(costPriceField.getText());
                double sellValue = Double.parseDouble(sellingPriceField.getText());
                String nameValue = nameField.getText();
                String brandValue = brandField.getText();

                if (costValue >= sellValue){//Case product cost >= product selling price
                    JOptionPane.showMessageDialog(null, "Cost price should be less " +
                            "than selling price" +
                            "\nLe coût d'achat devrait être moins que le prix de vente");
                    return;
                }// costvalue < sellvalue

                //After all data on the form validated, processing to the insertion in the database
                //Creating an instance of the product
                Product produit;
                if (Juice.isSelected()){
                        produit = new Juice(getBarcodenumber(), volumeValue, nameValue, brandValue, costValue, sellValue,
                                flavorField.getText(), Integer.parseInt(sugarField.getText()));
                        rep.addProduct(produit, getStock(), typevalue.getText(), selectedFile);
                        produit.saveSpecificAttributes(rep);//Adding the specifications in the beer table
                        JOptionPane.showMessageDialog(null, "Product added successfully\nAjout du produit réussie");
                        operationSuccess = true;
                        rep.dashoardDirection(getUsername());
                        dispose();
                        return;

                } else if (ESDrinks.isSelected()){
                    produit = new ESDrinks(getBarcodenumber(), volumeValue, nameValue, brandValue, costValue, sellValue, flavorField.getText(),
                            Integer.parseInt(sugarField.getText()), Integer.parseInt(caffeineField.getText()), Integer.parseInt(taurineField.getText()));

                    rep.addProduct(produit, getStock(), typevalue.getText(), selectedFile);
                        produit.saveSpecificAttributes(rep);//Adding the specifications in the beer table
                        JOptionPane.showMessageDialog(null, "Product added successfully\nAjout du produit réussie");
                        operationSuccess = true;
                        rep.dashoardDirection(getUsername());
                        dispose();
                        return;
                    }
                else if (Beer.isSelected()){
                        produit = new Beer(getBarcodenumber(), volumeValue, nameValue, brandValue, costValue, sellValue, producerField.getText(),
                                countryField.getText(), packingTypeField.getText(), Double.parseDouble(alcoholField.getText()));
                        rep.addProduct(produit, getStock(), typevalue.getText(), selectedFile);
                        produit.saveSpecificAttributes(rep);//Adding the specifications in the beer table
                        JOptionPane.showMessageDialog(null, "Product added successfully\nAjout du produit réussie");
                        operationSuccess = true;
                        rep.dashoardDirection(getUsername());
                        dispose();
                        return;
                        }
                else if (Wine.isSelected()){
                    produit = new Wine(getBarcodenumber(), volumeValue, nameValue, brandValue, costValue, sellValue, Double.parseDouble(alcoholField.getText()),
                            countryField.getText(), grapeField.getText(), Integer.parseInt(yearField.getText()));

                    rep.addProduct(produit, getStock(), typevalue.getText(), selectedFile);
                        produit.saveSpecificAttributes(rep);//Adding the specifications in the beer table
                        JOptionPane.showMessageDialog(null, "Product added successfully\nAjout du produit réussie");
                        operationSuccess = true;
                        rep.dashoardDirection(getUsername());
                        dispose();
                        return;
                    }
                else if (Spirit.isSelected()){
                    produit = new Spirit(getBarcodenumber(), volumeValue, nameValue, brandValue, costValue, sellValue, Double.parseDouble(alcoholField.getText()),
                            typeField.getText(), producerField.getText());
                        rep.addProduct(produit, getStock(), typevalue.getText(), selectedFile);
                        produit.saveSpecificAttributes(rep);//Adding the specifications in the beer table
                        JOptionPane.showMessageDialog(null, "Product added successfully\nAjout du produit réussie");
                        operationSuccess = true;
                        rep.dashoardDirection(getUsername());
                        dispose();
                        return;
                }

                if (operationSuccess){
                    //Update the transaction table with the transaction that has just been made
                    try {
                        rep.addTransaction("Purchase", getStock(), getStock()*Double.parseDouble(costPriceField.getText()), getUsername());
                        rep.updateTransactionProduct(getBarcodenumber(), rep.maxTrasactionID());
                    }catch (Exception exp){
                        exp.printStackTrace();
                    }
                    rep.dashoardDirection(getUsername());
                    dispose();
                }
            }
        });
    }
}
