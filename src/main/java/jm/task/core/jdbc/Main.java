package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService =new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Name1","LastName1", (byte) 30);
        System.out.println("User с именем – Name1 добавлен в базу данных");

        userService.saveUser("Name2","LastName2", (byte) 31);
        System.out.println("User с именем – Name2 добавлен в базу данных");

        userService.saveUser("Name3","LastName3", (byte) 32);
        System.out.println("User с именем – Name3 добавлен в базу данных");

        userService.saveUser("Name4","LastName4", (byte) 33);
        System.out.println("User с именем – Name4 добавлен в базу данных");
        System.out.println("");
        List<User> users = userService.getAllUsers();
        users.stream().forEach(user -> System.out.println(user.toString()));
        userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
