package br.com.learnvocab.builder;


import br.com.learnvocab.entity.Box;
import br.com.learnvocab.entity.User;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author Fred Brasil
 */
public class BoxBuilder {

    private List<Box> boxes = new ArrayList<>();;

    private BoxBuilder(Box box) {
        boxes.add(box);
    }

    public static BoxBuilder newBox(Integer numberDay) {
        Box box = create(numberDay);
        return new BoxBuilder(box);
    }
    
    private static Box create(Integer numberDay) {
        Box box = new Box();
        box.setDaysNumber(numberDay);
        box.setName("#1");
        box.setOrder(1);
        box.setDateRegister(Calendar.getInstance());        
        return box;
    }
    
    private static Box create(Integer numberDay, Integer order) {
        Box box = new Box();
        box.setDaysNumber(numberDay);
        box.setName("#"+order);
        box.setOrder(order);
        box.setDateRegister(Calendar.getInstance());        
        return box;
    }
    /*
    public BoxBuilder withUser(User user) {
        
        for(Box box : boxes){
            box.setUser(user);
        }        
        return this;
    }
     */
    public BoxBuilder more(Integer... numberDays) {        
        
        for (Integer numberDay : numberDays) {
            boxes.add(create(numberDay,boxes.size()+1));
        }        
        return this;
    }
     
    public Box buildOne() {
        return boxes.get(0);
    }

    public List<Box> buildAll() {
        return boxes;
    }
    

}
