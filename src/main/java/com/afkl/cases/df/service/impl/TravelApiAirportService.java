package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.Airport;
import com.afkl.cases.df.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.springframework.http.HttpMethod.GET;

/**
 * Airport service implementation using the Travel Api Mock
 *
 * @author dfjmax
 */
@Service
public class TravelApiAirportService implements AirportService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    @Autowired
    public TravelApiAirportService(RestTemplate restTemplate,
                                   @Value("${travel-api.baseUrl}") String baseUrl,
                                   @Value("${travel-api.endpoints.airports}") String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpoint = baseUrl + endpointUrl;
    }

    @Async
    @Override
    public CompletableFuture<PagedResources<Airport>> findBy(int size, int page, String lang, String term) {
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(endpoint)
                .queryParam("size", size)
                .queryParam("page", page)
                .queryParam("lang", lang)
                .queryParam("term", term).build();
        ResponseEntity<PagedResources<Airport>> responseEntity = restTemplate.exchange(uriComponents.toUri(), GET,
                null, new ParameterizedTypeReference<PagedResources<Airport>>() {
                });
        return completedFuture(responseEntity.getBody());
    }

    @Async
    @Override
    public CompletableFuture<Airport> findOneByCode(String code, String lang) {
        String url = String.format("%s/%s", endpoint, code);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("lang", lang).build();
        ResponseEntity<Airport> responseEntity = restTemplate.exchange(uriComponents.toUri(), GET,
                null, Airport.class);
        return completedFuture(responseEntity.getBody());
    }

}
