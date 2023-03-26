package com.example.boot05webadmin.acutuator.info;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AppInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("msg", "msg")
                .withDetail("code", 200)
                .withDetails(Collections.singletonMap("world","123456"));
    }
}
