package com.example.projectstore.api.controller;

import com.example.projectstore.api.model.OrderLine;
import com.example.projectstore.api.services.OrderLineService;
import com.example.projectstore.api.responses.OrderLineResponse;
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
    @PostMapping(value = "/{productId}", params = "quantity", headers = "token")
    public OrderLineResponse create(@RequestHeader String token, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.save(token, productId, quantity);
    }
    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/{productId}", params = "quantity", headers = "token")
    public OrderLineResponse update(@RequestHeader String token, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.update(token, productId, quantity);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{productId}", headers = "token")
    public OrderLineResponse delete(@RequestHeader String token,@PathVariable Long productId){
        return orderLineService.delete(token, productId);
    }

    @GetMapping(headers = "token")
    public List<OrderLineResponse> delete(@RequestHeader String token){
        return orderLineService.findAllAvaliable(token);
    }



}

