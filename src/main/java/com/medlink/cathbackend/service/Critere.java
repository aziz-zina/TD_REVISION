package com.medlink.cathbackend.service;

import com.medlink.cathbackend.entity.Voiture;

public interface Critere {
    boolean estSatisfaitPar(Voiture v);
}
