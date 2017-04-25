package by.simonov.model;

import lombok.Data;

@Data
public class Suggestion {
    private long positionId;
    private String primaryName;
    private String secondaryName;
    private String countryCode;
    private boolean coreCountry;
    private String fullName;
    private String country;
    private double latitude;
    private double longitude;
    private double locationId;
    private boolean inEurope;
    private String type;
}
