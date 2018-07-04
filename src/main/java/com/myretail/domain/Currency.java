package com.myretail.domain;

/**
 * Currency enum containing all the currencies I saw during my year in Latin America
 *
 * Created by MVW on 7/4/2018.
 */
public enum Currency {

    ARS("ARS", "Argentine Peso"),

    BOB("BOB", "Bolivian Boliviano"),

    CLP("CLP", "Chilean Peso"),

    COP("COP", "Colombian Peso"),

    CRC("CRC", "Costa Rican Colon"),

    EUR("EUR", "Euro"),

    GBP("GBP", "British Pound"),

    GTQ("GTQ", "Guatemalan Quetzal"),

    MXN("MXN", "Mexican Peso"),

    NIO("NIO", "Nicaraguan Cordoba Oro"),

    PEN("PEN", "Peruvian Nuevo Sol"),

    USD("USD", "US Dollar");

    private String code;

    private String longName;

    Currency(String code, String longName) {
        this.code = code;
        this.longName = longName;
    }

    public String getCode() {
        return code;
    }

    public String getLongName() {
        return longName;
    }
}
