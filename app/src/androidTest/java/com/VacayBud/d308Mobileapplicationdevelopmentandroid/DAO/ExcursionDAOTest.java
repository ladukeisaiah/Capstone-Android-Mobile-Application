package com.VacayBud.d308Mobileapplicationdevelopmentandroid.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.VacayBud.d308Mobileapplicationdevelopmentandroid.Database.Repository;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.Database.VacationDatabaseBuilder;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities.Excursion;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;


public class ExcursionDAOTest {
    Repository repository;
    ExcursionDAO excursionDAO;
    private VacationDatabaseBuilder db;

    @Before
    public void setUp() throws Exception {
        // Get the application context for the test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Build an in-memory database for testing
        db = Room.inMemoryDatabaseBuilder(context, VacationDatabaseBuilder.class)
                .allowMainThreadQueries() // Allow queries on the main thread for testing purposes
                .build();

        // Initialize the DAO
        excursionDAO = db.excursionDAO();
    }

    @Test
    public void excursionInsert() {
        Excursion excursion = new Excursion(0, "City Tour", 150.00, 1, "2024-02-02");
        excursionDAO.insert(excursion);

        // Then
        List<Excursion> allExcursions = excursionDAO.getAllExcursions();
        assertFalse(allExcursions.isEmpty());
        assertTrue(allExcursions.get(0).getExcursionName().equals(excursion.getExcursionName()));
    }
    @Test
    public void excursionUpdate() {Excursion excursion = new Excursion(0, "City Tour", 150.00, 1, "2024-02-02");
        excursionDAO.insert(excursion);

        // When
        List<Excursion> insertedExcursions = excursionDAO.getAllExcursions();
        Excursion updatedExcursion = insertedExcursions.get(0);
        updatedExcursion.setExcursionName("Updated City Tour");
        excursionDAO.update(updatedExcursion);

        // Then
        Excursion retrievedExcursion = excursionDAO.getAllExcursions().get(0);
        assertEquals("Updated City Tour", retrievedExcursion.getExcursionName());
    }

    @Test
    public void excursionDelete() {
        Excursion excursion = new Excursion(0, "City Tour", 150.00, 1, "2024-02-02");
        excursionDAO.insert(excursion);

        // When
        Excursion insertedExcursion = excursionDAO.getAllExcursions().get(0);
        excursionDAO.delete(insertedExcursion);

        // Then
        List<Excursion> allExcursions = excursionDAO.getAllExcursions();
        assertTrue(allExcursions.isEmpty());
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close(); // Close the in-memory database
        }
    }
}