package com.blog.Service.Impl;


import com.blog.Entity.Post;
import com.blog.Exception.ResourceNotFoundException;
import com.blog.Payload.Postdto;
import com.blog.Repository.PostRepository;
import com.blog.Service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {  // constructor based depedency injection(acts like @Autowired)
        this.postRepo = postRepo;
    }

    @Override
    public Postdto createPost(Postdto dto) {
        Post post =new Post();

        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());

        Post saved = postRepo.save(post);

        // after saving we have to send(Response) all data to Postman by converting (Post) to (Postdto)
        Postdto postDto=new Postdto();

        postDto.setId(saved.getId());
        postDto.setTitle(saved.getTitle());
        postDto.setDescription(saved.getDescription());
        postDto.setContent(saved.getContent());


        return postDto;
    }

    @Override
    public void deletePost(Long id) {
        // this below line acts like (----Throw block---)
        Post post=postRepo.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Record not Found...! "+id)
        );

        postRepo.deleteById(id);
    }

    @Override
    public List<Postdto> getAllPosts() {
        List<Post> listOfPosts = postRepo.findAll();
        List<Postdto> dtos = listOfPosts.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
       // the above line is converted to Podtdto
        return dtos;
    }

    @Override
    public List<Postdto> getAllPostsWithSorting(String field) {

        List<Post> listOfPost = postRepo.findAll(Sort.by(Sort.Direction.ASC,field));
        List<Postdto> dtos = listOfPost.stream().map(x -> mapToDto(x)).collect(Collectors.toList());

        return dtos;


    }

    // -----------------Pagination----------------------
    public List<Postdto> getAllPostsWithPagination(int offset, int pageSize){

        Page<Post> PostWithPagination = postRepo.findAll(PageRequest.of(offset, pageSize));

        // to convert (Page Obj) to (List) ---> following
        List<Post> listOfPageObjects = PostWithPagination.getContent();

        List<Postdto> dtoWithPageSize = listOfPageObjects.stream().map(p -> mapToDto(p)).collect(Collectors.toList());

        return dtoWithPageSize;
    }


    // the Below method code ---> is the Method which converts all the (post data) to (dto data)
   Postdto mapToDto(Post post){

       Postdto postdto = new Postdto();

       postdto.setId(post.getId());
       postdto.setTitle(post.getTitle());
       postdto.setDescription(post.getDescription());
       postdto.setContent(post.getContent());

       return postdto;
   }




}
