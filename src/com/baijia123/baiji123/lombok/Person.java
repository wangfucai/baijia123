package com.baiji123.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String id;
    private String name;
    private String identity;
    
    
    public static void main(String[] args) {
        Person p = new Person();
        p.setId("1");
        System.out.println(p.getId());
    }
}
