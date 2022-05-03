package com.company;

import static com.company.Constants.*;

public class User  implements Comparable<User>{

    //instance variables
    private String type;
    private int id;
    private String name;
    private double credit;

    //instance counter
    private static int instances = 0;

    //constructor
    public User(String type, int id, String name, double credit) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.credit = credit;
        instances++;
    }

    public User() {
        this("ex", 0, "Noname", 0.0);
    }

    //get set
    public String getType() {
        return this.type;
    }
    public boolean setType(String type) {
        this.type = type;
        return true;
    }

    public int getId() {
        return this.id;
    }
    public boolean setId(int id) {
        this.id = id;
        return true;
    }

    public String getName() {
        return name;
    }
    public boolean setName(String name) {
        this.name = name;
        return true;
    }

    public double getCredit() {
        return this.credit;
    }
    public boolean setCredit(double credit) {
        this.credit = credit;
        return true;
    }

    public static int getInstances() {
        return instances; //??? de ce e highlight?
    }

    //override methods
    @Override
    public String toString() {
        return this.getType() + SAVE_SEPARATOR + this.getId() + SAVE_SEPARATOR + this.getName() + SAVE_SEPARATOR + this.getCredit();
    }

    @Override
    public boolean equals(Object o) {
        User user = (User) o;
        return this.toString().equals(user.toString());
    }

    @Override
    public int compareTo(User o) {
        if (this.getId() == o.getId()) {
            return 0;
        } else if (this.getId() < o.getId()) {
            return -1;
        } else {
            return 1;
        }
    }

}
