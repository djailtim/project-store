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

    @PostMapping(value = "/{productDTOId}", params = "quantity")
    public OrderLineResponse create(@PathVariable Long userId,@PathVariable Long productDTOId, @RequestParam Long quantity){
        return orderLineService.save(userId, productDTOId, quantity);
    }

    @PutMapping(value = "/{productDTOId}", params = "quantity")
    public OrderLineResponse update(@PathVariable Long userId,@PathVariable Long productDTOId, @RequestParam Long quantity){
        return orderLineService.update(userId, productDTOId, quantity);
    }


    @DeleteMapping("/{productDTOId}")
    public OrderLineResponse delete(@PathVariable Long userId,@PathVariable Long productDTOId){
        return orderLineService.delete(userId, productDTOId);
    }





}

