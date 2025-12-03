package com.backend.cartapp.models.dto;

public class ProductRequest {
    private String name;
    private String description;
    private Long price;
    private String category;
    private Boolean featured;

    public String getName(){return name;}
    public void setName(String n){this.name=n;}

    public String getDescription(){return description;}
    public void setDescription(String d){this.description=d;}

    public Long getPrice(){return price;}
    public void setPrice(Long p){this.price=p;}

    public Boolean getFeatured(){return featured;}
    public void setFeatured(Boolean f){this.featured = f;}
    public String getCategory(){return category;}
    public void setCategory(String c){this.category=c;}

}