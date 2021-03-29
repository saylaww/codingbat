package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.Example;
import uz.pdp.codingbat.entity.Task;
import uz.pdp.codingbat.payload.ExampleDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.repository.ExampleRepository;
import uz.pdp.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {

    final ExampleRepository exampleRepository;
    final TaskRepository taskRepository;

    public ExampleService(ExampleRepository exampleRepository, TaskRepository taskRepository) {
        this.exampleRepository = exampleRepository;
        this.taskRepository = taskRepository;
    }

    public List<Example> getAll() {
        return exampleRepository.findAll();
    }

    public Example getOne(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return new Example();
        }
        return optionalExample.get();
    }

    public Response add(ExampleDto exampleDto) {
        Example example = new Example();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        Task task = optionalTask.get();

        example.setTask(task);

        exampleRepository.save(example);

        return new Response("Example saved", true);
    }


    public Response update(Integer id, ExampleDto exampleDto) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return  new Response("Example id not found", false);
        }
        Example example = optionalExample.get();
        example.setText(exampleDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        Task task = optionalTask.get();
        example.setTask(task);

        exampleRepository.save(example);

        return new Response("Task updated", true);
    }

    public Response delete(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent()){
            return new Response("Example id not found", false);
        }
        exampleRepository.deleteById(id);
        return new Response("Example deleted", true);
    }
}
