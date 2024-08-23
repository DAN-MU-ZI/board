package org.example.springrepositorytemplate.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Long> {

	@Query("select "
		+ "distinct p "
		+ "from Post p "
		+ "left outer join Member u1 on p.member=u1 "
		+ "left outer join Comment c on c.post=p "
		+ "left outer join Member u2 on c.member=u2 "
		+ "where "
		+ "   p.title like %:kw% "
		+ "   or p.content like %:kw% "
		+ "   or u1.username like %:kw% "
		+ "   or c.content like %:kw% "
		+ "   or u2.username like %:kw% ")
	Page<Post> findAllByKeyword(@Param("kw") String kw, Pageable pageable);

	Page<Post> findAllByTitleContainingIgnoreCase(String keyword, Pageable pageable);

	Page<Post> findAllByContentContainingIgnoreCase(String keyword, Pageable pageable);

	@Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :kw, '%')) " +
		"OR LOWER(p.content) LIKE LOWER(CONCAT('%', :kw, '%'))")
	Page<Post> findByTitleContainingOrContentContaining(@Param("kw") String kw, Pageable pageable);

	@Query("select distinct p from Post p "
		+ "left outer join Comment c on c.post = p "
		+ "where LOWER(c.content) LIKE LOWER(CONCAT('%', :kw, '%'))")
	Page<Post> findAllByCommentsContainingKeyword(@Param("kw") String kw, Pageable pageable);

	Page<Post> findAllByMember_Username(String keyword, Pageable pageable);
}
