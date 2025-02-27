package javafxcrud;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class OrderApp extends Application {
    private TableView<FoodOrder> table = new TableView<>();
    private ObservableList<FoodOrder> orders = FXCollections.observableArrayList();

    private TextField nameField = new TextField();
    private TextField foodItemField = new TextField();
    private TextField quantityField = new TextField();
    private TextField priceField = new TextField();

    private final OrderDAO orderDAO = new OrderDAO();

    private void loadOrders() {
        orders.setAll(orderDAO.getAllOrders());
    }

    private void addOrder() {
        String name = nameField.getText().trim();
        String foodItem = foodItemField.getText().trim();
        int quantity;
        double price;

        if (name.isEmpty() || foodItem.isEmpty()) {
            System.out.println("Name and Food Item cannot be empty!");
            return;
        }

        try {
            quantity = Integer.parseInt(quantityField.getText().trim());
            price = Double.parseDouble(priceField.getText().trim());
            if (quantity <= 0 || price <= 0) {
                System.out.println("Quantity and Price must be greater than zero.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid quantity or price. Enter numbers only.");
            return;
        }

        orderDAO.addOrder(name, foodItem, quantity, price);
        loadOrders();
    }

    private void deleteOrder() {
        FoodOrder selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            orderDAO.deleteOrder(selected.getId());
            loadOrders();
        }
    }

    @Override
    public void start(Stage stage) {
        TableColumn<FoodOrder, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(cell -> cell.getValue().nameProperty());

        TableColumn<FoodOrder, String> foodItemCol = new TableColumn<>("Food Item");
        foodItemCol.setCellValueFactory(cell -> cell.getValue().foodItemProperty());

        TableColumn<FoodOrder, Integer> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(cell -> cell.getValue().quantityProperty().asObject());

        TableColumn<FoodOrder, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(cell -> cell.getValue().priceProperty().asObject());

        TableColumn<FoodOrder, Double> totalCol = new TableColumn<>("Total");
        totalCol.setCellValueFactory(cell -> cell.getValue().totalProperty().asObject());

        table.getColumns().addAll(nameCol, foodItemCol, quantityCol, priceCol, totalCol);
        table.setItems(orders);
        loadOrders();

        Button addButton = new Button("Add Order");
        addButton.setOnAction(e -> addOrder());

        Button deleteButton = new Button("Delete Order");
        deleteButton.setOnAction(e -> deleteOrder());

        VBox vbox = new VBox(nameField, foodItemField, quantityField, priceField, addButton, deleteButton, table);
        vbox.setSpacing(10);

        stage.setScene(new Scene(vbox, 600, 500));
        stage.setTitle("Food Order Management");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
