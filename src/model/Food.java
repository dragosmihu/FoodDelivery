package model;

import java.util.List;

public class Food extends Product{
    private List<String> ingredients;
    private Taste taste;

    public Food(){

    }
    public Food(String name, double price, List<String> ingredients, Taste taste) {
        super(name, price);
        this.ingredients = ingredients;
        this.taste = taste;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public Taste getTaste() {
        return taste;
    }

    public void setTaste(Taste taste) {
        this.taste = taste;
    }
}
