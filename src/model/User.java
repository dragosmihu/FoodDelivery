package model;

public class User implements Comparable<User> {
    private String firstName;
    private String lastName;
    private int age;
    private int noOfOrders;
    private double spentMoney;

    public User(){
        spentMoney = 0;
    }

    public User(String firstName, String lastName, int age, int noOfOrders, double spentMoney) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.noOfOrders = noOfOrders;
        this.spentMoney = spentMoney;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNoOfOrders() {
        return noOfOrders;
    }

    public void setNoOfOrders(int noOfOrders) {
        this.noOfOrders = noOfOrders;
    }

    public double getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(double spentMoney) {
        this.spentMoney = spentMoney;
    }

    @Override
    public int compareTo(User o) {
        int firstNameResult = this.firstName.compareTo(o.firstName);
        if (firstNameResult == 0){
            int lastNameResult = this.lastName.compareTo(o.lastName);
            if( lastNameResult == 0){
                return this.age - o.age;
            }
            return lastNameResult;
        }
        return firstNameResult;
    }
}
