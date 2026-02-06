package com.inventory.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class App {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // INSERT
        Product p1 = new Product("Laptop", "HP Laptop", 55000, 10);
        Product p2 = new Product("Mouse", "Wireless Mouse", 700, 40);
        session.save(p1);
        session.save(p2);

        // READ
        Product p = session.get(Product.class, 1);
        if (p != null)
            System.out.println(p.getName());

        // UPDATE
        if (p != null) {
            p.setPrice(52000);
            p.setQuantity(8);
            session.update(p);
        }

        // DELETE
        Product d = session.get(Product.class, 2);
        if (d != null)
            session.delete(d);

        tx.commit();
        session.close();

        System.out.println("CRUD OPERATIONS DONE");
    }
}
