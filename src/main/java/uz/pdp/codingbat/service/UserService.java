package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.User;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.payload.UserDto;
import uz.pdp.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getOne(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new User();
        }
        return optionalUser.get();
    }

    public Response add(UserDto userDto) {
        boolean exists = userRepository.existsByEmail(userDto.getEmail());
        if (exists){
            return new Response("User email have in db", false);
        }
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        userRepository.save(user);

        return new Response("User saved", true);
    }

    public Response update(Integer id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new Response("User id not found", false);
        }
        User user = optionalUser.get();
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());

        userRepository.save(user);

        return new Response("User updated", true);
    }

    public Response delete(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()){
            return new Response("User id not found", false);
        }
        userRepository.deleteById(id);
        return new Response("User deleted", true);
    }
}
