package com.fsrg.controller;

import com.fsrg.model.dto.ClienteDTO;
import com.fsrg.model.entity.Cliente;
import com.fsrg.model.payload.MensajeResponse;
import com.fsrg.service.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {
    @Autowired
    private IClienteService clienteService;


    @GetMapping("clientes")
    public ResponseEntity<?> showAll(){
        Cliente cliente = null;
        try{
            List<Cliente> clienteList = clienteService.listAll();
            if(clienteList.isEmpty()){
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("No existen regitros").object(null).build(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
//            ClienteDTO clienteDTO = ClienteDTO.builder()
//                    .idCliente(cliente.getIdCliente())
//                    .nombre(cliente.getNombre())
//                    .apellido(cliente.getApellido())
//                    .correo(cliente.getCorreo())
//                    .fechaRegistro(cliente.getFechaRegistro())
//                    .build();
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("").object(clienteList).build(),HttpStatus.OK);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(MensajeResponse.builder().mensaje(exception.getMessage()).object(null).build(),HttpStatus.NOT_FOUND);
        }
    }



    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDTO cliente){
        Cliente clienteSave=null;
        try{
            clienteSave = clienteService.save(cliente);
            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .idCliente(clienteSave.getIdCliente())
                    .nombre(clienteSave.getNombre())
                    .apellido(clienteSave.getApellido())
                    .correo(clienteSave.getCorreo())
                    .fechaRegistro(clienteSave.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Guardado Correctamente").object(clienteDTO).build(),HttpStatus.CREATED) ;
        }catch (DataAccessException exception){
            return new ResponseEntity<>(MensajeResponse.builder().mensaje(exception.getMessage()).object(null).build(),HttpStatus.METHOD_NOT_ALLOWED) ;
        }
    }

    @PutMapping("cliente/{id}")
    public ResponseEntity<?> update(@RequestBody ClienteDTO cliente,@PathVariable Integer id){
        Cliente clienteUpdate = null;
        try{
            if(!clienteService.existsById(id)){
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("No existe un cliente asociado al id proporcionado").object(null).build(),HttpStatus.NOT_FOUND);
            }
            cliente.setIdCliente(id);
            clienteUpdate = clienteService.save(cliente);
            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .idCliente(clienteUpdate.getIdCliente())
                    .nombre(clienteUpdate.getNombre())
                    .apellido(clienteUpdate.getApellido())
                    .correo(clienteUpdate.getCorreo())
                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("Actualizado Correctamente").object(clienteDTO).build(),HttpStatus.CREATED);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(MensajeResponse.builder().mensaje(exception.getMessage()).object(null).build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("cliente/{id}")
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Cliente cliente = null;
        try{
            cliente = clienteService.findById(id);
            if(cliente == null){
                return new ResponseEntity<>(MensajeResponse.builder().mensaje("No existe un cliente asociado al id proporcionado").object(null).build(),HttpStatus.INTERNAL_SERVER_ERROR);
            }
            ClienteDTO clienteDTO = ClienteDTO.builder()
                    .idCliente(cliente.getIdCliente())
                    .nombre(cliente.getNombre())
                    .apellido(cliente.getApellido())
                    .correo(cliente.getCorreo())
                    .fechaRegistro(cliente.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(MensajeResponse.builder().mensaje("").object(clienteDTO).build(),HttpStatus.OK);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(MensajeResponse.builder().mensaje(exception.getMessage()).object(null).build(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try{
            Cliente clienteDelete = clienteService.findById(id);
            clienteService.delete(clienteDelete);
            return new ResponseEntity<>(clienteDelete,HttpStatus.NO_CONTENT);
        }catch (DataAccessException exception){
            return new ResponseEntity<>(MensajeResponse.builder().mensaje(exception.getMessage()).object(null).build(),HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


}
