package com.example.projectstore.api.order;

import com.example.projectstore.api.exceptions.DuplicatedException;
import com.example.projectstore.api.exceptions.NotFoundException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.responses.OrderLineResponse;
import com.example.projectstore.api.system.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderLineService {
    private final OrderLineRepository orderLineRepository;
    private final ModelMapper modelMapper;
    private final ProductsDBRepository productsDBRepository;
    private final JwtService jwtService;
    private final UserRepository userRepository;


    public OrderLineService(OrderLineRepository orderLineRepository,UserRepository userRepository, ProductsDBRepository productsDBRepository, JwtService jwtService, OrderRepository orderRepository) {
        this.orderLineRepository = orderLineRepository;
        this.productsDBRepository = productsDBRepository;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.modelMapper = new ModelMapper();
    }
    private Long getUserIdByToken(String token){
        String email = jwtService.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new NotFoundException("Usuario nao encontrado")).getId();
    }

    public OrderLineResponse save(String token, Long productId, Long quantity) {
        Long userId = getUserIdByToken(token);
        if (orderLineRepository.findAll().stream().anyMatch(orderLine ->
                orderLine.getProductId().equals(productId) && !orderLine.getOrdered())) {
           throw new DuplicatedException("Linha de pedido duplicada"); //ESTOURAR EXCESSAO
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

    public OrderLineResponse delete(String token, Long productId) {
        Long userId = getUserIdByToken(token);
        OrderLine orderLine = findById(userId, productId);
        orderLineRepository.delete(orderLine);
        return modelMapper.map(orderLine, OrderLineResponse.class);
    }
    public OrderLineResponse update(String token, Long productId, Long quantity) {
        Long userId = getUserIdByToken(token);
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
               .findFirst().orElseThrow(() -> new NotFoundException("Linha de pedido nao encontrada"));
    }

    private String getProductEntityTitle(Long productId){
        return productsDBRepository.findProductByProductDTOid(productId).orElseThrow().getTitle();
    }


}
