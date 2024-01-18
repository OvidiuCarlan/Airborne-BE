package airborne.persistance.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name="Reports")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reporter_id", referencedColumnName = "id")
    private UserEntity reporter;

    @ManyToOne
    @JoinColumn(name = "reported_id", referencedColumnName = "id")
    private UserEntity reported;

    @Column(name = "reason")
    private String reason;
}
