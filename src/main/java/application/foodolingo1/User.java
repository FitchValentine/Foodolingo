package application.foodolingo1;

public class User {
    private String firstName;
    private String userName;
    private String password;
    private String weight;
    private String height;
    private String age;
    private String gender;

    public User(String firstName, String userName, String password, String weight, String height, String age, String gender) {
        this.firstName = firstName;
        this.userName = userName;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.age = age;
        this.gender = gender;
    }

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
