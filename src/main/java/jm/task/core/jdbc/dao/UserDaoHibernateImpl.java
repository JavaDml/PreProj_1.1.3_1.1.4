package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = Util.hibernateConnectDB().openSession();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS `test`.`User` (\n" +
                "  `id` BIGINT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  `lastName` VARCHAR(45) NULL,\n" +
                "  `age` TINYINT NULL,\n" +
                "  PRIMARY KEY (`id`))\n" +
                "ENGINE = InnoDB;").executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.hibernateConnectDB().openSession();
        session.createSQLQuery("DROP TABLE IF EXISTS `test`.`User`").executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.hibernateConnectDB().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(new User(name, lastName, age));
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.hibernateConnectDB().openSession();
        Transaction tx1 = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>)  Util.hibernateConnectDB().openSession().createQuery("from User").list();
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.hibernateConnectDB().openSession();
        Transaction tx1 = session.beginTransaction();
        for(User user : getAllUsers()) {
            session.delete(user);
        }
        tx1.commit();
        session.close();
    }
}
