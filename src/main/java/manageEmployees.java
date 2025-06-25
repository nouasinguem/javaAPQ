import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class manageEmployees extends JFrame {

    private String username;

    public String getUsername() {return this.username;}

    public manageEmployees(String username) {
        super("Manage Employees");
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
        c.setLayout(new GridLayout(6,1));
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

        JLabel Welcome = new JLabel("Manage Employees");
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
        information.setFont(new Font("Verdana", Font.ITALIC, 16));

        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton all = new JButton("All");
        JButton admin = new JButton("Admin");
        JButton active = new JButton("Active");
        JButton inactive = new JButton("Inactive");

        all.setPreferredSize(new Dimension(130,20));
        admin.setPreferredSize(new Dimension(130,20));
        active.setPreferredSize(new Dimension(130,20));
        inactive.setPreferredSize(new Dimension(130,20));

        panel1.add(admin);
        panel1.add(active);
        panel1.add(inactive);
        panel1.add(all);

        c.add(panel1);
        panel1.setBackground(Color.GREEN);

        JPanel panel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 45, 0));
        JLabel total = new JLabel("Total");
        JTextArea totalArea = new JTextArea();

        totalArea.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        totalArea.setPreferredSize(new Dimension(65,20));
        totalArea.setBackground(Color.LIGHT_GRAY);
        totalArea.setEditable(false);

        panel2.add(total);
        panel2.add(totalArea);
        panel2.setBackground(Color.GREEN);

        c.add(panel2);

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
                Welcome.setText("Manager les Employés");
                all.setText("Tout");
                active.setText("Actif");
                inactive.setText("Inactif");
                back.setText("Retour");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                Welcome.setText("Manage Employees");
                all.setText("All");
                active.setText("Active");
                inactive.setText("Inactive");
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
        active.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               String r = rep.displayWorker("Active");
               information.setText(r);
               totalArea.setText(Integer.toString(rep.countWorkers("Active")) + " Active");
            }
        });
        inactive.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String r = rep.displayWorker("Inactive");
                information.setText(r);
                totalArea.setText(Integer.toString(rep.countWorkers("Inactive")) + " Inactive");
            }
        });
        all.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String r = rep.displayWorker("All");
                information.setText(r);
                totalArea.setText(Integer.toString(rep.countWorkers("All")) + " Staff");
            }
        });
        admin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String r = rep.displayWorker("Admin");
                information.setText(r);
                totalArea.setText(Integer.toString(rep.countWorkers("Admin")) + " Admin");
            }
        });

    }

    public static void main(String[] args) {
        new manageEmployees("Max");
    }
}
