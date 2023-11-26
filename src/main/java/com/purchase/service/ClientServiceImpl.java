package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.ClientItem;
import com.purchase.entity.Item;
import com.purchase.entity.Order;
import com.purchase.repository.ClientRepository;
import com.purchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        // Fetch all orders
        List<Order> orders = orderRepository.findAll();

        // Group orders by Client
        Map<Client, List<Order>> clientOrdersMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getClient));

        // Filter for specific client and map to ClientItem
        return clientOrdersMap.entrySet().stream()
                .filter(entry -> entry.getKey().getId().equals(id))
                .map(entry -> new ClientItem(entry.getKey(), mapOrdersToItems(entry.getValue())))
                .collect(Collectors.toList());
    }

    private List<Item> mapOrdersToItems(List<Order> orders) {
        return orders.stream()
                .map(Order::getItem)
                .collect(Collectors.toList());
    }

}
