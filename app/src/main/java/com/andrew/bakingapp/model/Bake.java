package com.andrew.bakingapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Bake {

    private int id;
    private String name;
    private int servings;
    private String image;

    @SerializedName("ingredients")
    private List<Ingredient> ingredientList;

    @SerializedName("steps")
    private List<Step> stepList;

    public Bake() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    public List<Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<Step> getStepList() {
        return stepList;
    }

    public static class Ingredient{
        private float quantity;
        private String measure;
        private String ingredient;

        public Ingredient() {
        }

        public float getQuantity() {
            return quantity;
        }

        public String getMeasure() {
            return measure;
        }

        public String getIngredient() {
            return ingredient;
        }
    }

    public static class Step{
        private int id;
        private String shortDescription;
        private String description;
        private String videoURL;
        private String thumbnailURL;

        public Step() {
        }

        public int getId() {
            return id;
        }

        public String getShortDescription() {
            return shortDescription;
        }

        public String getDescription() {
            return description;
        }

        public String getVideoURL() {
            return videoURL;
        }

        public String getThumbnailURL() {
            return thumbnailURL;
        }
    }
}
