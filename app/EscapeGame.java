package app;

import java.io.Serializable;
import java.util.Scanner;

import model.Alien;
import model.HTWRoom;
import model.Hero;
import model.HostileAlien;
import model.Lecturer;

/**
 * Das ist die Hauptklasse vom Spiel.
 * Hier passiert das ganze Spiel: Menü anzeigen, erkunden, kämpfen, pausieren.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */
public class EscapeGame implements Serializable {

    /** Damit Speichern/Laden klappt. */
    private static final long serialVersionUID = 1L;

    private final Hero hero;

    /** Räume der HTW. */
    private final HTWRoom[] rooms = new HTWRoom[3];

    /** Welche Runde gerade ist. */
    private int currentRound = 1;

    /** Maximale Runden pro Spiel, sonst ist verloren. */
    private static final int MAX_ROUNDS = 24;

    /** Merkt sich, ob die kleine Pause in dieser Runde schon benutzt wurde. */
    private boolean shortRestUsedThisRound = false;

    /** Beispiel-Übungsleiter. */
    private Lecturer lecturer = new Lecturer("Übungsleiter Müller");

    /** Beispiel-Raum mit echter HTW-Atmosphäre. */
    private HTWRoom room = new HTWRoom(
            "A210",
            "Ein Seminarraum im Gebäude A. Es stehen Tische in Reihen und vorne hängt ein Beamer."
    );

    /** True = Spiel läuft noch. */
    private boolean gameRunning = true;

    /** True = Spiel ist beendet (gewonnen oder verloren). */
    private boolean gameFinished = false;

    /**
     * Startet ein neues Spiel und erstellt den Helden.
     * Außerdem werden Beispiel-Räume vorbereitet.
     *
     * @param heroName Name des Spielers
     */
    public EscapeGame(String heroName) {
        this.hero = new Hero(heroName);
        this.room.setLecturer(this.lecturer);
        this.rooms[0] = this.room;
        this.rooms[1] = new HTWRoom("A001", "Ein Flur im Erdgeschoss. Man hört Schritte und Stimmen von Studierenden.");
        this.rooms[2] = new HTWRoom("A212", "Ein weiterer Seminarraum mit Whiteboard und ein paar Postern an der Wand.");
    }

    /**
     * Haupt-Schleife vom Spiel.
     * Das Menü läuft solange, bis der Spieler aufhört oder verliert.
     */
    public void run() {
        while (this.gameRunning && this.hero.isOperational() && !this.gameFinished) {
            this.showGameMenu();
            String input = this.readUserInput();
            this.handleGameMenuInput(input);
        }

        System.out.println("Zurück ins Hauptmenü...");
    }

    /**
     * Zeigt das Spielmenü an.
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
     * Liest eine Eingabe vom Spieler.
     *
     * @return die Eingabe als String
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Macht etwas je nachdem, was der Spieler im Menü eingibt.
     *
     * @param input Menü-Auswahl
     */
    private void handleGameMenuInput(String input) {
        switch (input) {
            case "1":
                this.exploreUniversity();
                break;
            case "2":
                this.showHeroStatus();
                break;
            case "3":
                this.showLaufzettel();
                break;
            case "4":
                this.takeBreak();
                break;
            case "5":
                this.gameRunning = false;
                break;
            default:
                System.out.println("Ungültige Eingabe. Bitte 1 bis 5 wählen.");
        }
    }

    /**
     * Der Spieler erkundet die Hochschule.
     * Dabei wird zufällig entschieden, was passiert:
     * - 20%: Es passiert nichts
     * - 52%: Ein feindliches Alien kommt
     * - 28%: Man trifft einen Übungsleiter im Raum
     * Danach wird die Runde +1 erhöht.
     */
    private void exploreUniversity() {

        /** Wenn die Zeit vorbei ist -> direkt verloren. */
        if (this.currentRound > MAX_ROUNDS) {
            System.out.println("Zeit ist vorbei. Spiel verloren.");
            this.gameFinished = true;
            return;
        }

        /** Neue Runde -> kleine Pause wieder erlauben. */
        this.shortRestUsedThisRound = false;

        int zufall = (int) (Math.random() * 100.0);

        if (zufall < 20) {
            System.out.println("Du erkundest die HTW. Nichts passiert.");

        } else if (zufall < 72) {
            HostileAlien alien = new HostileAlien();
            this.startAlienEncounter(alien);

        } else {
            System.out.println("Du betrittst den Raum " + this.room.getIdentifier() + ".");
            System.out.println(this.room.getDescription());

            Lecturer l = this.room.getLecturer();
            if (l == null) {
                System.out.println("Hier ist gerade kein Übungsleiter.");
            } else {
                System.out.println("Du triffst einen Übungsleiter: " + l.getName());
                if (l.isReadyToSign()) {
                    this.hero.signExerciseLeader(l);
                    l.sign();
                    System.out.println("Der Laufzettel wurde unterschrieben!");
                } else {
                    System.out.println("Der Übungsleiter hat bereits unterschrieben.");
                }
            }
        }

        /** Runde erhöhen */
        ++this.currentRound;
        System.out.println("Runde: " + this.currentRound + " von " + MAX_ROUNDS);

        /** Wenn nach der Aktion die Runden vorbei sind -> verloren */
        if (this.currentRound > MAX_ROUNDS) {
            System.out.println("Zeit ist vorbei. Spiel verloren.");
            this.gameFinished = true;
        }
    }

    /**
     * Startet eine Begegnung mit einem feindlichen Alien.
     * Der Spieler darf wählen:
     * 1 = Kämpfen
     * 2 = Fliehen (42% Chance)
     * Wenn Flucht nicht klappt, muss er kämpfen.
     *
     * @param alien das feindliche Alien
     */
    private void startAlienEncounter(Alien alien) {
        System.out.println("\nBEGEGNUNG: Ein feindliches Alien erscheint!");
        System.out.println(alien.getName() + ": " + alien.getGreeting());

        System.out.println("1 - Kämpfen");
        System.out.println("2 - Fliehen (42% Chance)");
        System.out.print("Deine Wahl: ");

        String choice = this.readUserInput();

        if (choice.equals("2")) {
            if (this.hero.flee()) {
                System.out.println("Du konntest erfolgreich fliehen!");
                return;
            } else {
                System.out.println("Flucht fehlgeschlagen! Der Kampf startet...");
            }
        } else if (!choice.equals("1")) {
            System.out.println("Ungültige Eingabe. Du musst kämpfen!");
        }

        this.fight(alien);
    }

    /**
    * Führt einen Kampf zwischen dem Helden und einem feindlichen Alien aus.
    * Der Kampf läuft so lange, bis entweder:
    * - das Alien besiegt ist oder
    * - der Held keine Lebenspunkte mehr hat.
    *
    * Während des Kampfes:
    * - Der Held greift zuerst an
    * - Danach greift das Alien zurück
    *
    * Am Ende:
    * - Sieg: +5 Erfahrungspunkte
    * - Niederlage: +1 Erfahrungspunkt und das Spiel endet
    *
    * @param var1 das feindliche Alien
    */
    private void fight(Alien alien) {
        System.out.println("\n--- KAMPF START ---");

        while (!alien.isDefeated() && this.hero.isOperational()) {
            int heroDamage = this.hero.attack();
            System.out.println("Du greifst an und machst " + heroDamage + " Schaden.");
            alien.takeDamage(heroDamage);

            if (alien.isDefeated()) {
                break;
            }

            int alienDamage = 5;
            System.out.println(alien.getName() + " greift zurück und macht " + alienDamage + " Schaden.");
            this.hero.takeDamage(alienDamage);
            System.out.println("Deine HP: " + this.hero.getHealthPoints() + "/50");
        }

        if (this.hero.isOperational()) {
            System.out.println("Du hast das Alien besiegt!");
            this.hero.addExperiencePoints(5);
            System.out.println("+5 EP erhalten. EP jetzt: " + this.hero.getExperiencePoints());
        } else {
            System.out.println("Du hast den Kampf verloren.");
            this.hero.addExperiencePoints(1);
            System.out.println("+1 EP erhalten. EP jetzt: " + this.hero.getExperiencePoints());
            this.gameFinished = true;
        }

        System.out.println("--- KAMPF ENDE ---\n");
    }

    /**
     * Zeigt den Status vom Helden
     */
    private void showHeroStatus() {
        System.out.println("\n========== HERO STATUS ==========");
        System.out.println("Name: " + this.hero.getName());
        System.out.println("Lebenspunkte: " + this.hero.getHealthPoints() + "/50");
        System.out.println("Erfahrungspunkte: " + this.hero.getExperiencePoints());
        System.out.println("---------------------------------");

        System.out.println("Laufzettel:");
        int signedCount = 0;

        for (Lecturer l : this.hero.getSignedExerciseLeaders()) {
            if (l != null) {
                System.out.println("✔ " + l.getName());
                signedCount++;
            }
        }

        int missing = this.hero.getSignedExerciseLeaders().length - signedCount;
        System.out.println("Unterschriften: " + signedCount + "/5");
        System.out.println("Fehlende Unterschriften: " + missing);
        System.out.println("---------------------------------");

        System.out.println("Aktuelle Runde: " + this.currentRound + " / " + MAX_ROUNDS);
        System.out.println("=================================\n");
    }

    /**
     * Zeigt nur den Laufzettel:
     * Also nur die Übungsleiter, die schon unterschrieben haben.
     */
    private void showLaufzettel() {
        System.out.println("\n--- Laufzettel ---");
        int count = 0;

        for (Lecturer l : this.hero.getSignedExerciseLeaders()) {
            if (l != null) {
                System.out.println("- " + l.getName());
                count++;
            }
        }

        System.out.println("Unterschriften: " + count + "/5");
    }

    /**
     * Verschnaufpause-Menü:
     * 1 = kleine Pause (+3 HP), aber nur einmal pro Runde
     * 2 = große Pause (+10 HP), kostet eine ganze Runde (Runde +1)
     * 
     * Wenn die maximale Rundenzahl überschritten wird, ist das Spiel verloren.
     */
    private void takeBreak() {
        System.out.println("\nVerschnaufpause:");
        System.out.println("1 - Kleine Pause (+3 HP) [nur 1x pro Runde]");
        System.out.println("2 - Große Pause (+10 HP) [kostet 1 Runde]");
        System.out.print("Deine Wahl: ");

        String choice = this.readUserInput();

        if (choice.equals("1")) {
            if (this.shortRestUsedThisRound) {
                System.out.println("Diese Runde wurde bereits eine kleine Verschnaufpause genutzt.");
                return;
            }

            this.hero.regenerate(false);
            this.shortRestUsedThisRound = true;
            System.out.println("Du machst eine kleine Pause.");

        } else if (choice.equals("2")) {
            this.hero.regenerate(true);
            System.out.println("Du machst eine große Pause.");
            ++this.currentRound;
            this.shortRestUsedThisRound = false;

            System.out.println("Runde: " + this.currentRound + " von " + MAX_ROUNDS);

            if (this.currentRound > MAX_ROUNDS) {
                System.out.println("Zeit ist vorbei. Spiel verloren.");
                this.gameFinished = true;
            }

        } else {
            System.out.println("Ungültige Eingabe. Keine Pause gemacht.");
        }
    }

    /**
     * Gibt den Helden zurück.
     *
     * @return der Held
     */
    public Hero getHero() {
        return this.hero;
    }

    /**
     * Gibt zurück, ob das Spiel gerade läuft.
     *
     * @return true wenn Spiel läuft
     */
    public boolean isGameRunning() {
        return this.gameRunning;
    }

    /**
     * Setzt, ob das Spiel laufen soll oder nicht.
     *
     * @param value true = läuft, false = stop
     */
    public void setGameRunning(boolean value) {
        this.gameRunning = value;
    }

    /**
     * Gibt zurück, ob das Spiel beendet ist.
     *
     * @return true wenn beendet
     */
    public boolean isGameFinished() {
        return this.gameFinished;
    }

    /**
     * Setzt, ob das Spiel beendet ist.
     *
     * @param value true = beendet
     */
    public void setGameFinished(boolean value) {
        this.gameFinished = value;
    }

    /**
     * Gibt zurück, ob die kleine Pause in dieser Runde schon benutzt wurde.
     *
     * @return true wenn schon benutzt
     */
    public boolean isShortRestUsedThisRound() {
        return this.shortRestUsedThisRound;
    }

    /**
     * Setzt, ob die kleine Pause schon benutzt wurde.
     *
     * @param value true/false
     */
    public void setShortRestUsedThisRound(boolean value) {
        this.shortRestUsedThisRound = value;
    }
}