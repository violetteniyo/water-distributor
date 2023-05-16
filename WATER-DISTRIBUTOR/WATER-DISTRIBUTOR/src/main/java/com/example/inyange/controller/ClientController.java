package com.example.inyange.controller;

import com.example.inyange.model.Client;
import com.example.inyange.model.Product;
import com.example.inyange.serviceImplementation.ClientServiceImplementation;
import com.example.inyange.serviceImplementation.ProductServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientServiceImplementation clientService;
    ProductServiceImplementation productService;

    @GetMapping("/clients")
    public String showClients(Model model){
        List<Client> listClients = clientService.displayClients();
        model.addAttribute("listClients", listClients);
        return "allClientsPage";
    }


    @GetMapping("/client/new")
    public String saveClient(Model model){
        Client client = new Client();
        //List<Product> listProduct = productService.displayProducts();
        model.addAttribute("client", client);
        //model.addAttribute("listProduct", listProduct );
        model.addAttribute("pageTitle","Purchase a product");

        return "client";
    }

    @PostMapping("/client/save")
    public String saveClient(Client client, RedirectAttributes ra){
       try {
            clientService.saveClient(client);


       }catch (Exception e){
           e.printStackTrace();
           ra.addFlashAttribute("message", "failed, try again");
       }
       client.setFirstname("");
       client.setLastname("");
       client.setPhone("");
       client.setProductName("");
       client.setQuantity(0);
       client.setAmount("");
       client.setAddress("");
        return "client";
    }

    @GetMapping("/client/edit/{id}")
    public String editClient(@PathVariable("id") int id, Model model, RedirectAttributes ra){
        try {
            Client client = clientService.findClientById(id);
            model.addAttribute("pageTitle", "edit client (ID: "+id+")");
            model.addAttribute("client", client);
            ra.addFlashAttribute("message", "client have successfully updated");
        }catch (Exception ex){
            ra.addFlashAttribute("message", "could not update");
            ex.printStackTrace();
        }
        return "client";
    }

    @GetMapping("/client/delete/{id}")
    public String deleteClient(@PathVariable("id") int id, RedirectAttributes ra){
        try {
            clientService.deleteClient(id);
            ra.addFlashAttribute("message", "Client with ID: "+id+" deleted successfully");
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return "redirect:/clients";
    }
}

