package com.example.adt_t5_api_1b.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImageService {

    // Obtiene el path absoluto a la carpeta donde arranca la aplicación
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String UPLOAD_DIR = USER_DIR + File.separator + "uploads";

    public String saveImage(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IOException("El archivo está vacío.");
        }

        // Crear directorio si no existe
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Generar nombre único
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        // Guardar en la ruta absoluta
        File destinationFile = new File(directory, fileName);
        file.transferTo(destinationFile);

        return fileName;
    }
}

