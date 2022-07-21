package com.algaworks.algalog.service;

import com.algaworks.algalog.exception.BusinessException;
import com.algaworks.algalog.model.Client;
import com.algaworks.algalog.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
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
}
