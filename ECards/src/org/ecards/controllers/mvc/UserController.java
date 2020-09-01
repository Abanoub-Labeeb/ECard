package org.ecards.controllers.mvc;

import org.ecards.entities.Authority;
import org.ecards.entities.User;
import org.ecards.entities.FKs.AuthorityID;
import org.ecards.entities.validation.UserValidator;
import org.ecards.service.ISecurityService;
import org.ecards.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
	@Autowired
    private IUserService userService;

    @Autowired
    private ISecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "Registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "Registration";
        }

        /*We will set the authority to ROLE_USER as this is user regesteration form*/
        Authority userAuthority = new Authority(new AuthorityID(userForm.getUsername(), "ROLE_USER"));
        
        userForm.setEnabled(true);
        
        userService.saveUser(userForm);
        userService.saveAuthority(userAuthority); 
        
        securityService.autoLogin(userForm.getUsername(), userForm.getPassword());

        return "redirect:/card";
    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "Login";
    }

}
