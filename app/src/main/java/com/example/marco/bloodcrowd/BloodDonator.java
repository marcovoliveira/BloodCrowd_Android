package com.example.marco.bloodcrowd;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * Created by Marco on 03/01/2018.
 */

public class BloodDonator implements Serializable {

    private ArrayList<Donator> donators;
    private ArrayList<Donator> donatorsPesquisados;

    public BloodDonator() {

        this.donators = new ArrayList<>();
        this.donatorsPesquisados = new ArrayList<>();
    }
    public void addDonator(Donator donator) {

        donators.add(donator);
    }
    public void addDonatorPesquisado(Donator donator) {

        donatorsPesquisados.add(donator);
    }


    public ArrayList<Donator> getDonators() {

        return donators;
    }

    public ArrayList<Donator> getDonatorsPesquisados() {

        return donatorsPesquisados;

    }



    public void clear(){

        donatorsPesquisados.clear();
    }
}
