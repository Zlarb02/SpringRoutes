package com.doctor_who.api;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Doctor {
    public int number;
    public String name;
    public String startDate;
    public String endDate;
    public ArrayList<String> companions;
}
