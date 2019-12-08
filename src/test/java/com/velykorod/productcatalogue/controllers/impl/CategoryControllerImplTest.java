//package com.velykorod.productcatalogue.controllers.impl;
//
//
//import com.velykorod.productcatalogue.persistance.domain.impl.Category;
//import com.velykorod.productcatalogue.service.impl.CategoryServiceImpl;
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
//@RunWith(MockitoJUnitRunner.class)
//@WebMvcTest
//public class CategoryControllerImplTest {
//    @Mock
//    CategoryServiceImpl categoryService;
//
//    @InjectMocks
//    CategoryControllerImpl categoryController;
//
//    MockMvc mock;
//
//    @Before
//    public void setup() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("/resources/templates");
//        viewResolver.setSuffix(".html");
//        mock = MockMvcBuilders.standaloneSetup(categoryController)
//                .setViewResolvers(viewResolver).build();
//    }
//@Test
//    public void addCategory() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/add_category")
//                .param("name", "test"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//
//    @Test
//    public void createCategory() throws Exception {
//        mock.perform(MockMvcRequestBuilders.get("/create_category"))
//                .andExpect(MockMvcResultMatchers.view().name("create_category"))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//    @Test
//    public void deleteCategory() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/delete_category")
//                .param("id", "1"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//
//    @Test
//    public void editCategory() throws Exception {
//        Mockito.when(categoryService.findById(1L)).thenReturn(new Category());
//
//        mock.perform(MockMvcRequestBuilders.get("/edit_category/1"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("edit_category"));
//    }
//
//    @Test
//    public void updateCategory() throws Exception {
//        mock.perform(MockMvcRequestBuilders.post("/update_category")
//                .param("id", "1")
//                .param("name", "test"))
//                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
//                .andExpect(MockMvcResultMatchers.view().name("redirect:/catalog"));
//    }
//}
