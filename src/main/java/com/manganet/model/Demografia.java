package com.manganet.model;

public enum Demografia {
	
	SEINEN("Seinen"),
    SHOJO("Shōjo"),
    SHONEN("Shōnen"),
    JOSEI("Josei"),
    KODOMO("Kodomo");

    private final String description;

    Demografia(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
