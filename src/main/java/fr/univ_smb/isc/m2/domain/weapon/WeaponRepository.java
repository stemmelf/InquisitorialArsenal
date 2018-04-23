package fr.univ_smb.isc.m2.domain.weapon;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WeaponRepository extends JpaRepository<Weapon, Long> {

    List<Weapon> findByState(String state);
    List<Weapon> findByOwner(String owner);

}