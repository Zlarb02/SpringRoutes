package com.doctor_who.api.entities;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DoctorEntity {
    public int number;
    public String name;
    public String startDate;
    public String endDate;
    public ArrayList<String> companions;
}
