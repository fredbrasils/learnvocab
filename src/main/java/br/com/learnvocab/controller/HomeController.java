package br.com.learnvocab.controller;

import br.com.learnvocab.entity.Role;
import br.com.learnvocab.entity.User;
import br.com.learnvocab.entity.enums.WordPriority;
import br.com.learnvocab.service.UserService;
import br.com.learnvocab.service.UserServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import java.util.Arrays;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class HomeController {

    @Autowired
    private UserServiceImpl userService;
    
    @RequestMapping("/")
    public ModelAndView home() throws Exception{
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("priorities", WordPriority.values());
        return mv;
    }
    
    @RequestMapping("/logar")
    public ModelAndView logar() throws Exception{
        ModelAndView mv = new ModelAndView("loginForm");
        return mv;
    }

    /* page not found */
    @RequestMapping("/{path}")
    public void home(@PathVariable("path") String path) throws Exception{        
        if(path != null || path.length() > 0) {
            throw new Exception();
        }        
    }

        
    @Transactional
    @ResponseBody
    @RequestMapping("/url-magica-maluca-oajksfbvad6584i57j54f9684nvi658efnoewfmnvowefnoeijn")
    public String urlMagicaMaluca() throws LearnVocabException {
            User user = new User(); 
            user.setName("Fred");
            user.setEmail("fred@learnvocab.com.br");
            user.setPassword("123456");
            user.setRoles(Arrays.asList(new Role("ROLE_ADMIN")));
            user.setDateRegister(Calendar.getInstance());

            userService.save(user);

            return "Url Mágica executada";
    }
}
