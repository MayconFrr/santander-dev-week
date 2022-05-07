package io.github.mayconfrr.bankline.controller;

import io.github.mayconfrr.bankline.dto.ClientDto;
import io.github.mayconfrr.bankline.model.Client;
import io.github.mayconfrr.bankline.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        return ResponseEntity.of(clientService.getClientById(id));
    }

    @PostMapping
    public ResponseEntity<Void> saveClient(ClientDto clientDto) {
        Client client = clientService.saveClient(clientDto);
        return ResponseEntity.created(
                MvcUriComponentsBuilder.fromController(getClass())
                        .path("/{id}")
                        .build(client.getId())
        ).build();
    }
}
