package com.example.inyange.serviceImplementation;

import com.example.inyange.model.Client;
import com.example.inyange.repository.ClientRepository;
import com.example.inyange.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImplementation implements ClientService {
    @Autowired
    ClientRepository repo;
    @Override
    public Client saveClient(Client client) {
        return repo.save(client);
    }

    @Override
    public List<Client> displayClients() {
        return repo.findAll();
    }

    @Override
    public Client updateClient(Client client) {
        Client savedClient = repo.findById( client.getId()).orElse(null);
        if (savedClient!=null){
            Client updatedClient = new Client();
            updatedClient.setFirstname(client.getFirstname());
            updatedClient.setLastname(client.getLastname());
            updatedClient.setPhone(client.getPhone());
            updatedClient.setAddress(client.getAddress());
            updatedClient.setProductName(client.getProductName());
            updatedClient.setQuantity(client.getQuantity());
            updatedClient.setAmount(client.getAmount());

            return repo.save(updatedClient);
        }
        return repo.save(client);
    }

    @Override
    public Client findClientById(int code) {
        return repo.findById(code).orElse(null);
    }

    @Override
    public void deleteClient(int code) {
        Client savedClient = repo.findById(code).orElse(null);
        if (savedClient!=null){
            repo.delete(savedClient);
        }

    }
}
