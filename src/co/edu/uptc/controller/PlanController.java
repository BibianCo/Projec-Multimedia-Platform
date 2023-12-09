package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.Random;

import co.edu.uptc.model.Plan;

public class PlanController {
    private Plan plan;
    private ArrayList<Plan> listPlans;

    public PlanController() {
        listPlans = new ArrayList<>();
        listPlans.add(new Plan("Basic plan", 10000, 1));
        listPlans.add(new Plan("Standard Plan ", 15000, 2));
        listPlans.add(new Plan("Premium Plan ", 35000, 4));
    }

    public String generateInvitationCode() {
        Random rd = new Random();
        String code = "";
        char[] randomString = new char[5];
        for (int i = 0; i < 5; i++) {
            randomString[i] = (char) (rd.nextInt(26) + 'a');

            code += randomString[i];
        }

        return code;
    }

    public Plan assignTypePlan(int option){
        plan = getListPlans().get(option-1);
        return plan;
    }

    public Plan assignUser(String userName){
        if(plan.getUserList().size() < plan.getNumberUsers()){
            plan.getUserList().add(userName);
        }
        return plan;
    }
    public ArrayList<Plan> getListPlans() {
        return listPlans;
    }

    public void setListPlans(ArrayList<Plan> listPlans) {
        this.listPlans = listPlans;
    }

}