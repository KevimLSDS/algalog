package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class DeliverySolicitationService {

    private final DeliveryRepository deliveryRepository;

    @Transactional
    public Delivery request(Delivery delivery) {
        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(LocalDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
