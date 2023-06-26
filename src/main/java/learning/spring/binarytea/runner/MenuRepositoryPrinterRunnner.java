package learning.spring.binarytea.runner;

import learning.spring.binarytea.repository.MenuJpaRepository;
import learning.spring.binarytea.repository.redis.RedisMenuRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Order(4)
public class MenuRepositoryPrinterRunnner implements ApplicationRunner {
    @Autowired
    private MenuJpaRepository menuJpaRepository;
    @Autowired
    private RedisMenuRepository redisMenuRepository;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        long size = 0;
        Iterable<?> menuList;
        if (redisMenuRepository.count() > 0 ){
            log.info("Loading menu from Redis.");
            size = redisMenuRepository.count();
            menuList = redisMenuRepository.findAll();
            log.info("Java咖啡缓存了{}条", redisMenuRepository.findByName("Java咖啡").size());
        } else {
            log.info("Loading menu from DB.");
            size = menuJpaRepository.count();
            menuList = menuJpaRepository.findAll();
        }
        log.info("共有{}个饮品可选。", size);
        menuList.forEach(i -> log.info("饮品：{}", i));
    }
}
