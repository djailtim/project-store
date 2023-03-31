package com.example.projectstore.api.controller;
import com.example.projectstore.api.services.OrderService;
import com.example.projectstore.api.responses.OrderResponse;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping
    public List<OrderResponse> getAll(HttpServletRequest request) {
        return orderService.getAll(request);
    }

    @GetMapping(value = "/{orderId}")
    public OrderResponse getById(@PathVariable Long orderId, HttpServletRequest request) {
        return orderService.getById(orderId, request);
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{orderId}")
    public void delete(@PathVariable Long orderId, HttpServletRequest request) {
        orderService.delete(orderId, request);

    }
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/placeOrder")
    public OrderResponse placeOrder(HttpServletRequest request) {
        return orderService.placeOrder(request);
    }
}
