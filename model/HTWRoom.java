package model;
import java.io.Serializable;

/**
 * Klasse für einen Raum in der HTW.
 * Ein Raum stellt einen Ort im Spiel dar,
 * den der Spieler betreten kann.
 *
 * @author Uthman Rasha
 * @author Souri Armita
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

    private String identifier;
    private String description;
    private Lecturer lecturer;
    
    /**
    * Erstellt einen neuen Raum mit Kennung und Beschreibung)
    * 
    * @param identifier Eindeutige Raumbezeichnung (z.B A 210)
    * @param description Beschreibung des Raums
    */
   public HTWRoom(String identifier, String description) {
          this.identifier = identifier;
          this.description = description;
          this.lecturer = null;
      }
   
    
    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Lecturer getLecturer() {
        return this.lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }
}   