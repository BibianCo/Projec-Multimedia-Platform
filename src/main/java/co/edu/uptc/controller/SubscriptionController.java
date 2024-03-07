package co.edu.uptc.controller;

import java.time.LocalDate;
import java.util.ArrayList;

import co.edu.uptc.model.Subscription;
import co.edu.uptc.persistence.Persistence;

public class SubscriptionController {

    private Persistence<Subscription> persistence;
    private PlanController planController;

    public SubscriptionController() {
    }

    public SubscriptionController(Persistence<Subscription> persistence, PlanController planController) {
        this.persistence = persistence;
        this.planController = planController;
    }

    public boolean add(Subscription subscription) {
        if (subscription.getPlan() != null && !planController.planExists(subscription.getPlan())
                && setStartDate(subscription) != null
                && setEndDate(subscription) != null) {
            return persistence.persist(subscription);
        } else {
            return false;
        }

    }

    public boolean delete(int id) {
        return persistence.erase(id);
    }

    public Subscription get(int id) {
        return persistence.obtainById(id);
    }

    public ArrayList<Subscription> getAll() {
        return persistence.obtainAll();
    }

    public boolean update(int id, Subscription newSubscription) {
        Subscription currentSubscription = get(id);
    
        if (currentSubscription != null) {
            int index = 0;
            for (Subscription subscription : getAll()) {
                if (subscription.getId() == id) {
                    return this.persistence.persist(index, newSubscription);
                }
                index++;
            }
        } else {
            return false;
        }
        return false;
    }
    

    public LocalDate setStartDate(Subscription subscription) {
        subscription.setDateStart(LocalDate.now());
        if (subscription.getDateStart() != null) {
            return subscription.getDateStart();
        } else {
            return null;
        }

    }

    public LocalDate setEndDate(Subscription subscription) {
        LocalDate findEndDate;
        if (subscription != null && subscription.getDateStart() != null) {
            findEndDate = subscription.getDateStart().plusDays(subscription.getPlan().getDuration());
            subscription.setDateEnd(findEndDate);
            return findEndDate;
        } else {
            return null;
        }
    }

    public boolean expireSubscription(Subscription subscription) {

        Subscription validateExpire = get(subscription.getId());
        System.out.println(validateExpire.toString());
        LocalDate DayNow = LocalDate.now();
        if (validateExpire != null && subscription.getDateEnd() != null && subscription.getDateEnd().isBefore(DayNow)) {
            return true;
        } else {
            return false;
        }

    }

    public int simulatePayment(String target, int quantity, Subscription subscription) {

        if ((target.length() == 10 || !target.matches("[0-9]+")) &&
                (subscription != null && get(subscription.getId()).getPlan().getPrice() <= quantity) && quantity > 0) {
            int result = quantity - get(subscription.getId()).getPlan().getPrice();
            if (result > 0) {
                return result;
            }
        }
        return -1;
    }
}
