package br.com.learnvocab.util;

/**
 *
 */
public class LearnVocabMessage{

    /* Name of Class CSS */
    public static final String ERROR = "error";
    public static final String SUCCESS = "success";
    public static final String INFO = "info";
    public static final String WARNING = "warning";
    
    private String clazz;
    private final String message;
    private final String field;

    public LearnVocabMessage(String clazz,String message, String field) {
        this.clazz = clazz;
        this.message = message;
        this.field = field;
    }    
    
    public LearnVocabMessage(String clazz, String message) {
        this.clazz = clazz;
        this.message = message;
        this.field = "";
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public String getClazz() {
        return clazz;
    }
    
    
}
