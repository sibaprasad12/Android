package com.ngallazzi.data.repositories.books;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J%\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u000b\u001a\u00020\fH\u0096@\u00f8\u0001\u0000\u00a2\u0006\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019\u00a8\u0006\u000e"}, d2 = {"Lcom/ngallazzi/data/repositories/books/BooksRemoteDataSourceImpl;", "Lcom/ngallazzi/data/repositories/books/BooksRemoteDataSource;", "service", "Lcom/ngallazzi/data/api/BooksApi;", "mapper", "Lcom/ngallazzi/data/mappers/BookApiResponseMapper;", "(Lcom/ngallazzi/data/api/BooksApi;Lcom/ngallazzi/data/mappers/BookApiResponseMapper;)V", "getBooks", "Lcom/ngallazzi/domain/common/Result;", "", "Lcom/ngallazzi/domain/entities/Volume;", "author", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "data_debug"})
public final class BooksRemoteDataSourceImpl implements com.ngallazzi.data.repositories.books.BooksRemoteDataSource {
    private final com.ngallazzi.data.api.BooksApi service = null;
    private final com.ngallazzi.data.mappers.BookApiResponseMapper mapper = null;
    
    @org.jetbrains.annotations.Nullable()
    @java.lang.Override()
    public java.lang.Object getBooks(@org.jetbrains.annotations.NotNull()
    java.lang.String author, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.ngallazzi.domain.common.Result<? extends java.util.List<com.ngallazzi.domain.entities.Volume>>> p1) {
        return null;
    }
    
    public BooksRemoteDataSourceImpl(@org.jetbrains.annotations.NotNull()
    com.ngallazzi.data.api.BooksApi service, @org.jetbrains.annotations.NotNull()
    com.ngallazzi.data.mappers.BookApiResponseMapper mapper) {
        super();
    }
}