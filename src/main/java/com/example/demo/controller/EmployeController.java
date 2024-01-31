package com.example.demo.controller;

import com.example.demo.model.CommonDto;
import com.example.demo.model.Employe;
import com.example.demo.model.EmployeDto;
import com.example.demo.model.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    @PostMapping("/time")
    public ResponseEntity<List<ResponseDto>> createIndividual(@RequestBody CommonDto commonDto) { // принимаем массив

        List<EmployeDto> list = (List<EmployeDto>) commonDto.getEmployees(); // сохраним в лист для удобства
        Map<String, Duration> map = new HashMap<>();  // здесь будет храниться инфа , у какого id какое duration_value

        for (EmployeDto employeDto: list) {  // для каждого EmployeDto найдем разность в начале и конце работ,
                                            //сохраним результат в Duration duration
            String id = employeDto.getId();
            LocalDateTime dateStart = employeDto.getDateStart();
            LocalDateTime dateEnd = employeDto.getDateEnd();

            if (dateStart.isBefore(dateEnd) && dateStart.isBefore(LocalDateTime.now())) { // проверка

                Duration duration = Duration.between(dateStart, dateEnd);

            //поскольку,в принимаемом json-е могут быть одни и теже работники  с разной отработкой - то используем мэп,
             //чтобы сохранить инфу, для какого id какая наработка (duration) накопилась

                if(map.containsKey(id)) { // если такой работник уже был..
                    map.computeIfPresent(id, (key, val) -> val.plus(duration)); // прибавим новую наработку к старой
                } else{
                    map.put(id, duration); // если не был, просто пишем в мапу
                }
            }
            else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        // теперь все это из мапы запишем в коллекцию ответных дто-шек
        List<ResponseDto> responselist = new ArrayList<>();

        for (Map.Entry<String, Duration> entry : map.entrySet()) {
            String hours = (entry.getValue().toHours() % 24) + "";
            String min = (entry.getValue().toMinutes() % 60) + "";
            String sec = (entry.getValue().toSeconds() % 60) + "";

            String result_string = hours + ":" + min + ":" + sec + ":"; //в строке итоговая инфа о наработке, дату я уже не стал трогать

            ResponseDto responseDto = ResponseDto.builder().id(entry.getKey()).time(result_string).build();
            responselist.add(responseDto);
        }
        return new ResponseEntity<>(responselist, HttpStatus.OK);
    }

}
