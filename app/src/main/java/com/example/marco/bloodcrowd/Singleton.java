package com.example.marco.bloodcrowd;

/**
 * Created by Marco on 03/01/2018.
 */

public class Singleton {
    private static final Singleton ourInstance = new Singleton();

    public static Singleton getInstance() {
        return ourInstance;
    }


    private BloodDonator bloodDonator;

    private Singleton() {
        this.bloodDonator = new BloodDonator();
    }

    public  BloodDonator getBloodDonator() {
        return bloodDonator;
    }

    public void setBloodDonator(BloodDonator bloodDonator){
        this.bloodDonator = bloodDonator;
    }
}
