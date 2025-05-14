package dao;

import constant.SQLConstant;
import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import util.Util;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    private Session getSession(){
        return sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        try (Session session = getSession()){
            session.beginTransaction();
            session.createNativeQuery(SQLConstant.createTable).executeUpdate();
            session.getTransaction().commit();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSession()){
            session.beginTransaction();
            session.createNativeQuery(SQLConstant.dropTable).executeUpdate();
            session.getTransaction().commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSession()){
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSession()){
            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> fromUsers;
        try (Session session = getSession()){
            session.beginTransaction();
            fromUsers = session.createQuery("from User", User.class).list();
            session.getTransaction().commit();
        }
        return fromUsers;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSession()){
            session.beginTransaction();
            session.createQuery("delete from User").executeUpdate();
            session.getTransaction().commit();
        }
    }
}
