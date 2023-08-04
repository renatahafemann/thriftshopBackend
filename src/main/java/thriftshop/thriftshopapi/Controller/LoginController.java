package thriftshop.thriftshopapi.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import thriftshop.thriftshopapi.Model.Client;
import thriftshop.thriftshopapi.Model.Login;
import thriftshop.thriftshopapi.Model.LoginResponse;

@RestController
@RequestMapping("/verification")
public class LoginController {

	
    @PostMapping("/newAccount")
	public ResponseEntity<LoginResponse> checkNewAccount(@Valid @RequestBody Client client){
		return ResponseEntity.ok(new LoginResponse("Success !"));
	}

	@PostMapping("/login")
	public ResponseEntity<LoginResponse> checkLogin(@Valid @RequestBody Login login){
		return ResponseEntity.ok(new LoginResponse("Success !"));
	}
    
}
