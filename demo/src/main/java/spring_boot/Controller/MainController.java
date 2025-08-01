package spring_boot.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.lang.Thread;

@RestController
public class MainController {

    @Value("classpath:/templates/getAnswer.txt")
    private Resource getTemplateResource;

    @Value("classpath:/templates/postAnswer.txt")
    private Resource postTemplateResource;

    // Метод для GET-запроса
    @GetMapping("/app/v1/getRequest")
    public ResponseEntity<String> handleGetRequest(
            @RequestParam("id") int id,
            @RequestParam("name") String name) throws IOException, InterruptedException {

        // Проверка условий
        if (id <= 10 || name.length() <= 5) {
            return new ResponseEntity<>("Invalid input parameters.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // Добавляем искусственную задержку в зависимости от параметра id
        if (id > 10 && id < 50) {
            Thread.sleep(1000); // Задержка 1 секунда
        } else {
            Thread.sleep(500); // Задержка 0.5 секунды
        }

        // Читаем файл шаблона
        byte[] contentBytes = Files.readAllBytes(getTemplateResource.getFile().toPath());
        String templateContent = new String(contentBytes, StandardCharsets.UTF_8);

        // Подменяем плейсхолдеры в файле на реальные значения
        String replacedContent = templateContent
                .replace("{id}", String.valueOf(id))
                .replace("{name}", name);

        return new ResponseEntity<>(replacedContent, HttpStatus.OK);
    }

    // Обработчик POST-запроса
    @PostMapping("/app/v1/postRequest")
    public ResponseEntity<String> handlePostRequest(@RequestBody Map<String, Object> requestData) throws IOException {

        // Проверка наличия обязательных полей
        if (!requestData.containsKey("name") || !requestData.containsKey("surname") || !requestData.containsKey("age")) {
            return new ResponseEntity<>("Missing required fields in the body.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Извлекаем нужные поля из тела запроса
        String name = (String) requestData.get("name");
        String surname = (String) requestData.get("surname");
        Integer age = (Integer) requestData.get("age");

        // Расчёт удвоенного возраста
        Integer doubledAge = age * 2;

        // Читаем содержимое файла-шаблона
        byte[] contentBytes = Files.readAllBytes(postTemplateResource.getFile().toPath());
        String templateContent = new String(contentBytes, StandardCharsets.UTF_8);

        // Производим нужную замену для Person2.age
        // Сначала удаляем некорректную конструкцию '{age}*2'
        String modifiedContent = templateContent.replace("\"age\": {age}*2", "\"age\": " + doubledAge);

        // Подменяем оставшиеся места-холдеры ({name}, {surname})
        modifiedContent = modifiedContent
                .replace("{name}", name)
                .replace("{surname}", surname)
                .replace("{age}", age.toString());

        return new ResponseEntity<>(modifiedContent, HttpStatus.OK);
    }


}
