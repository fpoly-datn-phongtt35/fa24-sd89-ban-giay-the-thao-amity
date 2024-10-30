package org.example.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.backend.dto.request.dotGiamGia.DotGiamGiaSearch;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("""
                select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
                 p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
                from PhieuGiamGia p where p.deleted=false 
            """)
    Page<phieuGiamGiaReponse> getAllPhieuGiamGia(Pageable pageable);

    @Query("""
                update PhieuGiamGia p 
                set p.deleted=:deleted where p.id=:id 
            """)
    @Modifying
    @Transactional
    void updateDetailPhieuGiamGia(Boolean deleted, UUID id);

    @Query("SELECT new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(" +
            "p.id, p.ma, p.ten, p.loai, p.giaTri, p.giamToiDa, p.mucDo, p.ngayBatDau, p.ngayKetThuc, p.soLuong, p.dieuKien, p.trangThai) " +
            "FROM PhieuGiamGia p " +
            "WHERE p.deleted = false AND (p.ma LIKE :find OR p.ten LIKE :find OR p.dieuKien LIKE :find)")
    List<phieuGiamGiaReponse> searchPGG(String find);

    @Query("SELECT new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(" +
            "p.id, p.ma, p.ten, p.loai, p.giaTri, p.giamToiDa, p.mucDo, p.ngayBatDau, p.ngayKetThuc, p.soLuong, p.dieuKien, p.trangThai) " +
            "FROM PhieuGiamGia p " +
            "WHERE p.deleted = false AND p.loai = :loai AND (p.ma LIKE :find OR p.ten LIKE :find OR p.dieuKien LIKE :find)")
    List<phieuGiamGiaReponse> searchByLoaiAndFind(Boolean loai, String find);

    @Query("SELECT new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(" +
            "p.id, p.ma, p.ten, p.loai, p.giaTri, p.giamToiDa, p.mucDo, p.ngayBatDau, p.ngayKetThuc, p.soLuong, p.dieuKien, p.trangThai) " +
            "FROM PhieuGiamGia p " +
            "WHERE p.deleted = false AND p.trangThai LIKE :trangThai AND (p.ma LIKE :find OR p.ten LIKE :find OR p.dieuKien LIKE :find)")
    List<phieuGiamGiaReponse> searchByTrangThaiAndFind(String trangThai, String find);

//    @Query("""
//            select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
//                            p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
//                             p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
//                            from PhieuGiamGia p where p.deleted=false
//                AND (COALESCE(:#{#keyFind}, '') = '' OR p.ma LIKE %:#{#keyFind}% OR p.ten LIKE %:#{#keyFind}%)
//                AND (COALESCE(:#{#minNgay}, null) IS NULL OR p.ngayBatDau >= :#{#minNgay})
//                AND (COALESCE(:#{#maxNgay}, null) IS NULL OR p.ngayKetThuc <= :#{#maxNgay})
//                AND (COALESCE(:#{#trangThai}, 'Tất Cả') IS NULL OR p.trangThai = :#{#trangThai})
//                AND (COALESCE(:#{#Loai}, 0) IS NULL OR p.loai = :#{#Loai})
//                AND (COALESCE(:#{#minGia}, null) IS NULL OR p.giaTri >= :#{#minGia})
//                AND (COALESCE(:#{#maxGia}, null) IS NULL OR p.giaTri <= :#{#maxGia})
//                AND (COALESCE(:#{#minGia}, null) IS NULL OR p.giamToiDa >= :#{#minGia})
//                AND (COALESCE(:#{#maxGia}, null) IS NULL OR p.giamToiDa <= :#{#maxGia})
//                """)
//    Page<phieuGiamGiaReponse> searchPhieuGiamGia(Pageable pageable, String keyFind, Integer Loai, String trangThai, Integer SapXep,
//                                                 Instant minNgay, Instant maxNgay, BigDecimal minGia, BigDecimal maxGia);
}
