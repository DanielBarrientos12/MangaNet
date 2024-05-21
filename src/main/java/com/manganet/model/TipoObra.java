package com.manganet.model;

public enum TipoObra {
	
	MANGA("Manga"),
    MANHUA("Manhua"), //comic chino
    MANHWA("Manhwa"); //comic coreano

    private final String description;

    TipoObra(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
