package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        UserService db = new UserServiceImpl();
        db.createUsersTable();
        db.saveUser("Андрей", "Петров", (byte) 20);
        db.saveUser("Иван", "Иванов", (byte) 21);
        db.saveUser("Саша", "Сидоров", (byte) 25);
        db.saveUser("Михаил", "Козлов", (byte) 30);
        for (User user : db.getAllUsers()) {
            System.out.println(user.toString());
        }
        db.cleanUsersTable();
        db.dropUsersTable();

        UserDao dbHibernate = new UserDaoHibernateImpl();
        dbHibernate.createUsersTable();
        dbHibernate.saveUser("Андрей", "Петров", (byte) 20);
        dbHibernate.saveUser("Иван", "Иванов", (byte) 21);
        dbHibernate.saveUser("Саша", "Сидоров", (byte) 25);
        dbHibernate.saveUser("Михаил", "Козлов", (byte) 30);
        dbHibernate.removeUserById(1L);
        for(User usr : dbHibernate.getAllUsers()) {
            System.out.println(usr.toString());
        }
        dbHibernate.cleanUsersTable();
        dbHibernate.dropUsersTable();
    }
}
