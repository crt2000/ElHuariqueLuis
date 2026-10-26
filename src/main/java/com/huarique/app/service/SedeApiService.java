package com.huarique.app.service;

import com.huarique.app.entity.Sede;
import com.huarique.app.repository.SedeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SedeApiService {

    private final SedeRepository sedeRepository;

    public SedeApiService(SedeRepository sedeRepository) {
        this.sedeRepository = sedeRepository;
    }

    public List<Sede> listar() {
        return sedeRepository.findAll();
    }

    public Optional<Sede> buscarPorId(Long id) {
        return sedeRepository.findById(id);
    }
}
