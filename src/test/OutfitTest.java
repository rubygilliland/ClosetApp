package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Category;
import model.ClothingItem;
import model.Color;
import model.Outfit;
import model.Season;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OutfitTest {

    private ClothingItem redTop;
    private ClothingItem blueBottom;
    private Outfit outfit;

    @BeforeEach
    public void setUp() {
        redTop = new ClothingItem(Season.SUMMER, Category.TOP, Color.RED, "Nike");
        blueBottom = new ClothingItem(Season.SUMMER, Category.JEANS, Color.BLUE, "Adidas");
        outfit = new Outfit("Casual Fit");
    }

    @Test
    public void testConstructorSetsName() {
        assertEquals("Casual Fit", outfit.getName());
    }

    @Test
    public void testAddPiece() {
        outfit.addPiece(redTop);
        List<ClothingItem> pieces = outfit.getPieces();

        assertEquals(1, pieces.size());
        assertNotSame(redTop, pieces.get(0)); // Deep copy check
        assertEquals(redTop, pieces.get(0));  // Value equality check
    }

    @Test
    public void testRemovePiece() {
        outfit.addPiece(redTop);
        outfit.removePiece(redTop);
        assertTrue(outfit.getPieces().isEmpty());
    }

    @Test
    public void testSetAndGetSeason() {
        outfit.setSeason(Season.WINTER);
        assertEquals(Season.WINTER, outfit.getSeason());
    }

    @Test
    public void testGetPiecesReturnsDeepCopy() {
        outfit.addPiece(redTop);
        List<ClothingItem> pieces1 = outfit.getPieces();
        pieces1.clear(); // Should not affect original list

        List<ClothingItem> pieces2 = outfit.getPieces();
        assertEquals(1, pieces2.size());
    }

    @Test
    public void testCopyConstructor() {
        outfit.setSeason(Season.SPRING);
        outfit.addPiece(redTop);
        outfit.addPiece(blueBottom);

        Outfit copy = new Outfit(outfit);

        assertEquals(outfit.getName(), copy.getName());
        assertEquals(outfit.getSeason(), copy.getSeason());
        assertEquals(outfit.getPieces().size(), copy.getPieces().size());

        // Ensure deep copy: same values, different objects
        assertNotSame(outfit.getPieces().get(0), copy.getPieces().get(0));
        assertEquals(outfit.getPieces().get(0), copy.getPieces().get(0));
    }

    @Test
    public void testToStringContainsExpectedValues() {
        outfit.setSeason(Season.FALL);
        outfit.addPiece(redTop);
        String str = outfit.toString();

        assertTrue(str.contains("Casual Fit"));
        assertTrue(str.contains("FALL"));
        assertTrue(str.contains("pieces"));
    }

    @Test
    public void testEqualsAndHashCode() {
        Outfit outfit1 = new Outfit("Work Fit");
        Outfit outfit2 = new Outfit("Work Fit");
        Outfit outfit3 = new Outfit("Party Fit");

        assertEquals(outfit1, outfit2);
        assertEquals(outfit1.hashCode(), outfit2.hashCode());

        assertNotEquals(outfit1, outfit3);
    }

    @Test
    public void testEqualsWithSameReference() {
        assertEquals(outfit, outfit);
    }

    @Test
    public void testEqualsWithNull() {
        assertNotEquals(outfit, null);
    }

    @Test
    public void testEqualsWithDifferentClass() {
        assertNotEquals(outfit, "not an outfit");
    }
}
