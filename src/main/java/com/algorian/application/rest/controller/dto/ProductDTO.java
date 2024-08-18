package com.algorian.application.rest.controller.dto;

import com.algorian.application.rest.entities.Maker;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductDTO {

    private Long id;

    private String name;

    private BigDecimal price;

    private Maker maker;

}
