package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.SubjectForum;
@Repository
public interface SubjectForumRepository extends JpaRepository< SubjectForum,Long> {
}
