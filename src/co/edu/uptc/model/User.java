package co.edu.uptc.model;

public class User extends Person {

    private UserCredentials userCredentials;

    public User(String firstName, String lastName, short age, String email, String password) {
        super(firstName, lastName, age);
        this.userCredentials = new UserCredentials(email, password);
    }

}
