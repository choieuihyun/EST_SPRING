package com.example.est_spring.day0717_restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board-post")
public class BoardPostController {

    private final BoardPostService boardPostService;

    @Autowired
    public BoardPostController(BoardPostService boardPostService) {
        this.boardPostService = boardPostService;
    }

    @PostMapping
    public ResponseEntity<BoardPostDto> createBoardPost(@RequestBody BoardPostDto boardPostDto){
        BoardPostDto createdBoardPostDto = boardPostService.createBoardPost(boardPostDto);
        return ResponseEntity.ok(createdBoardPostDto);
    }

    @GetMapping
    public ResponseEntity<List<BoardPostDto>> getBoardPostList(){
        List<BoardPostDto> boardPostDtos = boardPostService.getAllBoardPosts();
        return ResponseEntity.ok(boardPostDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardPostDto> getBoardPost(@PathVariable("id") Long id){
        BoardPostDto boardPostDto = boardPostService.getBoardPostDtoById(id);

        return ResponseEntity.ok(boardPostDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BoardPostDto> deleteBoardPost(@PathVariable("id") Long id){
        boardPostService.deleteBoardPost(id);
        return ResponseEntity.noContent().build(); // 리턴값이 없으니까 noContent
    }

    @PutMapping("/{id}")
    public ResponseEntity<BoardPostDto> updateBoardPost(@PathVariable Long id, @RequestBody BoardPostDto boardPostDto){
        BoardPostDto updatedBoardPostDto = boardPostService.updateBoardPost(id, boardPostDto);

        return ResponseEntity.ok(updatedBoardPostDto);
    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<CommentDto> addComment(@PathVariable Long postId, @RequestBody CommentDto commentDto){
        CommentDto createdCommentDto = boardPostService.createComment(postId, commentDto);

        return ResponseEntity.ok(createdCommentDto);
    }

    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable("postId") Long postId, @PathVariable("commentId") Long commentId){
        boardPostService.deleteComment(postId, commentId);
        return ResponseEntity.noContent().build(); // 이거 항상 유의.
    }

}