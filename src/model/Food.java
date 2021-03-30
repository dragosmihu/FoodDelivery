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

    @Override
    public String toString() {
        StringBuffer returnString = new StringBuffer(super.toString() + "Ingredients: ");
        for (String s : ingredients){
            returnString.append(s+", ");
        }
        returnString.append("\n Taste: ");
        switch (taste){
            case SALTY: returnString.append("salty");
            break;
            case SWEET: returnString.append("sweet");
            break;
            case SOUR: returnString.append("sour");
            break;
            default: returnString.append("bitter");
        }
        return returnString.toString();
    }
}
