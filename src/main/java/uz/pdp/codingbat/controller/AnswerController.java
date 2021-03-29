package uz.pdp.codingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.entity.Answer;
import uz.pdp.codingbat.payload.AnswerDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.service.AnswerService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/answer")
public class AnswerController {

    final AnswerService answerService;

    public AnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }

    @GetMapping
    public ResponseEntity<List<Answer>> getAnswers(){
        List<Answer> answers = answerService.getAll();
        return ResponseEntity.ok(answers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Answer> getAnswer(@PathVariable Integer id){
        Answer answer = answerService.getOne(id);
        return ResponseEntity.ok(answer);
    }

    @PostMapping
    public ResponseEntity<Response> addAnswer(@Valid @RequestBody AnswerDto answerDto){
        Response response = answerService.add(answerDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateAnswer(@PathVariable Integer id, @Valid @RequestBody AnswerDto answerDto){
        Response response = answerService.update(id, answerDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteAnswer(@PathVariable Integer id){
        Response response = answerService.delete(id);
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
