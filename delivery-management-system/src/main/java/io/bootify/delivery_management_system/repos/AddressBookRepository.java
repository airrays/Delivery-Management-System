package io.bootify.delivery_management_system.repos;

import io.bootify.delivery_management_system.domain.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressBookRepository extends JpaRepository<AddressBook, Long> {
}
