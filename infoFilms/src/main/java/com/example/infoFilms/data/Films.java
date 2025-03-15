package com.example.infoFilms.data;

public class Films {
	String titre;
	String realisateur;
	String genre;
	int duree;
	String synopsis;
	
	public Films() {}
	
	public Films(String titre, String realisateur, String genre, int duree, String synopsis) {
		this.titre = titre;
		this.realisateur = realisateur;
		this.genre = genre;
		this.duree = duree;
		this.synopsis = synopsis;
	}
	
	// Getters et Setters
	public String getTitreFilm() {
		return titre;
	}
	
	public void setTitreFilm(String titre) {
		this.titre = titre;
	}
	
	public String getRealisateur() {
		return realisateur;
	}
	
	public void setRealisateur(String realisateur) {
		this.realisateur = realisateur;
	}
	
	public String getGenreFilm() {
		return genre;
	}
	
	public void setGenreFilm(String genre) {
		this.genre = genre;
	}
	
	public int getDuree() {
		return duree;
	}
	
	public void setDuree(int duree) {
		this.duree = duree;
	}
	
	public String getSynopsis() {
		return synopsis;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
}
