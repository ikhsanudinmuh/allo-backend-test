package com.allobank.allobackendtest.repository;

import com.allobank.allobackendtest.model.Partai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartaiRepository extends JpaRepository<Partai, UUID> {
    @Query(
            nativeQuery = true,
            countQuery = """
            select
                count(p.id)
            from public.partai p
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and p.deleted_at is null) or
                (cast(:deleted as bool) is true and p.deleted_at is not null)
            )
            and (p.nama_partai ilike concat('%',cast(:namaPartai as text),'%'))
            and (p.nomor_urut = coalesce(cast(:nomorUrut as int), p.nomor_urut))
            """,
            value = """
            select
                p.id,
                p.nama_partai,
                p.nomor_urut,
                p.created_at,
                p.updated_at,
                p.deleted_at
            from public.partai p
            where (
                cast(:deleted as bool) is null or
                (cast(:deleted as bool) is false and p.deleted_at is null) or
                (cast(:deleted as bool) is true and p.deleted_at is not null)
            )
            and (p.nama_partai ilike concat('%',cast(:namaPartai as text),'%'))
            and (p.nomor_urut = coalesce(cast(:nomorUrut as int), p.nomor_urut))
            """
    )
    Page<Partai> getPagePartai(String namaPartai, Integer nomorUrut, Boolean deleted, Pageable pageable);
}
