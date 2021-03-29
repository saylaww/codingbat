package uz.pdp.codingbat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.codingbat.entity.Task;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.payload.TaskDto;
import uz.pdp.codingbat.service.TaskService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/task")
public class TaskController {

    final TaskService taskService;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasks(){
        List<Task> tasks = taskService.getAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id){
        Task task = taskService.getOne(id);
        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Response> addTask(@Valid @RequestBody TaskDto taskDto){
        Response response = taskService.add(taskDto);
        return ResponseEntity.status(response.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> updateTask(@PathVariable Integer id, @Valid TaskDto taskDto){
        Response response = taskService.update(id, taskDto);
        return ResponseEntity.status(response.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteTask(@PathVariable Integer id){
        Response response = taskService.delete(id);
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
