package kz.nurbekov.restaurant.Model;

public class User {
    private String Name;
    private String Password, Phone, isStaff;

    public User() {
    }

    public User(String name, String password) {
        Name = name;
        Password = password;
        isStaff = "false";
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(String isStaff) {
        this.isStaff = isStaff;
    }
}
