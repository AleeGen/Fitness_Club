package com.example.fitnessclub.controller;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;

@WebServlet(name = "imageController", urlPatterns = {"/uploadImage"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class ImageController extends HttpServlet {

    private static final String CONTENT_TYPE = "image/jpeg";
    private static final String DECODE = "UTF-8";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String pathEncode = request.getParameter(AttributeName.IMAGE_PATH);
        String pathDecode = URLDecoder.decode(pathEncode, DECODE);
        byte[] imageBytes = Files.readAllBytes(Paths.get(pathDecode));
        response.setContentType(CONTENT_TYPE);
        response.setContentLength(imageBytes.length);
        response.getOutputStream().write(imageBytes);
    }
}
