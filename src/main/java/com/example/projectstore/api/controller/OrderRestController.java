package com.example.projectstore.api.controller;

import com.example.projectstore.api.services.OrderService;
import com.example.projectstore.api.responses.OrderResponse;
import org.springframework.http.HttpStatus;
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
    public OrderResponse getById(@PathVariable Long orderId, @RequestHeader("token") String token) {
        return orderService.getById(orderId, token);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{orderId}", headers = "token")
    public void delete(@PathVariable Long orderId, @RequestHeader("token") String token) {
        orderService.delete(orderId, token);

    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/placeOrder", headers = "token")
    public OrderResponse placeOrder(@RequestHeader("token") String token) {
        return orderService.placeOrder(token);
    }
}
