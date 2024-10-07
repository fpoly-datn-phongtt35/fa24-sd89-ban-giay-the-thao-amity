package org.example.backend.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.example.backend.dto.response.dotGiamGia.DotGiamGiaResponse;
import org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse;
import org.example.backend.models.PhieuGiamGia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

public interface PhieuGiamGiaRepository extends JpaRepository<PhieuGiamGia, UUID> {
    @Query("""
                select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
                p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
                 p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
                from PhieuGiamGia p where p.deleted=false 
            """)
    List<phieuGiamGiaReponse> getAllPhieuGiamGia();

    @Query("""
                update PhieuGiamGia p 
                set p.deleted=:deleted where p.id=:id 
            """)
    @Modifying
    @Transactional
    void updateDetailPhieuGiamGia(Boolean deleted, UUID id);

//    @Query( value = """
//        select new org.example.backend.dto.response.phieuGiamGia.phieuGiamGiaReponse(
//        p.id, p.ma, p.ten,p.loai, p.giaTri,p.giamToiDa,p.mucDo,
//         p.ngayBatDau, p.ngayKetThuc,p.soLuong, p.dieuKien, p.trangThai)
//         from PhieuGiamGia p where p.deleted=false and
//          (p.ma like :find or p.ten like :find
//          or p.dieuKien like :find) :filter
//""" , nativeQuery = true)
//    List<phieuGiamGiaReponse> searchPGG(String find , String filter);

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
}
