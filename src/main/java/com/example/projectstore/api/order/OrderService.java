package com.example.projectstore.api.order;

import com.example.projectstore.api.exceptions.NotFoundException;
import com.example.projectstore.api.exceptions.UserNotMatchOrderException;
import com.example.projectstore.api.model.User;
import com.example.projectstore.api.repositories.ProductsDBRepository;
import com.example.projectstore.api.repositories.UserRepository;
import com.example.projectstore.api.responses.OrderResponse;
import com.example.projectstore.api.services.AwesomeService;
import com.example.projectstore.api.system.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final OrderLineRepository orderLineRepository;
    private final ProductsDBRepository productsDBRepository;
    private final UserRepository userRepository;
    private final AwesomeService awesomeService;
    private final OrderLineService orderLineService;
    private final JwtService jwtService;

     public OrderService(OrderRepository orderRepository, OrderLineRepository orderLineRepository, ProductsDBRepository productsDBRepository,
                        UserRepository userRepository, AwesomeService awesomeService, OrderLineService orderLineService, JwtService jwtService) {
        this.orderRepository = orderRepository;
        this.modelMapper = new ModelMapper();
        this.orderLineRepository = orderLineRepository;
        this.productsDBRepository = productsDBRepository;
        this.userRepository = userRepository;
        this.awesomeService = awesomeService;
        this.orderLineService = orderLineService;
        this.jwtService = jwtService;
    }

    public List<OrderResponse> getAll(String token) {
        Long userId = getUserIdByToken(token);
        return orderRepository.findAll().stream().filter(order -> order.getUserId().equals(userId))
                .map(order -> modelMapper.map(order, OrderResponse.class)).toList();
    }


    private Long getUserIdByToken(String token){
        String email = jwtService.extractUsername(token);
        Optional<User> userOptional = userRepository.findByEmail(email);
        return userOptional.orElseThrow(() -> new NotFoundException("Usuario nao encontrado")).getId();
    }
    public OrderResponse getById(Long orderId, String token) {
       Long userId = getUserIdByToken(token);
       Order orderOptional = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Pedido nao encontrado"));
       if (orderOptional.getUserId().equals(userId)) return modelMapper.map(orderOptional, OrderResponse.class);
       else throw new UserNotMatchOrderException("Pedido de usuario diferente.");
    }

    public void delete(Long orderId, String token) {
        Long userId = getUserIdByToken(token);
        Order orderOptional = orderRepository.findById(orderId).orElseThrow(() -> new NotFoundException("Pedido nao encontrado"));
        if (orderOptional.getUserId().equals(userId)) orderRepository.deleteById(orderId);
        else throw new UserNotMatchOrderException("Pedido de usuario diferente.");
    }

    private BigDecimal getProductEntityPrice(Long entityId){
        return productsDBRepository.findById(entityId).orElseThrow().getPrice();
    }
    private String getUserCurrency(Long userId){
      return  userRepository.findById(userId).orElseThrow().getCurrency();
    }
    private BigDecimal getUserCurrencyValue(Long userId) {
        if(getUserCurrency(userId).equals("USD")) return BigDecimal.valueOf(1);
        return awesomeService.search("USD-" + getUserCurrency(userId)).getCoefficient();
    }
    public OrderResponse placeOrder(String token) {
        Long userId = getUserIdByToken(token);
        List<OrderLine> orderLineList = orderLineRepository.getAllByUserId(userId).stream().filter(orderLine -> orderLine.getOrdered().equals(false)).toList();
        Order order = new Order();
        order.setUserId(userId);
        order.setOrderLineList(orderLineList);
        order.setCurrency(getUserCurrency(userId));
        order.setFinalPrice(
                orderLineList.stream().map(orderLine ->
                getProductEntityPrice(orderLine.getProductId()).multiply(BigDecimal.valueOf(orderLine.getQuantity())).multiply(getUserCurrencyValue(userId)))
                .reduce(BigDecimal.ZERO, BigDecimal::add).setScale(2, RoundingMode.UP));
        orderRepository.save(order);
        orderLineService.setOrdered(orderLineList);
        return modelMapper.map(order, OrderResponse.class);
    }
}
