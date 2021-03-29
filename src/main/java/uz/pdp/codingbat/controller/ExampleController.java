package uz.pdp.codingbat.controller;

import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.entity.Example;
import uz.pdp.codingbat.payload.ExampleDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.service.ExampleService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/example")
public class ExampleController {

    final ExampleService exampleService;

    public ExampleController(ExampleService exampleService) {
        this.exampleService = exampleService;
    }

    @GetMapping
    public ResponseEntity<List<Example>> getExamples(){
        List<Example> examples = exampleService.getAll();
        return ResponseEntity.ok(examples);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Example> getExample(@PathVariable Integer id){
        Example example = exampleService.getOne(id);
        return ResponseEntity.ok(example);
    }

    @PostMapping
    public ResponseEntity<Response> addExample(@Valid @RequestBody ExampleDto exampleDto){
        Response response = exampleService.add(exampleDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateExample(@PathVariable Integer id, @Valid @RequestBody ExampleDto exampleDto){
        Response response = exampleService.update(id, exampleDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTask(@PathVariable Integer id){
        Response response = exampleService.delete(id);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.NO_CONTENT:HttpStatus.CONFLICT).body(response);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
