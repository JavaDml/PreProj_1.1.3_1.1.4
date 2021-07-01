package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String pass = "root";

    private Connection connection;
    private static SessionFactory sessionFactory = null;
    private static StandardServiceRegistry serviceRegistry = null;

    public Util() {}

    public Connection connectDB() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static SessionFactory hibernateConnectDB() {
        if ( sessionFactory == null ) {
            Configuration configuration = new Configuration()
                    .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                    .setProperty("hibernate.connection.driver_class",
                            "com.mysql.jdbc.Driver")
                    .setProperty("hibernate.connection.url",
                            url)
                    .setProperty("hibernate.connection.username",
                            user)
                    .setProperty("hibernate.connection.password",
                            pass)
                    .setProperty("hibernate.dialect",
                            "org.hibernate.dialect.MySQLDialect")
                    .setProperty("hibernate.show_sql", "true");

                    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return sessionFactory;
    }

    public static void hibernateCloseDB() {
        StandardServiceRegistryBuilder.destroy(serviceRegistry);
    }
}
