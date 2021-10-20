package com.easycook.app.presentation;

import com.easycook.app.controllers.RecipeController;
import com.easycook.app.entities.IngredientRecipe;
import com.easycook.app.entities.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SearchByAmountPeopleController {

    @FXML
    private Label Amount1;

    @FXML
    private Label Amount2;

    @FXML
    private Label Amount3;

    @FXML
    private Label CookingTime;

    @FXML
    private Label CookingTime2;

    @FXML
    private Label CookingTime3;

    @FXML
    private ImageView ImageRecipe1;

    @FXML
    private ImageView ImageRecipe2;

    @FXML
    private ImageView ImageRecipe3;

    @FXML
    private Label Ingrediemt11;

    @FXML
    private Label Ingrediemt21;

    @FXML
    private Label Ingrediemt31;

    @FXML
    private Button SearchButton;

    @FXML
    private Button nextButton;

    @FXML
    private Label recipeTitle1;

    @FXML
    private Label recipeTitle2;

    @FXML
    private Label recipeTitle3;

    @FXML
    private Button returnButton;

    @FXML
    private AnchorPane txtIngredient12;

    @FXML
    private TextField txtamount;

    @FXML
    void NextPage(ActionEvent event) {

    }

    @FXML
    void Return(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("searchMenu.fxml"));
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            SearchMenuController controller = loader.getController();
            loader.setController(controller);
            stage.show();
        } catch (IOException e) {
            System.err.printf("Error: %s%n", e.getMessage());
        }
    }

    @FXML
    void SearchRecipe(ActionEvent event) {
        RecipeController recipeController = new RecipeController();
        List<Recipe> recipes = recipeController.findByAmount(Integer.parseInt(txtamount.getText()));
        Image recipe1;

        if (recipes.size() == 1) {

            recipeTitle1.setText(recipes.get(0).getName());
            CookingTime.setText(String.valueOf(recipes.get(0).getCookingTime()) + "minutos");
            Amount1.setText(recipes.get(0).getAmountPeople() + "personas");

            String ingredients1 = "";

            for (IngredientRecipe ing : recipes.get(0).getIngredients()) {
                ingredients1 += String.format("%s\n", ing.getName());
            }
            Ingrediemt11.setText(ingredients1);

            recipe1 = new Image(recipes.get(0).getUrlImage());
            ImageRecipe1.setImage(recipe1);

        } else if ((recipes.size() == 2)) {
            recipeTitle1.setText(recipes.get(0).getName());
            CookingTime.setText(String.valueOf(recipes.get(0).getCookingTime()) + "minutos");
            Amount1.setText(recipes.get(0).getAmountPeople() + "personas");

            String ingredients1 = "";

            for (IngredientRecipe ing : recipes.get(0).getIngredients()) {
                ingredients1 += String.format("%s\n", ing.getName());
            }

            Ingrediemt11.setText(ingredients1);

            recipeTitle2.setText(recipes.get(1).getName());
            CookingTime2.setText(String.valueOf(recipes.get(1).getCookingTime()) + "minutos");
            Amount2.setText(recipes.get(1).getAmountPeople() + "personas");

            String ingredients2 = "";

            for (IngredientRecipe ing : recipes.get(1).getIngredients()) {
                ingredients2 += String.format("%s\n", ing.getName());
            }

            Ingrediemt21.setText(ingredients2);


            recipe1 = new Image(recipes.get(0).getUrlImage());
            ImageRecipe1.setImage(recipe1);
            Image recipe2 = new Image(recipes.get(1).getUrlImage());
            ImageRecipe2.setImage(recipe2);
        } else if ((recipes.size() >= 3)) {

            recipeTitle1.setText(recipes.get(0).getName());
            CookingTime.setText(String.valueOf(recipes.get(0).getCookingTime()) + " minutos");
            Amount1.setText(recipes.get(0).getAmountPeople() + " personas");

            StringBuilder ingredients1 = new StringBuilder();

            for (IngredientRecipe ing : recipes.get(0).getIngredients()) {
                ingredients1.append(String.format("%s\n", ing.getName()));
            }

            Ingrediemt11.setText(ingredients1.toString());

            recipeTitle2.setText(recipes.get(1).getName());
            CookingTime2.setText(String.valueOf(recipes.get(1).getCookingTime()) + "minutos");
            Amount2.setText(recipes.get(1).getAmountPeople() + "personas");

            StringBuilder ingredients2 = new StringBuilder();

            for (IngredientRecipe ing : recipes.get(1).getIngredients()) {
                ingredients2.append(String.format("%s\n", ing.getName()));
            }

            Ingrediemt21.setText(ingredients2.toString());

            recipeTitle3.setText(recipes.get(2).getName());
            CookingTime3.setText(String.valueOf(recipes.get(2).getCookingTime()) + "minutos");
            Amount3.setText(recipes.get(2).getAmountPeople() + "personas");

            StringBuilder ingredients3 = new StringBuilder();

            for (IngredientRecipe ing : recipes.get(2).getIngredients()) {
                ingredients3.append(String.format("%s\n", ing.getName()));
            }

            Ingrediemt31.setText(ingredients3.toString());


            recipe1 = new Image(recipes.get(0).getUrlImage());
            ImageRecipe1.setImage(recipe1);
            Image recipe2 = new Image(recipes.get(1).getUrlImage());
            ImageRecipe2.setImage(recipe2);
            Image recipe3 = new Image(recipes.get(2).getUrlImage());
            ImageRecipe3.setImage(recipe3);


        }
    }

}
