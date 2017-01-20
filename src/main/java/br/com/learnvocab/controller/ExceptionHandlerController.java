package br.com.learnvocab.controller;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionHandlerController {

//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(Exception.class)
	public ModelAndView trataExceptionGenerica(Exception exception) {
		System.out.println("Erro genérico acontecendo");
		exception.printStackTrace();
		
		ModelAndView modelAndView = new ModelAndView("templates/error");
		modelAndView.addObject("exception", exception);
		return modelAndView;
	}	

}
