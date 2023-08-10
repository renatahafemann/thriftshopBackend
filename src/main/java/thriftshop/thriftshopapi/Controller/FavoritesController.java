package thriftshop.thriftshopapi.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

        Optional<Favorites> favoriteToFindOptional = this.favoritesRepository.findByClientAndFavoritesProducts(clientToAdd, productToAdd);

        if(favoriteToFindOptional.isPresent()){
            throw new InvalidInputException("Favorite already exists.");
        }

        Favorites favoriteToAdd = new Favorites(clientToAdd, productToAdd);
        return this.favoritesRepository.save(favoriteToAdd);

    }  

    @GetMapping("/find/{id}")
    public List<Product> findByClientId(@PathVariable("id") Long id){
        
        Client clientToFind = this.clientRepository.findById(id).get();
        List <Favorites> clientFavorites = this.favoritesRepository.findByClient(clientToFind);

        if(clientFavorites.isEmpty()){
            throw new InvalidInputException("You don't have any favorites yet.");
        }

        List<Product> favoritesProducts = new ArrayList<Product>(); 
        for (Favorites favorites : clientFavorites) {
            favoritesProducts.add(favorites.getFavoritesProducts());        
        }
                
        return favoritesProducts;       
    }

    @DeleteMapping("/delete")
    public List<Product> deleteById(@RequestParam("clientId") Long clientId, @RequestParam("productId") Long productId){
        Client clientToFind = this.clientRepository.findById(clientId).get();
        Product productToFind = this.productRepository.findById(productId).get();

        Optional<Favorites> favoriteToFindOptional = this.favoritesRepository.findByClientAndFavoritesProducts(clientToFind, productToFind);
        Favorites favoritesToDelete = favoriteToFindOptional.get();

        this.favoritesRepository.delete(favoritesToDelete);
        List <Favorites> clientFavorites = this.favoritesRepository.findByClient(clientToFind);
        List<Product> favoritesProducts = new ArrayList<Product>(); 
        for (Favorites favorites : clientFavorites) {
            favoritesProducts.add(favorites.getFavoritesProducts());        
        }
                
        return favoritesProducts; 

    }
    




}
