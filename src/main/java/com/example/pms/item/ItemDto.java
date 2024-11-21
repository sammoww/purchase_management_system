package com.example.pms.item;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class ItemDto {

    private Long id;
    private String  name;
    private  int box;
    private int unitsPerBox;

}
