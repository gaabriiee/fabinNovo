package javafxcrud;

import javafx.beans.property.*;

public class FoodOrder {
    private final IntegerProperty id = new SimpleIntegerProperty();
    private final StringProperty name = new SimpleStringProperty();
    private final StringProperty foodItem = new SimpleStringProperty();
    private final IntegerProperty quantity = new SimpleIntegerProperty();
    private final DoubleProperty price = new SimpleDoubleProperty();
    private final DoubleProperty total = new SimpleDoubleProperty();

    public FoodOrder(int id, String name, String foodItem, int quantity, double price, double total) {
        this.id.set(id);
        this.name.set(name);
        this.foodItem.set(foodItem);
        this.quantity.set(quantity);
        this.price.set(price);
        this.total.set(total);
    }

    public int getId() { return id.get(); }
    public String getName() { return name.get(); }
    public String getFoodItem() { return foodItem.get(); }
    public int getQuantity() { return quantity.get(); }
    public double getPrice() { return price.get(); }
    public double getTotal() { return total.get(); }

    public StringProperty nameProperty() { return name; }
    public StringProperty foodItemProperty() { return foodItem; }
    public IntegerProperty quantityProperty() { return quantity; }
    public DoubleProperty priceProperty() { return price; }
    public DoubleProperty totalProperty() { return total; }
}
