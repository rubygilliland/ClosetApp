package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Category;
import model.ClosetManager;
import model.ClothingItem;
import model.Color;
import model.Outfit;
import model.Season;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClosetManagerTest {

    private ClosetManager manager;
    private ClothingItem redTop;
    private ClothingItem blueBottom;

    @BeforeEach
    public void setUp() {
        manager = new ClosetManager();
        redTop = manager.addItem(Season.SUMMER, Category.TOP, Color.RED, "Nike");
        blueBottom = manager.addItem(Season.WINTER, Category.JEANS, Color.BLUE, "Adidas");
    }

    @Test
    public void testAddItemIndexesProperly() {
        List<ClothingItem> redItems = manager.getItemsByColor(Color.RED);
        assertEquals(1, redItems.size());
        assertEquals(redTop, redItems.get(0));

        List<ClothingItem> nikeItems = manager.getItemsByBrand("Nike");
        assertEquals(1, nikeItems.size());

        List<ClothingItem> summerItems = manager.getItemsBySeason(Season.SUMMER);
        assertEquals(1, summerItems.size());
    }

    @Test
    public void testRemoveItemCleansUpIndexes() {
        manager.removeItem(redTop);

        assertTrue(manager.getItemsByColor(Color.RED).isEmpty());
        assertTrue(manager.getItemsByBrand("Nike").isEmpty());
        assertTrue(manager.getItemsBySeason(Season.SUMMER).isEmpty());
    }

    @Test
    public void testCreateAndRemoveOutfit() {
        Outfit outfit = manager.createOutfit("Work Fit");
        assertEquals("Work Fit", outfit.getName());

        manager.removeOutfit(outfit);
        assertTrue(manager.getOutfits().isEmpty());
    }

    @Test
    public void testAddPieceToOutfit() {
        Outfit outfit = manager.createOutfit("Fit 1");
        manager.addPieceToOutfit(outfit, redTop);
        assertEquals(1, outfit.getPieces().size());
    }

    @Test
    public void testRemovePieceFromOutfit() {
        Outfit outfit = manager.createOutfit("Fit 2");
        manager.addPieceToOutfit(outfit, redTop);
        manager.removePieceFromOutfit(outfit, redTop);
        assertTrue(outfit.getPieces().isEmpty());
    }

    @Test
    public void testGetOutfitsReturnsDeepCopy() {
        Outfit outfit = manager.createOutfit("Gym Fit");
        manager.addPieceToOutfit(outfit, blueBottom);

        List<Outfit> copy = manager.getOutfits();
        assertEquals(1, copy.size());
        assertNotSame(outfit, copy.get(0));
        assertEquals(outfit.getName(), copy.get(0).getName());
    }

    @Test
    public void testGetItemsByColorReturnsCopy() {
        List<ClothingItem> redItems = manager.getItemsByColor(Color.RED);
        redItems.clear();
        assertEquals(1, manager.getItemsByColor(Color.RED).size());
    }

    @Test
    public void testGetItemsByBrandReturnsCopy() {
        List<ClothingItem> adidasItems = manager.getItemsByBrand("Adidas");
        adidasItems.clear();
        assertEquals(1, manager.getItemsByBrand("Adidas").size());
    }

    @Test
    public void testGetItemsBySeasonReturnsCopy() {
        List<ClothingItem> winterItems = manager.getItemsBySeason(Season.WINTER);
        winterItems.clear();
        assertEquals(1, manager.getItemsBySeason(Season.WINTER).size());
    }

    @Test
    public void testGetColorsBrandsSeasons() {
        Set<Color> colors = manager.getColors();
        Set<String> brands = manager.getBrands();
        Set<Season> seasons = manager.getSeasons();

        assertTrue(colors.contains(Color.RED));
        assertTrue(colors.contains(Color.BLUE));
        assertTrue(brands.contains("Nike"));
        assertTrue(brands.contains("Adidas"));
        assertTrue(seasons.contains(Season.SUMMER));
        assertTrue(seasons.contains(Season.WINTER));
    }

    @Test
    public void testGetItemsByNullKeyReturnsEmptyList() {
        assertTrue(manager.getItemsByColor(Color.BLACK).isEmpty());
        assertTrue(manager.getItemsByBrand("Unknown").isEmpty());
        assertTrue(manager.getItemsBySeason(Season.FALL).isEmpty());
    }
}


