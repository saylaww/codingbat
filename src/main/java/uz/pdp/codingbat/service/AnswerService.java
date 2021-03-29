package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.Answer;
import uz.pdp.codingbat.entity.Task;
import uz.pdp.codingbat.entity.User;
import uz.pdp.codingbat.payload.AnswerDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.repository.AnswerRepository;
import uz.pdp.codingbat.repository.TaskRepository;
import uz.pdp.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {

    final AnswerRepository answerRepository;
    final TaskRepository taskRepository;
    final UserRepository userRepository;

    public AnswerService(AnswerRepository answerRepository, TaskRepository taskRepository, UserRepository userRepository) {
        this.answerRepository = answerRepository;
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer getOne(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()){
            return new Answer();
        }
        return optionalAnswer.get();
    }

    public Response add(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setText(answerDto.getText());

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new Response("User id not found", false);
        }
        User user = optionalUser.get();
        answer.setUser(user);

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        Task task = optionalTask.get();
        answer.setTask(task);

        answerRepository.save(answer);
        return new Response("Answer saved", true);
    }

    public Response update(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()){
            return new Response("Answer id not found", false);
        }
        Answer answer = optionalAnswer.get();
        answer.setText(answerDto.getText());

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent()){
            return new Response("Task id not found", false);
        }
        Task task = optionalTask.get();

        answer.setTask(task);

        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent()){
            return new Response("User id not found", false);
        }
        User user = optionalUser.get();
        answer.setUser(user);

        answerRepository.save(answer);

        return new Response("Answer updated", true);
    }

    public Response delete(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent()){
            return new Response("Answer id not found", false);
        }
        answerRepository.deleteById(id);
        return new Response("Answer deleted", true);
    }
}
