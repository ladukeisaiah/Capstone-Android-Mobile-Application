package com.VacayBud.d308Mobileapplicationdevelopmentandroid.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.VacayBud.d308Mobileapplicationdevelopmentandroid.DAO.ExcursionDAO;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.DAO.VacationDAO;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities.Excursion;
import com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities.Vacation;

@Database(entities = {Vacation.class, Excursion.class}, version= 9,exportSchema = false)
public abstract class VacationDatabaseBuilder extends RoomDatabase {
    public abstract VacationDAO vacationDAO();
    public abstract ExcursionDAO excursionDAO();
    private static volatile VacationDatabaseBuilder INSTANCE;

    static VacationDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE==null){
            synchronized (VacationDatabaseBuilder.class){
                if (INSTANCE==null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),VacationDatabaseBuilder.class, "MyVacationDatabase.db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
