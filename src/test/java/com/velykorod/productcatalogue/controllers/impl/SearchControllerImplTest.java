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
public class SearchControllerImplTest {

    @Mock
    ProductServiceImpl productService;

    @InjectMocks
    SearchControllerImpl searchController;

    MockMvc mock;


    @Before
    public void setup(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates");
        viewResolver.setSuffix(".html");
        mock = MockMvcBuilders.standaloneSetup(searchController).setViewResolvers(viewResolver).build();
    }

    private String productName = "Test Product";
    private String productDescription = "Test Description";
    private Date productDate= new Date();
    private Product product = new Product(productName, productDescription, productDate);
    private Product otherProduct = new Product(1L, "Other Product", "Some description", productDate);
    private List<Product> allProducts = Arrays.asList(product, otherProduct);


    @Test
    public void testFindByName() throws Exception {
        Mockito.when(productService.findByName(Mockito.anyString())).thenReturn(allProducts);

        mock.perform(MockMvcRequestBuilders.get("/search").param("search", "name"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("catalog"))
                .andExpect(MockMvcResultMatchers.model().attribute("products", allProducts));
    }

}

