package co.edu.uptc.model;

import java.util.ArrayList;

public class User extends Entity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Subscription subscription;
    private ArrayList<Multimedia> listPersonalized = new ArrayList<>();

    public User(int id, String firstName, String lastName, String email, String password, Role role,
            Subscription subscription) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.subscription = subscription;
    }

    public User(int id, String firstName, String lastName, String email, String password, Role role) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + super.getId() +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public ArrayList<Multimedia> getListPersonalized() {
        return listPersonalized;
    }

    public void setListPersonalized(ArrayList<Multimedia> listPersonalized) {
        this.listPersonalized = listPersonalized;
    }

}
