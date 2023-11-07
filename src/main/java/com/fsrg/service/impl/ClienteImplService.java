package com.fsrg.service.impl;

import com.fsrg.model.dao.ClienteDAO;
import com.fsrg.model.dto.ClienteDTO;
import com.fsrg.model.entity.Cliente;
import com.fsrg.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteImplService implements IClienteService {
    @Autowired
    private ClienteDAO clienteDAO;

    @Override
    public List<Cliente> listAll() {
       return (List<Cliente>) clienteDAO.findAll();
    }

    @Transactional
    @Override
    public Cliente save(ClienteDTO clienteDTO) {
        Cliente cliente  = Cliente.builder()
                .idCliente(clienteDTO.getIdCliente())
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .correo(clienteDTO.getCorreo())
                .fechaRegistro(clienteDTO.getFechaRegistro())
                .build();
        return clienteDAO.save(cliente);
    }
//    Cuando se hace un busqueda o consulta se manda solo tipo lectura
    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDAO.findById(id).orElse(null);
    }


    @Transactional
    @Override
    public void delete(Cliente clienteDTO) {
        Cliente cliente  = Cliente.builder()
                .idCliente(clienteDTO.getIdCliente())
                .nombre(clienteDTO.getNombre())
                .apellido(clienteDTO.getApellido())
                .correo(clienteDTO.getCorreo())
                .fechaRegistro(clienteDTO.getFechaRegistro())
                .build();
         clienteDAO.delete(cliente);
    }

    @Override
    public boolean existsById(Integer id) {
        return clienteDAO.existsById(id);
    }
}
