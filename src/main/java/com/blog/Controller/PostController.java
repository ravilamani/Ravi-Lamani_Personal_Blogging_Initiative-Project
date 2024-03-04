package com.blog.Controller;

import com.blog.Payload.Postdto;
import com.blog.Service.PostService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  // it is an API layer (this will help us to build {Controller layer} for the (API))
@RequestMapping("/api/posts")
public class PostController {



    /* http://localhost:8080/api/posts  */
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // Creating the Post Data
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody Postdto dto, BindingResult bindingResult){
        // if error occurred it show the field message in the postman
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Postdto postdto = postService.createPost(dto);

        return new ResponseEntity<>("Post is created", HttpStatus.CREATED);
    }

    // delete the post record by the (ID)
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable Long id){

        postService.deletePost(id);

        return new ResponseEntity<>("Post is Deleted from the database",HttpStatus.OK);
    }

   // if you want to get all data from the (database)

    @GetMapping
    public ResponseEntity<List<Postdto>> getAllPosts(){

        List<Postdto> postDtos= postService.getAllPosts();

        return new  ResponseEntity<>(postDtos,HttpStatus.OK);

    }

    // Sorting and pagination

    @GetMapping("/{field}") // Sorting by Any (Field)
    public ResponseEntity<List<Postdto>> getAllPostsWithSort(@PathVariable String field){

        List<Postdto> AllpostDtos= postService.getAllPostsWithSorting(field);

        return new  ResponseEntity<>(AllpostDtos,HttpStatus.OK);

    }

    @GetMapping("/pagination/{offset}/{pageSize}") // Sorting by Any (Field)
    public ResponseEntity<List<Postdto>> getAllPostsWithSort(@PathVariable int offset, @PathVariable int pageSize){

        List<Postdto> postDtosWithPageSize= postService.getAllPostsWithPagination(offset, pageSize);

        return new  ResponseEntity<>(postDtosWithPageSize,HttpStatus.OK);

    }






}
