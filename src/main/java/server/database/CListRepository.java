package server.database;

import commons.CList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CListRepository extends JpaRepository<CList, Long> {}

