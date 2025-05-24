package com.allobank.allobackendtest.service.impl;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.PartaiReq;
import com.allobank.allobackendtest.model.Partai;
import com.allobank.allobackendtest.repository.PartaiRepository;
import com.allobank.allobackendtest.service.PartaiService;
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
public class PartaiServiceImpl implements PartaiService {
    private final PartaiRepository partaiRepository;

    @Transactional
    @Override
    public DataRes<Partai> createPartai(PartaiReq body) {
        Partai partai = partaiRepository.save(Partai.builder()
                .namaPartai(body.getNamaPartai())
                .nomorUrut(body.getNomorUrut())
                .build());

        return DataRes.<Partai>builder()
                .status(HttpStatus.CREATED.value())
                .message("Berhasil membuat data partai.")
                .data(partai)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Partai> updatePartai(UUID partaiId, PartaiReq body) {
        Optional<Partai> optionalPartai = partaiRepository.findById(partaiId);
        if (optionalPartai.isEmpty()) {
            return DataRes.<Partai>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }

        Partai partai = optionalPartai.get();
        partai.setNamaPartai(body.getNamaPartai());
        partai.setNomorUrut(body.getNomorUrut());
        partai.setUpdatedAt(LocalDateTime.now());
        Partai partaiSave = partaiRepository.save(partai);

        return DataRes.<Partai>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengubah data partai.")
                .data(partaiSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Partai> activatePartai(UUID partaiId) {
        Optional<Partai> optionalPartai = partaiRepository.findById(partaiId);
        if (optionalPartai.isEmpty()) {
            return DataRes.<Partai>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }

        Partai partai = optionalPartai.get();
        if (partai.getDeletedAt() != null) {
            partai.setDeletedAt(null);
        }
        Partai partaiSave = partaiRepository.save(partai);

        return DataRes.<Partai>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mengaktifkan data partai.")
                .data(partaiSave)
                .build();
    }

    @Transactional
    @Override
    public DataRes<Partai> deactivatePartai(UUID partaiId) {
        Optional<Partai> optionalPartai = partaiRepository.findById(partaiId);
        if (optionalPartai.isEmpty()) {
            return DataRes.<Partai>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }

        Partai partai = optionalPartai.get();
        if (partai.getDeletedAt() == null) {
            partai.setDeletedAt(LocalDateTime.now());
        }
        Partai partaiSave = partaiRepository.save(partai);

        return DataRes.<Partai>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil menonaktifkan data partai.")
                .data(partai)
                .build();
    }

    @Override
    public DataRes<Partai> getByPartaiId(UUID partaiId) {
        Optional<Partai> optionalPartai = partaiRepository.findById(partaiId);
        if (optionalPartai.isEmpty()) {
            return DataRes.<Partai>builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .message("Data partai tidak ditemukan.")
                    .build();
        }

        Partai partai = optionalPartai.get();

        return DataRes.<Partai>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data partai.")
                .data(partai)
                .build();
    }

    @Override
    public PageRes<Partai> getPagePartai(String namaPartai, Integer nomorUrut, Boolean deleted, Integer page, Integer pageSize) {
        Pageable pageable = PageRequest.of(page - 1, pageSize);

        Page<Partai> data = partaiRepository.getPagePartai(namaPartai, nomorUrut, deleted, pageable);

        return PageRes.<Partai>builder()
                .status(HttpStatus.OK.value())
                .message("Berhasil mendapatkan data partai.")
                .data(data.getContent())
                .currentPage(page)
                .totalPages(data.getTotalPages())
                .totalCount(data.getTotalElements())
                .build();
    }
}
