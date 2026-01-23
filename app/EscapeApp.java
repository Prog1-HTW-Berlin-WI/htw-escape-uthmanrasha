package app;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * Hauptklasse des Spiels.
 * Programm wird von hier gestartet und das Hauptmenü wird dann angezeigt.
 * Außerdem werden hier Spiele gestartet, gespeichert und geladen.
 * 
 * @author Uthman Rasha
 * @author Souri Armita
 */
public class EscapeApp {

    /** Datei, in der der Spielstand gespeichert wird. */
    public static final String SAVE_FILE_NAME = "save";

    /** Das laufende Spiel */
    private EscapeGame game;

    /** Gibt an, ob das Programm läuft */
    private static boolean gameRunning = true;

    /**
     * Start des Programms.
     * Gibt eine Begrüßung aus, und das Hauptmenü wird immer wieder angezeigt
     * 
     * @param args Übergabe Parameter bei Programmstart
     */
    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (gameRunning) {
            app.showMainMenu();
            String choice = app.readUserInput();
            app.handleUserInput(choice);
            System.out.println("====================");
        }
    }

    /**
     * Zeigt das Hauptmenü auf der Konsole an.
     * Benutzer kann auswählen, was er machen möchte.
     */
    private void showMainMenu() {
        System.out.println("You're in the main menu");
        System.out.println("What do you want to do next?");
        System.out.println("(1) Start new game");

        if (isGameRunning()) {
            System.out.println("(2) Resume game");
        }

        if (hasSavedGame()) {
            System.out.println("(3) Load game");
        }

        if (isGameRunning()) {
            System.out.println("(4) Save game");
        }

        if (hasSavedGame()) {
            System.out.println("(5) Delete saved game");
        }

        System.out.println("(6) Quit");
        System.out.println("");
        System.out.print("Please choose a number: ");
    }

    /**
     * Liest die Eingabe des Benutzers von der Konsole ein
     * 
     * @return die Eingabe des Benutzers als Text
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Reagiert auf die Eingabe des Benutzers.
     * Führt passende Aktion aus.
     * 
     * @param input Auswahl aus dem Menü
     */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                startGame();
                break;

            case "2":
                if (isGameRunning()) {
                    resumeGame();
                } else {
                    System.out.println("Kein laufendes Spiel zum Fortsetzen vorhanden.");
                }
                break;

            case "3":
                if (hasSavedGame()) {
                    loadGame();
                    resumeGame();
                } else {
                    System.out.println("Kein gespeichertes Spiel vorhanden.");
                }
                break;

            case "4":
                if (isGameRunning()) {
                    saveGame();
                } else {
                    System.out.println("Kein laufendes Spiel zum Speichern vorhanden.");
                }
                break;

            case "5":
                if (hasSavedGame()) {
                    deleteGame();
                } else {
                    System.out.println("Kein gespeichertes Spiel zum Löschen vorhanden.");
                }
                break;

            case "6":
                System.out.println("Programm wird beendet.");
                gameRunning = false;
                break;

            default:
                System.out.println("Ungültige Eingabe. Bitte 1 bis 6 wählen.");
                break;
        }
    }

    /**
     * Startet ein neues Spiel und erstellt ein neues Spielobjekt.
     */
    private void startGame() {
        System.out.print("Bitte gib den Namen deines Charakters ein: ");
        String name = readUserInput();

        while (name == null || name.trim().isEmpty()) {
            System.out.print("Name darf nicht leer sein. Bitte erneut eingeben: ");
            name = readUserInput();
        }

        this.game = new EscapeGame(name);
        resumeGame();
    }

    /**
     * Setzt das Spiel fort und startet den Spielablauf.
     */
    private void resumeGame() {
        if (game == null) {
            System.out.println("Kein Spiel zum Fortsetzen vorhanden.");
            return;
        }

        game.setGameRunning(true);
        game.run();
    }

    /**
     * Löscht den gespeicherten Spielstand.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
        } else {
            System.out.println("Kein Save zum Löschen vorhanden.");
        }
    }

    /**
     * Speichert das aktuelle Spiel in einer Datei, damit es später weitergespielt werden kann.
     */
    private void saveGame() {
        try (FileOutputStream fos = new FileOutputStream(SAVE_FILE_NAME);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(game);
            oos.flush();
            System.out.println("Game saved!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
        }
    }

    /**
     * Lädt ein zuvor gespeichertes Spiel aus einer Datei.
     */
    private void loadGame() {
        try (FileInputStream fis = new FileInputStream(SAVE_FILE_NAME);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            this.game = (EscapeGame) ois.readObject();
            System.out.println("Game loaded!");
        } catch (Exception ex) {
            System.err.println("Something went wrong while loading the game: " + ex.getMessage());
        }
    }

    /**
     * Prüft, ob aktuell ein Spiel vorhanden ist.
     *
     * @return true, wenn ein Spiel existiert
     */
    private boolean isGameRunning() {
        return game != null;
    }

    /**
     * Prüft, ob es einen gespeicherten Spielstand gibt.
     *
     * @return true, wenn eine Speicherdatei existiert
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

    /**
     * Prüft, ob das Spiel beendet ist.
     *
     * @return true, wenn das Spiel zu Ende ist
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }
}

