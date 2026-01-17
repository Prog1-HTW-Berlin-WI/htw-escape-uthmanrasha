package app;
/**
 * Zentrale Klasse des Spielablaufs.
 * Diese Klasse verwaltet das Spiel, den Helden, und den aktuellen Spielzustand.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */

import model.HTWRoom;
import model.Hero;
import model.Lecturer;

public class EscapeGame {

     /** Der Held, den der Spieler steuert */
    private final Hero hero;
    private int currentRound = 1;
    private final int Max_ROUNDS = 24;
    private Lecturer lecturer = new Lecturer("Übungsleiter Müller");

     /** Räume, die im Spiel vorhanden sind */
    private final HTWRoom[] rooms = new HTWRoom[3];

    /** Gibt an, ob das Spiel aktuell läuft */
    private boolean gameRunning = true;

     /** Gibt an, ob das Spiel beendet wurde */
    private boolean gameFinished = false;

    /**
     * Erstellt ein neues Spiel und legt einen neuen Helden an.
     */
    private final Hero hero;
    private final HTWRoom[] rooms = new HTWRoom[3];
    private boolean gameRunning = true;
    private boolean gameFinished = false;

    public EscapeGame() {
        this.hero = new Hero();
    }

    /**
     * Prüft, ob das Spiel gerade läuft.
     *
     * @return true, wenn das Spiel läuft
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Setzt, ob das Spiel läuft oder pausiert ist.
     *
     * @param gameRunning neuer Status des Spiels
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

     /**
     * Prüft, ob das Spiel beendet ist.
     *
     * @return true, wenn das Spiel beendet ist
     */
    public boolean isGameFinished() {
        return gameFinished;
    }

     /**
     * Setzt den Status, ob das Spiel beendet ist.
     *
     * @param gameFinished neuer Status des Spiels
     */
    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    /**
     * Startet den eigentlichen Spielablauf.
     * Hier läuft später die Spiellogik ab.
     */
    public void run() {
        System.out.println("The game has started. Or not?");
    }

     /**
     * Gibt den aktuellen Helden zurück.
     *
     * @return der Held des Spiels
     */
    public Hero getHero() {
        return hero;
    }
    public void exploreHTW() {

    if (currentRound > MAX_ROUNDS) {
        System.out.println("Zeit ist vorbei. Spiel verloren.");
        return;
    }

    int zufall = (int)(Math.random() * 100);

    if (zufall < 20) {
        System.out.println("Du erkundest die HTW. Nichts passiert.");

    } else if (zufall < 72) {
        System.out.println("Ein Alien erscheint!");
        // später Kampf

    } } else {
    System.out.println("Du triffst einen Übungsleiter: " + lecturer.getName());

    if (lecturer.isReadyToSign()) {
        hero.signExerciseLeader(lecturer);
        lecturer.sign();
        System.out.println("Der Laufzettel wurde unterschrieben!");
    } else {
        System.out.println("Der Übungsleiter hat bereits unterschrieben.");
        // später Unterschrift
    }
}
    }

    currentRound++;
    System.out.println("Runde: " + currentRound + " von 24");
}
}
