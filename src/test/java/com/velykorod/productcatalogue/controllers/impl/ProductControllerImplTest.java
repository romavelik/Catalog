//package com.velykorod.productcatalogue.controllers.impl;
//
//import com.velykorod.productcatalogue.persistance.domain.impl.Category;
//import com.velykorod.productcatalogue.persistance.domain.impl.Product;
//import com.velykorod.productcatalogue.service.impl.CategoryServiceImpl;
//import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.servlet.view.InternalResourceViewResolver;
//
//import java.util.Arrays;
//import java.util.Date;
//
//
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest
//public class ProductControllerImplTest {
//    @Mock
//    ProductServiceImpl productService;
//
//    @Mock
//    CategoryServiceImpl categoryService;
//
//    @InjectMocks
//    ProductControllerImpl productController;
//
//    MockMvc mock;
//
//    @Before
//    public void setup() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/resources/templates");
//        viewResolver.setSuffix(".html");
//        mock = MockMvcBuilders.standaloneSetup(productController)
//                .setViewResolvers(viewResolver).build();
//    }
//
//    private String productName = "Test Product";
//    private String productDescription = "Test Description";
//    private Date productDate = new Date();
//    private Product product = new Product(productName, productDescription, productDate, new Category());
//
//    @Test
//    public void testAddProduct() throws Exception {
//        Mockito.when(categoryService.findById(1L)).thenReturn(new Category());
//
//        mock.perform(MockMvcRequestBuilders.post("/add_product")
//                .param("name", "test")
//                .param("description", "test")
//                .param("categoryId", "1"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//
//    @Test
//    public void testCreateProduct() throws Exception {
//        Mockito.when(categoryService.findAll()).thenReturn(Arrays.asList(new Category()));
//
//        mock.perform(MockMvcRequestBuilders.get("/create_product"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("create_product"));
//    }
//
//    @Test
//    public void testDeleteProduct() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/delete_product")
//                .param("id", "1"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//
//    @Test
//    public void testUpdateProduct() throws Exception {
//        mock.perform(MockMvcRequestBuilders.get("/update_product/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("update_product"));
//    }
//
//    @Test
//    public void testEditProduct() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/update")
//                .param("id", "1")
//                .param("name", "test")
//                .param("description", "test"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//}