package br.com.learnvocab.controller.user;

import br.com.learnvocab.controller.BaseController;
import br.com.learnvocab.entity.User;
import br.com.learnvocab.service.RoleServiceImpl;
import br.com.learnvocab.service.UserServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import br.com.learnvocab.validation.UserValidation;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static final String PATH = "user/";
    
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private RoleServiceImpl roleService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        // validate the object user
        binder.addValidators(new UserValidation());
    }    
    
    /* List users */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView showUserList() throws Exception{
        
        ModelAndView mv = new ModelAndView(PATH + "user-list");
        
        // get all users
        mv.addObject("users", userService.findAll());
        return mv;
    }
    
    /* Return the user's form */
    @RequestMapping(value="formuser", method=RequestMethod.GET)
    public ModelAndView formUser(User user){
        
        ModelAndView mv = new ModelAndView(PATH + "user-form");
        mv.addObject("user", user);
        
        // get all roles
        mv.addObject("roles", roleService.findAll());
        return mv;
    }
     
    /* Register an user */
    @RequestMapping(value = "formuser", method=RequestMethod.POST)
    public void registerUser(@Valid User user, BindingResult result, HttpServletResponse response) throws IOException, Exception{
                  
        try{   
            // validate idiom's field
            validate(result);
            user.setDateRegister(Calendar.getInstance());          
            
            if(user.getId() == null){
                // save user
                userService.save(user);
            }else{
                // save user
                userService.update(user);                
            }
            
            // send message of success
            addMessage("user.msg.registed");
            
            // return response to ajax
            responseAjax(response,null);
            
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null);
        }
    }
    
    @RequestMapping(value="deleteuser/{id}", method=RequestMethod.POST)
    public void deleteIdiom(@PathVariable Long id, HttpServletResponse response) throws IOException {                        
        
        try{   
            
            // clear the cache of messages
            clearMessages();
            
            // delete user
            userService.delete(id);
            
            // send message of success
            addMessage("user.msg.deleted");
            
            // return response to ajax
            responseAjax(response, null);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null);
        }        
    }
    
    /* Edit user */
    @RequestMapping(value="edituser/{id}", method=RequestMethod.GET)
    public ModelAndView editUser(@PathVariable Long id) throws Exception{
        
        User user = userService.find(id);
        user.setPassword(null);
        return formUser(user);        
    }
}
