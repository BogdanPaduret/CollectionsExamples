package com.company.comparatori;

import com.company.User;

import java.util.Comparator;

public class ComparatorCredit implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {

        if (o1.getCredit() > o2.getCredit()) {
            return 1;
        } else if (o1.getCredit() < o2.getCredit()) {
            return -1;
        } else {
            return 0;
        }
    }

}
