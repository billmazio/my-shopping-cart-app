package com.purchase.dto;

import java.util.List;

public class OrderRequest {
    private Long clientId;
    private List<Long> itemIds;

    // Default constructor for JSON deserialization
    public OrderRequest() {
    }

    public OrderRequest(Long clientId, List<Long> itemIds) {
        this.clientId = clientId;
        this.itemIds = itemIds;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public List<Long> getItemIds() {
        return itemIds;
    }

    public void setItemIds(List<Long> itemIds) {
        this.itemIds = itemIds;
    }
}
