package com.next.dream.controller.api;

import com.next.dream.config.FileProperConfig;
import com.next.dream.enums.ResultEnum;
import com.next.dream.utils.FileUtils;
import com.next.dream.utils.ResultVOUtil;
import com.next.dream.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：〈处理文件操作〉
 *
 * @author liyaohua
 * create on 2018/4/10
 * @version 1.0
 */
@RestController
@RequestMapping("/api/file")
@Slf4j
public class FileController {

    @Autowired
    private FileProperConfig fileProperConfig;

    /**
     * @Description: <br>
     *  处理文件上传
     * @return
     * @since  1.0.0
     * @author liyaohua
     * Created On 2018/4/10 上午9:19
     */
    @PostMapping("/upload")
    public ResultVO upload(@RequestParam("file") MultipartFile file){
        log.info("【文件上传】file type:{},file name :{}",file.getContentType(),file.getName());
        try {
            String filePath = FileUtils.saveByteToFilePath(file.getBytes(),fileProperConfig.getPath(),file.getOriginalFilename());
            Map<String,String> data = new HashMap<>(1);
            data.put("filePath",filePath);
            return ResultVOUtil.success(data);
        } catch (Exception e) {
            log.error("读取文件内容异常",e);
            return ResultVOUtil.failed(ResultEnum.FILE_READ_ERROR);
        }

    }
}
