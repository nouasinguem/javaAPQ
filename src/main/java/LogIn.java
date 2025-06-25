import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LogIn extends JFrame {
    public LogIn(){
        super("Login");
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void design(){
        Container c = this.getContentPane();
        c.setBackground(Color.RED);
        c.setLayout(new GridLayout(6,1));
        JLabel welcome = new JLabel("Login");
        JLabel Username = new JLabel("Username");
        JLabel Password = new JLabel("Password");
        JTextField username = new JTextField(10);
        JPasswordField password = new JPasswordField(10);
        JButton signin = new JButton("Sign in");
        JButton signup = new JButton("Sign up");
        JButton exit = new JButton("Exit");

        signup.setPreferredSize(new Dimension(120, 30));
        signin.setPreferredSize(new Dimension(120, 30));
        signin.setBackground(Color.WHITE);
        signup.setBackground(Color.WHITE);

        //Setting up the french/english menu
        JMenuBar language = new JMenuBar();
        JMenuItem french = new JMenuItem("Français");
        JMenuItem english = new JMenuItem("English");
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JMenu menu = new JMenu("EN");
        menu.add(english);
        menu.add(french);
        language.add(menu);
        panel.add(language);
        add(panel);

        c.add(panel);


        //Setting the page title
        bitRepeated rep = new bitRepeated();
        rep.header(c, welcome);

        JPanel panel2 = new JPanel(new GridLayout(2,1));
        panel2.add(Username);
        panel2.add(username);
        panel2.setBackground(Color.GREEN);
        c.add(panel2);

        JPanel panel3 = new JPanel(new GridLayout(2,1));
        panel3.add(Password);
        panel3.add(password);
        c.add(panel3);
        panel3.setBackground(Color.GREEN);


        JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel4.add(signup);
        panel4.add(new JLabel(""));
        panel4.add(signin);
        c.add(panel4);
        panel4.setBackground(Color.RED);

        JPanel panel5 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel5.add(exit);
        exit.setBackground(Color.WHITE);
        c.add(panel5);
        panel5.setBackground(Color.RED);

        exit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                dispose();
            }
        });

        signup.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                new SignUp();
                dispose();
            }
        });

        signin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (username.getText().equals("") || password.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Please fill all the fields\nRemplisser tout les champs s'il vous plaît");
                } else{
                    //Queries to check username and passwords, admin or employee

                    String name = username.getText();
                    String pass = password.getText();
                    if (rep.authentification(name, pass)==1){//Check the login details
                        JOptionPane.showMessageDialog(null, "Login Successful\nConnection réussie");
                        //Check if employee or admin
                        if(rep.checkAdminRight(name)==1){
                            new AdminDashboard(name);
                            dispose();
                        }else{//Opening an employee dashboard if not an Admin
                            new employeeDashboard(name);
                            dispose();
                        }
                    } else if (rep.authentification(name, pass)==2) {
                        JOptionPane.showMessageDialog(null, "Waiting for admin approval\nEn Attente d'approbation de l'administration");
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed\nConnecxion échouée");
                    }
                }

            }
        });

        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                welcome.setText("Se connecter");
                Username.setText("Nom d'utilisateur");
                Password.setText("Mot de passe");
                exit.setText("Quitter");
                signin.setText("Connexion");
                signup.setText("S'incsrire");
            }
        });

        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                welcome.setText("Login");
                Username.setText("Username");
                Password.setText("Password");
                exit.setText("Exit");
                signin.setText("Sign in");
                signup.setText("Sign up");
            }
        });

    }
}

//the sql connection string is: mysql://root:Maxime11@127.0.0.1:3306/Maxime