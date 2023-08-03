package thriftshop.thriftshopapi.Exceptions;

public class InvalidInputException extends RuntimeException{
    public InvalidInputException(String message){
        super(message);
    }
    
}
