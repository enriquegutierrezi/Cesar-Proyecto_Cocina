package com.easycook.app.presentation;


import com.easycook.app.controllers.RecipeController;
import com.easycook.app.entities.IngredientRecipe;
import com.easycook.app.entities.Recipe;
import com.easycook.app.exceptions.AlreadyExistException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CreateRecipeFormController {

    @FXML
    private Button CreateRecipeButton;

    @FXML
    private TextField txtAmount1;

    @FXML
    private TextField txtAmount2;

    @FXML
    private TextField txtAmount3;

    @FXML
    private TextField txtAmountPeople;

    @FXML
    private TextField txtCookingTime;

    @FXML
    private TextField txtIngredient1;

    @FXML
    private TextField txtIngredient2;

    @FXML
    private TextField txtIngredient3;

    @FXML
    private TextField txtNameRecipe;

    @FXML
    private TextField txtState1;

    @FXML
    private TextField txtState2;

    @FXML
    private TextField txtState3;

    @FXML
    private TextField txtUrl;
    @FXML
    private Button ReturnButton;

    @FXML
    void CreateRecipe(ActionEvent event) {
        RecipeController recipeController = new RecipeController();

        IngredientRecipe ingredient1 =
                !txtIngredient1.getText().trim().isEmpty() ?
                        new IngredientRecipe(txtIngredient1.getText(), txtState1.getText().charAt(0), Integer.parseInt(txtAmount1.getText())) :
                        null;
        IngredientRecipe ingredient2 =
                !txtIngredient2.getText().trim().isEmpty() ?
                        new IngredientRecipe(txtIngredient2.getText(), txtState2.getText().charAt(0), Integer.parseInt(txtAmount2.getText())) :
                        null;
        IngredientRecipe ingredient3 =
                !txtIngredient3.getText().trim().isEmpty() ?
                        new IngredientRecipe(txtIngredient3.getText(), txtState3.getText().charAt(0), Integer.parseInt(txtAmount3.getText())) :
                        null;

        List<IngredientRecipe> ingredientsList = new ArrayList<>();

        ingredientsList.add(ingredient1);
        ingredientsList.add(ingredient2);
        ingredientsList.add(ingredient3);

        ingredientsList = ingredientsList.stream().filter(Objects::nonNull).collect(Collectors.toList());

        Recipe recipe = new Recipe(recipeController.getNextId(), txtNameRecipe.getText(), Integer.parseInt(txtCookingTime.getText()), Integer.parseInt(txtAmountPeople.getText()), new ArrayList<>(ingredientsList), txtUrl.getText());
        Alert alert = new Alert(Alert.AlertType.NONE);
        try {
            recipeController.createRecipe(recipe);
            alert.setAlertType(Alert.AlertType.CONFIRMATION);
            alert.setContentText(String.format("La receta %s ha sido creada exitosamente", txtNameRecipe.getText()));
            alert.show();
        } catch (AlreadyExistException e) {
            alert.setAlertType(Alert.AlertType.ERROR);
            alert.setContentText(String.format("La receta %s ya existe", txtNameRecipe.getText()));
            alert.show();
        }
    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CreationsMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            CreationsMenuController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }
}
