package ru.kovshov.shop.shop.models;

import java.io.FileNotFoundException;
import java.util.List;

public class User {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        User user = (User) obj;
        return user.getName().equals(this.name) && user.getAge() == this.age;
    }


    public void s(){
        List<String> list = List.of("a", "b", "c");
        list.stream()
                .map(String::toLowerCase)
                .filter(s -> s.startsWith("c"))
                .sorted()
                .forEach(System.out::println);
    }

}
