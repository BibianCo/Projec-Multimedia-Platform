package co.edu.uptc.test;

import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import co.edu.uptc.controller.PlanController;
import co.edu.uptc.model.User;

public class PlanTest {
 
        @Test
        void testgenerateInvitationCode() {

            PlanController pc = new PlanController();
            User u = new User("jhon", "correo", "si", "nombreusua", null);

            System.out.println("1. basic\n 2.standart \n3.premium");
            int option = 2;
            u.setPlan(pc.assignTypePlan(option));
            u.getPlan().setInvitationCode(pc.generateInvitationCode());
            //u.getPlan().setUserList(u.);
        }

}

