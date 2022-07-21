package com.algaworks.algalog.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Embeddable
public class Destination {

    @NotBlank
    @Column(name = "destination_name")
    private String name;

    @NotBlank
    @Column(name = "destination_address")
    private String address;

    @NotBlank
    @Column(name = "destination_number")
    private String number;

    @NotBlank
    @Column(name = "destination_complement")
    private String complement;

    @NotBlank
    @Column(name = "destination_district")
    private String district;
}
