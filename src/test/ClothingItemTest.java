package test;


	import org.junit.jupiter.api.BeforeEach;
	import org.junit.jupiter.api.Test;

import model.ClothingItem;
import model.Season;
import model.Color;
import model.Category;

import static org.junit.jupiter.api.Assertions.*;



	public class  ClothingItemTest{

	    private ClothingItem item;
	    private Season season;
	    private Category category;
	    private Color color;
	    private String brand;

	    @BeforeEach
	    public void setUp() {
	        season = Season.SUMMER;
	        category = Category.TOP;
	        color = Color.RED;
	        brand = "Nike";
	        item = new ClothingItem(season, category, color, brand);
	    }

	    @Test
	    public void testConstructorWithUUID() {
	        assertNotNull(item.getId());
	        assertEquals(season, item.getSeason());
	        assertEquals(category, item.getCategory());
	        assertEquals(color, item.getColor());
	        assertEquals(brand, item.getBrand());
	    }

	    @Test
	    public void testCopyConstructor() {
	        ClothingItem copy = new ClothingItem(item);
	        assertEquals(item.getId(), copy.getId());
	        assertEquals(item.getSeason(), copy.getSeason());
	        assertEquals(item.getCategory(), copy.getCategory());
	        assertEquals(item.getColor(), copy.getColor());
	        assertEquals(item.getBrand(), copy.getBrand());
	    }

	    @Test
	    public void testWithCategory() {
	        ClothingItem newItem = item.withCategory(Category.PANTS);
	        assertEquals(Category.PANTS, newItem.getCategory());
	        assertEquals(item.getId(), newItem.getId());
	        assertNotSame(item, newItem);
	    }

	    @Test
	    public void testWithColor() {
	        ClothingItem newItem = item.withColor(Color.BLUE);
	        assertEquals(Color.BLUE, newItem.getColor());
	        assertEquals(item.getId(), newItem.getId());
	        assertNotSame(item, newItem);
	    }

	    @Test
	    public void testWithSeason() {
	        ClothingItem newItem = item.withSeason(Season.WINTER);
	        assertEquals(Season.WINTER, newItem.getSeason());
	        assertEquals(item.getId(), newItem.getId());
	        assertNotSame(item, newItem);
	    }

	    @Test
	    public void testToString() {
	        String output = item.toString();
	        assertTrue(output.contains(brand));
	        assertTrue(output.contains(color.toString()));
	        assertTrue(output.contains(category.toString()));
	        assertTrue(output.contains(season.toString()));
	    }

	    @Test
	    public void testEqualsAndHashCode() {
	        ClothingItem copy = new ClothingItem(item);
	        assertEquals(item, copy);
	        assertEquals(item.hashCode(), copy.hashCode());

	        ClothingItem different = new ClothingItem(Season.WINTER, Category.OUTERWEAR, Color.BLACK, "Adidas");
	        assertNotEquals(item, different);
	    }

	    @Test
	    public void testEqualsWithSameReference() {
	        assertEquals(item, item);
	    }

	    @Test
	    public void testEqualsWithNull() {
	        assertNotEquals(item, null);
	    }

	    @Test
	    public void testEqualsWithDifferentClass() {
	        assertNotEquals(item, "not a clothing item");
	    }
	}


