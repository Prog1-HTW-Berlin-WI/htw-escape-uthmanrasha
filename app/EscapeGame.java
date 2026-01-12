package app;
/**
 * @author Uthman Rasha
 * @author Souri Armita
 */
/**
 * Zentrale Klasse des Spielablaufs.
 * Diese Klasse verwaltet das Spiel, den Helden, und den aktuellen Spielzustand.
 *
 * @author Uthman Rasha
 */

import model.HTWRoom;
import model.Hero;

public class EscapeGame {

     /** Der Held, den der Spieler steuert */
    private final Hero hero;

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
}
