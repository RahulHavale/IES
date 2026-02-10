package com.ies.controllers;

import com.ies.dtos.LoginForm;
import com.ies.dtos.RegisterForm;
import com.ies.dtos.UnlockForm;
import com.ies.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login(Model model, LoginForm loginForm){
        model.addAttribute("loginForm", loginForm);
        return "login";
    }

    @PostMapping("/")
    public String login(@ModelAttribute("loginForm") LoginForm form, Model model){
        boolean status = userService.login(form);
        if(status){
            model.addAttribute("succMsg","Successfully logged in");
        }else{
            model.addAttribute("errMsg","Wrong Credentials");
        }
        return "login";
    }

    @GetMapping("/register")
    public String createAccount(Model model, RegisterForm registerForm){
        model.addAttribute("registerForm", registerForm);
        return "createAccount";
    }

    @PostMapping("/register")
    public String createAccount(@ModelAttribute("registerForm") RegisterForm registerForm, Model model){
        boolean status = userService.register(registerForm);
        if(status){
            model.addAttribute("succMsg","Successfully registered");
        }else{
            model.addAttribute("errMsg","Email already existed, user unique email to create new ACC");
            return "createAccount";
        }
        return "createAccount";
    }

    @GetMapping("/unlock")
    public String unlockAccount(@RequestParam String email, Model model){
        UnlockForm unlockFormObj = new UnlockForm();
        unlockFormObj.setEmail(email);
        model.addAttribute("unlockForm",unlockFormObj);
        return "unlock_P";
    }

    @PostMapping("/unlock")
    public String unlockAccount(@ModelAttribute("unlockForm") UnlockForm form, Model model){
        boolean status = userService.unlockAccount(form);
        if(status){
            model.addAttribute("succMsg","New Password Saved Successfully");
        }else{
            model.addAttribute("errMsg","Temporary Password is Wrong");
            return "createAccount";
        }
        return "unlock_P";
    }
}
