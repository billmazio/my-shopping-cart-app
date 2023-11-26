package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.ClientItem;
import com.purchase.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IClientService {
     Page<ClientItem> findPaginated(Pageable pageable, String term);

     void createOrder(Client client, List<Item> items);

     List<ClientItem> findOrdersByClientId(Long id);

}

