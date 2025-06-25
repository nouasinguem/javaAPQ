import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUp extends JFrame {

    public SignUp() {
        super("Sign Up");
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }
    private void design() {
        Container c = this.getContentPane();
        c.setBackground(Color.RED);
        c.setLayout(new GridLayout(4,1));

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
        c.add(panel);

        JLabel welcome = new JLabel("Sign Up");
        JLabel firstName = new JLabel("First Name");
        JLabel lastName = new JLabel("Last Name");
        JLabel password = new JLabel("Password");
        JLabel phone = new JLabel("Phone Number");
        JTextField FirstName = new JTextField(10);
        JTextField LastName = new JTextField(10);
        JPasswordField Password = new JPasswordField(10);
        JTextField phoneNumber = new JTextField(10);
        JButton signin = new JButton("Sign in");
        JButton signup = new JButton("Sign up");
        FirstName.setToolTipText("Enter First Name\nEntrer votre prénom");
        LastName.setToolTipText("Enter Last Name\nEntrez votre nom");
        Password.setToolTipText("Enter Password\nEntrez votre mot de passe");
        phoneNumber.setToolTipText("Enter Phone Number\nEnrez votre numéro de telephone");
        signup.setPreferredSize(new Dimension(80, 30));
        signin.setPreferredSize(new Dimension(80, 30));
        signin.setBackground(Color.WHITE);
        signup.setBackground(Color.WHITE);


        //Setting the page title
        bitRepeated rep = new bitRepeated();
        rep.header(c, welcome);

        JPanel panel2 = new JPanel(new GridLayout(4,2, 20, 10));
        panel2.add(firstName);
        panel2.add(FirstName);
        panel2.add(lastName);
        panel2.add(LastName);
        panel2.add(password);
        panel2.add(Password);
        panel2.add(phone);
        panel2.add(phoneNumber);
        panel2.setBackground(Color.GREEN);
        c.add(panel2);

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panel3.add(signup);
        panel3.add(signin);
        c.add(panel3);
        signin.setPreferredSize(new Dimension(120, 30));
        signup.setPreferredSize(new Dimension(120, 30));
        panel3.setBackground(Color.RED);


        signin.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                JOptionPane.showMessageDialog(null, "Redirecting to the login page\nRedirection pour la page de connexion");
                new LogIn();
                dispose();
            }
        });

        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                welcome.setText("S'inscrire");
                firstName.setText("Prénom");
                lastName.setText("Nom");
                password.setText("Mot de passe");
                phone.setText("Numéro de telephone");
                signin.setText("Se Connecter");
                signup.setText("S'inscrire");
            }
        });

        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                welcome.setText("Sign Up");
                firstName.setText("First Name");
                lastName.setText("Last Name");
                password.setText("Password");
                phone.setText("Phone Number");
                signin.setText("Sign in");
                signup.setText("Sign up");
            }
        });

        signup.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String a1= phoneNumber.getText();//Username
                String a2 = FirstName.getText();//first name
                String a3 = LastName.getText();// Last name
                String a4 = Password.getText(); // password
                String a5 = rep.createWorkerID(a1,a2,a3);// Employee ID

                Worker w = new Worker(a5,a1,a2,a3,a4, 0, 0);//Creating the instance of worker

                if (a1.isEmpty() || a2.isEmpty() || a3.isEmpty() || a4.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fill in all the textfields\nRemplissez tout les champ d'ecriture");
                } else {
                    if(rep.isValidLong(a1)){
                        if(!rep.isValidPhoneNumber(a1)){
                            JOptionPane.showMessageDialog(null, "Phone number Invalid (UK format 07XXXXXXXXX)\nNuméro de téléphone invalide (Format UK 07XXXXXXXXX)");
                            return;
                        }
                        if(rep.addWorker(w)==1){
                            JOptionPane.showMessageDialog(null, "Worker Added Successfully\nAjout du travailleur réussie");
                            JOptionPane.showMessageDialog(null, "Your username is your phone number " +a1 +
                                    "\nVotre nom d'utilisateur est votre numéro de téléphone " + a1);
                            new LogIn();
                            dispose();
                        } else{//Existing workers
                            JOptionPane.showMessageDialog(null, "User Already Exists\nUtilisateur déjà existant");
                        }
                    } else{
                        JOptionPane.showMessageDialog(null, "Incorrect Data Type\nMauvais type de données");
                    }
                }
            }
        });

    }
}
