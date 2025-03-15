package com.example.infoFilms.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmsNotFoundException extends Exception {
	public FilmsNotFoundException(String titre) {
        super("Aucune information est trouvé pour ce film : " + titre);
    }
}
