package com.hailintang.blog.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.hailintang.blog.bean.Authority;
/**
 * authority的repository
 * @author aaron
 *
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long>{
}
