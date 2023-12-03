package com.purchase.controller;

import com.purchase.dto.ClientItemsResponseDTO;
import com.purchase.dto.ItemDTO;
import com.purchase.entity.Client;
import com.purchase.entity.Item;
import com.purchase.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/shoppingCart/clients")
public class ClientController {

    private final IClientService clientService;

    @Autowired
    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public ResponseEntity<Page<Client>> getAllClients(Pageable pageable,
                                                      @RequestParam(name = "term", required = false) String term) {
        Page<Client> clients = clientService.findPaginated(pageable, term);
        return ResponseEntity.ok(clients);
    }


@GetMapping("/{clientId}/items")
public ResponseEntity<ClientItemsResponseDTO> getClientItems(@PathVariable Long clientId) {
    Client client = clientService.findClientById(clientId);
    if (client == null) {
        return ResponseEntity.notFound().build();
    }

    List<Item> items = clientService.findItemsByClientId(clientId);

    ClientItemsResponseDTO responseDTO = new ClientItemsResponseDTO();
    responseDTO.setClientId(client.getId());

    // Set the name field, using an empty string if client name is null
    responseDTO.setName(client.getName() != null ? client.getName() : "" );
    responseDTO.setSurname(client.getSurname() != null ? client.getSurname() : "" );

    List<ItemDTO> itemDTOs = items.stream()
            .map(item -> {
                ItemDTO itemDTO = new ItemDTO();
                itemDTO.setItemId(item.getId());
                itemDTO.setItemName(item.getName());
                itemDTO.setPrice(item.getPrice());
                itemDTO.setCode(item.getCode());
                return itemDTO;
            })
            .collect(Collectors.toList());

    responseDTO.setItems(itemDTOs);

    return ResponseEntity.ok(responseDTO);
}
}
