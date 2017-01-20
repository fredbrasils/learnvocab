package br.com.learnvocab.dao;

import br.com.learnvocab.entity.Box;
import br.com.learnvocab.entity.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class BoxDAOImpl extends BaseDAOImpl<Box>{  

    public boolean existsDaysNumberSuperior(Integer daysNumber) {
        
        Map<String,Object> params = new HashMap<String,Object>();
        
        StringBuilder sql = new StringBuilder();
        sql.append("select b from Box b where b.daysNumber >= :daysNumber ");
        
        params.put("daysNumber", daysNumber);
        
        List<Box> boxes = createQuery(Box.class,sql.toString(), params).getResultList();

        return !boxes.isEmpty();
    }

    
}
