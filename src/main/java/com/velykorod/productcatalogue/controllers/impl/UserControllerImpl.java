package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.UserController;
import com.velykorod.productcatalogue.exceptions.EmailExistsException;
import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
import com.velykorod.productcatalogue.persistance.domain.impl.user.UserDto;
import com.velykorod.productcatalogue.service.UserService;
import com.velykorod.productcatalogue.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UserControllerImpl implements UserController {
    @Autowired
    UserService userService;

    @GetMapping("/signup")
    @Override
    public String signup(WebRequest webRequest, Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "signup";
    }

    @GetMapping("/signin")
    @Override
    public String signin() {
        return "signin";
    }

    @PostMapping("/register")
    @Override
    public ModelAndView register(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult, WebRequest webRequest, Errors errors) {
        User user = new User();
        if(!bindingResult.hasErrors())
        {
            user = createUserAccount(userDto, bindingResult);
        }
        if(user==null){
            bindingResult.reject("email", "message.regError");
        }
        if(bindingResult.hasErrors()){
            return new ModelAndView("signup", "user", userDto);
        } else {
            return new ModelAndView("catalog", "user", userDto);
        }
    }

    private User createUserAccount(UserDto accountDto, BindingResult result) {
        User registered = null;
        try {
            registered = userService.registerUser(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        return registered;
    }




    @GetMapping("/login")
    @Override
    public String login(String login, String password) {
        return "redirect:/catalog";
    }
}
