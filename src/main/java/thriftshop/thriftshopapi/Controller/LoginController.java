package thriftshop.thriftshopapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Model.LoginResponse;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {

    @PostMapping
	public ResponseEntity<LoginResponse> performLogin(@Valid @RequestBody Client client){
		return ResponseEntity.ok(new LoginResponse("Success !"));
	}
    
}
