package service;

import dto.CategoryDTO;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import repository.CategoryRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Path("/")
public class CategoryService extends AbstractService<Category, CategoryDTO> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Path("/categories")
    @GET
    @Produces("application/json")
    @Transactional(propagation = Propagation.REQUIRED)
    public Response getCategoryList(@QueryParam("level") int level) {

        List<CategoryDTO> categories = toDTOList(categoryRepository.findAll(), level);
        return Response.status(200).entity(categories).build();
    }

    private List<CategoryDTO> toDTOList(List<Category> list, int targetLevel) {

        return list.stream().map(e -> toDTO(e, (e.getLevel() < targetLevel))).collect(Collectors.toList());
    }

    @Override
    protected CategoryDTO toDTO(Category entity, boolean includeChildren) {
        return entity != null ? includeChildren ?
                CategoryDTO.builder().
                        id(entity.getId()).
                        brand(entity.getBrand()).
                        childCategory(toDTOList(categoryRepository.findChildCategory(entity.getId()))).
                        parentCategory(toDTO(categoryRepository.findParentCategory(entity.getId()), false)).
                        level(entity.getLevel()).
                        build() :
                CategoryDTO.builder().
                        id(entity.getId()).
                        brand(entity.getBrand()).
                        childCategory(null).
                        parentCategory(toDTO(categoryRepository.findParentCategory(entity.getId()), false)).
                        level(entity.getLevel()).
                        build() :
                null;
    }
}
