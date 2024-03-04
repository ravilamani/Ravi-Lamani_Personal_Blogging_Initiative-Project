package com.blog.Service;

import com.blog.Payload.Postdto;

import java.util.List;

public interface PostService {

    public Postdto createPost(Postdto dto);

    void deletePost(Long id);


    List<Postdto> getAllPosts();

    List<Postdto> getAllPostsWithSorting(String field);

    public List<Postdto> getAllPostsWithPagination(int offset, int pageSize);
}
