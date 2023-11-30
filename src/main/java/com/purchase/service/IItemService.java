package com.purchase.service;

import com.purchase.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IItemService {
    Page<Item> findPaginated(Pageable pageable, String term);
    void save(Item item);
    Optional<Item> findItemById(Long id);
    void delete(Long id);



}
