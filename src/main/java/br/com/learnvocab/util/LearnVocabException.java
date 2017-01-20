package br.com.learnvocab.util;

/**
 *
 */
public class LearnVocabException extends Exception{

    public static final String ERROR_VALIDATION = "error.validation";
    public static final String ERROR_RULE = "error.rule";
    public static final String ERROR_DATABASE = "error.database";
    
    private String error;
    private Object[] messageParam;
    
    public LearnVocabException(String error,String message) {
        super(message);
        this.error = error;
        this.messageParam = null;
    }
    
    public LearnVocabException(String error, String message, Object[] messageParam) {
        super(message);
        this.error = error;
        this.messageParam = messageParam;
    }

    public String getError() {
        return error;
    }

    public Object[] getMessageParam() {
        return messageParam;
    }
            
    
}
