package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.domain.model.Client;
import com.algaworks.algalog.domain.repository.ClientRepository;
import com.algaworks.algalog.domain.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/clients")
public class ClientController {

    private ClientRepository clientRepository;
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> listAll() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findClient(@PathVariable Long id) {
        return clientRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody @Valid Client client) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(client));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody @Valid Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);

        return ResponseEntity.ok(clientService.save(client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        clientService.delete(clientId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
