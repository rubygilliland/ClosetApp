package model;

import java.util.*;

/**
 * Manages a collection of clothing items and outfits.
 */
public class ClosetManager {
    private final Map<String, ClothingItem> closet;
    private final Map<Color, List<ClothingItem>> colors;
    private final Map<String, List<ClothingItem>> brands;
    private final Map<Season, List<ClothingItem>> seasons;
    private final List<Outfit> outfits;

    public ClosetManager() {
        this.closet = new HashMap<>();
        this.colors = new HashMap<>();
        this.brands = new HashMap<>();
        this.seasons = new HashMap<>();
        this.outfits = new ArrayList<>();
    }

    /*
     * Adds a new clothing item to the closet and maps it.
     */
    public void addItem(String imagePath, Season season, Category category, Color color, String brand) {
        ClothingItem newItem = new ClothingItem(imagePath, season, category, color, brand);
        closet.put(newItem.getId(), newItem);

        colors.computeIfAbsent(color, k -> new ArrayList<>()).add(newItem);
        brands.computeIfAbsent(brand, k -> new ArrayList<>()).add(newItem);
        seasons.computeIfAbsent(season, k -> new ArrayList<>()).add(newItem);

    }

    /*
     * Removes a clothing item from the closet and all maps.
     */
    public void removeItem(ClothingItem item) {
        closet.remove(item.getId());

        colors.getOrDefault(item.getColor(), Collections.emptyList()).removeIf(i -> i.equals(item));
        brands.getOrDefault(item.getBrand(), Collections.emptyList()).removeIf(i -> i.equals(item));
        seasons.getOrDefault(item.getSeason(), Collections.emptyList()).removeIf(i -> i.equals(item));
    }

    /*
     * Creates a new outfit with the given name.
     */
    public Outfit createOutfit(String name) {
        Outfit newOutfit = new Outfit(name);
        outfits.add(newOutfit);
        return newOutfit;
    }

    /*
     * Adds a piece to the specified outfit.
     */
    public void addPieceToOutfit(Outfit outfit, ClothingItem piece) {
        outfit.addPiece(piece);
    }

    /*
     * Removes a piece from the specified outfit.
     */
    public void removePieceFromOutfit(Outfit outfit, ClothingItem piece) {
        outfit.removePiece(piece);
    }

    /*
     * Removes the specified outfit.
     */
    public void removeOutfit(Outfit outfit) {
        outfits.removeIf(o -> o.getName().equals(outfit.getName()));
    }

    /*
     * Gets and returns a deep copy of all outfits.
     */
    public List<Outfit> getOutfits() {
        List<Outfit> copy = new ArrayList<>();
        for (Outfit o : outfits) {
            copy.add(new Outfit(o));
        }
        return copy;
    }

    public List<ClothingItem> getItemsByColor(Color color) {
        return deepCopyList(colors.get(color));
    }

    public List<ClothingItem> getItemsBySeason(Season season) {
        return deepCopyList(seasons.get(season));
    }

    public List<ClothingItem> getItemsByBrand(String brand) {
        return deepCopyList(brands.get(brand));
    }

    public Set<Color> getColors() {
        return colors.keySet();
    }

    public Set<String> getBrands() {
        return brands.keySet();
    }

    public Set<Season> getSeasons() {
        return seasons.keySet();
    }

    /*
     * Helper method to deep copy a list of ClothingItems.
     */
    private List<ClothingItem> deepCopyList(List<ClothingItem> items) {
        if (items == null) return List.of();
        List<ClothingItem> copy = new ArrayList<>();
        for (ClothingItem item : items) {
            copy.add(new ClothingItem(item));
        }
        return copy;
    }
}
