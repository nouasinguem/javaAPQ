import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class singleProductInfo extends JFrame {

    private String username;

    public singleProductInfo(String username) {
        super("Information about a product");
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
        c.setLayout(new GridLayout(6,1));
        Container c1 = this.getContentPane();
        c1.setBackground(Color.BLUE);
        c1.setLayout(new CardLayout(5,5));
        c1.add(c);
        bitRepeated rep = new bitRepeated();//With function

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
        JLabel SellingWelcome = new JLabel("Information about a product");
        JLabel barcode = new JLabel("Enter the barcode of the product");
        JTextField barcodeField = new JTextField(10);
        JTextArea information = new JTextArea();
        JButton confirm = new JButton("Confirm");
        JButton back = new JButton("Back");
        JButton print = new JButton("Print");
        JLabel file = new JLabel("File Name");
        JTextField fileField = new JTextField(10);

        //Setting the page title
        rep.header(c, SellingWelcome);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel1.add(barcode);
        panel1.add(barcodeField);
        barcodeField.setPreferredSize(new Dimension(160, 30));
        JPanel panel21 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel21.add(file);
        panel21.add(fileField);
        panel21.add(print);
        fileField.setPreferredSize(new Dimension(160, 30));
        panel1.setBackground(Color.GREEN);
        panel21.setBackground(Color.GREEN);

        c.add(panel1);
        c.add(panel21);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        //information.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        information.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel2.setBackground(Color.WHITE);
        information.setEditable(false);
        information.setLineWrap(true);
        information.setWrapStyleWord(true);


        JScrollPane scroll = new JScrollPane(information);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setPreferredSize(new Dimension(470, 80));
        panel2.add(scroll);
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
                SellingWelcome.setText("Informations sur un produit");
                barcodeField.setText("");
                barcode.setText("Entrer le code barre du produit");
                information.setText("");
                confirm.setText("Confirmer");
                back.setText("Retour");
                print.setText("Nom du fichier");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                SellingWelcome.setText("Information about a product");
                barcode.setText("Enter the barcode of the product");
                barcodeField.setText("");
                information.setText("");
                confirm.setText("Confirm");
                back.setText("Back");
                print.setText("Print");
                fileField.setText("");
                file.setText("File Name");
            }
        });

        //Going back to the dashboard onclick "Back"
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Check whether it's an Admin or an Employee logged in to open the right dashboard (Admin or Employee)
                if(rep.checkAdminRight(username)==1){
                    new AdminDashboard(username);
                    dispose();
                }else{//Opening an employee dashboard if not an Admin
                    new employeeDashboard(username);
                    dispose();
                }
            }
        });

        confirm.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){

                //Data validation
                if(barcodeField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill in the the space\nS'il vous plaît remplissez l'espace");
                }else{
                    if(rep.isValidInt(barcodeField.getText())){//Valid int
                        if(rep.checkExistingProduct(Integer.parseInt(barcodeField.getText()))){//Checks if the product exists
                            information.setText(rep.singleProduct(Integer.parseInt(barcodeField.getText())));
                            information.setFont(new Font("Verdana", Font.ITALIC, 15));
                        } else{
                            JOptionPane.showMessageDialog(null, "No such product exists\nPas de tel produit existant");
                        }
                    }else {
                        JOptionPane.showMessageDialog(null, "Wrong data type\nMauvais type de données");
                    }
                }

            }
        });

        print.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(fileField.getText().isEmpty() || barcodeField.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "All fields are required\nTout les champs sont requis");
                    return;
                }
                String fileName = fileField.getText();
                int barcode = Integer.parseInt(barcodeField.getText());
                File myObj = new File(fileName);
                try {
                    if (myObj.createNewFile()) {
                        JOptionPane.showMessageDialog(null, "File created\nFichier créé");
                    } else {
                        JOptionPane.showMessageDialog(null, "File already exists\nFichier existe déjà");
                        return;
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "An error occurred.");
                    e1.printStackTrace();
                    return;
                }

                if (rep.checkExistingProduct(barcode)) {
                    String r = rep.singleProduct(barcode);
                    byte[] arr = r.getBytes();
                    try {
                        Files.write(myObj.toPath(), arr);
                        JOptionPane.showMessageDialog(null, "File updated\nFichier mis à jour");
                        rep.dashoardDirection(getUsername());
                        dispose();
                    } catch (IOException e2) {
                        JOptionPane.showMessageDialog(null, "An error occurred while writing to the file.");
                        e2.printStackTrace();
                    }
                } else{
                    JOptionPane.showMessageDialog(null, "No such product exists\nPas de tel produit existant");
                }
            }
        });

    }
}
