package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
     Page<Client> findPaginated(Pageable pageable, String term);

     void createOrder(Client client);

     List<Item> findItemsByClientId(Long id);

}

