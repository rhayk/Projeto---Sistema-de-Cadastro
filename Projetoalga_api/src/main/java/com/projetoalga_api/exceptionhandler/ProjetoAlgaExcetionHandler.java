package com.projetoalga_api.exceptionhandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice       // ele observa toda a aplicação > compartilhar essa classe em toda a aplicação
public class ProjetoAlgaExcetionHandler extends ResponseEntityExceptionHandler{ //captura exceção de entidades
	
	@Autowired
	private MessageSource messageSource;
	
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, //capturar menssagens que n conseguiu ler
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		
		String mensagemUsuario = messageSource.getMessage("menssagem.invalida", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.getCause().toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
	}
	
	@Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
	
		List<Erro> erros = criarListaDeErros(ex.getBindingResult());
		return handleExceptionInternal(ex, erros, headers, HttpStatus.BAD_REQUEST, request);
		}
	
	
	private List<Erro> criarListaDeErros(BindingResult bindngResult) {
		List<Erro> erros = new ArrayList<>();
	
		  for(FieldError fieldError :  bindngResult.getFieldErrors() ) {
			String mensagemUsuario = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = fieldError.toString() ;
		erros.add(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		
		  }
		return erros;
		
	}


	
	public static class Erro{
		
		private String menssagemUsuario;
		private String mensagemDesenvolvedor;
		
		public  Erro(String menssagemUsuario, String menssagemDesevolvedor) {
			this.menssagemUsuario = menssagemUsuario;
			this.mensagemDesenvolvedor = menssagemDesevolvedor;
		}
		
		public String getMenssagemUsuario() {
			return menssagemUsuario;
		}
		
		public String getMensagemDesenvolvedor() {
			return mensagemDesenvolvedor;
		}
	}
}










