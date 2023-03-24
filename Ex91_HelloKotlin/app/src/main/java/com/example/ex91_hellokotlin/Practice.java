package com.example.ex91_hellokotlin;

public class Practice {
}


class ParentJava{

    String name;
    int age;

    public ParentJava(int age,String name){
        this.name = name;
        this.age = age;
    }
}

class ChildJava extends ParentJava{

    public ChildJava(int age, String name) {
        super(age, name);
    }
}