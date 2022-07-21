package com.algaworks.algalog.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @Embedded
    private Destination destination;

    private BigDecimal fee;

    @JsonProperty(access = Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime orderDate;

    @JsonProperty(access = Access.READ_ONLY)
    private LocalDateTime finishOrder;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Delivery delivery = (Delivery) o;
        return id != null && Objects.equals(id, delivery.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
