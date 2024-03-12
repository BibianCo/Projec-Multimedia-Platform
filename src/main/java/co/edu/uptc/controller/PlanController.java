package co.edu.uptc.controller;

import java.util.ArrayList;
import java.util.List;

import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.Persistence;

public class PlanController {
    private Persistence<Plan> persistence;

    public PlanController() {
    }

    public PlanController(Persistence<Plan> persistence) {
        this.persistence = persistence;
    }

    public boolean planExists(Plan plan) {
        if (get(plan.getId()) == null) {
            return true;
        } else {
            return false;
        }

    }

    public boolean add(Plan plan) {
        if (plan.getId() > 0 && !plan.getNamePlan().isEmpty() && plan.getDuration() > 0
                && !plan.getDescription().isEmpty() && plan.getPrice() > 1000 && planExists(plan)) {
            return persistence.persist(plan);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Plan get(int id) {
        return persistence.obtainById(id);
    }

    public ArrayList<Plan> getAll() {
        return persistence.obtainAll();
    }

    public boolean update(int id, Plan newPlan) {
        Plan currentPlan = get(id);

        if (currentPlan != null) {
            int index = 0;
            for (Plan plan : getAll()) {
                if (plan.getId() == id) {
                    return this.persistence.persist(index, newPlan);
                }
                index++;
            }
        }
        return false;
    }

}
