package com.purchase.service;

import com.purchase.entity.Client;
import com.purchase.entity.Item;
import com.purchase.entity.Order;
import com.purchase.repository.ClientRepository;
import com.purchase.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Collections;
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
    public Page<Client> findPaginated(Pageable pageable, String term) {
        return page(pageable,term);
    }

    @Override
    @Transactional
    public void createOrder(Client client) {
        clientRepository.save(client);


    }
    @Override
    public List<Item> findItemsByClientId(Long clientId) {
        // Fetch all orders and filter them for the given student ID
        return orderRepository.findAll().stream()
                .filter(order -> order.getClient().getId().equals(clientId))
                .map(Order::getItem)
                .distinct() // Ensure each seminar is listed only once
                .collect(Collectors.toList());
    }

    private Page<Client> page(Pageable pageable, String term) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Client> clients;

        if (term == null) {
            // If 'term' is null, retrieve all clients.
            clients = (List<Client>) clientRepository.findAll();
        } else {
            // Implement your filtering logic based on 'term'.
            // For example, you can search for clients by name or other criteria.
            LocalDate date = LocalDate.parse(term.trim());
            clients = getClientsByOrderDate(date); // Update this method for Clients
        }

        List<Client> list;
        if (clients.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, clients.size());
            list = clients.subList(startItem, toIndex);
        }

        // Create a paginated Page object for clients.
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), clients.size());
    }

    private List<Client> getClientsByOrderDate(LocalDate date) {
        List<Order> orders = orderRepository.findByOrderDate(date);

        // Extract clients from the orders for the given date.
        List<Client> clients = orders.stream()
                .map(Order::getClient) // Assuming Order has a getClient method
                .collect(Collectors.toList());

        return clients;
    }



}
