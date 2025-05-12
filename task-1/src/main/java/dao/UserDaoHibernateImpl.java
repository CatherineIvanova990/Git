package dao;

import DBConfig.DataBaseConfig;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.Util;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        String createTable = """
                create table if not exists Users
                          (
                              id       serial primary key,
                              name     varchar(255),
                              last_name varchar(255),
                              age      smallint
                          );
                """;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createNativeQuery(createTable).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {
        String createTable = """
                drop table if exists Users;
                """;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createNativeQuery(createTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> fromUsers;
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            fromUsers = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        }
        return fromUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.createQuery("delete from User ").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
