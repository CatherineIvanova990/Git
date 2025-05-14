package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    private static final String URL_KEY = "db.url";
    private static final String USER_KEY = "db.username";
    private static final String PASS_KEY = "db.password";

    private Util() {
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.put(Environment.URL, PropertiesUtil.getProperties(URL_KEY));
            properties.put(Environment.USER, PropertiesUtil.getProperties(USER_KEY));
            properties.put(Environment.PASS, PropertiesUtil.getProperties(PASS_KEY));
            properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
            Configuration configuration = new Configuration()
                    .setProperties(properties);
            configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;

    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        return DriverManager.getConnection(
                PropertiesUtil.getProperties(URL_KEY),
                PropertiesUtil.getProperties(USER_KEY),
                PropertiesUtil.getProperties(PASS_KEY));
    }
}
