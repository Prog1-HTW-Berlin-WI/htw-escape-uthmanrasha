package model;

/**
 * Freundliches Alien, das dem Spieler hilft
 * oder Informationen gibt.
 *
 * @author Uthman Rasha
 */
public class FriendlyAlien extends Alien {

    /**
     * Erstellt ein freundliches Alien mit festen Startwerten.
     */
    public FriendlyAlien() {
        super(
            "Hilfsbereite Riesenschnecke",
            15,
            true,
            "Keine Angst, ich will dir nichts Böses!"
        );
    }
}
