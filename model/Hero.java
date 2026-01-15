package model;
import java.io.Serializable;
import java.util.Random;

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

    private String name;
    private int healthPoints = 50;
    private int experiencePoints = 0;
    private Lecturer[] signedExerciseLeaders = new Lecturer[5];

    /**
     * Erstellt einen Hero mit Startwerten.
     * @param name Name des Spielcharakters
     */
    public Hero(String name) {
        this.name = name;
    }

    /**
     * Reduziert die Lebenspunkte um den angegebenen Wert (amount).
     * @param amount Schaden
     */
    public void takeDamage(int amount) {
        this.healthPoints = this.healthPoints - amount;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Regeneriert Lebenspunkte durch die Verschnaufspause (klein: +3, groß: +10, max. 50).
     * @param longRest true = große Pause, false = kleine Pause
     */
    public void regenerate(boolean longRest) {
        if (longRest) {
            this.healthPoints = this.healthPoints + 10;
        } else {
            this.healthPoints = this.healthPoints + 3;
        }
        if (this.healthPoints > 50) {
            this.healthPoints = 50;
        }
    }

    /**
     * Fluchtversuch mit 42% Erfolgschance.
     * @return true wenn Flucht gelingt, sonst false
     */
    public boolean flee() {
        Random random = new Random();
        int r = random.nextInt(100);
        return r < 42;
    }

    /**
     * Greift ein feindliches Wesen an und berechnet den Schaden.
     * @return Schaden als int
     */
    public int attack() {
        Random random = new Random();
        int r = random.nextInt(100);

        if (r < 13) {
            return 0;
        }

        int damage = (int) (this.experiencePoints * 2.3 + 1);
         if (r >= 13 && r < 25) {
            damage = damage * 2;
        }

        return damage;
    }

     /**
     * Trägt lecturer in den Laufzettel ein, wobei jeder lecturer nur einmal unterschreiben darf
     * @param lecturer Übungsgruppenleiter
     */
    public void signExerciseLeader(Lecturer lecturer) {
        if (lecturer == null) {
            return;
        }

        // schon eingetragen?
        for (int i = 0; i < this.signedExerciseLeaders.length; i++) {
            if (this.signedExerciseLeaders[i] == lecturer) {
                return;
            }
        }

        // nächster freier Platz (null)
        for (int i = 0; i < signedExerciseLeaders.length; i++) {
            if (this.signedExerciseLeaders[i] == null) {
                this.signedExerciseLeaders[i] = lecturer;
                break;
            }
        }
    }
    
    /**
     * Gibt die aktuellen Erfahrungspunkte zurück.
     * @return experiencePoints
     */
    public int getExperiencePoints() {
        return this.experiencePoints;
    }

    /**
     * Erhöht die Erfahrungspunkte.
     * @param experiencePoints zu addierende EP
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints = this.experiencePoints + experiencePoints;
    }

    /**
     * Prüft, ob der Held noch handlungsfähig ist.
     * @return true wenn healthpoints > 0
     */
    public boolean isOperational() {
        return healthPoints > 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public Lecturer[] getSignedExerciseLeaders() {
        return this.signedExerciseLeaders;
    }

    public void setSignedExerciseLeaders(Lecturer[] signedExerciseLeaders) {
        this.signedExerciseLeaders = signedExerciseLeaders;
    }    
}
