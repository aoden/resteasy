package model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public")
@EqualsAndHashCode(of = "id")
public class Category implements Serializable {

    @Id
    @GeneratedValue
    private int id;
    private String brand;
    private int level;
    @ManyToOne
    @JoinColumn(name = "parent")
    private Category parentCategory;
    @OneToMany(mappedBy = "parentCategory")
    private List<Category> childCategory;
}
