package org.example.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ConfigurationProperties(prefix = "person")
@Component
@Data
@ToString
public class Person {
    String username;
    Boolean boss;
    Date birth;
    Integer age;
    Pet pet;
    String[] interests;
    List<String> animal;
    Map<String, Object> score;
    Set<Double> salarys;
    Map<String, List<Pet>> allPets;
}
