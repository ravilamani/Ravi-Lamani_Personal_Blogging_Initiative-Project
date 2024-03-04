package com.blog.Service.Impl;

import com.blog.Entity.Comment;
import com.blog.Entity.Post;
import com.blog.Exception.ResourceNotFoundException;
import com.blog.Payload.CommentDto;
import com.blog.Repository.CommentRepository;
import com.blog.Repository.PostRepository;
import com.blog.Service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepo;
    private CommentRepository commentRepo;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
    }

    @Override
    public CommentDto createCommentForPost(long postId, CommentDto commentDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not Found...! " + postId)
        );

        Comment comment = new Comment();

        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        comment.setPost(post);

        Comment savedComment = commentRepo.save(comment);

        CommentDto dto = new CommentDto();

        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setBody(savedComment.getBody());
        dto.setEmail(savedComment.getEmail());

        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        Comment comment = commentRepo.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment is not found with the ID :" + commentId)
        );

        commentRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getAllComment() {
        List<Comment> allComments = commentRepo.findAll();
        List<CommentDto> dtos = allComments.stream().map((x) -> mapToDto(x)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public List<CommentDto> getAllCommentByPostID(long postId) {
        List<Comment> byPostId = commentRepo.findByPostId(postId);
        List<CommentDto> dtos = byPostId.stream().map((x) -> mapToDto(x)).collect(Collectors.toList());

        return dtos;
    }

    // to convert comment to CommentDto
    public CommentDto mapToDto(Comment comment){

        CommentDto dto = new CommentDto();

        dto.setId(comment.getId());
        dto.setName(comment.getName());
        dto.setBody(comment.getBody());
        dto.setEmail(comment.getEmail());

        return dto;
    }
}
