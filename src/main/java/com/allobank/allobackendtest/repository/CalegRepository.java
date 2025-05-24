package com.allobank.allobackendtest.repository;

import com.allobank.allobackendtest.model.Caleg;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CalegRepository extends JpaRepository<Caleg, UUID> {
    @Query(
            nativeQuery = true,
            countQuery = """
            select
                count(c.id)
            from public.caleg c
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and c.deleted_at is null) or
                (cast(:deleted as bool) is true and c.deleted_at is not null)
            )
            and (c.nama ilike concat('%',cast(:nama as text),'%'))
            and (c.nomor_urut = coalesce(cast(:nomorUrut as int), c.nomor_urut))
            and (c.jenis_kelamin = coalesce(cast(:jenisKelamin as varchar), c.jenis_kelamin))
            and (c.partai_id = coalesce(cast(:partaiId as UUID), c.partai_id))
            and (c.dapil_id = coalesce(cast(:dapilId as UUID), c.dapil_id))
            """,
            value = """
            select
                c.id,
                c.nama,
                c.nomor_urut,
                c.jenis_kelamin,
                c.dapil_id,
                c.partai_id,
                c.created_at,
                c.updated_at,
                c.deleted_at
            from public.caleg c
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and c.deleted_at is null) or
                (cast(:deleted as bool) is true and c.deleted_at is not null)
            )
            and (c.nama ilike concat('%',cast(:nama as text),'%'))
            and (c.nomor_urut = coalesce(cast(:nomorUrut as int), c.nomor_urut))
            and (c.jenis_kelamin = coalesce(cast(:jenisKelamin as varchar), c.jenis_kelamin))
            and (c.partai_id = coalesce(cast(:partaiId as UUID), c.partai_id))
            and (c.dapil_id = coalesce(cast(:dapilId as UUID), c.dapil_id))
            """
    )
    Page<Caleg> getPageCaleg(String nama, Integer nomorUrut, String jenisKelamin, UUID dapilId, UUID partaiId, Boolean deleted, Pageable pageable);
}
