package com.example.projectstore.api.order;

import com.example.projectstore.api.responses.OrderResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
        this.modelMapper = new ModelMapper();
    }

    public List<OrderResponse> getAll(Long userId) {
        return orderRepository.findAll().stream().filter(order -> order.getUserId().equals(userId))
                .map(order -> modelMapper.map(order, OrderResponse.class)).toList();
    }


    public OrderResponse getById(Long orderId) {
       return modelMapper.map(orderRepository.findById(orderId).orElseThrow(), OrderResponse.class);
    }

    public void delete(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
