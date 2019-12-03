package com.velykorod.productcatalogue.persistance.domain.impl;

import lombok.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private Date dateCreated;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column
    private String imgName;

    @Column
    private String fileName;


    public Product(Long id, String name, String description, Date dateCreated) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
    }

    public Product(String name, String description, Date dateCreated, Category category) {
        this.name = name;
        this.description = description;
        this.dateCreated = dateCreated;
        this.category = category;
    }

    public Product() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public Category getCategory() {
        return category;
    }

    public String getImgName() {
        return imgName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != null ? !id.equals(product.id) : product.id != null) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        if (description != null ? !description.equals(product.description) : product.description != null) return false;
        return dateCreated != null ? dateCreated.equals(product.dateCreated) : product.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }
}
