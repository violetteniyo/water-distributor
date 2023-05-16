package com.example.inyange.service;

import com.example.inyange.model.Client;

import java.util.List;

public interface ClientService {
    Client saveClient(Client client);
    List<Client> displayClients();
    Client updateClient(Client client);
    Client findClientById(int code);
    void deleteClient(int code);
}
