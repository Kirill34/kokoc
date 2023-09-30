package repo;

import model.ActionGeoPoint;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ActionGeoPointRepository extends CrudRepository<ActionGeoPoint, Long> {
    public List<ActionGeoPoint> findAllBySportActionId(Long id);
}
