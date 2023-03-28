package com.example.projectstore.api.order;
import com.example.projectstore.api.responses.OrderResponse;
import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.responses.OrderLineResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;
    private final ProductsDBRepository productsDBRepository;

    private final OrderRepository orderRepository;

    public OrderLineService(OrderLineRepository orderLineRepository, ProductsDBRepository productsDBRepository, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productsDBRepository = productsDBRepository;
        this.orderRepository = orderRepository;
        this.modelMapper = new ModelMapper();
    }

    public OrderLineResponse save(Long userId, Long productDTOId, Long quantity) {
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(quantity);
        orderLine.setUserId(userId);
        orderLine.setProductId(getProductEntityId(productDTOId));
        orderLine.setTitle(getProductEntityTitle(productDTOId));
        orderLineRepository.save(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }

    public OrderLineResponse delete(Long userId, Long productDTOId) {
        OrderLine orderLine = findById(userId, productDTOId);
        orderLineRepository.delete(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }
    public OrderLineResponse update(Long userId, Long productDTOId, Long quantity) {
            OrderLine OrderLine = findById(userId, productDTOId);
            OrderLine.setQuantity(quantity);
            orderLineRepository.save(OrderLine);
        return modelMapper.map(OrderLine, OrderLineResponse.class);
    }

    private OrderLine findById(Long userId, Long productDTOId){
       return orderLineRepository.findAll().stream().filter(orderLine ->
               orderLine.getUserId().equals(userId) && orderLine.getProductId().equals(getProductEntityId(productDTOId)))
               .findFirst().orElseThrow();
    }

    private Long getProductEntityId(Long productDTOId){
        return productsDBRepository.findProductByProductDTOid(productDTOId).orElseThrow().getId();
    }
    private String getProductEntityTitle(Long productDTOId){
        return productsDBRepository.findProductByProductDTOid(productDTOId).orElseThrow().getTitle();

    }
    private BigDecimal getProductEntityPrice(Long entityId){
       return productsDBRepository.findById(entityId).orElseThrow().getPrice();
    }

    public OrderResponse placeOrder(Long userId) {
        List<OrderLine> orderLineList = orderLineRepository.getAllByUserId(userId);
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderLineList(orderLineList);
        order.setCurrency("USD");
        order.setFinalPrice(
         orderLineList.stream().map(orderLine ->
         getProductEntityPrice(orderLine.getProductId()).multiply(BigDecimal.valueOf(orderLine.getQuantity())))
         .reduce(BigDecimal.ZERO, BigDecimal::add));
        orderRepository.save(order);
        return modelMapper.map(order, OrderResponse.class);
    }
}
