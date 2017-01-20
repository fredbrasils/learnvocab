package br.com.learnvocab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @RequestMapping(value="/login", method=RequestMethod.GET)
    public ModelAndView loginForm(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout) {
        
        ModelAndView mv = new ModelAndView ("loginForm");
        
        if (error != null) {
            mv.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            mv.addObject("msg", "You've been logged out successfully.");
        }
        
        return mv;
    }    
    
    /*
    @RequestMapping(value="/logout", method=RequestMethod.GET)
    public String logout() {
            return "redirect:/login?logout";
    }
    */
}
