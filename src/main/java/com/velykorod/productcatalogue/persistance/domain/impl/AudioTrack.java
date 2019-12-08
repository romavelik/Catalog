package com.velykorod.productcatalogue.persistance.domain.impl;

import com.velykorod.productcatalogue.persistance.domain.MediaFile;

import javax.persistence.*;

@Entity
@Table(name="audiotracks")
public class AudioTrack{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String fileName;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product product;

    public AudioTrack(String fileName, Product product) {
        this.fileName = fileName;
        this.product = product;
    }
    public AudioTrack() {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AudioTrack that = (AudioTrack) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return fileName != null ? fileName.equals(that.fileName) : that.fileName == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (fileName != null ? fileName.hashCode() : 0);
        return result;
    }
}
