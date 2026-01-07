package model;

import java.io.Serializable;

/**
 * Klasse für den Helden des Spiels.
 * Der Held wird vom Spieler gesteuert und kann gespeichert
 * und später wieder geladen werden.
 *
 * @author Uthman Rasha
 */
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

    /**
     * Versionsnummer für die Serialisierung.
     * Sie sorgt dafür, dass Spielstände korrekt
     * gespeichert und geladen werden können.
     */
    private static final long serialVersionUID = 3578735620108186013L;
}