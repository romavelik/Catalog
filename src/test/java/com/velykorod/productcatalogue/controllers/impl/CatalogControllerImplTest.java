package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class CatalogControllerImplTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    CatalogControllerImpl catalogController;

    MockMvc mock;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates");
        viewResolver.setSuffix(".html");
        mock = MockMvcBuilders.standaloneSetup(catalogController).setViewResolvers(viewResolver).build();
    }

    private String productName = "Test Product";
    private String productDescription = "Test Description";
    private Date productDate = new Date();
    private Product product = new Product(productName, productDescription, productDate);
    private Product otherProduct = new Product(1L, "Other Product", "Some description", productDate);
    private List<Product> allProducts = Arrays.asList(product, otherProduct);


    @Test
    public void testGetCatalog() throws Exception {
        Mockito.when(productService.findAll()).thenReturn(allProducts);

        mock.perform(MockMvcRequestBuilders.get("/catalog"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("catalog"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", allProducts));
    }

    @Test
    public void testProductDetails() throws Exception {
        Mockito.when(productService.findById(Mockito.anyLong())).thenReturn(otherProduct);

        mock.perform(MockMvcRequestBuilders.get("/catalog/product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("product"))
                .andExpect(MockMvcResultMatchers.model().attribute("product", otherProduct));
    }

    @Test
    public void testNullProduct() throws Exception {
        Mockito.when(productService.findById(Mockito.anyLong())).thenReturn(null);

        mock.perform(MockMvcRequestBuilders.get("/catalog/product/1"))
                .andExpect(MockMvcResultMatchers.view().name("error"));
    }
}
