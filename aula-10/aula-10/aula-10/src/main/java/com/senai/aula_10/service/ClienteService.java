package com.senai.aula_10.service;


import com.senai.aula_10.exception.EmailJaCadastradoException;
import com.senai.aula_10.model.Cliente;
import com.senai.aula_10.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
@Validated
public class ClienteService {
    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> listarTodos(){
        return clienteRepository.findAll();
    }

    public Cliente salvar(@Valid Cliente cliente){
        if(clienteRepository.findByEmail(cliente.getEmail()).isPresent()){
            throw new EmailJaCadastradoException("Cliente não cadastrado.");
        }
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(@Valid Cliente cliente){
        Cliente clienteAtualizar = clienteRepository.findByEmail(cliente.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));

        clienteAtualizar.setNome(cliente.getNome());
        clienteAtualizar.setEmail(cliente.getEmail());

        return clienteRepository.save(clienteAtualizar);
    }

    public void excluir(Long id){
        Cliente clienteExcluir = clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encotrando."));

        clienteRepository.delete(clienteExcluir);
    }
}
