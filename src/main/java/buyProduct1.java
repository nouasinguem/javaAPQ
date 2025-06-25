import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class buyProduct1 extends JFrame {

    private String username;

    public buyProduct1(String username) {
        super("Buy Product");
        this.username = username;
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public String getUsername() { return this.username; }

    public void design() {
        Container c = new Container();
        c.setBackground(Color.RED);
        c.setLayout(new GridLayout(5, 1));
        Container c1 = this.getContentPane();
        c1.setBackground(Color.BLUE);
        c1.setLayout(new CardLayout(5,5));
        c1.add(c);
        bitRepeated rep = new bitRepeated();//Containing all the sql commands and other commands

        //Setting up the french/english menu
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

        // Creating the elements on the page
        JLabel Welcome = new JLabel("Buying Product");
        JLabel barcode = new JLabel("Enter the barcode of the product");
        JTextField barcodeField = new JTextField(10);
        JLabel number = new JLabel("Enter the number of the product");
        JTextField numberField = new JTextField(10);
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");

        //Setting the page title
        rep.header(c, Welcome);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel1.add(barcode);
        panel1.add(barcodeField);
        barcodeField.setPreferredSize(new Dimension(160, 30));
        panel1.setBackground(Color.GREEN);
        c.add(panel1);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel2.add(number);
        panel2.add(numberField);
        numberField.setPreferredSize(new Dimension(160, 30));
        panel2.setBackground(Color.GREEN);
        c.add(panel2);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel3.add(back);
        panel3.add(confirm);
        back.setPreferredSize(new Dimension(100, 30));
        confirm.setPreferredSize(new Dimension(100, 30));
        panel3.setBackground(Color.RED);
        c.add(panel3);



        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                Welcome.setText("Acheter un produit");
                barcodeField.setText("");
                barcode.setText("Entrer le code barre du produit");
                numberField.setText("");
                number.setText("Entrer le nombre de produit");
                confirm.setText("Confirmer");
                back.setText("Retour");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                Welcome.setText("Buying product");
                barcode.setText("Enter the barcode of the product");
                barcodeField.setText("");
                numberField.setText("");
                number.setText("Enter the number of the product");
                confirm.setText("Confirm");
                back.setText("Back");
            }
        });

        //Going back to the dashboard onclick "Back"
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rep.dashoardDirection(getUsername());
                dispose();
            }
        });

        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                //Check if the textFields input are valid
                if(!rep.isValidStringTextfields(new ArrayList<>(List.of(barcodeField, numberField)))){
                    JOptionPane.showMessageDialog(null,"Please fill in all the fields\nS'il vous plaît remplissez tout les champs");
                    return;
                }
                if(!rep.isValidIntTextfields(new ArrayList<>(List.of(barcodeField, numberField)))){
                    JOptionPane.showMessageDialog(null, "Wrong data type\nMauvais type de données");
                    return;
                }
                    int barcodevalue = Integer.parseInt(barcodeField.getText());
                    int numbervalue = Integer.parseInt(numberField.getText());
                    if(rep.checkExistingProduct(barcodevalue)){//Check if the product is in the database
                        //Display the product information and change the stock in the database
                        JOptionPane.showMessageDialog(null,"Product information\n\n"+ rep.singleProduct(barcodevalue));
                        JOptionPane.showMessageDialog(null, "Updating the stock...\nMise à jour du stock...");
                        rep.updateStock(barcodevalue, numbervalue);//Adding the new stock
                        //Displaying the new stock updated
                        JOptionPane.showMessageDialog(null,"Product information\n\n"+ rep.singleProduct(barcodevalue));
                        try {// Updating the transaction table
                            rep.addTransaction("Purchase", Integer.parseInt(numberField.getText()),
                                    numbervalue*rep.getPrice(barcodevalue, "costPrice"), getUsername());
                            rep.updateTransactionProduct(barcodevalue, rep.maxTrasactionID());
                        }catch (Exception exp){
                            exp.printStackTrace();
                        }
                        //Redirecting the user to his dashboard
                        rep.dashoardDirection(getUsername());
                        dispose();
                    }else {
                        JOptionPane.showMessageDialog(null, "Creating a new product\nCreation d'un nouveau produit");
                        new BuyProduct(getUsername(), barcodevalue, numbervalue);
                        dispose();
                    }
                }

        });

    }
}
