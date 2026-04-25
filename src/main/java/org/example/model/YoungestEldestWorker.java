package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class YoungestEldestWorker {
    private String type;
    private String name;
    private String birthday;
}
