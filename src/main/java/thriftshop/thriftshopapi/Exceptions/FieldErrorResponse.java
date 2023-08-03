package thriftshop.thriftshopapi.Exceptions;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class FieldErrorResponse {
    private List<CustomFieldError> fieldErrors;

	
}
