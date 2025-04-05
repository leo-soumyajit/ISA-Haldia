package com.soumyajit.ISA.HIT.HALDIA.Repository;

import com.soumyajit.ISA.HIT.HALDIA.Entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comments,Long> {
}
