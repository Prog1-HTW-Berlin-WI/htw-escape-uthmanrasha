package app;

import java.util.Scanner;

import model.HTWRoom;
import model.Hero;
import model.Lecturer;
import model.Alien;
import model.HostileAlien;

/**
 * Zentrale Klasse des Spielablaufs.
 * Diese Klasse verwaltet das Spiel, den Helden, und den aktuellen Spielzustand.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */
public class EscapeGame {

    /** Der Held, den der Spieler steuert */
    private final Hero hero;

    /** Räume, die im Spiel vorhanden sind (später nutzbar) */
    private final HTWRoom[] rooms = new HTWRoom[3];

    /** Aktuelle Spielrunde */
    private int currentRound = 1;

    /** Maximale Anzahl an Runden */
    private static final int MAX_ROUNDS = 24;

    /** Beispiel-Lecturer (später durch echte Lecturer in Räumen ersetzen) */
    private Lecturer lecturer = new Lecturer("Übungsleiter Müller");

    /** Gibt an, ob das Spiel aktuell läuft */
    private boolean gameRunning = true;

    /** Gibt an, ob das Spiel beendet wurde */
    private boolean gameFinished = false;

    /**
     * Erstellt ein neues Spiel und legt einen neuen Helden an.
     *
     * @param heroName Name des Spielcharakters
     */
    public EscapeGame(String heroName) {
        this.hero = new Hero(heroName);
    }

    /**
     * Startet den Spielablauf (Spielmenü).
     */
    public void run() {
        while (gameRunning && hero.isOperational() && !gameFinished) {
            showGameMenu();
            String input = readUserInput();
            handleGameMenuInput(input);
        }

        System.out.println("Zurück ins Hauptmenü...");
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

    /**
     * Erkunden kostet eine Runde und löst ein Zufallsereignis aus.
     * 20%: nichts, 52%: feindliches Alien, 28%: Lecturer.
     */
    private void exploreUniversity() {

        if (currentRound > MAX_ROUNDS) {
            System.out.println("Zeit ist vorbei. Spiel verloren.");
            gameFinished = true;
            return;
        }

        int zufall = (int) (Math.random() * 100);

        if (zufall < 20) {
            System.out.println("Du erkundest die HTW. Nichts passiert.");

        } else if (zufall < 72) {
            Alien alien = new HostileAlien();
            startAlienEncounter(alien);

        } else {
            System.out.println("Du triffst einen Übungsleiter: " + lecturer.getName());

            if (lecturer.isReadyToSign()) {
                hero.signExerciseLeader(lecturer);
                lecturer.sign();
                System.out.println("Der Laufzettel wurde unterschrieben!");
            } else {
                System.out.println("Der Übungsleiter hat bereits unterschrieben.");
            }
        }

        currentRound++;
        System.out.println("Runde: " + currentRound + " von " + MAX_ROUNDS);

        if (currentRound > MAX_ROUNDS) {
            System.out.println("Zeit ist vorbei. Spiel verloren.");
            gameFinished = true;
        }
    }

    /**
     * Startet eine Begegnung mit einem feindlichen Alien.
     * Spieler kann kämpfen oder fliehen.
     *
     * @param alien feindliches Alien
     */
    private void startAlienEncounter(Alien alien) {
        System.out.println("\nBEGEGNUNG: Ein feindliches Alien erscheint!");
        System.out.println(alien.getName() + ": " + alien.getGreeting());

        System.out.println("1 - Kämpfen");
        System.out.println("2 - Fliehen (42% Chance)");
        System.out.print("Deine Wahl: ");

        String choice = readUserInput();

        if (choice.equals("2")) {
            if (hero.flee()) {
                System.out.println("Du konntest erfolgreich fliehen!");
                return;
            } else {
                System.out.println("Flucht fehlgeschlagen! Der Kampf startet...");
            }
        } else if (!choice.equals("1")) {
            System.out.println("Ungültige Eingabe. Du musst kämpfen!");
        }

        fight(alien);
    }

    /**
     * Kampf läuft bis Alien besiegt oder Held nicht mehr handlungsfähig.
     * Sieg: +5 EP, Niederlage: +1 EP.
     *
     * @param alien feindliches Alien
     */
    private void fight(Alien alien) {
        System.out.println("\n--- KAMPF START ---");

        while (!alien.isDefeated() && hero.isOperational()) {

            int heroDamage = hero.attack();
            System.out.println("Du greifst an und machst " + heroDamage + " Schaden.");
            alien.takeDamage(heroDamage);

            if (alien.isDefeated()) {
                break;
            }

            int alienDamage = 5;
            System.out.println(alien.getName() + " greift zurück und macht " + alienDamage + " Schaden.");
            hero.takeDamage(alienDamage);
            System.out.println("Deine HP: " + hero.getHealthPoints() + "/50");
        }

        if (hero.isOperational()) {
            System.out.println("Du hast das Alien besiegt!");
            hero.addExperiencePoints(5);
            System.out.println("+5 EP erhalten. EP jetzt: " + hero.getExperiencePoints());
        } else {
            System.out.println("Du hast den Kampf verloren.");
            hero.addExperiencePoints(1);
            System.out.println("+1 EP erhalten. EP jetzt: " + hero.getExperiencePoints());
            gameFinished = true; // optional: Spielende bei 0 HP
        }

        System.out.println("--- KAMPF ENDE ---\n");
    }

    /**
     * Zeigt den Status des Helden inkl. Laufzettel und Runde an (Aufgabe 11).
     */
    private void showHeroStatus() {
        System.out.println("\n========== HERO STATUS ==========");
        System.out.println("Name: " + hero.getName());
        System.out.println("Lebenspunkte: " + hero.getHealthPoints() + "/50");
        System.out.println("Erfahrungspunkte: " + hero.getExperiencePoints());
        System.out.println("---------------------------------");

        System.out.println("Laufzettel:");
        int signedCount = 0;

        for (Lecturer l : hero.getSignedExerciseLeaders()) {
            if (l != null) {
                System.out.println("✔ " + l.getName());
                signedCount++;
            }
        }

        int missing = hero.getSignedExerciseLeaders().length - signedCount;
        System.out.println("Unterschriften: " + signedCount + "/5");
        System.out.println("Fehlende Unterschriften: " + missing);
        System.out.println("---------------------------------");

        System.out.println("Aktuelle Runde: " + currentRound + " / " + MAX_ROUNDS);
        System.out.println("=================================\n");
    }

    /**
     * Zeigt nur den Laufzettel (nur Unterschriften).
     */
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

    /**
     * Verschnaufpause (klein oder groß).
     */
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
            currentRound++;

            if (currentRound > MAX_ROUNDS) {
                System.out.println("Zeit ist vorbei. Spiel verloren.");
                gameFinished = true;
            }
        } else {
            System.out.println("Ungültige Eingabe. Keine Pause gemacht.");
        }
    }

    public Hero getHero() {
        return hero;
    }

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
}