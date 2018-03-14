package edu.knoldus.crud.utiliy;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Employee {

    String id,name,age,city;
    /*public Employee(String id,String name,String age, String city){
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }*/
}
