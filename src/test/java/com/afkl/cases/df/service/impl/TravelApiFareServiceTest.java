package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.Fare;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.GET;

@RunWith(MockitoJUnitRunner.class)
public class TravelApiFareServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity<Fare> responseEntity;

    private TravelApiFareService service;

    @Before
    public void setUp() {
        service = new TravelApiFareService(restTemplate, "http://localhost", "/fares");
    }

    @Test
    public void testFindOneBy_shouldCallRestTemplate() {
        when(restTemplate.exchange(any(URI.class), eq(GET), eq(null), eq(Fare.class))).thenReturn(responseEntity);
        service.findOneBy("BCN", "LAS", "EUR");
        verify(restTemplate, times(1)).exchange(any(URI.class), eq(GET), eq(null), eq(Fare.class));
    }
}
