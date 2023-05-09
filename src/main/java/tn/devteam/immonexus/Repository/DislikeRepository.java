package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Dislike;
@Repository
public interface DislikeRepository extends JpaRepository<Dislike,Long> {
        }
