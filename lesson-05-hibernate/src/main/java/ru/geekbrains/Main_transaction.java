package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.salary.Customer;
import ru.geekbrains.salary.Product;
import ru.geekbrains.salary.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main_transaction {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Customer.class)
//                .addAnnotatedClass(Product.class)
//                .addAnnotatedClass(Transaction.class)
                .buildSessionFactory();

        EntityManager em = emFactory.createEntityManager();

        em.getTransaction().begin();

        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer("Vanya"));
        customers.add(new Customer("Ivan"));
        customers.add(new Customer("Leva"));
        customers.add(new Customer("Alex"));
        customers.add(new Customer("Stepa"));
        customers.forEach(em::persist);
        em.getTransaction().commit();

        List<Product> products = new ArrayList<>();
        products.add(new Product("Orange",140));
        products.add(new Product("Apple",140));
        products.add(new Product("Bread",40));
        products.add(new Product("Juice",56));
        products.add(new Product("Bublic",20));
        products.add(new Product("Shugar",45));
        products.forEach(em::persist);
        em.getTransaction().commit();

        List<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(customers.get(1), products.get(1)));
        transactions.add(new Transaction(customers.get(1), products.get(2)));
        transactions.add(new Transaction(customers.get(2), products.get(1)));
        transactions.add(new Transaction(customers.get(2), products.get(3)));
        transactions.add(new Transaction(customers.get(3), products.get(4)));
        transactions.add(new Transaction(customers.get(3), products.get(5)));
        transactions.add(new Transaction(customers.get(3), products.get(6)));

        transactions.forEach(em::persist);

        em.getTransaction().commit();

        // SELECT for one to many
//        User user = em.find(User.class, 1L);
//        user.getContacts().forEach(System.out::println);

        List<Transaction> transactions1 = em.createQuery(
                "select c from Transaction t " +
                        "inner join Customer c on t.id = c.id " +
                        "where c.id = 1", Transaction.class)
                .getResultList();

        transactions1.forEach(System.out::println);

        List<Transaction> transactions2 = em.createQuery(
                "select p from Transaction t " +
                        "inner join Product p on t.id = p.id " +
                        "where p.id = 3", Transaction.class)
                .getResultList();

        transactions2.forEach(System.out::println);

//        List<String> usernames = em.createQuery(
//                "select new java.lang.String(u.username) from User u " +
//                        "inner join Contact c on u.id = c.user.id " +
//                        "where c.type = 'mobile phone'", String.class)
//                .getResultList();
//
//        System.out.println(usernames);

        em.close();
    }
}
