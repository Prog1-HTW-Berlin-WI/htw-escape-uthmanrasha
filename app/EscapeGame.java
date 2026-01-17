package app;

import java.util.Scanner;

import model.HTWRoom;
import model.Hero;
import model.Lecturer;

/**
 * Zentrale Klasse des Spielablaufs.
 * Diese Klasse verwaltet das Spiel, den Helden, und den aktuellen Spielzustand.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */

<<<<<<< HEAD
=======
import model.HTWRoom;
import model.Hero;
import model.Lecturer;

>>>>>>> 2730194cb53fb289f08c34da6a948c5e1a6d8c03
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
    public EscapeGame(String heroName) {
        this.hero = new Hero(heroName);
    }
    
    /**
     * Startet den eigentlichen Spielablauf.
     * Hier läuft später die Spiellogik ab.
     */
    public void run() {
        while (gameRunning && hero.isOperational()) {
            showGameMenu();
            String input = readUserInput();
            handleGameMenuInput(input);
        }
    }

     /**
     * Zeigt das Spielmenü auf der Konsole an.
     */
    private void showGameMenu() {
        System.out.println("\n------------------------------------");
        System.out.println("SPIELMENÜ");
        System.out.println("1 - Hochschule erkunden");
        System.out.println("2 - Hero Status anzeigen");
        System.out.println("3 - Laufzettel anzeigen");
        System.out.println("4 - Verschnaufpause machen");
        System.out.println("5 - Spiel verlassen");
        System.out.println("------------------------------------");
        System.out.print("Deine Wahl: ");
    }

    /**
     * Liest eine Eingabe des Benutzers ein.
     *
     * @return Eingabe als String
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Verarbeitet die Auswahl aus dem Spielmenü.
     *
     * @param input Auswahl (1-5)
     */
    private void handleGameMenuInput(String input) {
        switch (input) {
            case "1":
                exploreUniversity();
                break;

            case "2":
                showHeroStatus();
                break;

            case "3":
                showLaufzettel();
                break;

            case "4":
                takeBreak();
                break;

            case "5":
                gameRunning = false;
                break;

            default:
                System.out.println("Ungültige Eingabe. Bitte 1 bis 5 wählen.");
        }
    }

    private void exploreUniversity() {
        System.out.println("Du erkundest die Hochschule...");
        // später: Räume / Begegnungen
    }

    private void showHeroStatus() {
        System.out.println("\n--- Hero Status ---");
        System.out.println("Name: " + hero.getName());
        System.out.println("HP: " + hero.getHealthPoints() + "/50");
        System.out.println("EP: " + hero.getExperiencePoints());
    }

    private void showLaufzettel() {
        System.out.println("\n--- Laufzettel ---");
        int count = 0;

        for (Lecturer l : hero.getSignedExerciseLeaders()) {
            if (l != null) {
                System.out.println("- " + l.getName());
                count++;
            }
        }

        System.out.println("Unterschriften: " + count + "/5");
    }

    private void takeBreak() {
        System.out.println("\nVerschnaufpause:");
        System.out.println("1 - Kleine Pause (+3 HP)");
        System.out.println("2 - Große Pause (+10 HP)");
        System.out.print("Deine Wahl: ");

        String choice = readUserInput();
        if (choice.equals("1")) {
            hero.regenerate(false);
            System.out.println("Du machst eine kleine Pause.");
        } else if (choice.equals("2")) {
            hero.regenerate(true);
            System.out.println("Du machst eine große Pause.");
        } else {
            System.out.println("Ungültige Eingabe. Keine Pause gemacht.");
        }
    }

    public Hero getHero() {
        return hero;
    }
<<<<<<< HEAD

    public boolean isGameRunning() {
        return gameRunning;
    }

    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    public boolean isGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }
=======
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
>>>>>>> 2730194cb53fb289f08c34da6a948c5e1a6d8c03
}
