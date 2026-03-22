package com.klu.repository;

import com.klu.model.Product;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // ---------------- BASIC findBy ----------------
    List<Product> findByCategory(String category);

    Product findByName(String name);

    // ---------------- And / Or ----------------
    List<Product> findByCategoryAndPriceGreaterThan(String category, double price);

    List<Product> findByCategoryOrName(String category, String name);

    // ---------------- Between ----------------
    List<Product> findByPriceBetween(double min, double max);

    // ---------------- Like ----------------
    List<Product> findByNameLike(String pattern);

    // ---------------- GreaterThan ----------------
    List<Product> findByPriceGreaterThan(double price);

    // ---------------- countBy ----------------
    long countByCategory(String category);

    // ---------------- existsBy ----------------
    boolean existsByName(String name);

    // ---------------- deleteBy ----------------
    @Transactional
    void deleteByName(String name);

    // ---------------- JPQL ----------------
    @Query("SELECT p FROM Product p ORDER BY p.price ASC")
    List<Product> sortByPrice();
}
