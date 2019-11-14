package com.velykorod.productcatalogue.persistance.domain.impl;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table(name="category")
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    Long Id;

    @Column
    String name;

    @OneToMany(mappedBy = "category", cascade=CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER)
    Set<Product> products;

    public Category() {

    }

    public void setId(Long id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (Id != null ? !Id.equals(category.Id) : category.Id != null) return false;
        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        int result = Id != null ? Id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
