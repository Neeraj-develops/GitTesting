package com.spring.Exceptions;

import lombok.Builder;

@Builder
public class ResourceNotFoundException extends RuntimeException{
     public ResourceNotFoundException(){
         super("Resource not found acception!!!");
     }
     public ResourceNotFoundException(String message){
         super(message);
     }
}
