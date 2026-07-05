package br.com.organizacasa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> resourceNotFound(ResourceNotFoundException ex){

        Map<String, Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status",404);
        erro.put("erro","Recurso não encontrado");
        erro.put("mensagem",ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> business(BusinessException ex){

        Map<String,Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status",400);
        erro.put("erro","Regra de negócio");
        erro.put("mensagem",ex.getMessage());

        return ResponseEntity.badRequest().body(erro);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> validation(MethodArgumentNotValidException ex){

        Map<String,Object> erro = new HashMap<>();

        erro.put("timestamp", LocalDateTime.now());
        erro.put("status",400);
        erro.put("erro","Validação");

        Map<String,String> campos = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(field ->
                        campos.put(field.getField(),field.getDefaultMessage()));

        erro.put("campos",campos);

        return ResponseEntity.badRequest().body(erro);

    }

}