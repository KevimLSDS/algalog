package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.model.Delivery;
import com.algaworks.algalog.domain.model.DeliveryStatus;
import com.algaworks.algalog.domain.repository.DeliveryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class DeliverySolicitationService {

    private final DeliveryRepository deliveryRepository;
    private final ClientService clientService;

    @Transactional
    public Delivery request(Delivery delivery) {
        Client client = clientService.search(delivery.getClient().getId());

        delivery.setClient(client);
        delivery.setStatus(DeliveryStatus.PENDENTE);
        delivery.setOrderDate(OffsetDateTime.now());

        return deliveryRepository.save(delivery);
    }
}
