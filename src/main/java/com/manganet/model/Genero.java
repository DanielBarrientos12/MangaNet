package com.manganet.model;

public enum Genero {
	
	ACCION("Acción"),
    AVENTURA("Aventura"),
    COMEDIA("Comedia"),
    DRAMA("Drama"),
    RECIENTOS_DE_LA_VIDA("Recuentos de la Vida"),
    ECCHI("Ecchi"),
    FANTASIA("Fantasía"),
    MAGIA("Magia"),
    SOBRENATURAL("Sobrenatural"),
    HORROR("Horror"),
    MISTERIO("Misterio"),
    PSICOLOGICO("Psicológico"),
    ROMANCE("Romance"),
    CIENCIA_FICCION("Ciencia Ficción"),
    THRILLER("Thriller"),
    DEPORTE("Deporte"),
    HAREM("Harem"),
    MECHA("Mecha"),
    SUPERVIVENCIA("Supervivencia"),
    REENCARNACION("Reencarnación"),
    GORE("Gore"),
    APOCALIPTICO("Apocalíptico"),
    TRAGEDIA("Tragedia"),
    VIDA_ESCOLAR("Vida Escolar"),
    HISTORIA("Historia"),
    MILITAR("Militar"),
    POLICIACO("Policiaco"),
    CRIMEN("Crimen"),
    SUPERPODERES("Superpoderes"),
    VAMPIROS("Vampiros"),
    ARTES_MARCIALES("Artes Marciales"),
    SAMURAI("Samurái"),
    GENERO_BENDER("Género Bender"),
    REALIDAD_VIRTUAL("Realidad Virtual"),
    CIBERPUNK("Ciberpunk"),
    MUSICA("Música"),
    ANIMACION("Animación"),
    DEMONIOS("Demonios"),
    FAMILIA("Familia"),
    REALIDAD("Realidad"),
    TELENOVELA("Telenovela"),
    GUERRA("Guerra");

    private final String description;

    Genero(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return description;
    }

}
