package com.example.boot05webadmin.acutuator.endpoint;

import com.fasterxml.jackson.databind.deser.std.CollectionDeserializer;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

/**
 * @Endpoint(id = "myServiceEndPoint")
 * 端点
 */
@Component
@Endpoint(id = "myServiceEndPoint")
public class MyServiceEndPoint {
    /**
     * 读
     * 端点的读操作
     */
    @ReadOperation
    public Map getDockerInfo() {
        return Collections.singletonMap("dockerInfo", "docker...");
    }

    /**
     * 写
     * 端点的写操作
     */
    @WriteOperation
    public void stopDockerInfo() {
        System.out.println("docker stop...");
    }
}
