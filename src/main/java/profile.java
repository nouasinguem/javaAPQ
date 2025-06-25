import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class profile extends JFrame {
    private String username;

    public String getUsername() { return this.username; }

    public profile(String username) {
        super("My Profile");
        this.username = username;
        design();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
    }

    public void design() {
        Container c = this.getContentPane();
        c.setBackground(Color.RED);
        c.setLayout(new BorderLayout(20,5));


        //Getting the informations of the user who is logged in
        bitRepeated rep = new bitRepeated();
        Worker worker = rep.workerInfo(getUsername());
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

        top.add(p, BorderLayout.EAST);
        top.add(panel, BorderLayout.WEST);
        c.add(top, BorderLayout.NORTH);

        //Defining the elements of the page
        JButton apply = new JButton("Apply");
        JButton back = new JButton("Back");
        JLabel username = new JLabel("Username");
        JLabel firstName = new JLabel("First Name");
        JLabel lastName = new JLabel("Last Name");
        JLabel password = new JLabel("Password");
        JLabel employee = new JLabel("Employee ID");
        JLabel active = new JLabel("Status");
        JLabel admin = new JLabel("Admin Status");
        JTextArea usernameField = new JTextArea(getUsername());
        JTextField firstNameField = new JTextField(worker.getFirstName());
        JTextField lastNameField = new JTextField(worker.getLastName());
        JTextField employeeIDField = new JTextField(worker.getUserID());
        JPasswordField passwordField = new JPasswordField(worker.getPassword());
        JButton status = new JButton("Active");
        JButton adminStatus = new JButton();
        JButton viewPassword = new JButton("View your Password");



        //Setting the page title
        JLabel title = new JLabel("My Profile");
        rep.header(c, title);

        JPanel panel2 = new JPanel(new GridLayout(7,2, 20, 0));
        JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));

        panel2.add(username);
        panel2.add(usernameField);
        panel2.add(firstName);
        panel2.add(firstNameField);
        panel2.add(lastName);
        panel2.add(lastNameField);
        panel2.add(password);
        p1.add(passwordField);
        p1.add(viewPassword);
        panel2.add(p1);
        panel2.add(employee);
        panel2.add(employeeIDField);
        panel2.add(active);
        panel2.add(status);
        panel2.add(admin);
        panel2.add(adminStatus);


        usernameField.setEditable(false);//Can only be modified in the database by an admin
        employeeIDField.setEditable(false);//Can be modified only in the database
        panel2.setBackground(Color.LIGHT_GRAY);
        p1.setBackground(Color.LIGHT_GRAY);
        status.setBackground(Color.GREEN);
        passwordField.setPreferredSize(new Dimension(200, 20));
        viewPassword.setPreferredSize(new Dimension(200, 20));

        //Definition of the admin status button
        if(rep.checkAdminRight(getUsername())==1){
            adminStatus.setBackground(Color.GREEN);
            adminStatus.setText("Active");
        }else{
            adminStatus.setBackground(Color.RED);
            adminStatus.setText("Inactive");
        }

        c.add(panel2);


        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        panel3.add(back);
        panel3.add(apply);
        back.setPreferredSize(new Dimension(100, 30));
        apply.setPreferredSize(new Dimension(100, 30));
        panel3.setBackground(Color.RED);
        c.add(panel3, BorderLayout.SOUTH);

        // Changes the text in French onclick
        french.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("FR");
                loggedUser.setText("Connecté(e) comme, " + rep.firstName(getUsername()));
                username.setText("Nom d'utilisateur");
                firstName.setText("Prénom");
                lastName.setText("Nom");
                password.setText("Mot de passe");
                employee.setText("Numéro de l'employé (e)");
                status.setText("Actif");
                if(rep.checkAdminRight(getUsername())==1){
                    adminStatus.setBackground(Color.GREEN);
                    adminStatus.setText("Actif");
                }else {
                    adminStatus.setBackground(Color.RED);
                    adminStatus.setText("Inactif");
                }
                if(viewPassword.getText().equals("View your Password")){
                    viewPassword.setText("Voir votre Mot de Passe");
                }
                if(viewPassword.getText().equals("Hide your Password")){
                    viewPassword.setText("Cacher votre Mot de Passe");
                }
                apply.setText("Appliquer");
                back.setText("Retour");
            }
        });

        // Changes the text in English onclick
        english.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                menu.setText("EN");
                loggedUser.setText("Logged in as, " + rep.firstName(getUsername()));
                username.setText("Username");
                firstName.setText("First Name");
                lastName.setText("Last Name");
                password.setText("Password");
                employee.setText("Employee ID");
                status.setText("Active");
                if(rep.checkAdminRight(getUsername())==1){
                    adminStatus.setBackground(Color.GREEN);
                    adminStatus.setText("Active");
                }else {
                    adminStatus.setBackground(Color.RED);
                    adminStatus.setText("Inactive");
                }
                if(viewPassword.getText().equals("Voir votre Mot de Passe")){
                    viewPassword.setText("View your Password");
                }
                if(viewPassword.getText().equals("Cacher votre Mot de Passe")){
                    viewPassword.setText("Hide your Password");
                }
                apply.setText("Apply");
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

        viewPassword.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //Show the characters of the password
                if (passwordField.getEchoChar() == 0) {
                    passwordField.setEchoChar('*');
                    if(menu.getText().equals("FR")){
                        viewPassword.setText("Voir votre Mot de Passe");
                    }else{
                        viewPassword.setText("View your Password");
                    }
                }else {
                    passwordField.setEchoChar((char) 0);
                    if(menu.getText().equals("EN")){
                        viewPassword.setText("Hide your Password");
                    } else{
                        viewPassword.setText("Cacher votre Mot de Passe");
                    }
                }
            }
        });

        apply.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(rep.isValidStringTextfields(new ArrayList<>(List.of(firstNameField, lastNameField, passwordField)))){
                    //After checking the fields are not empty updating the info
                    JOptionPane.showMessageDialog(null,"Updating your details...\nMise à jour de vos details");
                    rep.updateInfo(usernameField.getText(), firstNameField.getText(), lastNameField.getText(), passwordField.getText());
                    //Redirecting on the dashboard
                    rep.dashoardDirection(getUsername());
                } else{
                    JOptionPane.showMessageDialog(null,"All fields need to be filled\nTout les champs doivent être remplis");
                }

            }
        });

    }





    public static void main(String[] args) {
        new profile("25");
    }
}
