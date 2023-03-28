package com.example.projectstore.api.order;

import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.responses.OrderLineResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;
    private final ProductsDBRepository productsDBRepository;


    public OrderLineService(OrderLineRepository orderLineRepository, ProductsDBRepository productsDBRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productsDBRepository = productsDBRepository;
        this.modelMapper = new ModelMapper();
    }

    public OrderLineResponse save(Long userId, Long productDTOId, Long quantity) {
        if (findById(userId, productDTOId).isEmpty()){
        OrderLine orderLine = new OrderLine();
        orderLine.setQuantity(quantity);
        orderLine.setUserId(userId);
        orderLine.setProductId(getProductEntityId(productDTOId));
        orderLine.setTitle(getProductEntityTitle(productDTOId));
        orderLineRepository.save(orderLine);
            return modelMapper.map(orderLine, OrderLineResponse.class);
        }else return update(userId, productDTOId, quantity);
    }

    public OrderLineResponse delete(Long userId, Long productDTOId) {
        OrderLine orderLine = findById(userId, productDTOId).orElseThrow();
        orderLineRepository.delete(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }
    public OrderLineResponse update(Long userId, Long productDTOId, Long quantity) {
            OrderLine OrderLine = findById(userId, productDTOId).orElseThrow();
            OrderLine.setQuantity(quantity);
            orderLineRepository.save(OrderLine);
        return modelMapper.map(OrderLine, OrderLineResponse.class);
    }

    private Optional<OrderLine> findById(Long userId, Long productDTOId){
       return orderLineRepository.findAll().stream().filter(orderLine ->
               orderLine.getUserId().equals(userId) && orderLine.getProductId().equals(getProductEntityId(productDTOId))).findFirst();
    }

    private Long getProductEntityId(Long productDTOId){
        return productsDBRepository.findProductByProductDTOid(productDTOId).orElseThrow().getId();
    }
    private String getProductEntityTitle(Long productDTOId){
        return productsDBRepository.findProductByProductDTOid(productDTOId).orElseThrow().getTitle();

    }
}
