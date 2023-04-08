package dive.dev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class OrderItem extends PanacheEntity {

    private Long orderId;

    private Long menuItemId;

    private BigDecimal price;
}
