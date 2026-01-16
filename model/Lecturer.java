package model;
import java.io.Serializable;

/**
 * Klasse für einen Lecturer im Spiel.
 * Lecturer können im Spiel auftreten und
 * später eine wichtige Rolle übernehmen.
 *
 * @author Uthman Rasha
 * @author Souri Armita
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

    private String name;
    private boolean hasSigned = false;

    /**
     * Erstellt einen neuen Lecturer und setzt den Namen.
     * Der Lecturer hat am anfang noch nicht unterschrieben.
     * 
     * @param name Name des Übungsgruppenleiters
     */
    public Lecturer(String name) {
        this.name = name;
    }
    
    /**
     * Prüft, ob der Lecturer den Laufzettel unterschreiben kann.
     * Das ist der Fall, solange er zuvor noch nicht unterschrieben hat.
     * 
     * @return true, wenn der Lecturer noch unterschreiben darf
    */
   public boolean isReadyToSign() {
       return !hasSigned;
    }
    
    /**
     * Der Lecturer unterschreibt den Laufzettel.
     * Danach gilt er als erledigt.
    */
   public void sign() {
       this.hasSigned = true;
    }

    /**
    * Gibt zurück, ob der Lecturer schon unterschrieben hat.
    *
    * @return true, wenn bereits unterschrieben wurde
    */
   public boolean hasSigned() {
       return hasSigned;
    }

    /**
    * Gibt den Namen des Lecturers zurück.
    *
    * @return Name des Übungsgruppenleiters
    */
   public String getName() {
       return name;
    }
}