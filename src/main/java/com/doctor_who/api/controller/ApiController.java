package com.doctor_who.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.doctor_who.api.Doctor;

@RestController
public class ApiController {

    // [
    // {
    // "number": 1,
    // "name": "William Hartnell",
    // "startDate": "1963-11-23",
    // "endDate": "1966-10-29",
    // "companions": ["Susan Foreman", "Barbara Wright", "Ian Chesterton"]
    // },
    // {
    // "number": 2,
    // "name": "Patrick Troughton",
    // "startDate": "1966-10-29",
    // "endDate": "1969-06-21",
    // "companions": ["Jamie McCrimmon", "Victoria Waterfield", "Zoe Heriot"]
    // },
    // {
    // "number": 3,
    // "name": "Jon Pertwee",
    // "startDate": "1970-01-03",
    // "endDate": "1974-06-08",
    // "companions": ["Liz Shaw", "Jo Grant", "Sarah Jane Smith"]
    // },
    // {
    // "number": 4,
    // "name": "Tom Baker",
    // "startDate": "1974-06-08",
    // "endDate": "1981-03-21",
    // "companions": ["Sarah Jane Smith", "Leela", "Romana"]
    // },
    // {
    // "number": 5,
    // "name": "Peter Davison",
    // "startDate": "1982-01-04",
    // "endDate": "1984-03-16",
    // "companions": ["Adric", "Nyssa", "Tegan Jovanka"]
    // },
    // {
    // "number": 6,
    // "name": "Colin Baker",
    // "startDate": "1984-03-16",
    // "endDate": "1986-12-06",
    // "companions": ["Peri Brown", "Mel Bush"]
    // },
    // {
    // "number": 7,
    // "name": "Sylvester McCoy",
    // "startDate": "1987-09-07",
    // "endDate": "1989-12-06",
    // "companions": ["Mel Bush", "Ace"]
    // },
    // {
    // "number": 8,
    // "name": "Paul McGann",
    // "startDate": "1996-05-27",
    // "endDate": "1996-05-27",
    // "companions": ["Grace Holloway"]
    // },
    // {
    // "number": 9,
    // "name": "Christopher Eccleston",
    // "startDate": "2005-03-26",
    // "endDate": "2005-06-18",
    // "companions": ["Rose Tyler"]
    // },
    // {
    // "number": 10,
    // "name": "David Tennant",
    // "startDate": "2005-06-18",
    // "endDate": "2010-01-01",
    // "companions": ["Rose Tyler", "Martha Jones", "Donna Noble"]
    // },
    // {
    // "number": 11,
    // "name": "Matt Smith",
    // "startDate": "2010-01-01",
    // "endDate": "2013-12-25",
    // "companions": ["Amy Pond", "Rory Williams", "Clara Oswald"]
    // },
    // {
    // "number": 12,
    // "name": "Peter Capaldi",
    // "startDate": "2013-12-25",
    // "endDate": "2017-12-25",
    // "companions": ["Clara Oswald", "Bill Potts", "Nardole"]
    // },
    // {
    // "number": 13,
    // "name": "Jodie Whittaker",
    // "startDate": "2017-12-25",
    // "endDate": "2022-01-01",
    // "companions": ["Yasmin Khan", "Ryan Sinclair", "Graham O'Brien"]
    // }
    // ]
    public List<Doctor> doctorList = new ArrayList<>(
            Arrays.asList(
                    new Doctor(1, "William Hartnell", "1963-11-23", "1966-10-29",
                            new ArrayList<>(Arrays.asList("Susan Foreman", "Barbara Wright", "Ian Chesterton"))),
                    new Doctor(2, "Patrick Troughton", "1966-10-29", "1969-06-21",
                            new ArrayList<>(Arrays.asList("Jamie McCrimmon", "Victoria Waterfield", "Zoe Heriot"))),
                    new Doctor(3, "Jon Pertwee", "1970-01-03", "1974-06-08",
                            new ArrayList<>(Arrays.asList("Liz Shaw", "Jo Grant", "Sarah Jane Smith"))),
                    new Doctor(4, "Tom Baker", "1974-06-08", "1981-03-21",
                            new ArrayList<>(Arrays.asList("Sarah Jane Smith", "Leela", "Romana"))),
                    new Doctor(5, "Peter Davison", "1982-01-04", "1984-03-16",
                            new ArrayList<>(Arrays.asList("Adric", "Nyssa", "Tegan Jovanka"))),
                    new Doctor(6, "Colin Baker", "1984-03-16", "1986-12-06",
                            new ArrayList<>(Arrays.asList("Peri Brown", "Mel Bush"))),
                    new Doctor(7, "Sylvester McCoy", "1987-09-07", "1989-12-06",
                            new ArrayList<>(Arrays.asList("Mel Bush", "Ace"))),
                    new Doctor(8, "Paul McGann", "1996-05-27", "1996-05-27",
                            new ArrayList<>(Arrays.asList("Grace Holloway"))),
                    new Doctor(9, "Christopher Eccleston", "2005-03-26", "2005-06-18",
                            new ArrayList<>(Arrays.asList("Rose Tyler"))),
                    new Doctor(10, "David Tennant", "2005-06-18", "2010-01-01",
                            new ArrayList<>(Arrays.asList("Rose Tyler", "Martha Jones", "Donna Noble"))),
                    new Doctor(11, "Matt Smith", "2010-01-01", "2013-12-25",
                            new ArrayList<>(Arrays.asList("Amy Pond", "Rory Williams", "Clara Oswald"))),
                    new Doctor(12, "Peter Capaldi", "2013-12-25", "2017-12-25",
                            new ArrayList<>(Arrays.asList("Clara Oswald", "Bill Potts", "Nardole"))),
                    new Doctor(13, "Jodie Whittaker", "2017-12-25", "2022-01-01",
                            new ArrayList<>(Arrays.asList("Yasmin Khan", "Ryan Sinclair", "Graham O'Brien")))));

    @GetMapping("/doctor/{number}")
    public ResponseEntity<Doctor> getDoctor(@PathVariable int number) {
        Doctor doctor = doctorList.get(number - 1);
        return ResponseEntity.ok(doctor);
    }
}
