package xyz.energytrading.demo.repository;

import org.springframework.data.repository.Repository;
import xyz.energytrading.demo.models.ImportantInfo;

import java.util.List;
import java.util.Optional;

public interface ImportantInfoRepository extends Repository<ImportantInfo, Long> {
    ImportantInfo save(ImportantInfo info);
    Optional<ImportantInfo> findById(long id);
    Optional<List<ImportantInfo>> findAllByName(String name);
}
