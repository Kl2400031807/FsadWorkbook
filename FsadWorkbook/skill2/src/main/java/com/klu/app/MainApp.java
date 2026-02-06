package com.klu.app;

import com.klu.model.Product;
import com.klu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Scanner;

public class MainApp {

    static SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int choice;

        do 
        {
            System.out.println("...........Product Menu.............");
            System.out.println("1. Insert Product");
            System.out.println("2. Display Product");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Exit");
            System.out.print("Select your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertProduct(sc);
                    break;
                case 2:
                    displayProduct(sc);
                    break;
                case 3:
                    updateProduct(sc);
                    break;
                case 4:
                    deleteProduct(sc);
                    break;
                case 5:
                    System.out.println("Thank You!");
                    break;
                default:
                    System.out.println("Wrong choice!");
            }
        } while (choice != 5);

        factory.close();
        sc.close();
    }

    // ---------------- INSERT ----------------
    static void insertProduct(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter product name: ");
        String name = sc.next();

        System.out.print("Enter description: ");
        String desc = sc.next();

        System.out.print("Enter price: ");
        double price = sc.nextDouble();

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        Product p = new Product(name, desc, price, qty);
        session.persist(p);

        tx.commit();
        session.close();

        System.out.println("Product inserted successfully!");
    }

    // ---------------- DISPLAY ----------------
    static void displayProduct(Scanner sc) {

        Session session = factory.openSession();

        System.out.print("Enter product id: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.println("Name        : " + p.getName());
            System.out.println("Description : " + p.getDescription());
            System.out.println("Price       : " + p.getPrice());
            System.out.println("Quantity    : " + p.getQuantity());
        } else {
            System.out.println("Product not found!");
        }

        session.close();
    }

    // ---------------- UPDATE ----------------
    static void updateProduct(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter product id: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            System.out.print("Enter new price: ");
            p.setPrice(sc.nextDouble());

            System.out.print("Enter new quantity: ");
            p.setQuantity(sc.nextInt());

            tx.commit();
            System.out.println("Product updated successfully!");
        } else {
            System.out.println("Product not found!");
            tx.rollback();
        }

        session.close();
    }

    // ---------------- DELETE ----------------
    static void deleteProduct(Scanner sc) {

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();

        System.out.print("Enter product id: ");
        int id = sc.nextInt();

        Product p = session.get(Product.class, id);

        if (p != null) {
            session.delete(p);
            tx.commit();
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Product not found!");
            tx.rollback();
        }

        session.close();
    }
}
