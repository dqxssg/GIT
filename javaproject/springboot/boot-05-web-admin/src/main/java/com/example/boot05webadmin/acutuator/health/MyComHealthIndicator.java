package com.example.boot05webadmin.acutuator.health;

import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyComHealthIndicator extends AbstractHealthIndicator {

    /**
     * 真实的检查方法
     *
     * @param builder
     * @throws Exception
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //获取连接进行测试
        Map<String, Object> map = new HashMap<>();
        //检查完成
        if (1 == 2) {
            builder.status(Status.UP);
            //影响行数
            map.put("count", 1);
            //链接耗费多少毫秒
            map.put("ms", 100);
        } else {
            builder.status(Status.OUT_OF_SERVICE);
            //错误原因
            map.put("err", "连接超时");
            //超时时间
            map.put("ms", 3000);
        }
        //返回信息
        builder.withDetail("code", 100)
                .withDetails(map);

    }
}
