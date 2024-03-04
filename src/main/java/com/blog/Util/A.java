package com.blog.Util;


import com.blog.Entity.Post;
import com.blog.Payload.Postdto;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class A {

    public static void main(String[] args) {

        Post post1 = new Post();

        post1.setId(1);
        post1.setTitle("Raccoon");
        post1.setDescription("new tech");
        post1.setContent("tech of 2030");

        Post post2 = new Post();
        post2.setId(2);
        post2.setTitle("Java");
        post2.setDescription("java tech");
        post2.setContent("tech of 2030");

        Post post3 = new Post();
        post3.setId(3);
        post3.setTitle("Pyhton");
        post3.setDescription("Python tech");
        post3.setContent("tech of 2030");

        Post post4 = new Post();
        post4.setId(4);
        post4.setTitle("Javascript");
        post4.setDescription("Javascript tech");
        post4.setContent("tech of 2030");


        // call the below method
        Postdto postdto = convertAllPostToPostdto(post1);
        Postdto postdto1 = convertAllPostToPostdto(post2);
        Postdto postdto2 = convertAllPostToPostdto(post3);
        Postdto postdto3 = convertAllPostToPostdto(post4);

        /* For example, we have 1 lakh of Post address in order to convert that it is not possible to
         write 1 lakh lines of code ....Below code is easy to handle that....*/

        List<Post> postData = Arrays.asList(post1,post2,post3,post4);
        List<Postdto> collect = postData.stream().map(A::convertAllPostToPostdto).collect(Collectors.toList());


        System.out.println(collect);


    }

    public static Postdto convertAllPostToPostdto(Post post){  // All the data present Entity class (Post)

        // create the object of ( Postdto )
        Postdto dto = new Postdto();

        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());


        return dto;
    }
}
