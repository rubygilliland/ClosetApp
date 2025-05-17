package model;

public class ClothingItem {
	private Season season;
	private Category category;
	private Color color;
	private String brand;
	private String name;	


public ClothingItem(Season season, Category category, Color color, String brand, String name) {
	this.season = season;
	this.category = category;
	this.color = color;
	this.brand = brand;
	this.name = name;
}

// copy constructor
public ClothingItem(ClothingItem otherItem) {
	this.season = otherItem.season;
	this.category = otherItem.category;
	this.color = otherItem.color;
	this.brand = otherItem.brand;
	this.name = otherItem.name;
}

public void setName(String name) {
    this.name = name;
}

public void setCategory(Category category) {
    this.category = category;
}

public void setColor(Color color) {
    this.color = color;
}

public void setSeason(Season season) {
    this.season = season;
}

public Season getSeason() {
	return this.season;
}

public Category getCategory() {
	return this.category;
}

public Color getColor() {
	return this.color;
}

public String getBrand() {
	return this.brand;
}

public String getName() {
	return this.name;
}

@Override
public String toString() {
	return this.name + ", " + this.category + ", " +  this.color + ", " + this.brand + ", " + this.season;
}
}