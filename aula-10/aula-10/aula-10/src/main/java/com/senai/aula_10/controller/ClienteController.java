package com.senai.aula_10.controller;


import com.senai.aula_10.model.Cliente;
import com.senai.aula_10.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<Cliente> listarTodos(){
        return clienteService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> salvar(@Valid @RequestBody Cliente cliente){
        clienteService.salvar(cliente);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(Map.of("mensagem", "Cliente cadastrado com sucesso."));
    }

    @PutMapping
    public ResponseEntity<Map<String, Object>> atualizar(@Valid @RequestBody Cliente cliente){
        clienteService.atualizar(cliente);
        return ResponseEntity.ok()
                .body(Map.of("mensagem", "Cliente atualizado com sucesso."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> excluir(@PathVariable Long id){
        clienteService.excluir(id);
        return ResponseEntity.ok()
                .body(Map.of("mensagem", "Cliente excluído com sucesso."));
    }
}
