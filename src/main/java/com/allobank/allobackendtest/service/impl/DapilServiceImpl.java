package com.allobank.allobackendtest.service.impl;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.DapilReq;
import com.allobank.allobackendtest.model.Dapil;
import com.allobank.allobackendtest.repository.DapilRepository;
import com.allobank.allobackendtest.service.DapilService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DapilServiceImpl implements DapilService {
    private final DapilRepository dapilRepository;

    @Transactional
    @Override
    public DataRes<Dapil> createDapil(DapilReq body) {
        Dapil dapil = dapilRepository.save(Dapil.builder()
                .namaDapil(body.getNamaDapil())
                .provinsi(body.getProvinsi())
                .wilayahDapilList(body.getWilayahDapilList())
                .jumlahKursi(body.getJumlahKursi())
                .build());

        return DataRes.<Dapil>builder()
                .status(HttpStatus.CREATED.value())
                .message("Berhasil membuat data dapil.")
                .data(dapil)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Dapil> updateDapil(UUID dapilId, DapilReq body) {
        Optional<Dapil> optionalDapil = dapilRepository.findById(dapilId);
        if (optionalDapil.isEmpty()) {
            return DataRes.<Dapil>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }

        Dapil dapil = optionalDapil.get();
        dapil.setNamaDapil(body.getNamaDapil());
        dapil.setProvinsi(body.getProvinsi());
        dapil.setWilayahDapilList(body.getWilayahDapilList());
        dapil.setJumlahKursi(body.getJumlahKursi());
        dapil.setUpdatedAt(LocalDateTime.now());
        Dapil dapilSave = dapilRepository.save(dapil);

        return DataRes.<Dapil>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengubah data dapil.")
                .data(dapilSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Dapil> activateDapil(UUID dapilId) {
        Optional<Dapil> optionalDapil = dapilRepository.findById(dapilId);
        if (optionalDapil.isEmpty()) {
            return DataRes.<Dapil>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }

        Dapil dapil = optionalDapil.get();
        if (dapil.getDeletedAt() != null) {
            dapil.setDeletedAt(null);
        }
        Dapil dapilSave = dapilRepository.save(dapil);

        return DataRes.<Dapil>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengaktifkan data dapil.")
                .data(dapilSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Dapil> deactivateDapil(UUID dapilId) {
        Optional<Dapil> optionalDapil = dapilRepository.findById(dapilId);
        if (optionalDapil.isEmpty()) {
            return DataRes.<Dapil>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }

        Dapil dapil = optionalDapil.get();
        if (dapil.getDeletedAt() == null) {
            dapil.setDeletedAt(LocalDateTime.now());
        }
        Dapil dapilSave = dapilRepository.save(dapil);

        return DataRes.<Dapil>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil menonaktifkan data dapil.")
                .data(dapilSave)
                .build();
    }

    @Override
    public DataRes<Dapil> getByDapilId(UUID dapilId) {
        Optional<Dapil> optionalDapil = dapilRepository.findById(dapilId);
        if (optionalDapil.isEmpty()) {
            return DataRes.<Dapil>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }

        Dapil dapil = optionalDapil.get();

        return DataRes.<Dapil>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data dapil.")
                .data(dapil)
                .build();
    }

    @Override
    public PageRes<Dapil> getPageDapil(String namaDapil, String provinsi, Integer jumlahKursi, Boolean deleted, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<Dapil> data = dapilRepository.getPageDapil(namaDapil, provinsi, jumlahKursi, deleted, pageable);

        return PageRes.<Dapil>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data dapil.")
                .data(data.getContent())
                .currentPage(page)
                .totalPages(data.getTotalPages())
                .totalCount(data.getTotalElements())
                .build();
    }
}
