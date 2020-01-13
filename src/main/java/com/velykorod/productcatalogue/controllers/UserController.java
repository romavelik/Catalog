package com.velykorod.productcatalogue.controllers;

import com.velykorod.productcatalogue.persistance.domain.impl.user.User;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebResult;

public interface UserController {
    String signup(WebRequest webRequest, Model model);
    String signin();
    ModelAndView register(User user, BindingResult bindingResult, WebRequest webRequest, Errors errors);
    String login(String login, String password);
}
