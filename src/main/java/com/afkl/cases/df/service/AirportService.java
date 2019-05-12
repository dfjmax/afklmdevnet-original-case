package com.afkl.cases.df.service;

import com.afkl.cases.df.model.Airport;
import org.springframework.hateoas.PagedResources;

import java.util.concurrent.CompletableFuture;

/**
 * Airport Service interface
 *
 * @author dfjmax
 */
public interface AirportService {

    /**
     * Find a list of airports using the specified term async
     *
     * @param size the page size
     * @param page the page number
     * @param lang the language
     * @param term the search term
     * @return a list of airports matching the criteria
     */
    CompletableFuture<PagedResources<Airport>> findBy(int size, int page, String lang, String term);

    /**
     * Finds one airport by code async
     *
     * @param code the code
     * @param lang the language
     * @return an airport if it exists, null if it doesn't
     */
    CompletableFuture<Airport> findOneByCode(String code, String lang);

}
