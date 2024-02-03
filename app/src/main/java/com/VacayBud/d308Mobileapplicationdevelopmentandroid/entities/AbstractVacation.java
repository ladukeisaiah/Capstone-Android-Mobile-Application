package com.VacayBud.d308Mobileapplicationdevelopmentandroid.entities;

import androidx.room.PrimaryKey;


public abstract class AbstractVacation {

    @PrimaryKey(autoGenerate = true)
    protected int vacationID;
    protected String vacationName;
    protected double price;
    protected String startVacationDate;
    protected String endVacationDate;

    public abstract String vacationTimeline();

    public abstract double calculateTotalCost();
}
