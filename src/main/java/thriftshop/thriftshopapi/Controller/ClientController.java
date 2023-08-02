package thriftshop.thriftshopapi.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public Iterable<Client> allClients(){
        return this.clientRepository.findAll();
    }


}
