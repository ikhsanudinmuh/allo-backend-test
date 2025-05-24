package com.allobank.allobackendtest.dto;

import com.allobank.allobackendtest.model.JenisKelamin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CalegReq {
    @NotBlank(message = "nama tidak boleh kosong")
    private String nama;
    @NotNull(message = "dapilId harus diisi")
    private UUID dapilId;
    @NotNull(message = "partaiId harus diisi")
    private UUID partaiId;
    @NotNull(message = "nomorUrut harus diisi")
    private Integer nomorUrut;
    @NotNull(message = "jenisKelamin harus diisi")
    private JenisKelamin jenisKelamin;
}
