package com.medlink.cathbackend.entity;

import com.medlink.cathbackend.service.Critere;

public class CriterePrix implements Critere {
    private final float prix;

    public CriterePrix(float prix) {
        this.prix = prix;
    }

    public boolean estSatisfaitPar(Voiture v) {
        return v.getPrixLocation() <= prix;
    }
}
