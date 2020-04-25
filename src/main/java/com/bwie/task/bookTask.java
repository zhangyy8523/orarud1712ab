package com.bwie.task;

import com.bwie.dao.TBookMapper;
import com.bwie.model.TBook;
import com.bwie.model.TBookExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/25 9:35
 */

@Component
public class bookTask {
    @Autowired
    TBookMapper tBookMapper ;
    @Autowired
    private RedisTemplate redisTemplate ;

    @Scheduled(cron = "0/30 * * * * ?") //每隔十分钟，更新一下数据库中的点击量的数据
    public void updateRedisTraffic() {
        System.out.println("=============我去执行updateRedisTraffic================================");
        //得到当前库中所有的书籍
        List<TBook> books = tBookMapper.selectByExample(new TBookExample());
        //再循环，拿到书籍的id去redis查询点击量，并且同步更新到数据库中
        if(books!=null && books.size()>0){
            for (TBook book : books) {
                String id = book.getId();
                //判断id在redis是否存在
              if(redisTemplate.hasKey(id)) { //如果当前key在redis是存在的，则去同步数据库
                  Integer  count = (Integer) redisTemplate.opsForValue().get(id);
                  book.setTraffic((long)count); //赋值
                  //更新数据库
                  tBookMapper.updateByPrimaryKeySelective(book);
              }
            }
        }


    }

}
