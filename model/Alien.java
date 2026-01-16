package model;

/**
 * Abstrakte Basisklasse für alle Aliens im Spiel.
 * Von dieser Klasse erben später die verschiedenen Alien-Typen.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */
public abstract class Alien {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

     /**
     * Versionsnummer für die Serialisierung.
     * Diese wird benötigt, damit das Speichern 
     * und Laden von Spielständen korrekt funktioniert.
     */
    private static final long serialVersionUID = 1729389822767173584L;
}