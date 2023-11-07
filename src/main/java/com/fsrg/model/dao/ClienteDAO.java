package com.fsrg.model.dao;

import com.fsrg.model.dto.ClienteDTO;
import com.fsrg.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface ClienteDAO  extends CrudRepository<Cliente,Integer> {

}
