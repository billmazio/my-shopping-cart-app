package com.purchase.service;

import com.purchase.entity.Item;
import com.purchase.repository.ItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImpl implements IItemService {
    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Page<Item> findPaginated(Pageable pageable, String term) {
        return page(pageable, term);
    }

    @Override
    public void save(Item item) {
       itemRepository.save(item);
    }

    @Override
    public Optional<Item> findItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);
        return item;
    }

    @Override
    public void delete(Long id) {
        itemRepository.deleteById(id);
    }
    private Page<Item> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        ArrayList<Item> items;
        List<Item> list;

        if (term == null) {
            // If 'term' is null, retrieve all items.
            items = (ArrayList<Item>) itemRepository.findAll();
        } else {
            // If 'term' is provided, retrieve items by name containing the term.
            items = (ArrayList<Item>) itemRepository.findItemsByName(term);
        }

        if (items.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, items.size());
            list = items.subList(startItem, toIndex);
        }
        // Create a paginated Page object for items.
        Page<Item> itemPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), items.size());

        return itemPage;
    }

}
