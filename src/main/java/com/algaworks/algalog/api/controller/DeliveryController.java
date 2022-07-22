package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import com.algaworks.algalog.domain.service.DeliverySolicitationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final DeliverySolicitationService deliverySolicitationService;
    private final DeliveryRepository deliveryRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Delivery request(@RequestBody @Valid Delivery delivery) {
        return deliverySolicitationService.request(delivery);
    }

    @GetMapping
    public List<Delivery> list() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{deliveryId}")
    public ResponseEntity<DeliveryModel> search(@PathVariable Long deliveryId) {
        return deliveryRepository.findById(deliveryId)
                .map(delivery -> {
                    DeliveryModel deliveryModel = new DeliveryModel();

                    deliveryModel.setId(delivery.getId());
                    deliveryModel.setClientName(delivery.getClient().getName());
                    deliveryModel.setFee(delivery.getFee());
                    deliveryModel.setStatus(delivery.getStatus());
                    deliveryModel.setOrderDate(delivery.getOrderDate());
                    deliveryModel.setFinishOrder(delivery.getFinishOrder());

                    deliveryModel.getDestination().setName(delivery.getDestination().getName());
                    deliveryModel.getDestination().setAddress(delivery.getDestination().getAddress());
                    deliveryModel.getDestination().setNumber(delivery.getDestination().getNumber());
                    deliveryModel.getDestination().setComplement(delivery.getDestination().getComplement());
                    deliveryModel.getDestination().setDistrict(delivery.getDestination().getDistrict());


                    return ResponseEntity.ok(deliveryModel);
                }).orElse(ResponseEntity.notFound().build());
    }

}
