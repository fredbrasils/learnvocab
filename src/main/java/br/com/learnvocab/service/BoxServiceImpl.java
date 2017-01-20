package br.com.learnvocab.service;

import br.com.learnvocab.dao.BoxDAOImpl;
import br.com.learnvocab.entity.Box;
import br.com.learnvocab.entity.User;
import br.com.learnvocab.util.LearnVocabException;
import java.util.List;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Fred Brasil
 */
@Service
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BoxServiceImpl extends BaseServiceImpl<Box,BoxDAOImpl> implements BoxService{

    public BoxServiceImpl() {
        super(Box.class);
    }
    
    @Override
    public void save(List<Box> boxes) throws LearnVocabException{
        
        Integer numberDays = 0;
        
        /* Verified if the number of days from each box is superior 
           than the box before them
        */
        for(Box box : boxes){
            
            if(box.getDaysNumber() > numberDays){
               numberDays = box.getDaysNumber();
            }else{                
                throw new LearnVocabException(LearnVocabException.ERROR_RULE, "box.error.numberdays.invalid", new Object[]{box.getName(), numberDays});
            }
        }
        
        super.save(boxes);
    }
    
    @Override
    public void save(Box box) throws LearnVocabException{
        
        /* Verified if the number of days from each box is superior 
           than the box before them
        */
        if(existsDaysNumberSuperior(box.getDaysNumber())){
            throw new LearnVocabException(LearnVocabException.ERROR_RULE, "box.error.numberdays.invalid", new Object[]{box.getName(), box.getDaysNumber()});
        }
        
        super.save(box);
    }

    @Override
    public void delete(Long id) throws LearnVocabException {
        
         // get the box
        Box box = find(id);
        
        // remove box
        super.delete(box);
    }

    private boolean existsDaysNumberSuperior(Integer daysNumber) {
        return getDao().existsDaysNumberSuperior(daysNumber);
    }
}
