package util;

import DBConfig.DataBaseConfig;
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

    public static SessionFactory getSessionFactory() {
        Properties properties = new Properties();
        properties.put(Environment.DRIVER, DataBaseConfig.DRIVER);
        properties.put(Environment.URL, DataBaseConfig.URL);
        properties.put(Environment.USER, DataBaseConfig.USER);
        properties.put(Environment.PASS, DataBaseConfig.PASS);
        properties.put(Environment.DIALECT,"org.hibernate.dialect.PostgreSQLDialect");
        Configuration configuration = new Configuration()
                .setProperties(properties);
        configuration.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
        configuration.addAnnotatedClass(User.class);
        return configuration.buildSessionFactory();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName(DataBaseConfig.DRIVER);
        return DriverManager.getConnection
                (DataBaseConfig.URL, DataBaseConfig.USER, DataBaseConfig.PASS);
    }
    // реализуйте настройку соеденения с БД
}
