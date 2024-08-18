package com.algorian.application.rest.controller.dto;

import com.algorian.application.rest.entities.Product;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MakerDTO {

    private Long id;

    private String name;

    private List<Product> productList = new ArrayList<>();

}
