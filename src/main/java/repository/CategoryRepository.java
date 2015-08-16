package repository;

import model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query("select parentCategory from Category e where e.id = :id")
    public Category findParentCategory(@Param("id") Integer id);

    @Query("select childCategory from Category e where e.id = :id")
    public List<Category> findChildCategory(@Param("id") Integer id);
}
