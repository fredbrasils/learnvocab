/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.learnvocab.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Fred
 */
public class ObjectUtil {
    
    public static List<String> searchStringAtAtributes(Object o, String buscada){
    try {
        List<String> lista = new ArrayList<>();
        Class<?> c = o.getClass();
        for(Field f : c.getFields()){
            Object value = f.get(o);
            if(value != null){
                String strValue = value.toString();
                if(strValue.contains(buscada)){
                    lista.add(f.getName());
                }
            }
        }
        return lista;
    } catch (Exception e) {
        e.printStackTrace();
        throw new RuntimeException(e);
    }
}
}
