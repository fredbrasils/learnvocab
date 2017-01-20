package br.com.learnvocab.controller.word;

import br.com.learnvocab.controller.BaseController;
import br.com.learnvocab.entity.Idiom;
import br.com.learnvocab.entity.Word;
import br.com.learnvocab.entity.enums.WordPriority;
import br.com.learnvocab.service.IdiomServiceImpl;
import br.com.learnvocab.service.WordServiceImpl;
import br.com.learnvocab.util.LearnVocabException;
import br.com.learnvocab.util.LearnVocabMessage;
import br.com.learnvocab.validation.WordValidation;
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
@RequestMapping("/word")
public class WordController extends BaseController{

    private static final String PATH = "word/";
    
    @Autowired
    private WordServiceImpl  wordService;
    
    @Autowired
    private IdiomServiceImpl  idiomService;
    
//    private WordPriority[] priorities;
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        
        // validate the object word
        binder.addValidators(new WordValidation());
    }           
    
    /* Register a Word */
    @RequestMapping(method=RequestMethod.POST)    
    public void registerWord(@Valid Word word, BindingResult result, HttpServletResponse response) throws IOException, Exception{
                  
        try{   
            // validate word's field
            validate(result);
            word.setDateRegister(Calendar.getInstance());
            
            // save word
            wordService.save(word);
            
            // send message of success
            addMessage("word.msg.registed");
            
            // return response to ajax
            responseAjax(response, word);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null);
        }
    }    
    
    /* List words */
    @RequestMapping(method=RequestMethod.GET)
    public ModelAndView showWordList(Word word){
        ModelAndView mv = new ModelAndView(PATH + "word-list");
        
        // get all words
        mv.addObject("words", wordService.findAll());
        
        // get all idioms
        mv.addObject("idioms", idiomService.findAll());
        
        // get all priorities
        mv.addObject("priorities", WordPriority.values());
        return mv;
    }

//    public WordPriority[] getPriorities() {
//        
//        if(priorities == null){
//            priorities = WordPriority.values(getMessageSource());
//        }
//        
//        return priorities;
//    }
     
    /* Update the idiom */
    @RequestMapping(value="updateword",method=RequestMethod.POST)    
    public void updateIdiom(@Valid Word word, BindingResult result, HttpServletResponse response) throws IOException, Exception{    
        
        try{   
            // validate idiom's field
            validate(result);
            word.setDateRegister(Calendar.getInstance());
            
            // update word
            wordService.update(word);
            
            // send message of success
            addMessage("word.msg.updated");
            
            // return response to ajax
            responseAjax(response, word);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null);
        }
        
    } 
    
    @RequestMapping(value="deleteword/{id}", method=RequestMethod.POST)
    public void deleteIdiom(@PathVariable Long id, HttpServletResponse response) throws IOException {                        
        
        try{   
            
            // clear the cache of messages
            clearMessages();
            
            // delete word
            wordService.delete(id);
            
            // send message of success
            addMessage("word.msg.deleted");
            
            // return response to ajax
            responseAjax(response, null);
        }catch (LearnVocabException ex){
            
            // send message of error
            addMessage(ex);
            
            // return response to ajax
            responseAjax(response, null);
        }
        
    }
    
}
