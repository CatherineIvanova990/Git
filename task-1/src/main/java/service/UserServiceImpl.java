package service;

import dao.UserDaoHibernateImpl;
import dao.UserDaoJDBCImpl;
import model.User;
import util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
    private static final UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
//        userDaoJDBC.createUsersTable();
        userDaoHibernate.createUsersTable();
        System.out.println("база данных создана");
    }

    public void dropUsersTable() {
//        userDaoJDBC.dropUsersTable();
        userDaoHibernate.dropUsersTable();
        System.out.println("база данных удалена");
    }

    public void saveUser(String name, String lastName, byte age) {
//        userDaoJDBC.saveUser(name, lastName, age);
        userDaoHibernate.saveUser(name, lastName, age);
        System.out.println("пользователь с именем - " + name + " сохранён");
    }

    public void removeUserById(long id) {
//        userDaoJDBC.removeUserById(id);
        userDaoHibernate.removeUserById(id);
        System.out.println("пользователь с id - "+ id + " удалён");
    }

    public List<User> getAllUsers() {
//        List<User> users = userDaoJDBC.getAllUsers();
        List<User> users = userDaoHibernate.getAllUsers();
        System.out.println("получены пользователи из базы данных");
        return users;
    }

    public void cleanUsersTable() {
//        userDaoJDBC.cleanUsersTable();
        userDaoHibernate.cleanUsersTable();
        System.out.println("база данных очищена");
    }
}
