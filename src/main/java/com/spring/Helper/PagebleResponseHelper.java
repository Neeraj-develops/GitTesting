package com.spring.Helper;

import com.spring.Dtos.Electro_UsersDto;
import com.spring.Dtos.PageAbleResponse;
import com.spring.Entity.Electro_Users;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;

import java.util.List;

public class PagebleResponseHelper {

    public static <U,V>PageAbleResponse<U> pageAbleHelper(Page<V> page,Class<U> type){
        List<V> entity= page.getContent();
        List<U> listDtos= entity.stream()
                .map(object->new ModelMapper().map(object,type))
                .toList();
        PageAbleResponse<U> response= new PageAbleResponse<>();
        response.setContent(listDtos);
        response.setPageNumber(page.getNumber()+1);
        response.setPageSize(page.getSize());
        response.setTotalPage(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setLastPage(page.isLast());
        return response;
    }
}
