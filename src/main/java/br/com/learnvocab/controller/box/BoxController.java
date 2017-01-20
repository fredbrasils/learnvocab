package br.com.learnvocab.controller.box;

import br.com.learnvocab.controller.BaseController;
import br.com.learnvocab.entity.Box;
import br.com.learnvocab.service.BoxServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import br.com.learnvocab.util.LearnVocabMessage;
import br.com.learnvocab.validation.BoxValidation;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
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
@RequestMapping("/box")
public class BoxController extends BaseController{

    private static final String PATH = "box/";
    
    @Autowired
    private BoxServiceImpl  boxService;
    
    private List<Box> boxes;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        // validate the object box     
        binder.addValidators(new BoxValidation()); 
    }           
    
    /* Register an box */
    @RequestMapping(method=RequestMethod.POST)    
    public void registerBox(@Valid Box box, BindingResult result, HttpServletResponse response) throws IOException, Exception{
                  
        try{   
            // validate box's field
            validate(result); 
            box.setDateRegister(Calendar.getInstance());
            
            // define box's order
            box.setOrder(getBoxes().size()+1);            
            
            // if name is null or empty the name will be the order
            if(box.getName() == null || box.getName().trim().isEmpty()){
                box.setName("#"+box.getOrder());
            }
            
            // save box
            boxService.save(box); 
            
            // send message of success
            addMessage("box.msg.registed"); 
            
            // return response to ajax
            responseAjax(response, box); 
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex); 
            
            // return response to ajax
            responseAjax(response, null); 
        }
    }    
    
    /* List boxs */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView showBoxList(){
        
        ModelAndView mv = new ModelAndView(PATH + "box-list");
        
        setBoxes(boxService.findAll());
        
        // get all boxes
        mv.addObject("boxes", getBoxes());
        return mv;
    }
    
    /* Delete box */
    @RequestMapping(value="deletebox/{id}", method=RequestMethod.POST)
    public void deleteBox(@PathVariable Long id, HttpServletResponse response) throws IOException {                        
        
        try{   
            // clear the cache of messages
            clearMessages(); 
            
            // remove box by id
            boxService.delete(id);
            
            // send message of success
            addMessage("box.msg.deleted"); 
            
            // return response to ajax
            responseAjax(response, null);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null); 
        }
        
    }

    public List<Box> getBoxes() {
        return boxes;
    }

    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }
    
}
