package com.afkl.cases.df.model;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * Class describing an airport
 *
 * @author dfjmax
 */
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Airport {

    private String code;
    private String name;
    private String description;
    private Coordinates coordinates;

}
