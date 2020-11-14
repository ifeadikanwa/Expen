package com.example.expen.model_classes;

public class Categories {
    private String categoryName;
    private String categoryBudget;
    private boolean hasContent;

    public Categories(){}

    public Categories(String categoryName, String categoryBudget, boolean hasContent) {
        this.categoryName = categoryName;
        this.categoryBudget = categoryBudget;
        this.hasContent = hasContent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryBudget() {
        return categoryBudget;
    }

    public void setCategoryBudget(String categoryBudget) {
        this.categoryBudget = categoryBudget;
    }


    public boolean hasContent() {
        return hasContent;
    }

    public void setHasContent(boolean hasContent) {
        this.hasContent = hasContent;
    }
}
