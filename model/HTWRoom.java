/**
 * @author Uthman Rasha
 * @author Souri Armita
 */

package model;
import java.io.Serializable;

/**
 * Klasse für einen Raum in der HTW.
 * Ein Raum stellt einen Ort im Spiel dar,
 * den der Spieler betreten kann.
 *
 * @author Uthman Rasha
 */
public class HTWRoom implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

    /**
     * Versionsnummer für die Serialisierung.
     * Sie wird benötigt, damit Spielstände
     * korrekt gespeichert und geladen werden können.
     */
    private static final long serialVersionUID = 9065680017147292999L;
}
