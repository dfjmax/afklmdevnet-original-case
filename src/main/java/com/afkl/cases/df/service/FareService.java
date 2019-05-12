package com.afkl.cases.df.service;

import com.afkl.cases.df.model.Fare;

import java.util.concurrent.CompletableFuture;

/**
 * Fare Service interface
 *
 * @author dfjmax
 */
public interface FareService {

    /**
     * Returns a fare for a specified airport from/to combination async
     *
     * @param from     the airport from
     * @param to       the airport to
     * @param currency the specified currency
     * @return a fare if exists or null if it doesn't
     */
    CompletableFuture<Fare> findOneBy(String from, String to, String currency);

}
