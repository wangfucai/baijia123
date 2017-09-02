package com.baijia123.util;

public class ClonableTest {

    public static class Name implements Cloneable{
        private Integer id;
        private String name;

        Name(Integer id, String name) {
            super();
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
        
        @Override
        protected Object clone() throws CloneNotSupportedException {
            // TODO Auto-generated method stub
            return super.clone();
        }

    }

    public static class Person implements Cloneable {
        private Name name;
        private Integer age;

        public Person(Name name, Integer age) {
            super();
            this.name = name;
            this.age = age;
        }

        public Name getName() {
            return name;
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            //Éî¿½±´
            Person p = (Person)super.clone();
            p.name = (Name)p.getName().clone();
            // TODO Auto-generated method stub
            //Ç³¿½±´
            //return super.clone();
            return p;
        }

    }

    public static void main(String[] args) throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        Name name = new Name(1, "test");
        Person p = new Person(name, 31);
        Person copy = (Person)p.clone();
        copy.getName().setId(3);
        copy.getName().setName("test1");
        System.out.println("copy name = " + name.getName());
    }
}
