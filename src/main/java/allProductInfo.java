import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.zip.CheckedOutputStream;

public class allProductInfo extends JFrame {
    private String username;

    public allProductInfo(String username) {
        super("All Product Information");
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
        c.setLayout(new GridLayout(7,1));
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
        JLabel Welcome = new JLabel("All Product Information");
        JButton back = new JButton("Back");

        //Setting the page title
        rep.header(c, Welcome);


        //Container to display information
        JTextArea information = new JTextArea();
        information.setEditable(false);
        information.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, new Color(0, 0, 0, 100)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        JScrollPane scroll = new JScrollPane(information);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        c.add(scroll);
        scroll.setPreferredSize(new Dimension(200, 500));
        scroll.setBackground(Color.RED);

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton all = new JButton("All");
        JButton Juice = new JButton("Juice");
        JButton Energitic = new JButton("Energetic");
        JButton Wine = new JButton("Wine");
        JButton Spirit = new JButton("Spirit");
        JButton Beer = new JButton("Beer");
        JButton print = new JButton("Print");

        all.setPreferredSize(new Dimension(130,20));
        Juice.setPreferredSize(new Dimension(130,20));
        Energitic.setPreferredSize(new Dimension(130,20));
        Wine.setPreferredSize(new Dimension(130,20));
        Spirit.setPreferredSize(new Dimension(130,20));
        Beer.setPreferredSize(new Dimension(130,20));
        JLabel file = new JLabel("File name");
        JTextField fileField = new JTextField(10);

        panel1.add(all);
        panel1.add(Juice);
        panel1.add(Energitic);
        panel1.add(Beer);
        panel1.add(Wine);
        panel1.add(Spirit);

        c.add(panel1);
        panel1.setBackground(Color.GREEN);

        JPanel panel2 = new JPanel(new GridLayout(1,2));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 5));
        JLabel totalSelling = new JLabel("Total Selling");
        JTextArea selling = new JTextArea();
        p1.add(totalSelling);
        p1.add(selling);
        JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        JLabel totalCost = new JLabel("Total Cost");
        JTextArea cost = new JTextArea();
        p2.add(totalCost);
        p2.add(cost);
        panel2.add(p2);
        panel2.add(p1);

        cost.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 50)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        cost.setPreferredSize(new Dimension(65,20));
        cost.setBackground(Color.LIGHT_GRAY);
        cost.setEditable(false);
        selling.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 2, 2, new Color(0, 0, 0, 50)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        selling.setPreferredSize(new Dimension(65,20));
        selling.setBackground(Color.LIGHT_GRAY);
        selling.setEditable(false);
        p2.setBackground(Color.GREEN);
        p1.setBackground(Color.GREEN);
        panel2.setBackground(Color.RED);
        c.add(panel2);
        JPanel panel21 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel21.add(file);
        panel21.add(fileField);
        c.add(panel21);
        panel21.setBackground(Color.GREEN);
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel3.add(print);
        panel3.add(back);
        back.setPreferredSize(new Dimension(100, 30));
        print.setPreferredSize(new Dimension(100, 30));
        panel3.setBackground(Color.RED);
        c.add(panel3);

        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                Welcome.setText("Informations sur les produits");
                all.setText("Tout");
                Juice.setText("Jus");
                Beer.setText("Bière");
                Wine.setText("Vin");
                Spirit.setText("Whisky");
                Energitic.setText("Energetique");
                totalSelling.setText("Prix de vente");
                totalCost.setText("Prix d'achat");
                back.setText("Retour");
                print.setText("Imprimer");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                Welcome.setText("All Product Information");
                all.setText("All");
                Juice.setText("Juice");
                Beer.setText("Beer");
                Wine.setText("Wine");
                Spirit.setText("Spirit");
                Energitic.setText("Energetic");
                totalSelling.setText("Total Selling");
                totalCost.setText("Total Cost");
                back.setText("Back");
                print.setText("Print");
            }
        });

        //Going back to the dashboard onclick "Back"
        back.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                rep.dashoardDirection(getUsername());
                dispose();
            }
        });
        all.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String display = "";
                display += "\tJuice\n\n" + rep.displayAllJuice("All") + "\n\tEnergetic\n\n"+ rep.displayAllEDrink("All")+
                        "\n\tBeer\n\n"+ rep.displayAllBeer("All");
                display += "\n\tWine\n\n" + rep.displayAllWine("All");
                display += "\n\tSpirit\n\n"+rep.displayAllSpirit("All");
                information.setText(display);
                List<Double> result= rep.prices("All");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });

        Juice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display all juice
                information.setText("\tJuice\n\n"+rep.displayAllJuice("All"));
                List<Double> result= rep.prices("Juice");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });

        Energitic.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                information.setText("\tEnergetic\n\n"+rep.displayAllEDrink("All"));
                List<Double> result= rep.prices("Energetic");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });

        Beer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display all beer
                information.setText("\tBeer\n\n"+rep.displayAllBeer("All"));
                List<Double> result= rep.prices("Beer");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });
        Wine.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Display all wine
                information.setText("\tWine\n\n"+rep.displayAllWine("All"));
                List<Double> result= rep.prices("Wine");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });

        Spirit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                information.setText("\tSpirit\n\n"+rep.displayAllSpirit("All"));
                List<Double> result= rep.prices("Spirit");
                cost.setText(result.get(0).toString()+" €");
                selling.setText(result.get(1).toString()+" €");
            }
        });

        print.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(information.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Select a filter\nSelectionnez un filtre");
                    return;
                }
                String fileName = fileField.getText();
                File myObj = new File(fileName);
                try {
                    if (myObj.createNewFile()) {
                        JOptionPane.showMessageDialog(null, "File created\nFichier créé");
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "File already exists\nFichier existe déjà");
                        return;
                    }
                } catch (IOException e1) {
                    JOptionPane.showMessageDialog(null, "An error occurred.");
                    e1.printStackTrace();
                    return;
                }

                    String r = information.getText();
                    r+="\nTotal Cost: "+cost.getText()+"\n";
                    r+="Total Sale: "+selling.getText()+"\n";
                    r+="=======================================";

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
            }
        });
    }
}
