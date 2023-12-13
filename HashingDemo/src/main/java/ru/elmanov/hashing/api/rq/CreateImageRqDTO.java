package ru.elmanov.hashing.api.rq;

import lombok.Builder;
import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

@Value
@Builder
public class CreateImageRqDTO {

    String name;
    MultipartFile content;
}
