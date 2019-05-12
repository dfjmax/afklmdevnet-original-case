package com.afkl.cases.df.controller;

import com.afkl.cases.df.model.Airport;
import com.afkl.cases.df.model.Fare;
import com.afkl.cases.df.model.FareDetails;
import com.afkl.cases.df.service.AirportService;
import com.afkl.cases.df.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * The airport controller
 *
 * @author dfjmax
 */
@RestController
@RequestMapping(path = "/api/fares", produces = APPLICATION_JSON_VALUE)
public class FareController {

    private final FareService fareService;
    private final AirportService airportService;

    @Autowired
    public FareController(final FareService fareService, final AirportService airportService) {
        this.fareService = fareService;
        this.airportService = airportService;
    }

    @RequestMapping(path = "/{from}/{to}", method = GET)
    public FareDetails retrieve(@PathVariable("from") String from, @PathVariable("to") String to,
                                @RequestParam(value = "lang", required = false, defaultValue = "EN") String lang,
                                @RequestParam(value = "currency", required = false, defaultValue = "EUR") String currency)
            throws ExecutionException, InterruptedException {
        CompletableFuture<Fare> asyncFare = fareService.findOneBy(from, to, currency);
        CompletableFuture<Airport> asyncAirportFrom = airportService.findOneByCode(from, lang);
        CompletableFuture<Airport> asyncAirportTo = airportService.findOneByCode(to, lang);
        CompletableFuture<Void> asyncCalls = CompletableFuture.allOf(asyncFare, asyncAirportFrom, asyncAirportTo);

        asyncCalls.get();

        return new FareDetails(asyncFare.get(), asyncAirportFrom.get(), asyncAirportTo.get());
    }

}
