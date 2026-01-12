package app;
/**
 * @author Uthman Rasha
 * @author Souri Armita
 */

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
 * @author Uthman Rasha
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
     * @param args Übergabe Parameter bei Programmstart
     */
public class EscapeApp {

    public static final String SAVE_FILE_NAME = "save";
    private EscapeGame game;
    private boolean gameRunning = true;

    public static void main(String[] args) {
        System.out.println("Welcome to the HTW escape");
        System.out.println("========================================\n");

        EscapeApp app = new EscapeApp();

        while (gameRunning) {
        while (true) {
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

        // (2) nur wenn Spiel gestartet wurde
        if (isGameRunning()) {
            System.out.println("(2) Resume game");
        }

        // (3) nur wenn Save existiert
        if (hasSavedGame()) {
            System.out.println("(3) Load game");
        }

        // (4) nur wenn Spiel gestartet wurde
        if (isGameRunning()) {
            System.out.println("(4) Save game");
        }

        // (5) nur wenn Save existiert
        if (hasSavedGame()) {
            System.out.println("(5) Delete saved game");
        }

         if (game != null) {
            System.out.println("(2) continue game");
            System.out.println("(4) Save game");
        }
        if (hasSavedGame()) {
            System.out.println("(3) Load game " );
            System.out.println("(5) Delete saved game");
        }
        System.out.println("(6) Quit");
        System.out.println("");
        System.out.println("Please choose a number between 1 and 6: ");
    }

    /**
     * Liest die Eingabe des Benutzers von der Konsole ein.
     * 
     * @return die Eingabe des Benutzers als Text
     */
    private String readUserInput() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        // TBD
        return userInput;
    }
/**
 * Reagiert auf die Eingabe des Benutzers.
 * Führt passende Aktion aus.
 * @param input Auswahl aus dem Menü
 */
    private void handleUserInput(String input) {
        switch (input) {
            case "1":
                this.startGame();
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
                    System.out.println("Kein laufendes Spiel zum Speichern vorhanden");
                }
                break;

            case "5":
                if (hasSavedGame()) {
                    deleteGame();
                } else {
                    System.out.println("Kein gespeichertes Spiel zum Löschen vorhanden.");
                }
                break;
            // ...
            case "6":
                System.out.println("Programm wird beendet.");
                gameRunning = false;
                break;
            default:
                System.out.println("Invalid input. Please choose a correct number between 1 and 6");
                break;
        }
    }

    /**
     * Startet ein neues Spiel und erstellt ein neues Spielobjekt.
     */

    private void handleUserInput(String input) {
        switch (input) {
  case "1":
            this.startGame();
            break;

        case "2":
            if (game != null) {
                this.resumeGame();
            }
            break;

        case "3":
            if (hasSavedGame()) {
                this.loadGame();
            }
            break;

        case "4":
            if (game != null) {
                this.saveGame();
            }
            break;

        case "5":
            if (hasSavedGame()) {
                this.deleteGame();
            }
            break;

        case "6":
            System.out.println("Bye 👋");
            System.exit(0);
            break;

        default:
            System.out.println("Invalid input. Please choose a number between 1 and 6.");
    }
}


    private void startGame() {
        this.game = new EscapeGame();
        resumeGame();
    }

    /**
     * Setzt das Spiel fort und startet den Spielablauf.
     */
    private void resumeGame() {
        this.game.setGameRunning(true);
        this.game.run();
    }

    /**
     * Löscht den gespeicherten Spielstand.
     */
    private void deleteGame() {
        if (new File(SAVE_FILE_NAME).delete()) {
            System.out.println("Game deleted!");
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
        } catch (Exception ex) {
            System.err.println("Something went wrong while saving the game: " + ex.getMessage());
            return;
        }
        System.out.println("Game saved!");
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
     * Prüft, ob das Spiel beendet ist.
     *
     * @return true, wenn das Spiel zu Ende ist
     */
    private boolean isGameFinished() {
        return game != null && game.isGameFinished();
    }

    /**
     * Prüft, ob es einen gespeicherten Spielstand gibt.
     *
     * @return true, wenn eine Speicherdatei existiert
     */
    private boolean hasSavedGame() {
        return new File(SAVE_FILE_NAME).exists();
    }

}
