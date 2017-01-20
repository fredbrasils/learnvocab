package br.com.learnvocab.entity.enums;

import java.util.Locale;
import org.springframework.context.MessageSource;

/**
 *
 */
public enum WordPriority {
    HIGHT("H","Hight"),
    MEDIUM("M","Medium"),
    LOW("L","Low");
    
    private String code;
    private String description;

    private WordPriority(String code,String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name();
    }
    
//    public static WordPriority[] values(MessageSource message){
//        
//        WordPriority[] priorities = values();
//        
//        for (WordPriority priority : priorities) {
//            priority.setDescription(message.getMessage(priority.getDescription(), null, Locale.getDefault()));
//        }
//        
//        return priorities;
//    }
}