package com.example.projectstore.api.controller;

import com.example.projectstore.api.model.OrderLine;
import com.example.projectstore.api.services.OrderLineService;
import com.example.projectstore.api.responses.OrderLineResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/orderline")
@RequiredArgsConstructor
@Log4j2
@PreAuthorize("hasAnyRole('ADMIN', 'USER')")
public class OrderLineRestController {

  private final OrderLineService orderLineService;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/{productId}", params = "quantity")
    public OrderLineResponse create(HttpServletRequest request, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.save(request, productId, quantity);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/{productId}", params = "quantity")
    public OrderLineResponse update(HttpServletRequest request, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.update(request, productId, quantity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{productId}")
    public OrderLineResponse delete(HttpServletRequest request ,@PathVariable Long productId){
        return orderLineService.delete(request, productId);
    }

    @GetMapping
    public List<OrderLineResponse> delete(HttpServletRequest request){
        return orderLineService.findAllAvaliable(request);
    }



}

