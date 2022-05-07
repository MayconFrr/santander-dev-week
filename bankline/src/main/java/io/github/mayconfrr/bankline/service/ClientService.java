package io.github.mayconfrr.bankline.service;

import io.github.mayconfrr.bankline.repository.ClientRepository;
import io.github.mayconfrr.bankline.dto.ClientDto;
import io.github.mayconfrr.bankline.model.Account;
import io.github.mayconfrr.bankline.model.Client;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client saveClient(ClientDto clientDto) {
        Client client = Client.builder()
                .cpf(clientDto.cpf())
                .name(clientDto.name())
                .account(Account.builder()
                        .number(new Date().getTime())
                        .balance(BigDecimal.ZERO)
                        .build())
                .build();

        return clientRepository.save(client);
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
}
