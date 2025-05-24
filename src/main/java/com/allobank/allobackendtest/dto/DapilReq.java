package com.allobank.allobackendtest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class DapilReq {
    @NotBlank(message = "namaDapil tidak boleh kosong")
    private String namaDapil;
    @NotBlank(message = "provinsi tidak boleh kosong")
    private String provinsi;
    @NotNull(message = "wilayah harus diisi")
    private List<String> wilayahDapilList;
    @NotNull(message = "jumlahKursi harus diisi")
    private int jumlahKursi;
}
