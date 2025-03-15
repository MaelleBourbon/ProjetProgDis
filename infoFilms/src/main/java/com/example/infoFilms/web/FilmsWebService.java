package com.example.infoFilms.web;

import com.example.infoFilms.data.Films;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FilmsWebService {
	Logger logger = LoggerFactory.getLogger(FilmsWebService.class);
    List<Films> films = new ArrayList<>();
    
    public FilmsWebService() {
    	films.add(new Films("Inception", "Christopher Nolan", "Sci-Fi", 148, "Un voleur est spécialisé dans l'art du vol en pénétrant dans les rêves des gens."));
        films.add(new Films("The Matrix", "Lana Wachowski", "Action", 136, "Un hacker découvre la vérité sur la réalité dans laquelle il vit."));
    }
    
    @GetMapping("/films")
    public List<Films> getAllFilms() {
        return films;
    }
    
    @GetMapping("/films/{titre}")
    public Films getInfoFilm(@PathVariable String titre) throws FilmsNotFoundException {
    	Films film = null;
        for (Films f : films) {
            if (f.getTitreFilm().equalsIgnoreCase(titre)) {
                film = f;
                break;
            }
        }
        if (film == null) {
            throw new FilmsNotFoundException(titre);
        }
        return film;
    }
    
    @PostMapping("/films/ajout/{titre}/{realisateur}")
    public ResponseEntity<String> addFilms(@RequestBody Films film, @PathVariable String titre, @PathVariable String realisateur) {
        logger.info("Tentative d'ajout du film : {} réalisé par {}", titre, realisateur);
        boolean filmExist = films.stream()
                                  .anyMatch(f -> f.getTitreFilm().equalsIgnoreCase(titre) && f.getRealisateur().equalsIgnoreCase(realisateur));
        if (filmExist) {
            logger.warn("Le film '{}' réalisé par '{}' existe déjà.", titre, realisateur);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Le film '" + titre + "' réalisé par '" + realisateur + "' existe déjà.");
        }        
        films.add(film);
        logger.info("Film ajouté avec succès : {}", film);
        return ResponseEntity.status(HttpStatus.CREATED)
                             .body("Le film '" + titre + "' réalisé par '" + realisateur + "' a été ajouté.");
    }

    
    @DeleteMapping("/films/suppression/{titre}/{realisateur}")
    public ResponseEntity<String> deleteFilms(@PathVariable("titre") String titre, @PathVariable("realisateur") String realisateur) {
        boolean enleve = films.removeIf(film -> 
            film.getTitreFilm().equalsIgnoreCase(titre) && film.getRealisateur().equalsIgnoreCase(realisateur));
        if (enleve) {
            logger.info("Le film '{}' réalisé par '{}' a été supprimé.", titre, realisateur);
            return ResponseEntity.status(HttpStatus.OK)
                                 .body("Le film '" + titre + "' réalisé par '" + realisateur + "' a été supprimé.");
        } else {
            logger.warn("Aucun film trouvé avec le titre '{}' et le réalisateur '{}' pour suppression.", titre, realisateur);
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Aucun film trouvé pour '" + titre + "' réalisé par '" + realisateur + "'.");
        }
    }

}
