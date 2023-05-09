package tn.devteam.immonexus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.devteam.immonexus.Entities.Likes;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Likes,Long> {
    List<Likes> findByUserId(Long userId);

}
