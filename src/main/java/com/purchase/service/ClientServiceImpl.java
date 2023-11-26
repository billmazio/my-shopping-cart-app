package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.ClientItem;
import com.purchase.entity.Item;
import com.purchase.repository.ClientRepository;
import com.purchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class ClientServiceImpl implements IClientService{

    private final ClientRepository clientRepository;

    private final OrderRepository orderRepository;
    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, OrderRepository orderRepository) {
        this.clientRepository = clientRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<ClientItem> findPaginated(Pageable pageable, String term) {
        return page(pageable,term);
    }

    @Override
    @Transactional
    public void createOrder(Client client, List<Item> items) {
        clientRepository.save(client);


    }

    @Override
    public List<ClientItem> findOrdersByClientId(Long id) {
        return null;
    }
}
