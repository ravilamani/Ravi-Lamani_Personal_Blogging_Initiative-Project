package com.blog.Service;

import com.blog.Payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createCommentForPost(long postId, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getAllComment();

    List<CommentDto> getAllCommentByPostID(long postId);
}
