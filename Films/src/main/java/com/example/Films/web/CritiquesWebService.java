package com.example.Films.web;

import com.example.Films.data.Critiques;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CritiquesWebService {
	Logger logger = LoggerFactory.getLogger(CritiquesWebService.class);
    List<Critiques> critiques = new ArrayList<>();

    public CritiquesWebService() {
    	critiques.add(new Critiques("Inception", "Maelle", "Super film!", 5));
    	critiques.add(new Critiques("Interstellar", "Nour", "Un chef-d'œuvre de science-fiction", 5));
    }


    @GetMapping("/critiques")
    public List<Critiques> getAllCritiques() {
        return critiques;
    }

    @GetMapping("/critiques/films/{titreFilm}")
    public List<Critiques> getCritiqueFilm(@PathVariable("titreFilm") String titreFilm) throws CritiquesNotFoundData{
    	List<Critiques> critiquesFilms = critiques.stream()
                .filter(critique -> critique.getTitreFilm().equalsIgnoreCase(titreFilm))
                .toList();

        if (critiquesFilms.isEmpty()) {
            throw new CritiquesNotFoundData(titreFilm);
        }

        return critiquesFilms;
    }
    
    @GetMapping("/critiques/auteur/{auteur}")
    public List<Critiques> getCritiqueAuteur(@PathVariable("auteur") String auteur) throws CritiquesNotFoundData{
    	List<Critiques> critiquesFilms = critiques.stream()
                .filter(critique -> critique.getAuteur().equalsIgnoreCase(auteur))
                .toList();

        if (critiquesFilms.isEmpty()) {
            throw new CritiquesNotFoundData("Aucune critique trouvée pour l'auteur : " + auteur);
        }

        return critiquesFilms;
    }

    @PostMapping("/critiques")
    public void addCritique(@RequestBody Critiques critique) {
    	critiques.add(critique);
        logger.info("Nouvelle critique ajoutée : " + critique.getTitreFilm() + " par " + critique.getAuteur());
    }

    @DeleteMapping("/critiques/{titreFilm}/{auteur}")
    public void deleteCritique(@PathVariable("titreFilm") String titreFilm, @PathVariable("auteur") String auteur) {
        boolean enleve = critiques.removeIf(critique -> 
        critique.getTitreFilm().equalsIgnoreCase(titreFilm) && critique.getAuteur().equalsIgnoreCase(auteur)
        );

        if (enleve) {
            logger.info("Critique de " + auteur + " supprimée pour le film : " + titreFilm);
        } else {
            logger.warn("Aucune critique trouvée pour " + titreFilm + " par " + auteur);
        }
    }

}
