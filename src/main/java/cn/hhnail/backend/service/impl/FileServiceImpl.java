package cn.hhnail.backend.service.impl;

import cn.hhnail.backend.mapper.FileMapper;
import cn.hhnail.backend.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    FileMapper fileMapper;

    @Override
    @Transactional
    public void saveFile(String targetPath, MultipartFile file) {
        // 参数准备
        String fileName = file.getOriginalFilename();
        UUID uuid = UUID.randomUUID();

        // 逻辑存储
        Map<String, Object> param = new HashMap<>();
        param.put("origin_name", fileName);
        param.put("uuid", uuid.toString());
        param.put("c_time", new Date());
        param.put("u_time", null);
        param.put("description", "I am description");
        fileMapper.saveFile(param);

        // 物理存储
        File dest = new File(targetPath + fileName);
        try {
            file.transferTo(dest);
            log.info("文件保存成功");
        } catch (Exception e) {
            log.info("文件保存失败", e);
        }
    }

}
