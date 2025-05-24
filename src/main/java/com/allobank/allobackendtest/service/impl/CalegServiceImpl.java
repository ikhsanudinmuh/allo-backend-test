package com.allobank.allobackendtest.service.impl;

import com.allobank.allobackendtest.dto.CalegReq;
import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.model.Caleg;
import com.allobank.allobackendtest.model.Dapil;
import com.allobank.allobackendtest.model.Partai;
import com.allobank.allobackendtest.repository.CalegRepository;
import com.allobank.allobackendtest.repository.DapilRepository;
import com.allobank.allobackendtest.repository.PartaiRepository;
import com.allobank.allobackendtest.service.CalegService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CalegServiceImpl implements CalegService {
    private final CalegRepository calegRepository;
    private final DapilRepository dapilRepository;
    private final PartaiRepository partaiRepository;

    @Transactional
    @Override
    public DataRes<Caleg> createCaleg(CalegReq body) {
        Optional<Dapil> optionalDapil = dapilRepository.findById(body.getDapilId());
        if (optionalDapil.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }
        Dapil dapil = optionalDapil.get();

        Optional<Partai> optionalPartai = partaiRepository.findById(body.getPartaiId());
        if (optionalPartai.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }
        Partai partai = optionalPartai.get();

        Caleg caleg = calegRepository.save(Caleg.builder()
                .nama(body.getNama())
                .jenisKelamin(body.getJenisKelamin())
                .nomorUrut(body.getNomorUrut())
                .dapil(dapil)
                .partai(partai)
                .build());

        return DataRes.<Caleg>builder()
                .status(HttpStatus.CREATED.value())
                .message("Berhasil membuat data caleg.")
                .data(caleg)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Caleg> updateCaleg(UUID calegId, CalegReq body) {
        Optional<Caleg> optionalCaleg = calegRepository.findById(calegId);
        if (optionalCaleg.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data caleg tidak ditemukan.")
                    .build();
        }
        Caleg caleg = optionalCaleg.get();

        Optional<Dapil> optionalDapil = dapilRepository.findById(body.getDapilId());
        if (optionalDapil.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data dapil tidak ditemukan.")
                    .build();
        }
        Dapil dapil = optionalDapil.get();

        Optional<Partai> optionalPartai = partaiRepository.findById(body.getPartaiId());
        if (optionalPartai.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }
        Partai partai = optionalPartai.get();

        caleg.setNama(body.getNama());
        caleg.setJenisKelamin(body.getJenisKelamin());
        caleg.setNomorUrut(body.getNomorUrut());
        caleg.setDapil(dapil);
        caleg.setPartai(partai);
        caleg.setUpdatedAt(LocalDateTime.now());
        Caleg calegSave = calegRepository.save(caleg);

        return DataRes.<Caleg>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengubah data caleg.")
                .data(calegSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Caleg> activateCaleg(UUID calegId) {
        Optional<Caleg> optionalCaleg = calegRepository.findById(calegId);
        if (optionalCaleg.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data caleg tidak ditemukan.")
                    .build();
        }

        Caleg caleg = optionalCaleg.get();
        if (caleg.getDeletedAt() != null) {
            caleg.setDeletedAt(null);
        }
        Caleg calegSave = calegRepository.save(caleg);

        return DataRes.<Caleg>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengaktifkan data caleg.")
                .data(calegSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Caleg> deactivateCaleg(UUID calegId) {
        Optional<Caleg> optionalCaleg = calegRepository.findById(calegId);
        if (optionalCaleg.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data caleg tidak ditemukan.")
                    .build();
        }

        Caleg caleg = optionalCaleg.get();
        if (caleg.getDeletedAt() == null) {
            caleg.setDeletedAt(LocalDateTime.now());
        }
        Caleg calegSave = calegRepository.save(caleg);

        return DataRes.<Caleg>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil menonaktifkan data caleg.")
                .data(calegSave)
                .build();
    }

    @Override
    public DataRes<Caleg> getByCalegId(UUID calegId) {
        Optional<Caleg> optionalCaleg = calegRepository.findById(calegId);
        if (optionalCaleg.isEmpty()) {
            return DataRes.<Caleg>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data caleg tidak ditemukan.")
                    .build();
        }

        Caleg caleg = optionalCaleg.get();

        return DataRes.<Caleg>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data caleg.")
                .data(caleg)
                .build();
    }

    @Override
    public PageRes<Caleg> getPageCaleg(String nama, Integer nomorUrut, String jenisKelamin, UUID dapilId, UUID partaiId, Boolean deleted, Integer page, Integer pageSize, String sortBy, String sortOrder) {
        Sort.Direction direction = sortOrder.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);

        Page<Caleg> data = calegRepository.getPageCaleg(nama, nomorUrut, jenisKelamin, dapilId, partaiId, deleted, pageable);

        return PageRes.<Caleg>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data caleg.")
                .data(data.getContent())
                .currentPage(page)
                .totalPages(data.getTotalPages())
                .totalCount(data.getTotalElements())
                .build();
    }
}
