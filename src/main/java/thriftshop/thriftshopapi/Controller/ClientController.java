package thriftshop.thriftshopapi.Controller;

import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
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
        Optional<Client> clientToFindOptional = this.clientRepository.findByEmailIgnoreCase(client.getEmail());
        if(clientToFindOptional.isPresent()){
            throw new InvalidInputException("There is an account already linked to this email.");
        }
        
        String password = bc.encode(client.getPassword());
        Client clientToSave = client;
        clientToSave.setPassword(password);

        Client savedClient = this.clientRepository.save(clientToSave);
        return savedClient;
    }

    @PostMapping("/login")
    public Client performLogin(@RequestBody Client client){
        Optional<Client> clientToFindOptional = this.clientRepository.findByEmailIgnoreCase(client.getEmail());
        if(!clientToFindOptional.isPresent()){
            throw new InvalidInputException("No account available for the provided email.");
        }
        Client clientToCheck = clientToFindOptional.get();
        
        if(!bc.matches(client.getPassword(), clientToCheck.getPassword())){
            throw new InvalidInputException("Incorrect password.");
        }
		return clientToCheck;
	}
    


}
