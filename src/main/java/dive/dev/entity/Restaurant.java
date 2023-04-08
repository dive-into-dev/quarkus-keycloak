package dive.dev.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Getter
@Setter
@Entity
public class Restaurant extends PanacheEntity {

    private String name;

    private String location;

    @Column(name = "type_name")
    private String type;
}
