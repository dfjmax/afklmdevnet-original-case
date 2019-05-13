package com.afkl.cases.df.controller;

import com.afkl.cases.df.model.Airport;
import com.afkl.cases.df.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * The airport controller
 *
 * @author dfjmax
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/api/airports", produces = APPLICATION_JSON_VALUE)
public class AirportController {

    private final AirportService airportService;

    @Autowired
    public AirportController(final AirportService airportService) {
        this.airportService = airportService;
    }

    @RequestMapping(method = GET)
    public CompletableFuture<PagedResources<Airport>> index(
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
            @RequestParam(value = "lang", required = false, defaultValue = "EN") String lang,
            @RequestParam(value = "term", required = false, defaultValue = "") String term) {
        return airportService.findBy(size, page, lang, term);
    }

}
