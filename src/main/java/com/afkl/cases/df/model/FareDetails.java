package com.afkl.cases.df.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class describing the fare details
 *
 * @author dfjmax
 */
@AllArgsConstructor
@Getter
public class FareDetails {

    private Fare fare;
    private Airport origin;
    private Airport destination;

}
