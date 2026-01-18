package model;
import java.io.Serializable;
import java.util.Random;

/**
 * Klasse für den Helden des Spiels.
 * Der Held wird vom Spieler gesteuert und kann gespeichert
 * und später wieder geladen werden.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */
public class Hero implements Serializable {

    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

    private static final int MAX_HP = 50;

    /**
     * Versionsnummer für die Serialisierung.
     * Sie sorgt dafür, dass Spielstände korrekt
     * gespeichert und geladen werden können.
     */
    private static final long serialVersionUID = 3578735620108186013L;

    private String name;
    private int healthPoints = MAX_HP;
    private int experiencePoints = 0;
    private Lecturer[] signedExerciseLeaders = new Lecturer[5];

    /**
     * Erstellt einen Hero mit Startwerten.
     *
     * @param name Name des Spielcharakters
     */
    public Hero(String name) {
        this.name = name;
    }

    /**
     * Reduziert die Lebenspunkte um den angegebenen Wert.
     * Die Lebenspunkte fallen nicht unter 0.
     *
     * @param amount Schaden
     */
    public void takeDamage(int amount) {
        this.healthPoints -= amount;
        if (this.healthPoints < 0) {
            this.healthPoints = 0;
        }
    }

    /**
     * Regeneriert Lebenspunkte durch eine Verschnaufpause.
     * Kleine Pause: +3 HP, große Pause: +10 HP.
     * Lebenspunkte überschreiten MAX_HP nicht.
     *
     * @param longRest true = große Pause, false = kleine Pause
     */
    public void regenerate(boolean longRest) {
        if (longRest) {
            this.healthPoints += 10;
        } else {
            this.healthPoints += 3;
        }

        if (this.healthPoints > MAX_HP) {
            this.healthPoints = MAX_HP;
        }
    }

    /**
     * Versucht aus einer Begegnung zu fliehen.
     * Erfolgswahrscheinlichkeit: 42%.
     *
     * @return true bei erfolgreicher Flucht
     */
    public boolean flee() {
        Random random = new Random();
        return random.nextInt(100) < 42;
    }

    /**
     * Greift ein feindliches Wesen an.
     * 13% Fehlschlag (0 Schaden),
     * 12% kritischer Treffer (doppelter Schaden).
     *
     * @return berechneter Schaden
     */
    public int attack() {
        Random random = new Random();
        int r = random.nextInt(100);

        if (r < 13) {
            return 0;
        }

        int damage = (int) (this.experiencePoints * 2.3 + 1);

        if (r < 25) {
            damage *= 2;
        }

        return damage;
    }

    /**
     * Trägt einen Übungsgruppenleiter in den Laufzettel ein.
     * Jeder Lecturer darf nur einmal unterschreiben.
     *
     * @param lecturer Übungsgruppenleiter
     */
    public void signExerciseLeader(Lecturer lecturer) {
        if (lecturer == null) {
            return;
        }

        for (Lecturer l : signedExerciseLeaders) {
            if (l == lecturer) {
                return;
            }
        }

        for (int i = 0; i < signedExerciseLeaders.length; i++) {
            if (signedExerciseLeaders[i] == null) {
                signedExerciseLeaders[i] = lecturer;
                break;
            }
        }
    }

    /**
     * Gibt die aktuellen Erfahrungspunkte zurück.
     *
     * @return Erfahrungspunkte
     */
    public int getExperiencePoints() {
        return experiencePoints;
    }

    /**
     * Erhöht die Erfahrungspunkte.
     *
     * @param experiencePoints zu addierende EP
     */
    public void addExperiencePoints(int experiencePoints) {
        this.experiencePoints += experiencePoints;
    }

    /**
     * Prüft, ob der Held noch handlungsfähig ist.
     *
     * @return true, wenn HP > 0
     */
    public boolean isOperational() {
        return healthPoints > 0;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    /**
     * Setzt die Lebenspunkte unter Beachtung der Grenzen.
     *
     * @param healthPoints neue Lebenspunkte
     */
    public void setHealthPoints(int healthPoints) {
        if (healthPoints < 0) {
            this.healthPoints = 0;
        } else if (healthPoints > MAX_HP) {
            this.healthPoints = MAX_HP;
        } else {
            this.healthPoints = healthPoints;
        }
    }

    public Lecturer[] getSignedExerciseLeaders() {
        return signedExerciseLeaders;
    }
}