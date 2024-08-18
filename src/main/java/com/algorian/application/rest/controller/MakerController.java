package com.algorian.application.rest.controller;

import com.algorian.application.rest.controller.dto.MakerDTO;
import com.algorian.application.rest.entities.Maker;
import com.algorian.application.rest.service.IMakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/maker")
public class MakerController {

    private final IMakerService _makerService;

    @GetMapping("/find/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Maker> makerOptional = _makerService.findById(id);
        if (makerOptional.isPresent()) {
            Maker maker = makerOptional.get();

            MakerDTO makerDTO = MakerDTO.builder()
                    .id(maker.getId())
                    .name(maker.getName())
                    .productList(maker.getProductList())
                    .build();

            return ResponseEntity.ok(makerDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        List<MakerDTO> makerDTOList = _makerService.findAll()
                .stream()
                .map(maker -> MakerDTO.builder()
                        .id(maker.getId())
                        .name(maker.getName())
                        .productList(maker.getProductList())
                        .build())
                .toList();

        return ResponseEntity.ok(makerDTOList);
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody MakerDTO makerDTO) {
        if (makerDTO.getName().isBlank()) return ResponseEntity.badRequest().build();

        _makerService.save(Maker.builder()
                .name(makerDTO.getName())
                .build());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMaker(@RequestBody MakerDTO makerDTO, @PathVariable Long id) {
        Optional<Maker> find = _makerService.findById(id);
        if (find.isPresent()){

            if (makerDTO.getName().isBlank()) {
                return ResponseEntity.badRequest().body("El nombre no puede ser nulo");
            }

            Maker maker = find.get();
            maker.setName(makerDTO.getName());
            _makerService.save(maker);

            return ResponseEntity.ok("Registro Actualizado");
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
            Optional<Maker> find = _makerService.findById(id);
            if (find.isEmpty()) return  ResponseEntity.notFound().build();

            _makerService.deleteById(id);
            return ResponseEntity.noContent().build();
    }

}
