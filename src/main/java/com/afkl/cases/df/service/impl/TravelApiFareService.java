package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.Fare;
import com.afkl.cases.df.service.FareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.springframework.http.HttpMethod.GET;

/**
 * Fare service implementation using the Travel Api Mock
 *
 * @author dfjmax
 */
@Service
public class TravelApiFareService implements FareService {

    private final RestTemplate restTemplate;
    private final String endpoint;

    @Autowired
    public TravelApiFareService(RestTemplate restTemplate,
                                @Value("${travel-api.baseUrl}") String baseUrl,
                                @Value("${travel-api.endpoints.fares}") String endpointUrl) {
        this.restTemplate = restTemplate;
        this.endpoint = baseUrl + endpointUrl;
    }

    @Override
    public CompletableFuture<Fare> findOneBy(String from, String to, String currency) {
        String url = String.format("%s/%s/%s", endpoint, from, to);
        UriComponents uriComponents = UriComponentsBuilder.fromHttpUrl(url).queryParam("currency", currency).build();
        ResponseEntity<Fare> responseEntity = restTemplate.exchange(uriComponents.toUri(), GET,
                null, Fare.class);
        return completedFuture(responseEntity.getBody());
    }
}
