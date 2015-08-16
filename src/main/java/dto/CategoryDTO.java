package dto;

import lombok.*;
import model.Category;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CategoryDTO {

    private int id;
    private String brand;
    private int level;
    private CategoryDTO parentCategory;
    private List<CategoryDTO> childCategory;
}
