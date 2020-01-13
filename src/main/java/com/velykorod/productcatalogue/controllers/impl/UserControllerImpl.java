package com.velykorod.productcatalogue.controllers.impl;

import com.velykorod.productcatalogue.controllers.UserController;
import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
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
        User user = new User();
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
    public ModelAndView register(@ModelAttribute("user") @Valid User user, BindingResult bindingResult, WebRequest webRequest, Errors errors) {
        if(!bindingResult.hasErrors())
        {
            userService.registerUser(user);
        }
        if(user==null){
            bindingResult.reject("email", "message.regError");
        }
        if(bindingResult.hasErrors()){
            return new ModelAndView("signup", "user", user);
        } else {
            return new ModelAndView("catalog", "user", user);
        }
    }




    @GetMapping("/login")
    @Override
    public String login(String login, String password) {
        return "redirect:/catalog";
    }
}
