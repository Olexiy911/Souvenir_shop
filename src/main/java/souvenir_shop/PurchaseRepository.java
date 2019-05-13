package souvenir_shop;

import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;

public interface PurchaseRepository extends CrudRepository <Purchase, Integer> {
    List<Purchase> findAllByDate(Date date);
}
