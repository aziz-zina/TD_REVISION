package com.medlink.cathbackend.entity;

import com.medlink.cathbackend.exception.VoitureException;
import com.medlink.cathbackend.service.Critere;

import java.util.*;

public class Agence {
    private final String nom;
    private final ListVoitures vs;
    private final Map<Client, ListVoitures> ClientVoitureLoue;

    public Agence(String nom) {
        this.nom = nom;
        vs = new ListVoitures();
        ClientVoitureLoue = new HashMap<Client, ListVoitures>();
    }

    public void ajoutVoiture(Voiture v) throws VoitureException {
        vs.ajoutVoiture(v);
    }

    public void suppVoiture(Voiture v) throws VoitureException {
        vs.supprimeVoiture(v);
    }

    public void loueClientVoiture(Client cl, Voiture v) throws VoitureException {
        if(!vs.getVoitures().contains(v)) {
            throw new VoitureException("Voiture inexistante");
        }
        suppVoiture(v);
        ListVoitures voituresLoue = ClientVoitureLoue.get(cl);
        if (voituresLoue == null) {
            voituresLoue = new ListVoitures();
            ClientVoitureLoue.put(cl, voituresLoue);
        }
    }

    public void retourClientVoiture(Client cl, Voiture v) throws VoitureException {
        ListVoitures voituresLoue = ClientVoitureLoue.get(cl);
        if (voituresLoue == null || !voituresLoue.getVoitures().contains(v)) {
            throw new VoitureException("Voiture non louée par ce client");
        }
        voituresLoue.supprimeVoiture(v);
        ajoutVoiture(v);
    }

    public List<Voiture> selectVoitureSelonCritere(Critere c) {
        List<Voiture> voitures = new ArrayList<>();
        for (Voiture v : vs.getVoitures()) {
            if (c.estSatisfaitPar(v)) {
                voitures.add(v);
            }
        }
        return voitures;
    }

    public Set<Client> ensembleClientsLoueurs() {
        return ClientVoitureLoue.keySet();
    }

    public Collection<ListVoitures> collectionVoituresLouees() {
        return ClientVoitureLoue.values();
    }

    public void afficheLesClientsEtLeursListesVoitures() {
        for (Map.Entry<Client, ListVoitures> entry : ClientVoitureLoue.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public Map<Client, ListVoitures> triCodeCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparing(Client::getCode));
        sortedMap.putAll(ClientVoitureLoue);
        return sortedMap;
    }

    public Map<Client, ListVoitures> triNomCroissant() {
        Map<Client, ListVoitures> sortedMap = new TreeMap<>(Comparator.comparing(Client::getNom));
        sortedMap.putAll(ClientVoitureLoue);
        return sortedMap;
    }
}