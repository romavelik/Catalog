//package com.velykorod.productcatalogue.service.impl;
//
//
//import com.velykorod.productcatalogue.persistance.domain.impl.Category;
//import com.velykorod.productcatalogue.persistance.repository.CategoryRepository;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.runners.MockitoJUnitRunner;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
//@RunWith(MockitoJUnitRunner.class)
//public class CategoryServiceImplTest {
//
//    @InjectMocks
//    private CategoryServiceImpl categoryService;
//
//    @Mock
//    private CategoryRepository categoryRepository;
//
//    private Category category = new Category();
//    private Category otherCategory = new Category();
//    private List<Category> categories = Arrays.asList(category, otherCategory);
//
//    @Test
//    public void findAll() {
//        category.setId(1L);
//        category.setName("category");
//        otherCategory.setId(2L);
//        otherCategory.setName("other");
//        Mockito.when(categoryRepository.findAll()).thenReturn(categories);
//
//        List <Category> categoryList = (List)categoryService.findAll();
//        assertNotNull(categoryList);
//        assertEquals("category", categoryList.get(0).getName());
//        assertEquals("other", categoryList.get(1).getName());
//    }
//
//    @Test
//    public void findById() {
//        category.setName("category");
//        Mockito.when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
//
//        Category foundCategory = categoryService.findById(1L);
//        assertNotNull(foundCategory);
//        assertEquals("category", foundCategory.getName());
//    }
//
//    @Test
//    public void addCategory() {
//        categoryService.addCategory(category);
//        Mockito.verify(categoryRepository, Mockito.times(1)).save(category);
//    }
//
//    @Test
//    public void deleteCategory() {
//        categoryService.deleteCategory(1L);
//        Mockito.verify(categoryRepository, Mockito.times(1)).deleteById(1L);
//    }
//
//    @Test
//    public void editCategory() {
//        otherCategory.setId(1L);
//        otherCategory.setName("other");
//        categoryService.editCategory(otherCategory);
//        Mockito.verify(categoryRepository, Mockito.times(1)).updateCategory(otherCategory.getName(), otherCategory.getId());
//    }
//}
