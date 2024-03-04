package com.blog.Payload;



import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postdto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotEmpty
    @Size(min = 2,message = "Title should be At least 2 character ")
    private String title;

    @NotEmpty
    @Size(min = 4,message = "Description should be At least 2 character ")
    private String description;

    @NotEmpty
    @Size(min = 4,message = "Content should be At least 2 character ")
    private String content;




}
