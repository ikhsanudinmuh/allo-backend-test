package com.allobank.allobackendtest.service;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.PartaiReq;
import com.allobank.allobackendtest.model.Partai;

import java.util.UUID;

public interface PartaiService {
    DataRes<Partai> createPartai(PartaiReq body);

    DataRes<Partai> updatePartai(UUID partaiId, PartaiReq body);

    DataRes<Partai> activatePartai(UUID partaiId);

    DataRes<Partai> deactivatePartai(UUID partaiId);

    DataRes<Partai> getByPartaiId(UUID partaiId);

    PageRes<Partai> getPagePartai(String namaPartai, Integer nomorUrut, Boolean deleted, Integer page, Integer pageSize);
}
