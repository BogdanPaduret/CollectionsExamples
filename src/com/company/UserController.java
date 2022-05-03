package com.company;

import com.company.comparatori.ComparatorNume;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

import static com.company.Constants.*;

public class UserController {

    //instance variables
    private ArrayList<User> users;
    private String path;

    //constructor
    public UserController() {
        this("src/com/company/users");
    }
    public UserController(String path) {
        this.path = path;
        users = new ArrayList<>();
        load();
    }

    //CREATE
    public String toSave() {
        String string = "";
        for (User user : users) {
            string += user.toString();
        }
        return string;
    }
    public boolean save() {
        try {
            users.clear();
            File file = new File(path);
            FileWriter fileWriter = new FileWriter(file);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            printWriter.print(toSave());
            printWriter.close();

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public void add(User user) {
        users.add(user);
    }

    //READ
    public boolean load() {
        try {
            users.clear();
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line != "") {
                    loadUser(line);
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    private boolean loadUser(String input) {
        try {
            String[] strings = input.split(SAVE_SEPARATOR);

            String type = strings[0];
            int id = Integer.parseInt(strings[1]);
            String name = strings[2];
            double credit = Double.parseDouble(strings[3]);

            users.add(new User(type, id, name,credit));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public int getSize() {
        int c = 0;
        for (User user : users) {
            c++;
        }
        return c;
    }

    public ArrayList<User> sortDefault() {
        ArrayList<User> sortedUsers = new ArrayList<>();

        sortedUsers.addAll(users);

        boolean flag;

        do {
            flag = true;
            for (int i = 0; i < sortedUsers.size() - 1; i++) {
                if (sortedUsers.get(i).compareTo(sortedUsers.get(i + 1)) > 0) {
                    User user = sortedUsers.get(i);
                    sortedUsers.set(i, sortedUsers.get(i + 1));
                    sortedUsers.set(i + 1, user);
                    flag = false;
                }
            }

        } while (flag == false);

       return sortedUsers;
    }
    public ArrayList<User> sort(Comparator<User> comparator) {

        ArrayList<User> sortedUsers = new ArrayList<>();
        sortedUsers.addAll(users);

        boolean flag;
        do {
            flag = true;
            for (int i = 0; i < sortedUsers.size() - 1; i++) {
                if (comparator.compare(sortedUsers.get(i), sortedUsers.get(i + 1)) > 0) {
                    User user = sortedUsers.get(i);
                    sortedUsers.set(i, sortedUsers.get(i + 1));
                    sortedUsers.set(i + 1, user);
                    flag = false;
                }
            }
        } while (flag == false);

        return sortedUsers;

    }

    public void showSorted(ArrayList<User> sortedList) {
        for (User user : sortedList) {
            System.out.println(user.toString());
        }
    }

    public void show() {
        for (User user : users) {
            System.out.println(user.toString());
        }
    }

    public User maxUser(Comparator<User> comparator){
        return Collections.max(users,comparator);
    }

    public int getId(User user,Comparator<User> userComparator) {

        ArrayList<User> sortedList = new ArrayList<>();
        sortedList.addAll(users);
        Collections.sort(sortedList, userComparator);

        int index = Collections.binarySearch(sortedList, user, userComparator);

        return sortedList.get(index).getId();
    }
    public int getId(User user) {
        return getId(user, null);
    }

    public void copy(ArrayList<User> src) {
             Collections.copy(users, src);
    }

    public boolean check(ArrayList<User> comparedTo) {
        return Collections.disjoint(users, comparedTo);
    }

    public int checkFrequency(User user) {
        return Collections.frequency(users, user);
    }

    //UPDATE
    public void update() {

    }

    public void fill(User user) {
        Collections.fill(users, user);
    }

    //DELETE
    public void clearSession() {
        users.clear();
    }
    public void deleteAll() {
        clearSession();
        save();
    }




}
