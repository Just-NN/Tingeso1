package usach.tingeso.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long idReport;

    @Column(columnDefinition = "TEXT")
    private String r1Details;

    @Column(columnDefinition = "TEXT")
    private String r2RepairsVsVehiclesByTotalType;

    @Column(columnDefinition = "TEXT")
    private String r3AverageByBrand;

    @Column(columnDefinition = "TEXT")
    private String r4RepairsVsVehiclesByTotalEngine;
}