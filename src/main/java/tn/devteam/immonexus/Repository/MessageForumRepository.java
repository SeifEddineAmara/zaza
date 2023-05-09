package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.MessageForum;
@Repository
public interface MessageForumRepository extends JpaRepository<MessageForum,Long> {
}
