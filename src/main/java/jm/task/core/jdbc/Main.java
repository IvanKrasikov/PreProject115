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
        userService.saveUser("Name2","LastName2", (byte) 31);
        userService.saveUser("Name3","LastName3", (byte) 32);
        userService.saveUser("Name4","LastName4", (byte) 33);

        userService.removeUserById(1);

        List<User> users = userService.getAllUsers();
        users.stream().forEach(user -> System.out.println(user.toString()));

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
