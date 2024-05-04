package org.jfm.infra.repository.mapper;

import org.jfm.domain.entities.Cliente;
import org.jfm.infra.repository.entities.ClienteEntity;

import jakarta.enterprise.context.ApplicationScoped;

// TODO: utilizar mapstruct no lugar de mapper de modo manual

@ApplicationScoped
public class ClienteMapper {
    
    public Cliente toDomain(ClienteEntity cliente) {
        Cliente clienteDomain = new Cliente();
        
        clienteDomain.setId(cliente.getId());
        clienteDomain.setNome(cliente.getNome());
        clienteDomain.setCpf(cliente.getCpf());
        clienteDomain.setEmail(cliente.getEmail());
        clienteDomain.setDataCriacao(cliente.getDataCriacao());
        clienteDomain.setDataAtualizacao(cliente.getDataAtualizacao());
        clienteDomain.setAtivo(cliente.getAtivo());

        return clienteDomain;
    };

    public ClienteEntity toDto(Cliente cliente) {
        ClienteEntity clienteDto = new ClienteEntity();
        
        clienteDto.setId(cliente.getId());
        clienteDto.setNome(cliente.getNome());
        clienteDto.setCpf(cliente.getCpf());
        clienteDto.setEmail(cliente.getEmail());
        clienteDto.setDataCriacao(cliente.getDataCriacao());
        clienteDto.setDataAtualizacao(cliente.getDataAtualizacao());
        clienteDto.setAtivo(cliente.getAtivo());

        return clienteDto;
    };
    
}
