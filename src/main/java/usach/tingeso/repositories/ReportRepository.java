package usach.tingeso.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import usach.tingeso.entities.ReportEntity;

public interface ReportRepository extends JpaRepository<ReportEntity, Long>{
    ReportEntity findReportByIdReport(Long id);
}