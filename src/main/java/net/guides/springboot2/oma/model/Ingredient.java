/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.guides.springboot2.oma.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

/**
 *
 * @author Mari
 */
@Entity(name="Ingredient")
@Table(name="ingredient")
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class Ingredient implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    
    @NaturalId
    @Column(name = "ingredient_name", nullable=false)
    private String name;
    
    @OneToMany(
        mappedBy = "ingredient",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<RecipeIngredients> recipes = new ArrayList<>();
         
    @Column(name="serving_unit")
    private String servingUnit;
    
    @Column(name="number_of_units")
    private int numberOfUnits;
    
    @Column(name="calories")
    private int cals;
    
    @Column(name="carbs")
    private int carbs;
    
    @Column(name="sugar")
    private int sugar;
    
    @Column(name= "protein")
    private int protein;
    
    @Column(name="fat")
    private int fat;
    
    @Column(name="fiber")
    private int fiber;
    
    @Column(name="sodium")
    private int sodium;
    
    public Ingredient()  {
        
    }
    
    public Ingredient(String name) {
        this.name = name;
    }
    public Ingredient(String name, String unit, int numberOfUnits, int cals) {
        this.name = name;
        this.servingUnit = unit;
        this.numberOfUnits = numberOfUnits;
        this.cals = cals;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
     public void setName(String name) {
        this.name = name;
    }
     
    public List<RecipeIngredients> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<RecipeIngredients> recipes) {
        this.recipes = recipes;
    }
    
    public String getServingUnit() {
        return servingUnit;
    }
    
    public void setServingUnit(String unit) {
        this.servingUnit = unit;
    }
    
    public int getNumberOfUnits() {
        return numberOfUnits;
    }
    
    public void setNumberOfUnits(int number) {
        this.numberOfUnits = number;
    }
    
    public int getCals() {
        return cals;
    }
    
    public void setCals(int cals) {
        this.cals = cals;
    }
    
    public int getCars() {
        return carbs;
    }
    
    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }
    
    public int getSugar() {
        return sugar;
    }
    
    public void setSugar(int sugar) {
        this.sugar = sugar;
    }
    
    public int getProtein() {
        return protein;
    }
    
    public void setProtein(int protein) {
        this.protein = protein;
    }
    
    public int getFat() {
        return fat;
    }
    
    public void setFat(int fat) {
        this.fat = fat;
    }
    
    public int getFiber() {
        return fiber;
    }
    
    public void setFiber(int fiber) {
        this.fiber = fiber;
    }
    
    public int getSodium() {
        return sodium;
    }
    
    public void setSodium(int sodium) {
        this.sodium = sodium;
    }
    
    /*This methoid IS NOT ready!!!! */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient ingr = (Ingredient) o;
        return Objects.equals(name, ingr.name);
    }
 
    /*What does this mean???? */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
    
    @Override
    public String toString() {
        return "Ingredient [id=" + id + ", name=" + name + ", servingUnit=" + servingUnit + ", numberOfUnits=" + numberOfUnits
      + ", cals=" + cals+ ", carbs=" + carbs + ", sugar=" + sugar + ", protein=" + protein + ", fat=" + fat + ", fiber=" + fiber + ", sodium=" + sodium + "]";
    }

}
