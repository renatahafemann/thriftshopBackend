package thriftshop.thriftshopapi.Repository;

import org.springframework.data.repository.CrudRepository;

import thriftshop.thriftshopapi.Model.Client;

public interface ClientRepository extends CrudRepository<Client, Long>{

    
}
