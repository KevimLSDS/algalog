package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.service.DeliverySolicitationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private DeliverySolicitationService deliverySolicitationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@RequestBody Delivery delivery) {
        return deliverySolicitationService.request(delivery);
    }

}
