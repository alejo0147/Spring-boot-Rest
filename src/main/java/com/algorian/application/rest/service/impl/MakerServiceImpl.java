package com.algorian.application.rest.service.impl;

import com.algorian.application.rest.entities.Maker;
import com.algorian.application.rest.persistence.IMakerDAO;
import com.algorian.application.rest.service.IMakerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MakerServiceImpl implements IMakerService {

    private final IMakerDAO _makerDAO;

    public List<Maker> findAll() {
        return _makerDAO.findAll();
    }

    @Override
    public Optional<Maker> findById(Long id) { return _makerDAO.findById(id); }

    @Override
    public void save(Maker maker) {
        _makerDAO.save(maker);
    }

    @Override
    public void deleteById(Long id) {
        _makerDAO.deleteById(id);
    }
}
