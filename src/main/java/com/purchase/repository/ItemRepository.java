package com.purchase.repository;

import com.purchase.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "SELECT * FROM items WHERE name LIKE %:term%", nativeQuery = true)
    List<Item> findItemsByName(@Param("term")String term);
}
