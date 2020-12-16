package com.app.vendas.core.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vendas.domain.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
	
	Optional<List<Order>> findByCustomer(Order order);
	
	@Query(" select o from Order o left join fetch o.items where o.id = :id ")
    Optional<Order> findByIdFetchItems(@Param("id") Integer id);

}
