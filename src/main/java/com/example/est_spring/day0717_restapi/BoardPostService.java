package com.example.est_spring.day0717_restapi;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j // 로그찍을때 꿀팁
public class BoardPostService {

    List<BoardPost> boardPosts = new ArrayList<>();
    private Long nextPostId = 1L;
    private Long nextCommentId = 1L;

    public BoardPostDto createBoardPost(BoardPostDto boardPostDto) {
        BoardPost boardPost = convertToBoardPostEntity(boardPostDto);
        boardPost.setId(nextPostId++);
        boardPost.setCreatedAt(LocalDateTime.now());
        boardPosts.add(boardPost);
        return convertToBoardPostDto(boardPost);
    }

    private static BoardPost convertToBoardPostEntity(BoardPostDto boardPostDto) {
        BoardPost boardPost = new BoardPost();
        boardPost.setTitle(boardPostDto.getTitle());
        boardPost.setContent(boardPostDto.getContent());
        boardPost.setAuthor(boardPostDto.getAuthor());
        if (boardPostDto.getComments() != null) {
            boardPostDto.getComments().forEach(commentDto -> {
                Comment comment = convertToCommentEntity(commentDto);
                comment.setBoardPost(boardPost);
                boardPost.addComment(comment);

            });
        }
        return boardPost;
    }

    private static Comment convertToCommentEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setContent(commentDto.getContent());
        comment.setAuthor(commentDto.getAuthor());
        return comment;
    }

    private BoardPostDto convertToBoardPostDto(BoardPost boardPost) {
        BoardPostDto boardPostDto = new BoardPostDto();
        boardPostDto.setId(boardPost.getId());
        boardPostDto.setTitle(boardPost.getTitle());
        boardPostDto.setContent(boardPost.getContent());
        boardPostDto.setAuthor(boardPost.getAuthor());
        boardPostDto.setCreatedAt(boardPost.getCreatedAt());
        boardPostDto.setUpdatedAt(boardPost.getUpdatedAt());

        if (boardPost.getComments() != null) {
            boardPostDto.setComments(
                    boardPost.getComments().stream().map(BoardPostService::convertToCommentDto)
                            .collect(Collectors.toList())
            );
        }
        return boardPostDto;
    }

    private static CommentDto convertToCommentDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setContent(comment.getContent());
        commentDto.setAuthor(comment.getAuthor());
        commentDto.setCreatedAt(comment.getCreatedAt());
        return commentDto;
    }

    public List<BoardPostDto> getAllBoardPosts() {
        return boardPosts.stream()
                .map(this::convertToBoardPostDto)
                .collect(Collectors.toList());
    }

    public BoardPostDto getBoardPostDtoById(Long id) {
        log.info("sdfsdfsdfsdf");
        return boardPosts.stream()
                .filter(p -> p.getId().equals(id))
                .map(this::convertToBoardPostDto)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없어요"));

    }

    public void deleteBoardPost(Long id) {
        BoardPost boardPost = findBoardPostById(id);
        boardPosts.remove(boardPost);
    }

    public BoardPostDto updateBoardPost(Long id, BoardPostDto updateBoardPostDto) {
        // 이 메서드에 리스트에 저장하는 기능이 없는데 어떻게 업데이트가 되는것인가?
        // 간단하게 설명하면 findBoardPostById로 받아온 리스트 내의 객체가 유지되어 리턴된다고 생각하면 된다.

        BoardPost boardPost = findBoardPostById(id);
        boardPost.setTitle(updateBoardPostDto.getTitle());
        boardPost.setContent(updateBoardPostDto.getContent());
        boardPost.setUpdatedAt(LocalDateTime.now());
        return convertToBoardPostDto(boardPost);
    }

    public CommentDto createComment(Long postId, CommentDto commentDto) {
        BoardPost boardPost = findBoardPostById(postId);
        Comment comment = convertToCommentEntity(commentDto);
        comment.setId(nextCommentId++);
        comment.setCreatedAt(LocalDateTime.now());

        boardPost.addComment(comment);
        return convertToCommentDto(comment);
    }

    public void deleteComment(Long postId, Long commentId) {
        BoardPost boardPost = findBoardPostById(postId);
        Comment comment = findCommentById(commentId, boardPost);
        boardPost.removeComment(comment);
    }

    // 원래는 delete에 있었는데 이걸 따로 분리함.
    // SRP (단일 책임 원칙)을 지키게 되는 것.
    private BoardPost findBoardPostById (Long id){
        return boardPosts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 삭제."));
    }

    private Comment findCommentById(Long commentId, BoardPost boardPost){
        return boardPost.getComments().stream()
                .filter(c -> c.getId().equals(commentId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("댓글이 없어용"));
    }




}