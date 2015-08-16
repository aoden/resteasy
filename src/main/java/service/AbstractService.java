package service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Abstract Service
 * @param <E> Entity type
 * @param <T> DTO type
 */
public abstract class AbstractService<E, T> {

    /**
     * Convert to dto
     *
     * @param entity entity to convert
     * @return
     */
    protected abstract T toDTO(E entity);
    protected List<T> toDTOList(List<E> entities) {

        return entities.stream().map(e -> toDTO(e)).collect(Collectors.toList());
    }
}
