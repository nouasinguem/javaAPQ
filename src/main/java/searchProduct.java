import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

public class searchProduct extends JFrame {
    private String username;

    public String getUsername() { return this.username; }

    public searchProduct(String username) {
        super("Search Product");
        this.username = username;
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void design() {
        Container c = new Container();
        c.setLayout(new BoxLayout( c, BoxLayout.Y_AXIS));
        c.setBackground(Color.RED);
        Container c1 = this.getContentPane();
        c1.setBackground(Color.BLUE);
        c1.setLayout(new CardLayout(5,5));
        c1.add(c);
        bitRepeated rep = new bitRepeated();//With functions

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
        JButton back = new JButton("Back");
        JButton search = new JButton("Search");

        //Setting the page title
        JLabel title = new JLabel("Search products");
        rep.header(c, title);

        //Defining radio button to give the user the choice to select the product type

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
//        buttons.setSize(new Dimension(500, 25));
        ButtonGroup productType = new ButtonGroup();
        JRadioButton all = new JRadioButton("All");
        JRadioButton Juice = new JRadioButton("Juice");
        JRadioButton ESDrinks = new JRadioButton("Energetic");
        JRadioButton Beer = new JRadioButton("Beer");
        JRadioButton Wine = new JRadioButton("Wine");
        JRadioButton Spirit = new JRadioButton("Spirit");

        //Adding the elements in the group
        productType.add(all);
        productType.add(Juice);
        productType.add(ESDrinks);
        productType.add(Beer);
        productType.add(Wine);
        productType.add(Spirit);
        //Adding elements on the panel
        buttons.add(all);
        buttons.add(Juice);
        buttons.add(ESDrinks);
        buttons.add(Beer);
        buttons.add(Wine);
        buttons.add(Spirit);
        buttons.setBackground(Color.GREEN);
        JPanel p1 = new JPanel(new GridLayout(2,1));

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton name =new JButton("Name");
        JButton brand = new JButton("Brand");
        JButton volume = new JButton("Volume");
        JButton cost = new JButton("Purchase Price");
        JButton sale = new JButton("Sale Price");
        JButton stock = new JButton("Stock");

        name.setPreferredSize(new Dimension(130,20));
        brand.setPreferredSize(new Dimension(130,20));
        volume.setPreferredSize(new Dimension(130,20));
        cost.setPreferredSize(new Dimension(130,20));
        sale.setPreferredSize(new Dimension(130,20));
        stock.setPreferredSize(new Dimension(130,20));

        panel1.add(name);
        panel1.add(brand);
        panel1.add(volume);
        panel1.add(cost);
        panel1.add(sale);
        panel1.add(stock);

        p1.add(buttons);
        p1.add(panel1);
        p1.setPreferredSize(new Dimension(470, 200));
        c.add(p1);
        panel1.setBackground(Color.GREEN);
        JPanel p2 = new JPanel(new GridLayout(1,2));
        JLabel filter = new JLabel("Name");
        JTextField filterValue = new JTextField();
        filterValue.setPreferredSize(new Dimension(200,20));
        filterValue.setBorder(BorderFactory.createLineBorder(Color.black));
        filterValue.setBackground(Color.LIGHT_GRAY);
        JPanel p3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        p3.add(filter);
        p2.add(p3);
        p3.setBackground(Color.GREEN);
        p2.add(filterValue);
        p2.setBackground(Color.GREEN);
        c.add(p2);

        //Container to display information
        JTextArea information = new JTextArea();
        information.setEditable(false);
        information.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scroll = new JScrollPane(information);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(scroll);
        scroll.setPreferredSize(new Dimension(200, 500));
        scroll.setBackground(Color.RED);
        information.setBackground(Color.LIGHT_GRAY);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel3.add(search);
        panel3.add(back);
        search.setPreferredSize(new Dimension(100, 30));
        back.setPreferredSize(new Dimension(100, 30));
        panel3.setBackground(Color.RED);
        c.add(panel3);

        //Redirecting to the dashboard
        back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rep.dashoardDirection(getUsername());
                dispose();
            }
        });
        //To change the filter label
        name.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(name.getText());
                filterValue.setText("");
                information.setText("");
            }
        });
        //To change the filter label
        brand.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(brand.getText());
                filterValue.setText("");
                information.setText("");
            }
        });
        //To change the filter label
        volume.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(volume.getText());
                filterValue.setText("");
                information.setText("");
            }
        });
        //To change the filter label
        cost.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(cost.getText());
                filterValue.setText("");
                information.setText("");
            }
        });

        //To change the filter label
        sale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(sale.getText());
                filterValue.setText("");
                information.setText("");
            }
        });
        //To change the filter label
        stock.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                filter.setText(stock.getText());
                filterValue.setText("");
                information.setText("");
            }
        });
        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                all.setText("Tout");
                Juice.setText("Jus");
                Beer.setText("Bière");
                Wine.setText("Vin");
                Spirit.setText("Whisky");
                back.setText("Retour");
                name.setText("Nom");
                brand.setText("Marque");
                cost.setText("Prix d'achat");
                sale.setText("Prix de vente");
                filter.setText("Nom");
                filterValue.setText("");
                search.setText("Chercher");
                title.setText("Chercher des Produits");
                information.setText("");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                all.setText("All");
                Juice.setText("Juice");
                Beer.setText("Beer");
                Wine.setText("Wine");
                Spirit.setText("Spirit");
                back.setText("Back");
                name.setText("Name");
                brand.setText("Brand");
                cost.setText("Purchase Price");
                sale.setText("Sale Price");
                filterValue.setText("");
                filter.setText("Name");
                search.setText("Search");
                title.setText("Search products");
                information.setText("");
            }
        });

        search.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                boolean isNumber = true;//Identify the filter nature
                //Check the filter type has been selected
                if (!all.isSelected() && !Juice.isSelected() && !ESDrinks.isSelected() &&
                        !Wine.isSelected() && !Beer.isSelected() && !Spirit.isSelected()) {
                    JOptionPane.showMessageDialog(null, "Please select a product type\nS'il vous plaît sélectionnez un type de produit");
                    return;
                }
                //Check if the filter value has been filled in properly, correct in double or string
                if(filter.getText().equals("Name") || filter.getText().equals("Nom") || filter.getText().equals("Brand") ||
                        filter.getText().equals("Marque") || filter.getText().equals("Volume")){//For string expected checking value
                    if(rep.isValidStringTextfields(new ArrayList<>(Arrays.asList(filterValue)))==false){
                        JOptionPane.showMessageDialog(null, "Please enter a valid filter\nS'il vous plaît entrez un filter valide");
                        return;
                    }else{
                        isNumber = false;
                    }
                }
                //Check if the filter value has been filled in properly, correct in double, int or string
                if(filter.getText().equals("Purchase Price") || filter.getText().equals("Sale Price") || filter.getText().equals("Prix de vente") ||
                        filter.getText().equals("Prix d'achat")){//For string expected checking value
                    if(rep.isValidDoubleTextfields(new ArrayList<>(Arrays.asList(filterValue)))==false){
                        JOptionPane.showMessageDialog(null, "Wrong data type\nMauvais type de données");
                        return;
                    }
                }
                if(filter.getText().equals("Stock") || filter.getText().equals("Volume")){
                    if(rep.isValidIntTextfields(new ArrayList<>(Arrays.asList(filterValue)))==false){
                        JOptionPane.showMessageDialog(null, "Wrong data type\nMauvais type de données");
                        return;
                    }
                }
                //After all checks have been done on the data entries, proceed to the operations
                //Defining special type;
                String specialType ="";
                if(all.isSelected()){
                    specialType = "All";
                }else if(Juice.isSelected()){
                    specialType = "Juice";
                }else if(ESDrinks.isSelected()){
                    specialType = "Energetic";
                }else if(Wine.isSelected()){
                    specialType = "Wine";
                }else if(Beer.isSelected()){
                    specialType = "Beer";
                }else if(Spirit.isSelected()){
                    specialType = "Spirit";
                }
                //Defining specialAttribute the filtering attribute
                String specialAttribute = "";
                if(filter.getText().equals("Name") || filter.getText().equals("Nom")){
                    specialAttribute = "name";
                }else if(filter.getText().equals("Brand") || filter.getText().equals("Marque")){
                    specialAttribute = "brand";
                }else if(filter.getText().equals("Sale Price") || filter.getText().equals("Prix de vente")){
                    specialAttribute = "sellingPrice";
                }else if(filter.getText().equals("Purchase Price") || filter.getText().equals("Prix d'achat")){
                    specialAttribute = "costPrice";
                } else if (filter.getText().equals("Stock")){
                    specialAttribute = "stock";
                }else if(filter.getText().equals("Volume")){
                    specialAttribute = "volume";
                }
                //Defining the element to search for
                String searchText = filterValue.getText();
                //Filtering and displaying informations
                information.setText("All products with the value '"+searchText+"' as "+specialAttribute+"\n\n"+
                         rep.searchProducts(specialType, specialAttribute, searchText, isNumber));
            }
        });
    }

    public static void main(String[] args) {
        new searchProduct("");
    }
}
