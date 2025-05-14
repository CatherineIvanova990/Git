package service;

import dao.UserDao;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public void createUsersTable() {
        userDao.createUsersTable();
        System.out.println("база данных создана");
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
        System.out.println("база данных удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);
        System.out.println("пользователь с именем - " + name + " сохранён");
    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
        System.out.println("пользователь с id - "+ id + " удалён");
    }

    public List<User> getAllUsers() {
        List<User> users = userDao.getAllUsers();
        System.out.println("получены пользователи из базы данных");
        return users;
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
        System.out.println("база данных очищена");
    }
}
