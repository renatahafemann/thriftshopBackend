package thriftshop.thriftshopapi.Exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CustomFieldError {
    private String field;
	private String message;
}
