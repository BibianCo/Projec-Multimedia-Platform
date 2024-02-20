package co.edu.uptc.controller;

import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.Persistence;

public class PlanController {

    private Persistence<Plan> persistence;

    public boolean addPlan(Plan plan) {
        return persistence.persist(plan);
    }

}
