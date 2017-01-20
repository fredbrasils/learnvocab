package br.com.learnvocab.entity.enums;

/**
 *
 */
public enum FlowAction {
    FORWARD("F"),BACK("B");
    
    private String code;

    private FlowAction(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
}