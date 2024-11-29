package com.medlink.cathbackend.entity;

import com.medlink.cathbackend.service.Critere;

public class CritereMarque implements Critere {

    private final String marque;

    public CritereMarque(String marque) {
        this.marque = marque;
    }

    public boolean estSatisfaitPar(Voiture v) {
        return v.getMarque().equals(marque);
    }
}
