package com.algaworks.algalog.api.assembler;

import com.algaworks.algalog.api.model.DeliveryModel;
import com.algaworks.algalog.domain.model.Delivery;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class DeliveryAssembler {

    private final ModelMapper modelMapper;

    public DeliveryModel toModel(Delivery delivery) {
        return modelMapper.map(delivery, DeliveryModel.class);
    }

    public List<DeliveryModel> toCollectionModel(List<Delivery> deliveryList) {
        return deliveryList.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
