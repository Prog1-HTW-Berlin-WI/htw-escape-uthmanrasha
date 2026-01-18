package model;

import java.io.Serializable;

/**
 * Abstrakte Basisklasse für alle Aliens im Spiel.
 * Von dieser Klasse erben später die verschiedenen Alien-Typen.
 *
 * @author Uthman Rasha
 * @author Souri Armita
 */
public abstract class Alien implements Serializable {


    // Bitte serialVersionUID beibehalten, damit die Klasse bei der
    // Speicherung als Datei (Serialisierung) und beim Laden (Deserialisierung)
    // konsistent bleibt und Versionierungsprobleme vermieden werden.

     /**
     * Versionsnummer für die Serialisierung.
     * Diese wird benötigt, damit das Speichern 
     * und Laden von Spielständen korrekt funktioniert.
     */
    private static final long serialVersionUID = 1729389822767173584L;

    protected String name;
    protected int lifePoints;
    protected boolean friendly;
    protected String greeting;

    /**
     * Erstellt ein neues Alien mit den angegebenen Eigenschaften.
     *
     * @param name Name des Aliens
     * @param lifePoints Lebenspunkte des Aliens
     * @param friendly Gibt an, ob das Alien freundlich ist
     * @param greeting Begrüßungstext des Aliens
     */
    public Alien(String name, int lifePoints, boolean friendly, String greeting) {
        this.name = name;
        this.lifePoints = lifePoints;
        this.friendly = friendly;
        this.greeting = greeting;
    }

    /**
     * Reduziert die Lebenspunkte des Aliens um den angegebenen Schaden.
     * Die Lebenspunkte fallen nicht unter 0.
     *
     * @param amount Schadenswert
     */
    public void takeDamage(int amount) {
        lifePoints -= amount;

        if (lifePoints < 0) {
            lifePoints = 0;
        }

        System.out.println(name + " erleidet " + amount +
                " Schaden. Verbleibende Lebenspunkte: " + lifePoints);
    }

    /**
     * Prüft, ob der Alien besiegt wurde.
     *
     * @return true, wenn lifePoints <= 0
     */
    public boolean isDefeated() {
        return lifePoints <= 0;
    }

    public String getName() {
        return name;
    }

    public boolean isFriendly() {
        return friendly;
    }

    public String getGreeting() {
        return greeting;
    }

    public int getLifePoints() {
        return lifePoints;
    }
}