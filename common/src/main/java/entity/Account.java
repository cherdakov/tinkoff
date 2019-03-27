package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;


@Entity
@Table(name = "customers")
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Setter
    private UUID id;


    @NotBlank
    @Column(
            name = "owner",
            nullable = false
    )
    @Getter
    @Setter
    private UUID ownerId;


    @NotBlank
    @Column(
            name = "total",
            nullable = false
    )
    @Getter
    @Setter
    private long total;

    public void debit(long amount){
        this.total -= amount;
    }

    public void credit(long amount){
        this.total += amount;
    }
}