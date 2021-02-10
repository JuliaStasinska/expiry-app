package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.juliastasinska.expiryapp.templates.FoodCategory;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    List<Subscription> findAll();

    //List<Subscription> findBySubscriptionStatus(Subscription.SubscriptionStatus status);

    //List<Subscription> findByRenewsOn(LocalDate date);

    List<Subscription> findByDescriptionContaining(String descriptionFragment);

    Optional<Subscription> findById(int id);

    <S extends Subscription> S save(S source);

}
