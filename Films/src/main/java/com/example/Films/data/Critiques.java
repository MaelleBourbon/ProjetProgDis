package com.example.Films.data;

public class Critiques {
    private String titre;
    private String auteur;
    private String commentaire;
    private int note;
    
    public Critiques() {}
    
    public Critiques(String titre, String auteur, String commentaire, int note) {
        this.titre = titre;
        this.auteur = auteur;
        this.commentaire = commentaire;
        this.note = note;
    }

    // Getters et Setters
    public String getTitreFilm() {
        return titre;
    }

    public void setTitreFilm(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
