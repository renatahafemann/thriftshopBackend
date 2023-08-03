package thriftshop.thriftshopapi.Controller;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import thriftshop.thriftshopapi.Exceptions.InvalidInputException;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Repository.ClientRepository;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private ClientRepository clientRepository;
    BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

    public ClientController(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @GetMapping
    public Iterable<Client> allClients(){
        return this.clientRepository.findAll();
    }

    @PostMapping("/newAccount")
    public Client addClient(@RequestBody Client client){
        Optional<Client> clientToFindOptional = this.clientRepository.findByEmail(client.getEmail());
        if(clientToFindOptional.isPresent()){
            throw new InvalidInputException("There is an account already linked to this email.");
        }
        
        String password = bc.encode(client.getPassword());
        Client clientToSave = client;
        clientToSave.setPassword(password);

        return this.clientRepository.save(clientToSave);
    }


}
