public class Worker extends User {

    private int isActive, isAdmin;

    public Worker() {
        super();
        //For the worker not initialized
    }
    public Worker(String userID, String phone, String firstName, String lastName, String password,
                  int isActive, int isAdmin) {// 0 for non (active or admin), 1 for (activ or admin)
        super(userID, phone, firstName, lastName, password);
        this.isActive = isActive;
        this.isAdmin = isAdmin;
    }

    // Getters
    public int getIsActive() {return this.isActive;}
    public int getIsAdmin() {return this.isAdmin;}

    //Setters
    public void setIsActive(int isActive) {this.isActive = isActive;} // For admin use to add a user as an employee
    public void setIsAdmin(int isAdmin) {this.isAdmin = isAdmin;} // For admin use to promote an employee

    @Override
    public String display(){// this function displays a worker details
        String result = "";
        result+= "Worker: " + this.getUserID();
        if (this.getIsActive()==1){
            result += " Active as";
            if (this.getIsAdmin()==1){
                result+=" Admin";
            }else {
                result+=" Employee";
            }
        } else {
            result+=" Inactive";
        }
        result+="\nUsername: " + this.getPhone();
        result+="\nFull Name: " + this.getFirstName() + " " + this.getLastName();
        result+="\n---------------------------\n";

        return result;
    }
}
