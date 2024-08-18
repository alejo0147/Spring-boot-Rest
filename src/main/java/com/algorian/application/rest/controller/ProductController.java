package com.algorian.application.rest.controller;

import com.algorian.application.rest.controller.dto.ProductDTO;
import com.algorian.application.rest.entities.Maker;
import com.algorian.application.rest.entities.Product;
import com.algorian.application.rest.service.IMakerService;
import com.algorian.application.rest.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final IProductService _productService;
    private final IMakerService _makerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Product> find = _productService.findById(id);
        if (find.isPresent()) {
            Product product = find.get();

            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .maker(product.getMaker())
                    .build();

            return ResponseEntity.ok(productDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<ProductDTO> productDTOList = _productService.findAll()
                .stream()
                .map(product -> ProductDTO.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .price(product.getPrice())
                        .maker(product.getMaker())
                        .build()
                ).toList();

        return ResponseEntity.ok(productDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody ProductDTO productDTO) {
        if (productDTO.getName().isBlank() ||
                Objects.isNull(productDTO.getPrice()) ||
                Objects.isNull(productDTO.getMaker())) {
            return ResponseEntity.badRequest().body("El nombre, precio y fabricante son obligatorios");
        }

        Long makerId = productDTO.getMaker().getId();
        Optional<Maker> makerOptional = _makerService.findById(makerId);
        if (makerOptional.isEmpty())
            return ResponseEntity.badRequest().body("El fabricante con ID:" + makerId + " no existe");


        Optional<Product> existingProduct = _productService.findByName(productDTO.getName());
        if (existingProduct.isPresent())
            return ResponseEntity.badRequest().body("Ya existe un producto con el nombre: " + productDTO.getName());

        Product product = Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .maker(makerOptional.get())
                .build();

        _productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO, @PathVariable Long id) {
        if (productDTO.getName().isBlank() ||
                Objects.isNull(productDTO.getPrice()) ||
                Objects.isNull(productDTO.getMaker())) {
            return ResponseEntity.badRequest().body("El nombre, precio y fabricante son obligatorios");
        }
        Long makerId = productDTO.getMaker().getId();
        Optional<Maker> makerOptional = _makerService.findById(makerId);
        if (makerOptional.isEmpty())
            return ResponseEntity.badRequest().body("El fabricante con ID:" + makerId + " no existe");

        Optional<Product> find = _productService.findById(id);
        if (find.isPresent()) {
            Product product = find.get();

            if (!product.getName().equals(productDTO.getName())) {
                Optional<Product> existingProduct = _productService.findByName(productDTO.getName());
                if (existingProduct.isPresent()) {
                    return ResponseEntity.badRequest().body("Ya existe un producto con el nombre: " + productDTO.getName());
                }
            }

            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setMaker(makerOptional.get());

            _productService.save(product);
            return ResponseEntity.ok("Producto actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        Optional<Product> find = _productService.findById(id);
        if (find.isEmpty()) return ResponseEntity.notFound().build();

        _productService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
