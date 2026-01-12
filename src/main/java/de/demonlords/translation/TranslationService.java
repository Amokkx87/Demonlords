package de.demonlords.translation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslationService {

    @Autowired
    private TranslationRepository translationRepository;

    public String translate(String key, String country) {
        return translationRepository
                .findByLangvarNameAndLangvarCountry(key, country)
                .map(Translation::getText)
                .orElse(key); // Fallback: Key anzeigen
    }

    // Komfort-Methode (z. B. für DE)
    public String translateDE(String key) {
        return translate(key, "de");
    }
}
