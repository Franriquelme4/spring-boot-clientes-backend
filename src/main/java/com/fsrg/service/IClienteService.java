package com.fsrg.service;

import com.fsrg.model.dto.ClienteDTO;
import com.fsrg.model.entity.Cliente;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IClienteService {
    List<Cliente> listAll();

    @Transactional
    Cliente save(ClienteDTO clienteDTO);

    Cliente findById(Integer id);
    void delete(Cliente clienteDTO);

    boolean existsById(Integer id);


}
