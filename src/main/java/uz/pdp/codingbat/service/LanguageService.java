package uz.pdp.codingbat.service;

import org.springframework.stereotype.Service;
import uz.pdp.codingbat.entity.Language;
import uz.pdp.codingbat.payload.LanguageDto;
import uz.pdp.codingbat.payload.Response;
import uz.pdp.codingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {

    final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    public Language getOne(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new Language();
        }
        return optionalLanguage.get();
    }

    public Response add(LanguageDto languageDto) {
        boolean exists = languageRepository.existsByName(languageDto.getName());
        if (exists){
            return new Response("language have in db", false);
        }
        Language language = new Language();
        language.setName(languageDto.getName());

        languageRepository.save(language);

        return new Response("Language saved", true);
    }

    public Response update(Integer id, LanguageDto languageDto) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new Response("Language id not found", false);
        }
        Language language = optionalLanguage.get();
        language.setName(languageDto.getName());

        languageRepository.save(language);

        return new Response("Language updated", true);
    }

    public Response delete(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()){
            return new Response("LAnguage id not found", false);
        }
        languageRepository.deleteById(id);
        return new Response("Language deleted", true);
    }
}
