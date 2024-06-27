package com.doctor_who.api.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor_who.api.entities.DoctorEntity;

@RestController
public class ApiController {
        public List<DoctorEntity> doctorList = new ArrayList<>(
                        Arrays.asList(
                                        new DoctorEntity(1, "William Hartnell", "1963-11-23", "1966-10-29",
                                                        new ArrayList<>(Arrays.asList("Susan Foreman", "Barbara Wright",
                                                                        "Ian Chesterton"))),
                                        new DoctorEntity(2, "Patrick Troughton", "1966-10-29", "1969-06-21",
                                                        new ArrayList<>(Arrays.asList("Jamie McCrimmon",
                                                                        "Victoria Waterfield", "Zoe Heriot"))),
                                        new DoctorEntity(3, "Jon Pertwee", "1970-01-03", "1974-06-08",
                                                        new ArrayList<>(Arrays.asList("Liz Shaw", "Jo Grant",
                                                                        "Sarah Jane Smith"))),
                                        new DoctorEntity(4, "Tom Baker", "1974-06-08", "1981-03-21",
                                                        new ArrayList<>(Arrays.asList("Sarah Jane Smith", "Leela",
                                                                        "Romana"))),
                                        new DoctorEntity(5, "Peter Davison", "1982-01-04", "1984-03-16",
                                                        new ArrayList<>(Arrays.asList("Adric", "Nyssa",
                                                                        "Tegan Jovanka"))),
                                        new DoctorEntity(6, "Colin Baker", "1984-03-16", "1986-12-06",
                                                        new ArrayList<>(Arrays.asList("Peri Brown", "Mel Bush"))),
                                        new DoctorEntity(7, "Sylvester McCoy", "1987-09-07", "1989-12-06",
                                                        new ArrayList<>(Arrays.asList("Mel Bush", "Ace"))),
                                        new DoctorEntity(8, "Paul McGann", "1996-05-27", "1996-05-27",
                                                        new ArrayList<>(Arrays.asList("Grace Holloway"))),
                                        new DoctorEntity(9, "Christopher Eccleston", "2005-03-26", "2005-06-18",
                                                        new ArrayList<>(Arrays.asList("Rose Tyler"))),
                                        new DoctorEntity(10, "David Tennant", "2005-06-18", "2010-01-01",
                                                        new ArrayList<>(Arrays.asList("Rose Tyler", "Martha Jones",
                                                                        "Donna Noble"))),
                                        new DoctorEntity(11, "Matt Smith", "2010-01-01", "2013-12-25",
                                                        new ArrayList<>(Arrays.asList("Amy Pond", "Rory Williams",
                                                                        "Clara Oswald"))),
                                        new DoctorEntity(12, "Peter Capaldi", "2013-12-25", "2017-12-25",
                                                        new ArrayList<>(Arrays.asList("Clara Oswald", "Bill Potts",
                                                                        "Nardole"))),
                                        new DoctorEntity(13, "Jodie Whittaker", "2017-12-25", "2022-01-01",
                                                        new ArrayList<>(Arrays.asList("Yasmin Khan", "Ryan Sinclair",
                                                                        "Graham O'Brien")))));

        @GetMapping("/doctor/{id}")
        public ResponseEntity<?> getDoctor(@PathVariable int id) {
                if (id > 13) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(Map.of("error", "Impossible de récupérer l'incarnation " + id));
                }
                DoctorEntity doctor = doctorList.get(id - 1);
                return ResponseEntity.ok(doctor);
        }

        @GetMapping("/doctors")
        public ResponseEntity<List<DoctorEntity>> getDoctors(@RequestParam(required = false) String startDate,
                        @RequestParam(required = false) String endDate) {
                if (startDate != null && endDate != null) {
                        LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
                        LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

                        List<DoctorEntity> filteredDoctors = doctorList.stream()
                                        .filter(doctor -> {
                                                LocalDate doctorStart = LocalDate.parse(doctor.getStartDate(),
                                                                DateTimeFormatter.ISO_DATE);
                                                LocalDate doctorEnd = LocalDate.parse(doctor.getEndDate(),
                                                                DateTimeFormatter.ISO_DATE);
                                                return (doctorStart.isEqual(start) || doctorStart.isAfter(start)) &&
                                                                (doctorEnd.isEqual(end) || doctorEnd.isBefore(end));
                                        })
                                        .collect(Collectors.toList());

                        return ResponseEntity.ok(filteredDoctors);
                } else {
                        return ResponseEntity.ok(doctorList);
                }
        }

        @PostMapping("/doctor")
        public ResponseEntity<?> addDoctor(@RequestBody DoctorEntity doctor) {
                boolean doctorExists = doctorList.stream()
                                .anyMatch(d -> d.getNumber() == doctor.getNumber());

                if (doctorExists) {
                        return ResponseEntity.status(HttpStatus.CONFLICT)
                                        .body(Map.of("error",
                                                        "Le docteur numéro " + doctor.getNumber() + " existe déjà"));
                }

                doctorList.add(doctor);
                return ResponseEntity.status(HttpStatus.CREATED)
                                .body(Map.of("message", "Le docteur numéro " + doctor.getNumber() + " a été ajouté"));
        }

        @PutMapping("/doctor/{id}")
        public ResponseEntity<?> updateDoctor(@PathVariable int id, @RequestBody DoctorEntity doctor) {
                if (id > doctorList.size()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(Map.of("error", "Impossible de modifier l'incarnation "
                                                        + id));
                }
                doctorList.set(id - 1, doctor);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("message",
                                                "Le docteur numéro " + id + " a été mis à jour"));
        }

        @DeleteMapping("/doctor/{id}")
        public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
                if (id > doctorList.size()) {
                        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                        .body(Map.of("error", "Impossible de supprimer l'incarnation " + id));
                }
                doctorList.remove(id - 1);
                return ResponseEntity.status(HttpStatus.OK)
                                .body(Map.of("message", "Le docteur numéro " + id + " a été supprimé"));
        }
}
