package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.Language;
import uz.pdp.codingbat.entity.Task;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.payload.TaskDto;
import uz.pdp.codingbat.repository.LanguageRepository;
import uz.pdp.codingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    final TaskRepository taskRepository;
    final LanguageRepository languageRepository;

    public TaskService(TaskRepository taskRepository, LanguageRepository languageRepository) {
        this.taskRepository = taskRepository;
        this.languageRepository = languageRepository;
    }

    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    public Task getOne(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new Task();
        }
        return optionalTask.get();
    }

    public Response add(TaskDto taskDto) {
        Task task = new Task();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setHint(taskDto.getHint());
        task.setSolution(taskDto.getSolution());
        task.setMethod(taskDto.getMethod());

        Language language = new Language();
        language.setName(taskDto.getLanguage());

        Language savedLan = languageRepository.save(language);
        task.setLanguage(savedLan);

        taskRepository.save(task);
        return new Response("Task added", true);
    }

    public Response update(Integer id, TaskDto taskDto) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        Task task = optionalTask.get();
        task.setName(taskDto.getName());
        task.setText(taskDto.getText());
        task.setHint(taskDto.getHint());
        task.setMethod(taskDto.getMethod());
        task.setSolution(taskDto.getSolution());

        Language language = new Language();
        language.setName(taskDto.getLanguage());

        task.setLanguage(language);

        taskRepository.save(task);

        return new Response("Task updated", true);
    }

    public Response delete(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        taskRepository.deleteById(id);
        return new Response("Task deleted", true);
    }
}
