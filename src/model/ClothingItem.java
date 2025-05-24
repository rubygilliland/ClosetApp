package model;

import java.util.Objects;
import java.util.UUID;

/**
 * Immutable class representing a clothing item.
 */
public final class ClothingItem {
    private final String id;
    private final Season season;
    private final Category category;
    private final Color color;
    private final String brand;

    /**
     * Constructs a new ClothingItem with the specified attributes.
     */
    public ClothingItem(Season season, Category category, Color color, String brand) {
        this(UUID.randomUUID().toString(), season, category, color, brand);
    }

    /**
     * Copy constructor.
     */
    public ClothingItem(ClothingItem other) {
        this(other.id, other.season, other.category, other.color, other.brand);
    }

    /**
     * Full constructor with ID.
     */
    private ClothingItem(String id, Season season, Category category, Color color, String brand) {
        this.id = id;
        this.season = Objects.requireNonNull(season);
        this.category = Objects.requireNonNull(category);
        this.color = Objects.requireNonNull(color);
        this.brand = Objects.requireNonNull(brand);
    }

    public String getId() {
        return id;
    }

    public Season getSeason() {
        return season;
    }

    public Category getCategory() {
        return category;
    }

    public Color getColor() {
        return color;
    }

    public String getBrand() {
        return brand;
    }

    /**
     * Returns a new ClothingItem with a modified category.
     */
    public ClothingItem withCategory(Category newCategory) {
        return new ClothingItem(this.id, season, newCategory, color, brand);
    }

    /**
     * Returns a new ClothingItem with a modified color.
     */
    public ClothingItem withColor(Color newColor) {
        return new ClothingItem(this.id, season, category, newColor, brand);
    }

    /**
     * Returns a new ClothingItem with a modified season.
     */
    public ClothingItem withSeason(Season newSeason) {
        return new ClothingItem(this.id, newSeason, category, color, brand);
    }

    @Override
    public String toString() {
        return brand + " " + color + " " + category + " (" + season + ")";
    }

    @Override
    public int hashCode() {
    	
    	// hash only on ID
        return Objects.hash(id); 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ClothingItem)) return false;
        ClothingItem other = (ClothingItem) obj;
        return id.equals(other.id);
    }
}
