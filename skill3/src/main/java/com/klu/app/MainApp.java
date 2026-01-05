package com.klu.app;

import java.util.*;
import org.hibernate.*;
import org.hibernate.query.Query;
import com.klu.model.ProductHQL;
import com.klu.util.HibernateUtil;

public class MainApp {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int ch;
        do {
            System.out.println("\n=== PRODUCT HQL MENU ===");
            System.out.println("1. Insert Sample Products");
            System.out.println("2. Show All Products");
            System.out.println("3. Sort by Price (DESC)");
            System.out.println("4. Sort by Quantity");
            System.out.println("5. Pagination (First 3)");
            System.out.println("6. Aggregate Functions");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            ch = sc.nextInt();

            switch(ch) {
                case 1: insertProducts(); break;
                case 2: showProducts(); break;
                case 3: sortByPrice(); break;
                case 4: sortByQuantity(); break;
                case 5: pagination(); break;
                case 6: aggregate(); break;
            }
        } while(ch != 0);
    }

    // INSERT DATA
    static void insertProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = s.beginTransaction();

        s.persist(new ProductHQL("Laptop","Electronics",58000,8));
        s.persist(new ProductHQL("Mouse","Electronics",500,20));
        s.persist(new ProductHQL("Keyboard","Electronics",1200,15));
        s.persist(new ProductHQL("Chair","Furniture",3500,5));
        s.persist(new ProductHQL("Table","Furniture",8000,2));
        s.persist(new ProductHQL("Book","Stationery",300,50));

        tx.commit();
        s.close();
        System.out.println("Products inserted successfully!");
    }

    // DISPLAY DATA
    static void showProducts() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<ProductHQL> q = s.createQuery("from ProductHQL", ProductHQL.class);

        System.out.println("ID | NAME | PRICE | QTY");
        for(ProductHQL p : q.list()) {
            System.out.println(
                p.getId()+" | "+p.getName()+" | "+p.getPrice()+" | "+p.getQuantity());
        }
        s.close();
    }

    // SORT BY PRICE
    static void sortByPrice() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<ProductHQL> q = s.createQuery(
            "from ProductHQL order by price desc", ProductHQL.class);

        System.out.println("-- Sorted by Price (DESC) --");
        for(ProductHQL p : q.list()) {
            System.out.println(p.getName()+" -> "+p.getPrice());
        }
        s.close();
    }

    // SORT BY QUANTITY
    static void sortByQuantity() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<ProductHQL> q = s.createQuery(
            "from ProductHQL order by quantity desc", ProductHQL.class);

        System.out.println("-- Sorted by Quantity --");
        for(ProductHQL p : q.list()) {
            System.out.println(p.getName()+" -> "+p.getQuantity());
        }
        s.close();
    }

    // PAGINATION
    static void pagination() {
        Session s = HibernateUtil.getSessionFactory().openSession();
        Query<ProductHQL> q = s.createQuery("from ProductHQL", ProductHQL.class);
        q.setFirstResult(0);
        q.setMaxResults(3);

        System.out.println("-- First 3 Products --");
        for(ProductHQL p : q.list()) {
            System.out.println(p.getName());
        }
        s.close();
    }

    // AGGREGATE FUNCTIONS
    static void aggregate() {
        Session s = HibernateUtil.getSessionFactory().openSession();

        Query count = s.createQuery("select count(*) from ProductHQL");
        Query min = s.createQuery("select min(price) from ProductHQL");
        Query max = s.createQuery("select max(price) from ProductHQL");

        System.out.println("Total Products: " + count.uniqueResult());
        System.out.println("Min Price: " + min.uniqueResult());
        System.out.println("Max Price: " + max.uniqueResult());

        s.close();
    }
}
