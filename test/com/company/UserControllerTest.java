package com.company;

import com.company.comparatori.ComparatorCredit;
import com.company.comparatori.ComparatorNume;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    UserController userController;

    @BeforeEach
    public void init() {
        userController = new UserController("test/com/company/users");
    }

    @AfterEach
    public void close() {
        userController.clearSession();
        userController.load();
    }

    @Test
    public void loadTest() {
        userController.load();
        assertEquals(7,userController.getSize());
    }

    @Test
    public void staticInstancesTest() {
        assertEquals(7,User.getInstances());
    }

    @Test
    public void showSortedDefaultTest() {
        userController.showSorted(userController.sortDefault());
    }

    @Test
    public void showSortedNameTest() {
        userController.showSorted(userController.sort(new ComparatorNume()));
    }

    @Test
    public void showSortedCreditTest() {
        userController.showSorted(userController.sort(new ComparatorCredit()));

        System.out.println("==============================================");
        System.out.println(userController.maxUser(new ComparatorCredit()));

        User user = new User("client", 97, "Mihaita", 962.84);

        System.out.println("Collections test begin");
        assertEquals(97, userController.getId(user,new ComparatorNume()));
        System.out.println("Collections test end");
    }

    @Test
    public void collectionsCopyTest() {
        ArrayList<User> newList = new ArrayList<>();
        newList.add(new User("ex", 999, "Alin", 123.45));
        newList.add(new User("ex", 888, "Verde", 234.56));
        newList.add(new User("ex", 777, "Pamant", 345.67));
        userController.copy(newList);
        userController.show();
    }

    @Test
    public void disjointTest() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("ex", 999, "Alin", 123.45));
        users.add(new User("ex", 888, "Verde", 234.56));
        users.add(new User("ex", 777, "Pamant", 345.67));
        System.out.println(userController.check(users));

        ArrayList<User> users1 = new ArrayList<>();
        users1.add(new User("client",6,"Tudor",578.95));
        System.out.println(userController.check(users1));
    }

    @Test
    public void fillTest() {
        User user = new User();
        userController.fill(user);
        userController.show();
        System.out.println(User.getInstances());
    }

    @Test
    public void frequencyTest() {
        User user = new User("client", 96, "Victor", 273.19);
        User user1 = new User("client", 97, "Mihai", 962.84);
        User user2 = new User("client", 97, "Victor", 273.19);
        assertEquals(2, userController.checkFrequency(user));
        assertEquals(1, userController.checkFrequency(user1));
        assertEquals(0, userController.checkFrequency(user2));
    }

    @Test
    public void indexOfSublist() {
        User user = new User("client", 96, "Victor", 273.19);
        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        ArrayList<User> usersSorted = userController.sortDefault();
        int retFirst = Collections.indexOfSubList(usersSorted, users);
        int retLast = Collections.lastIndexOfSubList(usersSorted, users);
//        userController.showSorted(usersSorted);
//        System.out.println(ret);
        assertEquals(8, retFirst);
        assertEquals(9,retLast);
    }

    @Test
    public void reverse() {
        User user = new User("client", 1, "Mihai", 123.45);
        User user1 = new User("admin", 2, "Victor", 234.56);
        User user2 = new User("angajat", 3, "Ana", 345.67);

        ArrayList<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        users.add(user2);

        ArrayList<User> usersAscending = new ArrayList<>();
        usersAscending.addAll(users);

        ArrayList<User> usersDescending = new ArrayList<>();
        usersDescending.add(user2);
        usersDescending.add(user1);
        usersDescending.add(user);

        Collections.reverse(usersAscending);

        assertEquals(usersAscending, usersDescending);

    }
}