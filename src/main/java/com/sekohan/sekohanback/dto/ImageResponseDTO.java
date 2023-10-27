package com.sekohan.sekohanback.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageResponseDTO {
    private String path;

    @Builder
    public ImageResponseDTO(String path){
        this.path=path;
    }
}
