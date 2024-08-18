package com.algorian.application.rest.persistence.impl;

import com.algorian.application.rest.entities.Maker;
import com.algorian.application.rest.persistence.IMakerDAO;
import com.algorian.application.rest.repository.IMakerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class MakerDAOImpl implements IMakerDAO {

    private final IMakerRepository _makerRepository;

    @Override
    public List<Maker> findAll() {
        return _makerRepository.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) {
        return _makerRepository.findById(id);
    }

    @Override
    public void save(Maker maker) {
        _makerRepository.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        _makerRepository.deleteById(id);
    }
}
