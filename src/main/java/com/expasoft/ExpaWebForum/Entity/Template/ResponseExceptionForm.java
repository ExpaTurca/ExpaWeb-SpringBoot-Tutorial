package com.expasoft.ExpaWebForum.Entity.Template;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import org.springframework.http.HttpHeaders;

public record ResponseExceptionForm(int statusCode, String body, HttpHeaders httpHeaders, HttpStatus status, WebRequest request,String exception) { }
