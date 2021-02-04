package pl.juliastasinska.expiryapp.items;

import org.springframework.data.jpa.repository.JpaRepository;

interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

}
