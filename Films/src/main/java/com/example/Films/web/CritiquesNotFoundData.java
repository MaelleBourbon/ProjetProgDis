package com.example.Films.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CritiquesNotFoundData extends Exception {

    public CritiquesNotFoundData(String titre) {
        super("Aucune critique trouvée pour le film : " + titre);
    }
}