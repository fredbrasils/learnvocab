package br.com.learnvocab.controller;

import br.com.learnvocab.util.LearnVocabException;
import br.com.learnvocab.util.LearnVocabMessage;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;


public abstract class BaseController {
    
    @Autowired
    private MessageSource messageSource;
    private List<LearnVocabMessage> message;
    
    /* Return message by resource bundle */
    private String getMessage(String key) {
 
        try {            
            return messageSource.getMessage(key, null, Locale.getDefault());
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }
    
    /* Return message by resource bundle */
    private String getMessage(String key, Object[] obj) {
 
        try {            
            return MessageFormat.format(messageSource.getMessage(key, obj, Locale.getDefault()), obj);            
        }
        catch (Exception e) {
            return "Unresolved key: " + key;
        }
    }
    
    /* Return error's validation */
    public void validate(BindingResult result) throws Exception{
        
        message = new ArrayList<>();                    
        
        if (result.hasErrors()) { // if exist errors                        
                    
            // take all validations' errors
            for(FieldError erro : result.getFieldErrors()){                
                message.add(new LearnVocabMessage(LearnVocabMessage.ERROR, getMessage(erro.getCode()), erro.getField()));
            }
            
            // return validation's exception
            throw new LearnVocabException(LearnVocabException.ERROR_VALIDATION,"");
        }           
    }

    /* Add message to the front-end */
    public void addMessage(String clazz,String code){
        if(message == null){
            message = new ArrayList<>();
        }
        if(code != null && !code.trim().isEmpty()){
            message.add(new LearnVocabMessage(clazz,getMessage(code)));
        }
    }
    
    public void addMessage(String code){
        if(message == null){
            message = new ArrayList<>();
        }
        if(code != null && !code.trim().isEmpty()){
            message.add(new LearnVocabMessage(LearnVocabMessage.SUCCESS,getMessage(code)));
        }
    }
    
    public void addMessage(LearnVocabException ex){
        if(message == null){
            message = new ArrayList<>();
        }
        
        String code = ex.getMessage();        
        Object[] messageParam = ex.getMessageParam();
        
        if(!code.trim().isEmpty()){
            if(messageParam != null){
                message.add(new LearnVocabMessage(LearnVocabMessage.ERROR,getMessage(code,messageParam)));
            }else{
                message.add(new LearnVocabMessage(LearnVocabMessage.ERROR,getMessage(code)));
            }
        }
    }
    
    public void clearMessages(){
        message = new ArrayList<>();
    }
    
    public void responseAjax(HttpServletResponse response, Object entity) throws IOException{
        JsonObject myObj = new JsonObject();
        Gson gson = new Gson();
        
        // Convert the message in JSON
        JsonElement msgJson = gson.toJsonTree(message);
        myObj.add("message", msgJson);         
        
        // Convert the object in JSON if exist
        if(entity != null){
            JsonElement objJson = gson.toJsonTree(entity);
            myObj.add(entity.getClass().getSimpleName().toLowerCase(), objJson);            
        }
        
        // Add Json Object inside the response
        PrintWriter out = response.getWriter();        
        out.println(myObj.toString()); 
        out.close();
    }    
    
    public List<LearnVocabMessage> getMessage() {
        return message;
    }    

    public MessageSource getMessageSource() {
        return messageSource;
    }    
    
}
