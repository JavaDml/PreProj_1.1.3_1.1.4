package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {

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
