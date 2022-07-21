package com.algaworks.algalog.controller;

import com.algaworks.algalog.model.Client;
import com.algaworks.algalog.repository.ClientRepository;
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
        return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Client> updateClient(@PathVariable Long clientId, @RequestBody @Valid Client client) {
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        client.setId(clientId);

        return ResponseEntity.ok(clientRepository.save(client));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long clientId){
        if (!clientRepository.existsById(clientId)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
