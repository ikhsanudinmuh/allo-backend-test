package com.allobank.allobackendtest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PartaiReq {
    @NotBlank(message = "namaPartai tidak boleh kosong")
    private String namaPartai;
    @NotNull(message = "nomorUrut harus diisi")
    private Integer nomorUrut;
}
