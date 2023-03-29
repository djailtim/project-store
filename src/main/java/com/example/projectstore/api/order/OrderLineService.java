package com.example.projectstore.api.order;

import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.responses.OrderLineResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;
    private final ProductsDBRepository productsDBRepository;


    public OrderLineService(OrderLineRepository orderLineRepository, ProductsDBRepository productsDBRepository, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productsDBRepository = productsDBRepository;
        this.modelMapper = new ModelMapper();
    }

    public OrderLineResponse save(Long userId, Long productId, Long quantity) {
        if (orderLineRepository.findAll().stream().anyMatch(orderLine ->
                orderLine.getProductId().equals(productId) && !orderLine.getOrdered())) {
            return null; //ESTOURAR EXCESSAO
        }

        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(quantity);
        orderLine.setUserId(userId);
        orderLine.setProductId(productId);
        orderLine.setTitle(getProductEntityTitle(productId));
        orderLine.setOrdered(false);
        orderLineRepository.save(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }

    public OrderLineResponse delete(Long userId, Long productId) {
        OrderLine orderLine = findById(userId, productId);
        orderLineRepository.delete(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }
    public OrderLineResponse update(Long userId, Long productId, Long quantity) {
            OrderLine OrderLine = findById(userId, productId);
            OrderLine.setQuantity(quantity);
            orderLineRepository.save(OrderLine);
        return modelMapper.map(OrderLine, OrderLineResponse.class);
    }

    public void setOrdered(List<OrderLine> orderLineList){
        orderLineList.stream().peek(orderLine -> orderLine.setOrdered(true)).forEach(orderLineRepository::save);
        }

    private OrderLine findById(Long userId, Long productId){
       return orderLineRepository.findAll().stream().filter(orderLine ->
               orderLine.getUserId().equals(userId) && orderLine.getProductId().equals(productId))
               .findFirst().orElseThrow();
    }

    private String getProductEntityTitle(Long productId){
        return productsDBRepository.findProductByProductDTOid(productId).orElseThrow().getTitle();
    }


}
