package com.medlink.cathbackend;

import com.medlink.cathbackend.entity.*;
import com.medlink.cathbackend.exception.VoitureException;
import com.medlink.cathbackend.service.Critere;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Créer une Agence
            Agence agence = new Agence("Agence de Location");

            // Créer quelques Voitures
            Voiture voiture1 = new Voiture(1, "Toyota", 50.0f);
            Voiture voiture2 = new Voiture(2, "BMW", 6000.0f);
            Voiture voiture3 = new Voiture(3, "Ford", 70.0f);

            // Ajouter des Voitures à l'Agence
            agence.ajoutVoiture(voiture1);
            agence.ajoutVoiture(voiture2);
            agence.ajoutVoiture(voiture3);

            // Supprimer une Voiture de l'Agence
            //agence.suppVoiture(voiture3);

            // Créer quelques Clients
            Client client1 = new Client(101, "Aziz", "Zina");
            Client client2 = new Client(102, "Bayrem", "Jlassi");

            // Louer des Voitures aux Clients
            agence.loueClientVoiture(client1, voiture1);
            agence.loueClientVoiture(client2, voiture2);
            agence.loueClientVoiture(client1, voiture3);

            // Print the state before returning cars
            System.out.println("État avant le retour des voitures:");
            agence.afficheLesClientsEtLeursListesVoitures();

            // Retourner des Voitures des Clients
            agence.retourClientVoiture(client1, voiture1);
            agence.retourClientVoiture(client2, voiture2);

            // Print the state before returning cars
            System.out.println("État apres le retour des voitures:");
            agence.afficheLesClientsEtLeursListesVoitures();

            // Sélectionner des Voitures selon un Critère
            Critere critere = v -> v.getPrixLocation() < 60.0f;
            List<Voiture> voituresSelectionnees = agence.selectVoitureSelonCritere(critere);
            System.out.println("Voitures sélectionnées selon le critère:");
            for (Voiture v : voituresSelectionnees) {
                System.out.println(v);
            }

            // Obtenir l'ensemble des Clients loueurs
            Set<Client> clientsLoueurs = agence.ensembleClientsLoueurs();
            System.out.println("Ensemble des clients loueurs:");
            for (Client client : clientsLoueurs) {
                System.out.println(client);
            }

            // Obtenir la collection des Voitures louées
            Collection<ListVoitures> voituresLouees = agence.collectionVoituresLouees();
            System.out.println("Collection des voitures louées:");
            for (ListVoitures listVoitures : voituresLouees) {
                listVoitures.affiche();
            }

            // Afficher tous les Clients et leurs Voitures louées
            agence.afficheLesClientsEtLeursListesVoitures();

            // Afficher les Clients triés par code
            System.out.println("Clients triés par code:");
            Map<Client, ListVoitures> clientsTriesParCode = agence.triCodeCroissant();
            clientsTriesParCode.forEach((client, listVoitures) -> {
                System.out.println(client + " :");
                listVoitures.affiche();
            });

            // Afficher les Clients triés par nom
            System.out.println("Clients triés par nom:");
            Map<Client, ListVoitures> clientsTriesParNom = agence.triNomCroissant();
            clientsTriesParNom.forEach((client, listVoitures) -> {
                System.out.println(client + " :");
                listVoitures.affiche();
            });

        } catch (VoitureException e) {
            e.printStackTrace();
        }
    }
}