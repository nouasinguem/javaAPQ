import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class lowStockProducts extends JFrame {
    private String username;

    public String getUsername() {return username;}

    public lowStockProducts(String username) {
        super("Low Stock Products");
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
        c.setBackground(Color.RED);
        c.setLayout(new GridLayout(5,1));
        Container c1 = this.getContentPane();
        c1.setBackground(Color.BLUE);
        c1.setLayout(new CardLayout(5,5));
        c1.add(c);
        bitRepeated rep = new bitRepeated();

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
        JLabel Welcome = new JLabel("Low Stock Products");
        JButton back = new JButton("Back");

        //Setting the page title
        rep.header(c, Welcome);


        //Container to display information
        JTextArea information = new JTextArea();
        information.setEditable(false);
        information.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JScrollPane scroll = new JScrollPane(information);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(scroll);
        scroll.setBackground(Color.RED);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton all = new JButton("All");
        JButton Juice = new JButton("Juice");
        JButton Energitic = new JButton("Energitic");
        JButton Wine = new JButton("Wine");
        JButton Spirit = new JButton("Spirit");
        JButton Beer = new JButton("Beer");

        all.setPreferredSize(new Dimension(130,20));
        Juice.setPreferredSize(new Dimension(130,20));
        Energitic.setPreferredSize(new Dimension(130,20));
        Wine.setPreferredSize(new Dimension(130,20));
        Spirit.setPreferredSize(new Dimension(130,20));
        Beer.setPreferredSize(new Dimension(130,20));

        panel1.add(all);
        panel1.add(Juice);
        panel1.add(Energitic);
        panel1.add(Beer);
        panel1.add(Wine);
        panel1.add(Spirit);

        c.add(panel1);
        panel1.setBackground(Color.GREEN);



        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel3.add(back);
        back.setPreferredSize(new Dimension(100, 30));
        panel3.setBackground(Color.RED);
        c.add(panel3);

        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                Welcome.setText("Produits à recharger");
                all.setText("Tout");
                Juice.setText("Jus");
                Beer.setText("Bière");
                Wine.setText("Vin");
                Spirit.setText("Whisky");
                Energitic.setText("Energetique");
                back.setText("Retour");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                Welcome.setText("Low Stock Products");
                all.setText("All");
                Juice.setText("Juice");
                Beer.setText("Beer");
                Wine.setText("Wine");
                Spirit.setText("Spirit");
                Energitic.setText("Energetic");
                back.setText("Back");
            }
        });

        //Going back to the dashboard onclick "Back"
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rep.dashoardDirection(getUsername());
            }
        });

        all.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String display = "";
                display += "\tJuice\n\n" + rep.displayAllJuice("Low") + "\n\tEnergetic\n\n"+ rep.displayAllEDrink("Low")+
                        "\n\tBeer\n\n"+ rep.displayAllBeer("Low");
                display += "\n\tWine\n\n" + rep.displayAllWine("Low");
                display += "\n\tSpirit\n\n"+rep.displayAllSpirit("Low");
                information.setText(display);

            }
        });

        Juice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display all juice
                information.setText("\tJuice\n\n"+rep.displayAllJuice("Low"));

            }
        });

        Energitic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display low in stock EDrinks
                information.setText("\tEnergetic\n\n"+rep.displayAllEDrink("Low"));
            }
        });

        Beer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display low in stock beer
                information.setText("\tBeer\n\n"+rep.displayAllBeer("Low"));

            }
        });
        Wine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display low in stock wine
                information.setText("\tWine\n\n"+rep.displayAllWine("Low"));
            }
        });

        Spirit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display low in stock spirits
                information.setText("\tSpirit\n\n"+rep.displayAllSpirit("Low"));
            }
        });

    }

    public static void main(String[] args) {
        new lowStockProducts("25");
    }

}
