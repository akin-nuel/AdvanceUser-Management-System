package com.example.AdvanceUser;

import com.example.AdvanceUser.Model.AdvanceUser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RestTemplateTest {

    public static void main(String[] args) {

        RestTemplate template = new RestTemplate();
        String url = "http://localhost:8080/api/v3/users";
        AdvanceUser newUser = new AdvanceUser("Akinola Emmanuel", "akinolaemmanuel273@gmail.com", "Male", "09090311758");
        ResponseEntity<AdvanceUser> user1 = template.postForEntity(url, newUser, AdvanceUser.class);

        AdvanceUser extracted = user1.getBody();
        System.out.println(extracted);
    }
}
