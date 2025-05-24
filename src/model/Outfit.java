package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents an outfit composed of multiple clothing items.
 */
public class Outfit {
    private final String name;
    private final List<ClothingItem> pieces;
    private Season season;

    /**
     * Creates a new outfit with the given name.
     */
    public Outfit(String name) {
        this.name = Objects.requireNonNull(name);
        this.pieces = new ArrayList<>();
    }

    /**
     * Copy constructor: deep copies all clothing items.
     */
    public Outfit(Outfit other) {
        this.name = other.name;
        this.season = other.season;
        this.pieces = new ArrayList<>();
        for (ClothingItem item : other.pieces) {
            this.pieces.add(new ClothingItem(item));
        }
    }

    /**
     * Adds a deep copy of the given clothing item to the outfit.
     */
    public void addPiece(ClothingItem piece) {
        this.pieces.add(new ClothingItem(piece));
    }

    /**
     * Removes an equivalent clothing item from the outfit.
     */
    public void removePiece(ClothingItem piece) {
        this.pieces.removeIf(p -> p.equals(piece));
    }

    /**
     * Sets the outfit's associated season.
     */
    public void setSeason(Season season) {
        this.season = season;
    }

    /**
     * Returns a deep copy of the clothing items in the outfit.
     */
    public List<ClothingItem> getPieces() {
        List<ClothingItem> copy = new ArrayList<>();
        for (ClothingItem c : pieces) {
            copy.add(new ClothingItem(c));
        }
        return copy;
    }

    public String getName() {
        return name;
    }

    public Season getSeason() {
        return season;
    }

    @Override
    public String toString() {
        return "Outfit{name='" + name + "', season=" + season + ", pieces=" + pieces + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Outfit)) return false;
        Outfit outfit = (Outfit) o;
        return Objects.equals(name, outfit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
