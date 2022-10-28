package cn.hhnail.backend.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * 保存文件 到 指定 服务器地址
     * @param targetPath
     * @param file
     */
    public void saveFile(String targetPath, MultipartFile file);

}
