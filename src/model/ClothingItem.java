package model;

import java.util.Objects;
import java.util.UUID;

import javax.swing.ImageIcon;

/**
 * Immutable class representing a clothing item.
 */
public final class ClothingItem {
    private final String imagePath;
    private final ImageIcon image;
    private final String id;
    private final Season season;
    private final Category category;
    private final Color color;
    private final String brand;

    /*
     * Constructs a new ClothingItem with the specified attributes.
     */
    public ClothingItem(String imagePath, Season season, Category category, Color color, String brand) {
        this.imagePath = Objects.requireNonNull(imagePath);
        this.image = new ImageIcon(imagePath);
        this.id = UUID.randomUUID().toString();
        this.season = Objects.requireNonNull(season);
        this.category = Objects.requireNonNull(category);
        this.color = Objects.requireNonNull(color);
        this.brand = Objects.requireNonNull(brand);
    }

    /*
     * Copy constructor.
     */
    public ClothingItem(ClothingItem other) {
        this(other.imagePath, other.id, other.season, other.category, other.color, other.brand);
    }

    /*
     * Full constructor with ID (used for immutability-based edits).
     */
    private ClothingItem(String imagePath, String id, Season season, Category category, Color color, String brand) {
        this.imagePath = Objects.requireNonNull(imagePath);
        this.image = new ImageIcon(imagePath);
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

    public ImageIcon getImageIcon() {
        return image;
    }

    public String getImagePath() {
        return imagePath;
    }

    /*
     * Returns a new ClothingItem with a modified category.
     */
    public ClothingItem withCategory(Category newCategory) {
        return new ClothingItem(imagePath, id, season, newCategory, color, brand);
    }

    /*
     * Returns a new ClothingItem with a modified color.
     */
    public ClothingItem withColor(Color newColor) {
        return new ClothingItem(imagePath, id, season, category, newColor, brand);
    }

    /*
     * Returns a new ClothingItem with a modified season.
     */
    public ClothingItem withSeason(Season newSeason) {
        return new ClothingItem(imagePath, id, newSeason, category, color, brand);
    }
    
    /*
     * Returns a new ClothingItem with a modified image path.
     */
    public ClothingItem withImagePath(String newImagePath) {
        return new ClothingItem(newImagePath, id, season, category, color, brand);
    }

    /*
     * Returns a new ClothingItem with a modified brand.
     */
    public ClothingItem withBrand(String newBrand) {
    	return new ClothingItem(imagePath, id, season, category, color, newBrand);
    }

    @Override
    public String toString() {
        return brand + " " + color + " " + category + " (" + season + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // hash only on ID
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ClothingItem)) return false;
        ClothingItem other = (ClothingItem) obj;
        return id.equals(other.id);
    }
}

