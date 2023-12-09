package co.edu.uptc.model;

import java.util.ArrayList;

public class Plan {
    private String description;
    private int value;
    private int numberUsers;
    private ArrayList<String> userList;
    private String invitationCode;

    public Plan() {
    }

    public Plan(String description, int value, int numberUsers) {
        this.description = description;
        this.value = value;
        this.numberUsers = numberUsers;
        userList = new ArrayList<>();

    }

    public Plan(String description, int value, int numberUsers, String invitationCode) {
        this.description = description;
        this.value = value;
        this.numberUsers = numberUsers;
        this.invitationCode = invitationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getNumberUsers() {
        return numberUsers;
    }

    public void setNumberUsers(int numberUsers) {
        this.numberUsers = numberUsers;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public ArrayList<String> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<String> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "Plan [description=" + description + ", value=" + value + "]";
    }

}
