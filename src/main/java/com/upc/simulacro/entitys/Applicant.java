package com.upc.simulacro.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Applicant {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long dni;
   private String   name;
   private  int age;

   private Status status;
   private int salary;
    private boolean bag;

    public Applicant() {
    }

    public Applicant(Long dni, String name, int age, Status status, int salary, boolean bag) {
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.status = status;
        this.salary = salary;
        this.bag = bag;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "dni=" + dni +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", status=" + status +
                ", salary=" + salary +
                ", bag=" + bag +
                '}';
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean isBag() {
        return bag;
    }

    public void setBag(boolean bag) {
        this.bag = bag;
    }
}
