package com.allobank.allobackendtest.service;

import com.allobank.allobackendtest.dto.DataRes;
import com.allobank.allobackendtest.dto.PageRes;
import com.allobank.allobackendtest.dto.DapilReq;
import com.allobank.allobackendtest.model.Dapil;

import java.util.UUID;

public interface DapilService {
    DataRes<Dapil> createDapil(DapilReq body);

    DataRes<Dapil> updateDapil(UUID dapilId, DapilReq body);

    DataRes<Dapil> activateDapil(UUID dapilId);

    DataRes<Dapil> deactivateDapil(UUID dapilId);

    DataRes<Dapil> getByDapilId(UUID dapilId);

    PageRes<Dapil> getPageDapil(String namaDapil, String provinsi, Integer jumlahKursi, Boolean deleted, Integer page, Integer pageSize);
}
