package com.leave.backend.Controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.leave.backend.Services.HolidayService;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:4200" , maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class HolidaysDay {
    private final HolidayService holidayService;
    @GetMapping("/holidays")
    @PreAuthorize("hasRole('USER') or hasRole('RH')")
    public ResponseEntity<?> callRapidEndpointToGetHolidays(){
        return ResponseEntity.ok(holidayService.getHolidaysDay());

    }
//     // @Value("${api.key}") // Injection de la clé API depuis les propriétés de l'application
//     // private String apiKey;

//     @GetMapping(value="/holidays")
//     //@PreAuthorize("hasRole('RH')")
//     public List<Object> getHolidays(){

//                  String url = "https://anyapi.io/api/v1/holidays?country=MA&language=fr&apiKey=p9912krcon0brd4nobt804d19plv2h82ec2al9bmt6sjt0iqbq5o";
// //         RestTemplate restTemplate = new RestTemplate();

// //         // Création des headers avec la clé API
// //         // HttpHeaders headers = new HttpHeaders();
// //         // headers.set("X-Api-Key", apiKey);

// //         // Ajout des headers à la requête
// //         // Object[] holidays = restTemplate.getForObject(url, Object[].class, headers);
// //  Object[] holidays = restTemplate.getForObject(url, Object[].class);
//    RestTemplate restTemplate = new RestTemplate();
// HttpHeaders headers = new HttpHeaders();
// headers.set("X-Api-Key", "p9912krcon0brd4nobt804d19plv2h82ec2al9bmt6sjt0iqbq5o");
// HttpEntity<String> entity = new HttpEntity<>(headers);
// Object[] holidays = restTemplate.exchange(url, HttpMethod.GET, entity, Object[].class).getBody();

//         return Arrays.asList(holidays);
//     }
}
