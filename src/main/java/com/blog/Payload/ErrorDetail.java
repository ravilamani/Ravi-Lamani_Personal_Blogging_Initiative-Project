package com.blog.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
// this is the payload layer which takes data from (Postman) and gives back to (Postman)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {

    private Date dateTime;
    private String message;
    private String detail;

}
