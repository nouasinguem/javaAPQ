import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class employeeDashboard extends JFrame {
        private String username;

    public employeeDashboard(String username) {
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
        c.setLayout(new GridLayout(6,1));
        c.setBackground(Color.RED);
        bitRepeated rep = new bitRepeated();

        // Defining element on our page
        JButton buy = new JButton("Buy a product");
        JButton sell = new JButton("Sell a product");
        JButton single = new JButton("Single product information");
        JButton all = new JButton("All products information");
        JButton search = new JButton("Search product");
        JButton lowStock = new JButton("Low stock products");
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
        JLabel l1 = new JLabel("Welcome");
        rep.header(c, l1);

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

        // Logging out
        rep.Dashboardfooter(c, logout);

        //Here we want to disable the Low Stock product button if there's no low stock product
        if (!rep.checkLowStock()){
            lowStock.setEnabled(false);
        }else {
            lowStock.setBackground(Color.RED);
        }


        logout.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "See you later !");
                new LogIn();
                dispose();
            }
        });

        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                profile.setText("Profil");
                l1.setText("Bienvenue");
                buy.setText("Acheter un produit");
                sell.setText("Vendre un produit");
                single.setText("Informations sur un produit");
                all.setText("Informations sur tout produits");
                search.setText("Chercher un produit");
                lowStock.setText("Produits en faible stock");
                logout.setText("Deconnexion");
            }
        });

        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, "+ rep.firstName(getUsername()));
                profile.setText("Profile");
                l1.setText("Welcome");
                buy.setText("Buy a product");
                sell.setText("Sell a product");
                single.setText("Single product information");
                all.setText("All products information");
                search.setText("Search product");
                lowStock.setText("Low stock products");
                logout.setText("Logout");
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
        //Redirecting the user to buy product page
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
                JOptionPane.showMessageDialog(null, "Redirecting to information about a product \nRedirection pour Informations sur un produit");
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

        profile.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to your profil \nRedirection pour votre profile");
                new profile(getUsername());
                dispose();
            }
        });
    }
}
