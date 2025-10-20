package br.com.fiap.Sprint4_SOA_XP.repository;

import br.com.fiap.Sprint4_SOA_XP.modelo.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
