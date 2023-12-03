package com.purchase.dto;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class ClientItemsResponseDTO {

    private Long clientId;
    @NotBlank(message = "name shouldn't be null")
    private String name;

    @NotBlank(message = "surname shouldn't be null")
    private String surname;


    private List<ItemDTO> items;


    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ClientItemsResponseDTO() {

    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
}
