package com.example.projectstore.api.order;

import com.example.projectstore.api.responses.OrderLineResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/users/{userId}/orderline")
@RequiredArgsConstructor
@Log4j2
public class OrderLineRestController {

  private final OrderLineService orderLineService;

    @PostMapping(value = "/{productId}", params = "quantity")
    public OrderLineResponse create(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.save(userId, productId, quantity);
    }

    @PutMapping(value = "/{productId}", params = "quantity")
    public OrderLineResponse update(@PathVariable Long userId, @PathVariable Long productId, @RequestParam Long quantity){
        return orderLineService.update(userId, productId, quantity);
    }


    @DeleteMapping("/{productId}")
    public OrderLineResponse delete(@PathVariable Long userId,@PathVariable Long productId){
        return orderLineService.delete(userId, productId);
    }



}

