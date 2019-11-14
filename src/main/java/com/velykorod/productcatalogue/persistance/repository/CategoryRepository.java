package com.velykorod.productcatalogue.persistance.repository;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    void deleteById(Long id);

    @Modifying
    @Transactional
    @Query("update Category c set c.name =:name where c.id =:id")
    void updateCategory(@Param("name") String name, @Param("id") Long id);
}
