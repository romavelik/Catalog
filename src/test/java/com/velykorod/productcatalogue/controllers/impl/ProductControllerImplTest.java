package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.persistance.domain.impl.Category;
import com.velykorod.productcatalogue.persistance.domain.impl.Product;
import com.velykorod.productcatalogue.service.impl.CategoryServiceImpl;
import com.velykorod.productcatalogue.service.impl.ProductServiceImpl;
import com.velykorod.productcatalogue.service.impl.StorageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest
public class ProductControllerImplTest {
    @Mock
    StorageServiceImpl storageService;

    @Mock
    ProductServiceImpl productService;

    @Mock
    CategoryServiceImpl categoryService;

    @InjectMocks
    ProductControllerImpl productController;

    MockMvc mock;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/resources/templates");
        viewResolver.setSuffix(".html");
        mock = MockMvcBuilders.standaloneSetup(productController)
                .setViewResolvers(viewResolver).build();
    }

    private String productName = "Test Product";
    private String productDescription = "Test Description";
    private Date productDate = new Date();
    private Product product = new Product(productName, productDescription, productDate, new Category());

    @Test
    public void testAddProduct() throws Exception {
        File file = new File("D:/projects/programming/test_stor/1.jpg");
        String name = "image";
        String originalName = "1.jpg";
        byte[] content = file.getPath().getBytes();
        MockMultipartFile multipartFile = new MockMultipartFile(name, originalName, MediaType.MULTIPART_FORM_DATA_VALUE, content);
        Mockito.when(categoryService.findById(1L)).thenReturn(new Category());

        mock.perform(MockMvcRequestBuilders.multipart("/add_product")
                    .file(multipartFile)
                    .param("name", "test")
                    .param("description", "test")
                    .param("categoryId", "1")
                    .param("price", "12"))
                    .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                    .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));

    }

    @Test
    public void testCreateProduct() throws Exception {
        Mockito.when(categoryService.findAll()).thenReturn(Arrays.asList(new Category()));

        mock.perform(MockMvcRequestBuilders.get("/create_product"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("create_product"));
    }

    @Test
    public void testDeleteProduct() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/delete_product")
                .param("id", "1")
                .param("name", "test"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        mock.perform(MockMvcRequestBuilders.get("/update_product/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("update_product"));
    }

    @Test
    public void testEditProduct() throws Exception {
        mock.perform(MockMvcRequestBuilders.post("/update")
                .param("id", "1")
                .param("name", "test")
                .param("description", "test")
                .param("price", "12")
                .param("oldName", "previous"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
    }
}