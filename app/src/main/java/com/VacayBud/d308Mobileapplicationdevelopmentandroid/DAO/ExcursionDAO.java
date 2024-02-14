package com.VacayBud.d308Mobileapplicationdevelopmentandroid.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities.Excursion;

import java.util.List;
//comments to test pushing
@Dao
public interface ExcursionDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Excursion excursion);

    @Update
    void update(Excursion excursion);

    @Delete
    void delete(Excursion excursion);

    @Query("SELECT * FROM EXCURSIONS ORDER BY excursionID ASC")
    List<Excursion> getAllExcursions();

    @Query("SELECT * FROM EXCURSIONS WHERE vacationID=:prod ORDER BY excursionID ASC")
    List<Excursion> getAssociatedExcursions(int prod);
}
