package com.afkl.cases.df.service.impl;

import com.afkl.cases.df.model.Airport;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.http.HttpMethod.GET;

@RunWith(MockitoJUnitRunner.class)
public class TravelApiAirportServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ResponseEntity responseEntity;

    private TravelApiAirportService service;

    @Before
    public void setUp() {
        service = new TravelApiAirportService(restTemplate, "http://localhost", "/airports");
    }

    @Test
    public void testFindBy_shouldCallRestTemplate() {
        when(restTemplate.exchange(any(URI.class), eq(GET), eq(null), any(ParameterizedTypeReference.class))).thenReturn(responseEntity);
        service.findBy(10, 1, "EN", "");
        verify(restTemplate, times(1)).exchange(any(URI.class), eq(GET), eq(null), any(ParameterizedTypeReference.class));
    }

    @Test
    public void testFindOneByCode_shouldCallRestTemplate() {
        when(restTemplate.exchange(any(URI.class), eq(GET), eq(null), eq(Airport.class))).thenReturn(responseEntity);
        service.findOneByCode("BCN", "EN");
        verify(restTemplate, times(1)).exchange(any(URI.class), eq(GET), eq(null), eq(Airport.class));
    }

}
