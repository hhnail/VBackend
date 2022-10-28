package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Override
    public void saveFile(String targetPath, MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File dest = new File(targetPath + fileName);
        try {
            file.transferTo(dest);
            log.info("文件保存成功");
        } catch (Exception e) {
            log.info("文件保存失败", e);
        }
    }

}
