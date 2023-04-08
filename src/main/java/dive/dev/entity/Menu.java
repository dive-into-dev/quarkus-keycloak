package dive.dev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Menu extends PanacheEntity {

    private Long restaurantId;

    private Boolean active;

    @Transient
    private List<MenuItem> menuItems;

}
