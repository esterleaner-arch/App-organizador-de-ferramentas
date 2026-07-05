package br.com.organizacasa.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class UploadService {

    @Value("${upload.dir:uploads}")
    private String uploadDir;

    public String salvarImagem(MultipartFile arquivo) throws IOException {

        if (arquivo == null || arquivo.isEmpty()) {
            return null;
        }

        Path pasta = Paths.get(uploadDir);

        if (!Files.exists(pasta)) {
            Files.createDirectories(pasta);
        }

        String nomeArquivo = System.currentTimeMillis() + "_"
                + StringUtils.cleanPath(arquivo.getOriginalFilename());

        Path destino = pasta.resolve(nomeArquivo);

        Files.copy(arquivo.getInputStream(), destino);

        return nomeArquivo;

    }

}