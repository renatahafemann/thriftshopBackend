package thriftshop.thriftshopapi.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import thriftshop.thriftshopapi.Exceptions.InvalidInputException;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Model.Favorites;
import thriftshop.thriftshopapi.Model.Product;
import thriftshop.thriftshopapi.Repository.ClientRepository;
import thriftshop.thriftshopapi.Repository.FavoritesRepository;
import thriftshop.thriftshopapi.Repository.ProductRepository;

@RestController
@RequestMapping("/favorites")
public class FavoritesController {
    private FavoritesRepository favoritesRepository;
    private ClientRepository clientRepository;
    private ProductRepository productRepository;

    public FavoritesController(FavoritesRepository favoritesRepository, ClientRepository clientRepository, ProductRepository productRepository){
        this.favoritesRepository = favoritesRepository;
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/add")
    public Favorites addFavorite(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId){
        
        Client clientToAdd = this.clientRepository.findById(clientId).get();
        Product productToAdd = this.productRepository.findById(productId).get();

        Favorites favoriteToAdd = new Favorites(clientToAdd, productToAdd);
        return this.favoritesRepository.save(favoriteToAdd);

    }

    @GetMapping("/find")
    public Iterable<Favorites> findAll(){
                
        return this.favoritesRepository.findAll();
        
    }

    @GetMapping("/find/{id}")
    public List<Favorites> findByClientId(@PathVariable("id") Long id){
        Optional<Client> clientToFindOptional = this.clientRepository.findById(id);
        Client clientToFind = clientToFindOptional.get();
        List <Favorites> clientFavorites = this.favoritesRepository.findByClient(clientToFind);

        if(clientFavorites.isEmpty()){
            throw new InvalidInputException("You don't have any favorites yet.");
        }
                
        return clientFavorites;
        
    }



}
