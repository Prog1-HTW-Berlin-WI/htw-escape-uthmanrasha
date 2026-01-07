package model;
/**
 * @author Uthman Rasha
 * @author Souri Armita
 */
import java.io.Serializable;

/**
 * Klasse für einen Lecturer im Spiel.
 * Lecturer können im Spiel auftreten und
 * später eine wichtige Rolle übernehmen.
 *
 * @author Uthman Rasha
 */
public class Lecturer implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

    /**
     * Versionsnummer für die Serialisierung.
     * Sie stellt sicher, dass Spielstände
     * richtig gespeichert und geladen werden können.
     */
    private static final long serialVersionUID = 540082607047283589L;
}