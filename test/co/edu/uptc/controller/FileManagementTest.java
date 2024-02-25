package co.edu.uptc.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.FileManagement;

public class FileManagementTest {

    private FileManagement<Plan> fileManagement;
    private Type type;

    @Before
    public void setUp() {
        type = new TypeToken<ArrayList<Plan>>() {
        }.getType();
        fileManagement = new FileManagement<>(type, "plans");

    }

    @Test
    public void obtainAll() {
        assertEquals(1, fileManagement.obtainAll().get(0).getId());
        assertEquals("Carla", fileManagement.obtainAll().get(1).getNamePlan());
        assertEquals(4, fileManagement.obtainAll().get(3).getId());
    }

    @Test
    public void PrintWriterTest() {
        assertEquals(true, fileManagement.persist(new Plan(23, "gold", "asdsd", 45, 30)));
        assertEquals(false, fileManagement.persist(null));
    }

}
