package spring_boot.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.HashMap;

@RestController
public class MainController {

    @Value("classpath:/templates/getAnswer.txt")
    private Resource getTemplateResource;

    @Value("classpath:/templates/postAnswer.txt")
    private Resource postTemplateResource;

    // Метод для GET-запроса
    @GetMapping(value="/app/v1/getRequest", produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> handleGetRequest(
            @RequestParam(name="id") Integer id,
            @RequestParam(name="name") String name) throws Exception {

        if (id <= 10) {
            return new ResponseEntity<>("ID must be greater than 10.", HttpStatus.BAD_REQUEST);
        }
        if (name.length() <= 5) {
            return new ResponseEntity<>("Name length must be more than 5 characters.", HttpStatus.BAD_REQUEST);
        }

        Thread.sleep((id >= 10 && id < 50) ? 1000L : 500L); // Задержка выполнения

        Map<String, Object> model = new HashMap<>();
        model.put("name", name);

        byte[] contentBytes = Files.readAllBytes(getTemplateResource.getFile().toPath());
        String templateContent = new String(contentBytes, StandardCharsets.UTF_8);

        return new ResponseEntity<>(templateContent.replace("${name}", name), HttpStatus.OK);
    }

    // Метод для POST-запроса
    @PostMapping(value="/app/v1/postRequest", consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> handlePostRequest(@RequestBody RequestData requestData) throws Exception {

        if(StringUtils.isEmpty(requestData.getName()) ||
                StringUtils.isEmpty(requestData.getSurname()) ||
                requestData.getAge() == null){

            return new ResponseEntity<>("Missing required fields in the body.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Map<String, Object> model = new HashMap<>();
        model.put("name", requestData.getName());
        model.put("surname", requestData.getSurname());
        model.put("age", requestData.getAge());

        byte[] contentBytes = Files.readAllBytes(postTemplateResource.getFile().toPath());
        String templateContent = new String(contentBytes, StandardCharsets.UTF_8);

        return new ResponseEntity<>(templateContent
                .replace("${name}", requestData.getName())
                .replace("${surname}", requestData.getSurname())
                .replace("${age}", requestData.getAge().toString()), HttpStatus.OK);
    }

    static class RequestData{
        private String name;
        private String surname;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}


