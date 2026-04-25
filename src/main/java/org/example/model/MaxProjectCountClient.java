package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MaxProjectCountClient {
    private String name;
    private int projectCount;
}