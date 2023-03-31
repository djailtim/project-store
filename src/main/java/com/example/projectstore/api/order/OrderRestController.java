package com.example.projectstore.api.order;

import com.example.projectstore.api.responses.OrderResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/order")
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class OrderRestController {

    private final OrderService orderService;


    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;

    }

    @GetMapping(headers = "token")
    public List<OrderResponse> getAll(@RequestHeader String token) {
        return orderService.getAll(token);
    }

    @GetMapping(value = "/{orderId}", headers = "token")
    public OrderResponse getById(@PathVariable Long orderId, @RequestHeader String token) {
        return orderService.getById(orderId, token);
    }

    @DeleteMapping(value = "/{orderId}", headers = "token")
    public void delete(@PathVariable Long orderId, @RequestHeader String token) {
        orderService.delete(orderId, token);

    }

    @PostMapping(value = "/placeOrder", headers = "token")
    public OrderResponse placeOrder(@RequestHeader String token) {
        return orderService.placeOrder(token);
    }
}
