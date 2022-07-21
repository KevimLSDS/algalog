package com.algaworks.algalog.domain.service;

import com.algaworks.algalog.domain.exception.BusinessException;
import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class ClientService {

    ClientRepository clientRepository;

    @Transactional
    public Client save(Client client) {
        boolean existingEmail = clientRepository
                .findByEmail(client.getEmail())
                .stream()
                .anyMatch(existingClient -> existingClient.equals(client));

        if (existingEmail) {
            throw new BusinessException("Já existe um cliente com este email!");
        }

        return clientRepository.save(client);
    }

    @Transactional
    public void delete(Long clientId) {
        clientRepository.deleteById(clientId);
    }

    public Client search(Long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new BusinessException("Cliente não encontrado!"));
    }
}
