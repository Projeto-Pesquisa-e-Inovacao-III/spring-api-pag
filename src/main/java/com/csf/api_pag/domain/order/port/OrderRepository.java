package com.csf.api_pag.domain.order.port;

import com.csf.api_pag.domain.order.entity.Order;

public interface OrderRepository {
    Order save(Order order);
    boolean existsByGatewayOrderId(String gatewayOrderId);
}
