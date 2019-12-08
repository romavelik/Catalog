package com.velykorod.productcatalogue.persistance.domain;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;

import javax.persistence.*;

@Entity
public abstract class MediaFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String fileName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    public MediaFile(String fileName, Product product) {
        this.fileName = fileName;
        this.product = product;
    }

    public MediaFile() {
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public Product getProduct() {
        return product;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
