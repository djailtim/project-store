package com.example.projectstore.api.order;

import com.example.projectstore.api.repositories.UserRestRepository;
import com.example.projectstore.api.responses.OrderResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/order")
public class OrderRestController {

    private final OrderService orderService;


    public OrderRestController(OrderService orderService){
        this.orderService = orderService;

    }

    @GetMapping
    public List<OrderResponse> getAll(@PathVariable Long userId){
        return orderService.getAll(userId);
    }
    @GetMapping("/{orderId}")
    public OrderResponse getById(@PathVariable Long orderId){
       return orderService.getById(orderId);
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable Long orderId){
        orderService.delete(orderId);

    }
}
