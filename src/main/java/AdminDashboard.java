import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalTime;

public class AdminDashboard extends JFrame {

    private String username;

    public AdminDashboard(String username) {
        super("Dashboard");
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
        Container c = this.getContentPane();
        c.setLayout(new GridLayout(7,1));
        c.setBackground(Color.RED);

        bitRepeated rep = new bitRepeated();//Where all the function to retriieve data through sql are

        // Defining element on our page

        JButton buy = new JButton("Buy a product");
        JButton sell = new JButton("Sell a product");
        JButton single = new JButton("Single product information");
        JButton all = new JButton("All products information");
        JButton search = new JButton("Search product");
        JButton lowStock = new JButton("Low stock products");
        JButton approval = new JButton("Manage employees");
        JButton transaction = new JButton("View transactions");
        JButton logout = new JButton("Logout");

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
        loggedUser.setFont(new Font("Serif", Font.ITALIC, 13));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        loggedUser.setFont(new Font("Serif", Font.ITALIC, 13));
        p1.add(loggedUser);
        JButton profile = new JButton("Profile");
        profile.transferFocusBackward();
        profile.setBackground(Color.LIGHT_GRAY);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p2.add(profile);
        JPanel p = new JPanel(new GridLayout(2,1));

        p.add(p2);
        p.add(p1);

        top.add(p);
        top.add(panel);
        c.add(top);

        //Setting the page title
        JLabel title = new JLabel("Welcome");

        rep.header(c, title);

        // Actions  the user is able to perform
        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel2.add(sell);
        panel2.add(buy);
        sell.setPreferredSize(new Dimension(200, 25));
        buy.setPreferredSize(new Dimension(200, 25));
        panel2.setBackground(Color.GREEN);
        c.add(panel2);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel3.add(single);
        panel3.add(all);
        single.setPreferredSize(new Dimension(200, 25));
        all.setPreferredSize(new Dimension(200, 25));
        panel3.setBackground(Color.GREEN);
        c.add(panel3);

        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel4.add(search);
        panel4.add(lowStock);
        search.setPreferredSize(new Dimension(200, 25));
        lowStock.setPreferredSize(new Dimension(200, 25));
        panel4.setBackground(Color.GREEN);
        c.add(panel4);

        // Adding the admin approval button
        JPanel panel41 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel41.add(approval);
        panel41.add(transaction);
        panel41.setBackground(Color.GREEN);
        approval.setPreferredSize(new Dimension(200, 25));
        transaction.setPreferredSize(new Dimension(200, 25));
        c.add(panel41);

        // Logging out
        rep.Dashboardfooter(c, logout);

        //Here we want to disable the Low Stock product button if there's no low stock product
        if (!rep.checkLowStock()){
            lowStock.setEnabled(false);
        }else {
            lowStock.setBackground(Color.RED);
        }
        //Manifestation if a user is waiting for approval
        if(rep.countWorkers("Inactive")>0) approval.setBackground(Color.ORANGE);

        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Bye bye !");
                new LogIn();
                dispose();
            }
        });

        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                title.setText("Bienvenue");
                buy.setText("Acheter un produit");
                sell.setText("Vendre un produit");
                single.setText("Informations sur un produit");
                all.setText("Informations sur tout produits");
                search.setText("Chercher un produit");
                lowStock.setText("Produits en faible stock");
                logout.setText("Deconnexion");
                approval.setText("Gérer les employés");
                transaction.setText("Voir les transactions");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                title.setText("Welcome");
                buy.setText("Buy a product");
                sell.setText("Sell a product");
                single.setText("Single product information");
                all.setText("All products information");
                search.setText("Search product");
                lowStock.setText("Low stock products");
                logout.setText("Logout");
                approval.setText("Manage employees");
                transaction.setText("View transactions");
            }
        });

        //Redirect the user to the sell product page
        sell.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to sell products \nRedirection pour vendre des produits");
                new SellProduct(getUsername());
                dispose();
            }
        });

        buy.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to Buy Products \nRedirection pour Acheter des produits");
                new buyProduct1(getUsername());
                dispose();
            }
        });

        //Redirect the user to get information about a single product
        single.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to information about a product \nRedirection pour informations sur un produits");
                new singleProductInfo(getUsername());
                dispose();
            }
        });

        all.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to All Product Information \nRedirection pour Information sur tout les produits");
                new allProductInfo(getUsername());
                dispose();
            }
        });

        search.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to Search Products \nRedirection pour Chercher  des Produits");
                new searchProduct(getUsername());
                dispose();
            }
        });

        lowStock.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to Low Stock Products \nRedirection pour Les produits à recharger");
                new lowStockProducts(getUsername());
                dispose();
            }
        });

        approval.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to Manage Employees \nRedirection pour Gerer les Employés");
                new manageEmployees(getUsername());
                dispose();
            }
        });

        profile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to your profil \nRedirection pour votre profile");
                new profile(getUsername());
                dispose();
            }
        });

        transaction.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to view transactions, \nRedirection pour voir les transactions");
                new viewTransaction(getUsername());
                dispose();
            }
        });
    }
}
