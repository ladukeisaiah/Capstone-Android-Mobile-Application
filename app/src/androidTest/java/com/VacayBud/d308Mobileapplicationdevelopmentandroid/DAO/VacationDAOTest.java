package com.VacayBud.d308Mobileapplicationdevelopmentandroid.DAO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.room.Room;
import androidx.test.platform.app.InstrumentationRegistry;

import com.VacayBud.d308Mobileapplicationdevelopmentandroid.Database.VacationDatabaseBuilder;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities.Vacation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class VacationDAOTest {
    VacationDatabaseBuilder db;
    VacationDAO vacationDAO;

    @Before
    public void setUp() throws Exception {
        // Get the application context for the test
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        // Build an in-memory database for testing
        db = Room.inMemoryDatabaseBuilder(context, VacationDatabaseBuilder.class)
                .allowMainThreadQueries() // Allow queries on the main thread for testing purposes
                .build();

        // Initialize the DAO
        vacationDAO = db.vacationDAO();
    }

    @After
    public void tearDown() throws Exception {
        if (db != null) {
            db.close(); // Close the in-memory database
        }
    }

    @Test
    public void vacationInsert() {
        Vacation vacation = new Vacation(0, "Beach Holiday", 2000.00, "Beach Resort", "2024-07-01", "2024-07-15", "Summer Break");
        vacationDAO.insert(vacation);

        // Then
        List<Vacation> allVacations = vacationDAO.getAllVacations();
        assertFalse(allVacations.isEmpty());
        assertEquals("Beach Holiday", allVacations.get(0).getVacationName());
    }

    @Test
    public void vacationUpdate() {
        Vacation vacation = new Vacation(0, "Beach Holiday", 2000.00, "Beach Resort", "2024-07-01", "2024-07-15", "Summer Break");
        vacationDAO.insert(vacation);

        // When
        List<Vacation> insertedVacations = vacationDAO.getAllVacations();
        Vacation updatedVacation = insertedVacations.get(0);
        updatedVacation.setVacationName("Updated Beach Holiday");
        vacationDAO.update(updatedVacation);

        // Then
        Vacation retrievedVacation = vacationDAO.getAllVacations().get(0);
        assertEquals("Updated Beach Holiday", retrievedVacation.getVacationName());
    }

    @Test
    public void vacationDelete() {
        Vacation vacation = new Vacation(0, "Beach Holiday", 2000.00, "Beach Resort", "2024-07-01", "2024-07-15", "Summer Break");
        vacationDAO.insert(vacation);

        // When
        Vacation insertedVacation = vacationDAO.getAllVacations().get(0);
        vacationDAO.delete(insertedVacation);

        // Then
        List<Vacation> allVacations = vacationDAO.getAllVacations();
        assertTrue(allVacations.isEmpty());
    }
}