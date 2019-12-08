package com.velykorod.productcatalogue.persistance.repository;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String name);

    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update Product p set p.name =:name, p.description =:description, p.price =:price where p.id =:id")
    void updateProduct(@Param("name") String name, @Param("description") String description, @Param("id") Long id, @Param("price") BigDecimal price);
}
