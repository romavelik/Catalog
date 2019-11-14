package com.velykorod.productcatalogue.service.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.persistance.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private String productName = "Test Product";
    private String productDescription = "Test Description";
    private Date productDate= new Date();
    private Product product = new Product(productName, productDescription, productDate, new Category());
    private List<Product> similarNameProducts = Arrays.asList(product);
    private Product otherProduct = new Product(1L, "Other Product", "Some description", productDate);
    private List<Product> allProducts = Arrays.asList(product, otherProduct);

    @Test
    public void testFindByName(){
        Mockito.when(productRepository.findByName(Mockito.anyString())).thenReturn(similarNameProducts);

        List <Product> resultProducts = productService.findByName(productName);

        assertEquals(productName, resultProducts.get(0).getName());
        assertEquals(productDescription, resultProducts.get(0).getDescription());
        assertEquals(productDate, resultProducts.get(0).getDateCreated());
    }

    @Test
    public void testFindAll(){
        Mockito.when(productRepository.findAll()).thenReturn(allProducts);

        Iterable<Product> resultProducts = productService.findAll();

        assertEquals(productName, allProducts.get(0).getName());
        assertEquals("Some description", allProducts.get(1).getDescription());
        assertEquals("Other Product", allProducts.get(1).getName());
    }

    @Test
    public void testFindById() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(otherProduct));

        Product resultProduct = productService.findById(1L);

        assertNotNull(resultProduct);
        assertEquals("Other Product", resultProduct.getName());
        assertEquals("Some description", resultProduct.getDescription());
    }

    @Test
    public void testAddProduct(){
        productService.addProduct(product);
        Mockito.verify(productRepository, Mockito.times(1)).save(product);
    }

    @Test
    public void testEditProduct(){
        productService.editProduct(otherProduct);
        Mockito.verify(productRepository, Mockito.times(1)).updateProduct(otherProduct.getName(), otherProduct.getDescription(), otherProduct.getId());
    }

    @Test
    public void testDeleteProduct(){
        productService.deleteProduct(1L);
        Mockito.verify(productRepository, Mockito.times(1)).deleteById(1L);
    }


}
