package thriftshop.thriftshopapi.Exceptions.Handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import thriftshop.thriftshopapi.Exceptions.CustomFieldError;
import thriftshop.thriftshopapi.Exceptions.FieldErrorResponse;

@ControllerAdvice
public class ControllerErrorHandler extends ResponseEntityExceptionHandler{

    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		FieldErrorResponse fieldErrorResponse = new FieldErrorResponse();

		List<CustomFieldError> fieldErrors = new ArrayList<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			CustomFieldError fieldError = new CustomFieldError();
			fieldError.setField(((FieldError) error).getField());
			fieldError.setMessage(error.getDefaultMessage());
			fieldErrors.add(fieldError);
		});

		fieldErrorResponse.setFieldErrors(fieldErrors);
		return new ResponseEntity<>(fieldErrorResponse, status);
		
	}
}
