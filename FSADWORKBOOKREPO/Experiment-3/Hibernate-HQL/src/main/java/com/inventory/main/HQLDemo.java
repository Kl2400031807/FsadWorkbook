package com.inventory.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // 🔹 Insert extra products (5–8)
        session.save(new Product("Keyboard", "Electronics", 1200, 20));
        session.save(new Product("Monitor", "Electronics", 9000, 5));
        session.save(new Product("Chair", "Furniture", 3500, 15));
        session.save(new Product("Table", "Furniture", 5500, 8));
        session.save(new Product("Pen", "Stationery", 20, 200));

        tx.commit();

        /* ================= SORTING ================= */

        System.out.println("\n--- Price Ascending ---");
        Query<Product> q1 = session.createQuery(
                "from Product order by price asc", Product.class);
        q1.list().forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        System.out.println("\n--- Price Descending ---");
        Query<Product> q2 = session.createQuery(
                "from Product order by price desc", Product.class);
        q2.list().forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        System.out.println("\n--- Quantity Highest First ---");
        Query<Product> q3 = session.createQuery(
                "from Product order by quantity desc", Product.class);
        q3.list().forEach(p -> System.out.println(p.getName()+" "+p.getQuantity()));

        /* ================= PAGINATION ================= */

        System.out.println("\n--- First 3 Products ---");
        Query<Product> q4 = session.createQuery("from Product", Product.class);
        q4.setFirstResult(0);
        q4.setMaxResults(3);
        q4.list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Next 3 Products ---");
        Query<Product> q5 = session.createQuery("from Product", Product.class);
        q5.setFirstResult(3);
        q5.setMaxResults(3);
        q5.list().forEach(p -> System.out.println(p.getName()));

        /* ================= AGGREGATES ================= */

        Long totalCount = (Long) session.createQuery(
                "select count(*) from Product").uniqueResult();
        System.out.println("\nTotal Products: " + totalCount);

        Long availableCount = (Long) session.createQuery(
                "select count(*) from Product where quantity > 0").uniqueResult();
        System.out.println("Products with quantity > 0: " + availableCount);

        List<Object[]> groupCount = session.createQuery(
                "select description, count(*) from Product group by description")
                .list();
        System.out.println("\nCount by Description:");
        for (Object[] obj : groupCount)
            System.out.println(obj[0] + " -> " + obj[1]);

        Object[] minMax = (Object[]) session.createQuery(
                "select min(price), max(price) from Product").uniqueResult();
        System.out.println("\nMin Price: " + minMax[0]);
        System.out.println("Max Price: " + minMax[1]);

        /* ================= GROUP BY ================= */

        System.out.println("\n--- Group By Description ---");
        List<Object[]> grp = session.createQuery(
                "select description, count(*) from Product group by description")
                .list();
        for (Object[] o : grp)
            System.out.println(o[0] + " : " + o[1]);

        /* ================= WHERE ================= */

        System.out.println("\n--- Products Price 1000 to 6000 ---");
        Query<Product> q6 = session.createQuery(
                "from Product where price between 1000 and 6000", Product.class);
        q6.list().forEach(p -> System.out.println(p.getName()+" "+p.getPrice()));

        /* ================= LIKE ================= */

        System.out.println("\n--- Name starts with 'M' ---");
        session.createQuery(
                "from Product where name like 'M%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Name ends with 'r' ---");
        session.createQuery(
                "from Product where name like '%r'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Name contains 'ta' ---");
        session.createQuery(
                "from Product where name like '%ta%'", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        System.out.println("\n--- Name length = 3 ---");
        session.createQuery(
                "from Product where length(name)=3", Product.class)
                .list().forEach(p -> System.out.println(p.getName()));

        session.close();
    }
}
