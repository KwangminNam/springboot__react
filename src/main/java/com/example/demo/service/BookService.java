package com.example.demo.service;


import com.example.demo.domain.Book;
import com.example.demo.domain.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Book 저장하기(Book book){
        return bookRepository.save(book);
    }

    @Transactional(readOnly = true)
    public Book 한건가져오기(Long id){
        return bookRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("check id"));
    }

    @Transactional(readOnly = true)
    public List 모두가져오기(){
        return bookRepository.findAll();
    }

    @Transactional
    public Book 수정하기(Long id,Book book){
        Book bookEntity = bookRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("check id!"));
        bookEntity.setTitle(book.getTitle());
        bookEntity.setAuthor(book.getAuthor());
        return bookEntity;
    }

    @Transactional
    public String 삭제하기(Long id){
         bookRepository.deleteById(id);
         return "ok";
    }

}
