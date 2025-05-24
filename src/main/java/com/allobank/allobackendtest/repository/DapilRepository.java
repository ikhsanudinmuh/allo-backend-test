package com.allobank.allobackendtest.repository;

import com.allobank.allobackendtest.model.Dapil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DapilRepository extends JpaRepository<Dapil, UUID> {
    @Query(
            nativeQuery = true,
            countQuery = """
            select
                count(d.id)
            from public.dapil d
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and d.deleted_at is null) or
                (cast(:deleted as bool) is true and d.deleted_at is not null)
            )
            and (d.nama_dapil ilike concat('%',cast(:namaDapil as text),'%'))
            and (d.provinsi ilike concat('%',cast(:provinsi as text),'%'))
            and (d.jumlah_kursi = coalesce(cast(:jumlahKursi as int), d.jumlah_kursi))
            """,
            value = """
            select
                d.id,
                d.nama_dapil,
                d.provinsi,
                d.wilayah_dapil_list,
                d.jumlah_kursi,
                d.created_at,
                d.updated_at,
                d.deleted_at
            from public.dapil d
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and d.deleted_at is null) or
                (cast(:deleted as bool) is true and d.deleted_at is not null)
            )
            and (d.nama_dapil ilike concat('%',cast(:namaDapil as text),'%'))
            and (d.provinsi ilike concat('%',cast(:provinsi as text),'%'))
            and (d.jumlah_kursi = coalesce(cast(:jumlahKursi as int), d.jumlah_kursi))
            """
    )
    Page<Dapil> getPageDapil(String namaDapil, String provinsi, Integer jumlahKursi, Boolean deleted, Pageable pageable);
}
