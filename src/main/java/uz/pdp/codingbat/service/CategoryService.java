package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.Category;
import uz.pdp.codingbat.entity.Language;
import uz.pdp.codingbat.payload.CategoryDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.repository.CategoryRepository;
import uz.pdp.codingbat.repository.LanguageRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;
    final LanguageRepository languageRepository;

    public CategoryService(CategoryRepository categoryRepository, LanguageRepository languageRepository) {
        this.categoryRepository = categoryRepository;
        this.languageRepository = languageRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    public Category getOne(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Category();
        }
        return optionalCategory.get();
    }

    public Response add(CategoryDto categoryDto) {
        boolean exists = categoryRepository.existsByName(categoryDto.getName());
        if (exists){
            return new Response("This Category name have in db", false);
        }
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        List<Integer> integerList = categoryDto.getLanguage();

        List<Language> languages = new ArrayList<>();

        for (Integer integer : integerList) {
            Optional<Language> optionalLanguage = languageRepository.findById(integer);
            if (optionalLanguage.isPresent()){
                languages.add(optionalLanguage.get());
            }
        }

        category.setLanguage(languages);

        categoryRepository.save(category);

        return new Response("Category saved", true);
    }

    public Response update(Integer id, CategoryDto categoryDto) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Response("Category id not found", false);
        }
        Category category = optionalCategory.get();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        List<Integer> language = categoryDto.getLanguage();

        List<Language> languageList = new ArrayList<>();
        for (Integer integer : language) {
            Optional<Language> optionalLanguage = languageRepository.findById(integer);
            if (optionalLanguage.isPresent()){
                languageList.add(optionalLanguage.get());
            }
        }
        category.setLanguage(languageList);

        categoryRepository.save(category);
        return new Response("Category updated", true);
    }

    public Response delete(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()){
            return new Response("Category id not found", false);
        }
        categoryRepository.deleteById(id);
        return new Response("Category deleted", true);
    }
}
