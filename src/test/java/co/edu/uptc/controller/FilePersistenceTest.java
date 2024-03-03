package co.edu.uptc.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Type;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.reflect.TypeToken;

import co.edu.uptc.model.Plan;
import co.edu.uptc.persistence.FilePersistence;

public class FilePersistenceTest {

    private FilePersistence<Plan> fileManagement;
    private Type type;

    @Before
    public void setUp() {
        type = new TypeToken<ArrayList<Plan>>() {
        }.getType();
        fileManagement = new FilePersistence<Plan>(type, "plans");
        fileManagement.createFile();
        Plan plan1 = new Plan(1, "Plan 1", "Description 1", 100, 30);
        Plan plan2 = new Plan(2, "Plan 2", "Description 2", 200, 45);
        fileManagement.persist(plan1);
        fileManagement.persist(plan2);
    }

    @Test
    public void testObtainAll() {
        assertEquals(1, fileManagement.obtainAll().get(0).getId());
        assertEquals("Plan 2", fileManagement.obtainAll().get(1).getNamePlan());
    }

    @Test
    public void PrintWriterTest() {
        assertEquals(true, fileManagement.persist(new Plan(23, "gold", "asdsd", 45, 30)));
        assertEquals(false, fileManagement.persist(null));
    }

    @Test
    public void eraseTest() {
        type = new TypeToken<ArrayList<Plan>>() {
        }.getType();
        FilePersistence<Plan> filePersistence = new FilePersistence<>(type, "test_file");
        filePersistence.createFile();

        Plan plan1 = new Plan(1, "Plan 1", "Description 1", 100, 30);
        Plan plan2 = new Plan(2, "Plan 2", "Description 2", 200, 45);
        Plan plan3 = new Plan(3, "Plan 3", "Description 3", 150, 60);

        filePersistence.persist(plan1);
        filePersistence.persist(plan2);
        filePersistence.persist(plan3);

        assertNotNull(filePersistence.obtainById(1));
        assertNotNull(filePersistence.obtainById(2));
        assertNotNull(filePersistence.obtainById(3));

        assertTrue(filePersistence.erase(2));

        assertNull(filePersistence.obtainById(2));

        assertNotNull(filePersistence.obtainById(1));
        assertNotNull(filePersistence.obtainById(3));

        filePersistence.deleteFile();
    }

    @Test
    public void testUpdate() {
        setUp();
        Plan plan = new Plan(1, "lll", "asd", 45, 12);
        assertEquals(true, fileManagement.persist(0, plan));
    }

    @After
    public void tearDown() {
        fileManagement.deleteFile();
    }
}
