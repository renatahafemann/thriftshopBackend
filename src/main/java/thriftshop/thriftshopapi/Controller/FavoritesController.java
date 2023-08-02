package thriftshop.thriftshopapi.Controller;

import thriftshop.thriftshopapi.Repository.FavoritesRepository;

public class FavoritesController {
    private FavoritesRepository favoritesRepository;

    public FavoritesController(FavoritesRepository favoritesRepository){
        this.favoritesRepository = favoritesRepository;
    }




}
