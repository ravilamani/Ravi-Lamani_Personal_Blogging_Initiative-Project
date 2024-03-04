package com.blog.Controller;

import com.blog.Payload.CommentDto;
import com.blog.Service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createCommentForPost(@PathVariable long postId,@RequestBody CommentDto commentDto){
        CommentDto commentForPost = commentService.createCommentForPost(postId, commentDto);

        return new ResponseEntity<>(commentForPost, HttpStatus.CREATED);

    }

    // to delete the Comment

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);

        return new ResponseEntity<>("Comment is Deleted...",HttpStatus.OK);
    }

    // to get all the Comment from the database
    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComment( ){
        List<CommentDto> allComment=commentService.getAllComment();
        return new ResponseEntity<>(allComment,HttpStatus.OK);
    }

   // Get all  Comments By (PostId) ---> Create List<Comment> findByColumnName(long postId)

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getAllCommentByPostID(@PathVariable long postId ){
        List<CommentDto> allCommentByPostID=commentService.getAllCommentByPostID(postId);
        return new ResponseEntity<>(allCommentByPostID,HttpStatus.OK);
    }






}
