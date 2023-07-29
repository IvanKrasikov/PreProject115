package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE users (id BIGINT PRIMARY KEY AUTO_INCREMENT, " +
                    "name VARCHAR(255), lastName VARCHAR(255), age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("NOT CREATE USERS TABLE");
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("NOT DROP USERS TABLE");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("INSERT users(name, lastName, age) VALUE(\""
                    + name + "\", \"" + lastName + "\", " + age + ")").executeUpdate();
            transaction.commit();
        } catch (IllegalStateException e) {
            System.out.println("NOT SAVE USER");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id = " + id).executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("NOT REMOVE USER BY ID = " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).getResultList();
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users WHERE id > 0").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            System.out.println("NOT CLEAN USERS TABLE");
        }
    }
}
