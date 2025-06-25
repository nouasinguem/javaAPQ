import java.util.Date;

public abstract class User {
    protected String phone, firstName, lastName, password, userID;

    public User (){
        //For all the users no initialized
    }
    public User(String userID, String phone, String firstName, String lastName, String password) {
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.userID = userID;
    }

    // Writing the getters

    public String getUserID() {return this.userID;}
    public String getPhone() {return this.phone;}
    public String getFirstName() {return this.firstName;}
    public String getLastName() {return this.lastName;}
    public String getPassword() {return this.password;}

    // Writing setters

    public void setUserID(String userID) {this.userID = userID;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setFirstName(String firstName) {this.firstName = firstName;}
    public void setLastName(String lastName) {this.lastName = lastName;}
    public void setPassword(String password) {this.password = password;}

    public abstract String display();



}
