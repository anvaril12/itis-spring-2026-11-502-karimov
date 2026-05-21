package org.example;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class MessageSource {

    private static volatile MessageSource instance;

    private MessageSource() {

    }

    public static MessageSource getInstance() {
        if (instance == null) {
            synchronized (MessageSource.class) {
                if (instance == null) {
                    instance = new MessageSource();
                    instance.loadAllProperties();
                }
            }
        }
        return instance;
    }

    private final Map<String, Properties> propertiesCache = new ConcurrentHashMap<>();

    private static final Locale[] SUPPORTED_LOCALES = {
            Locale.ENGLISH,
            new Locale("ru")
    };

    private void loadAllProperties() {
        for (Locale locale : SUPPORTED_LOCALES) {
            String lang = locale.getLanguage();
            String fileName = "messeges_" + lang + ".properties";

            Properties props = loadFromClasspath(fileName);
            propertiesCache.put(lang, props);
        }
    }

    private Properties loadFromClasspath(String fileName) {
        URL url = getClass().getClassLoader().getResource(fileName);
        if (url == null) {
            throw new RuntimeException("Файл не найден: " + fileName);
        }

        try {
            URI uri = url.toURI();
            Path path = Paths.get(uri);
            byte[] bytes = Files.readAllBytes(path);
            String content = new String(bytes, StandardCharsets.UTF_8);

            Properties props = new Properties();
            props.load(new java.io.StringReader(content));
            return props;

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException("Ошибка при чтении файла: " + fileName, e);
        }
    }

    public String getMessage(String key, Locale locale, Object... args) {
        String lang = locale.getLanguage();

        Properties props = propertiesCache.get(lang);
        if (props == null) {
            props = propertiesCache.get(Locale.ENGLISH.getLanguage());
        }

        String value = props.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException(
                    "Ключ '" + key + "' не найден для языка '" + lang + "'"
            );
        }

        return substitute(value, args);
    }

    private String substitute(String template, Object[] args) {
        if (args == null || args.length == 0) {
            return template;
        }
        String result = template;
        for (int i = 0; i < args.length; i++) {
            result = result.replace("{" + i + "}", String.valueOf(args[i]));
        }
        return result;
    }
}
