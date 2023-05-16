package com.example.inyange.controller;

import com.example.inyange.model.User;
import com.example.inyange.serviceImplementation.UserServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    UserServiceImplementation service;

    @GetMapping("/signup")
    public String getSignupPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("pageTitle","signupPage");
        return "signupPage";
    }

    @PostMapping("/signup/save")
    public String saveSignup(User user, RedirectAttributes ra) {
        User saved = service.saveUser(user);

        if (saved != null) {
            ra.addFlashAttribute("message", "saved successfully");


        } else {
            ra.addFlashAttribute("message", "failed, try again");
        }
        user.setUsername("");
        user.setEmail("");
        user.setPassword("");


        return "signupPage";

    }

    @GetMapping("/user/login")
    public String showLoginPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "loginPage";
    }

//    @PostMapping("/user/login")
//    public String tryLogin(String user, RedirectAttributes ra) {
//        User foundUser = service.findUserUsername(user);
//        if (foundUser != null) {
//            if (foundUser.getUsername().equals("admin")) {
//                if (foundUser.getPassword().equals("07288")) {
//
//                    return "UsersPage";
//
//
//                }
//                else {
//                    ra.addFlashAttribute("message", "invalid password");
//                }
//
//
//            }else {
//                return "homePage";
//            }
//        }
//        return "";
//    }





    @GetMapping("/users")
    public String displayUsers(Model model){
        List<User> listUsers = service.displayUsers();
        model.addAttribute("listUsers", listUsers);

        return "UsersPage";
    }

    @GetMapping("/user/edit/{id}")
    public String editUser(Model model, @PathVariable("id") int id,RedirectAttributes ra){
       try {
           User updatedUser = service.findUserById(id);
           model.addAttribute("user", updatedUser);
           model.addAttribute("pageTitle", "edit User (ID: " + id + ")");
           return "signupPage";
       }catch (Exception ex){
           ex.printStackTrace();
       }
       return "redirect:/users";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try {
            service.deleteUser(id);
            ra.addFlashAttribute("message","User deleted successfully");
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return "redirect:/users";
    }
    @PostMapping("/login/user")
    public String userLogin( User username, Model mode){
        User user = service.findUserUsername( username);
        mode.addAttribute("user", user);
        if (user!=null){
            if (user.getUsername().equals("admin")){
                return "homePage";
            }else {
                return "redirect:/user/login";
            }
        }
        return "redirect:/signup";
    }
}