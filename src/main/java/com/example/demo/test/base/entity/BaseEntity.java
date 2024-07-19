package com.example.demo.test.base.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseEntity {

    private LocalDate createDate;

    private LocalDate updateDate;
}
