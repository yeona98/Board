package com.jane.board.repository;

import com.jane.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    // 1. entity class 내부에 association 있을 때
    @Query("SELECT b, w FROM Board b LEFT JOIN b.writer w WHERE b.bno = :bno")
    Object getBoardWithWriter(@Param("bno") Long bno); // Board 와 Member 를 같이 조회
    // 2. 특정 board 와 그 board 에 속한 reply 를 조회할 때 --> entity class 내부에 association X
    @Query("SELECT b, r FROM Board b LEFT JOIN Reply r ON r.board = b WHERE b.bno = :bno")
    List<Object[]> getBoardWithReply(@Param("bno") Long bno);

    // list 화면에 필요한 데이터 조회를 위한 JPQL
    @Query(value = "SELECT b, w, count(r) " +
            " FROM Board b " +
            " LEFT JOIN b.writer w" +
            " LEFT JOIN Reply r ON r.board = b " +
            " GROUP BY b",
            countQuery = "SELECT count (b) FROM Board b")
    Page<Object[]> getBoardWithReplyCount(Pageable pageable);

    // read 화면에 필요한 데이터 조회를 위한 JPQL
    @Query("SELECT b, w, count(r) " +
            " FROM Board b LEFT JOIN b.writer w " +
            " LEFT OUTER JOIN Reply r ON r.board = b " +
            " WHERE b.bno = :bno")
    Object getBoardByBno(@Param("bno") Long bno);
}
