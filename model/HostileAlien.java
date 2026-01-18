package model;

/**
 * Feindliches Alien, das den Spieler angreift.
 *
 * @author Uthman Rasha
 */
public class HostileAlien extends Alien {

    /**
     * Erstellt ein feindliches Alien mit festen Startwerten.
     */
    public HostileAlien() {
        super(
            "Aggressiver Schleimling",
            30,
            false,
            "Grrrr! Du kommst hier nicht vorbei!"
        );
    }
}