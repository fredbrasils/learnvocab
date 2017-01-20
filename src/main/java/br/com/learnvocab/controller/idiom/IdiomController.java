package br.com.learnvocab.controller.idiom;

import br.com.learnvocab.controller.BaseController;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.service.IdiomServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import br.com.learnvocab.util.LearnVocabMessage;
import br.com.learnvocab.validation.IdiomValidation;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/idiom")
public class IdiomController extends BaseController{

    private static final String PATH = "idiom/";
    
    @Autowired
    private IdiomServiceImpl  idiomService;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        // validate the object idiom     
        binder.addValidators(new IdiomValidation()); 
    }           
    
    /* Register an idiom */
    @RequestMapping(method=RequestMethod.POST)    
    public void registerIdiom(@Valid Idiom idiom, BindingResult result, HttpServletResponse response) throws IOException, Exception{
                  
        try{   
            // validate idiom's field
            validate(result); 
            idiom.setDateRegister(Calendar.getInstance());
            
            // save idiom
            idiomService.save(idiom); 
            
            // send message of success
            addMessage(LearnVocabMessage.SUCCESS,"idiom.msg.registed"); 
            
            // return response to ajax
            responseAjax(response, idiom); 
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(LearnVocabMessage.ERROR,ex.getMessage()); 
            
            // return response to ajax
            responseAjax(response, null); 
        }
    }    
    
    /* List idioms */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView showIdiomList(){
        
        ModelAndView mv = new ModelAndView(PATH + "idiom-list");
        
        // get all idioms
        mv.addObject("idioms", idiomService.findAll());
        return mv;
    }
    
    /* Delete idiom */
    @RequestMapping(value="deleteidiom/{id}", method=RequestMethod.POST)
    public void deleteIdiom(@PathVariable Long id, HttpServletResponse response) throws IOException {                        
        
        try{   
            // clear the cache of messages
            clearMessages(); 
            
            // remove idiom by id
            idiomService.delete(id);
            
            // send message of success
            addMessage(LearnVocabMessage.SUCCESS,"idiom.msg.deleted"); 
            
            // return response to ajax
            responseAjax(response, null);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(LearnVocabMessage.ERROR,ex.getMessage());
            
            // return response to ajax
            responseAjax(response, null); 
        }
        
    }
    
    /* Update the idiom */
    @RequestMapping(value="updateidiom",method=RequestMethod.POST)    
    public void updateIdiom(@Valid Idiom idiom, BindingResult result, HttpServletResponse response) throws IOException, Exception{    
        
        try{   
            // validate idiom's field
            validate(result);
            idiom.setDateRegister(Calendar.getInstance());
            
            // update idiom
            idiomService.update(idiom);
            
            // send message of success
            addMessage(LearnVocabMessage.SUCCESS,"idiom.msg.updated");
            
            // return response to ajax
            responseAjax(response, idiom);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(LearnVocabMessage.ERROR,ex.getMessage());
            
            // return response to ajax
            responseAjax(response, null);
        }
        
    } 
}
