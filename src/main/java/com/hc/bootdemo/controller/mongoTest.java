package com.hc.bootdemo.controller;

import com.hc.bootdemo.config.GridConfig;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.commons.io.IOUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @desc
 * @Author：hanchuang
 * @Version 1.0
 * @Date：add on 16:11 2019/6/10
 */
@RestController
@RequestMapping(value = "/mongo")
public class mongoTest {

    @Autowired
    private GridFsTemplate gridFsTemplate;
    @Autowired
    private GridConfig gridConfig;

    // 音频文件上传
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST, produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ObjectId uploadFile(@RequestParam("file") MultipartFile file) throws Exception {

        return gridFsTemplate.store(file.getInputStream(), file.getOriginalFilename(), file.getContentType());
    }

    // 音频文件下载
    @RequestMapping(value = "/getFile", method = RequestMethod.GET)
    public void getFile(@RequestParam(value = "id") String id,HttpServletResponse response) throws IOException {
        System.out.println("id================"+id);
        GridFSFile gridFSFile = gridFsTemplate.findOne(new Query().addCriteria(Criteria.where("_id").is(id)));
        GridFsResource fsResource = gridConfig.convertGridFSFile2Resource(gridFSFile);
        IOUtils.copy(fsResource.getInputStream(),response.getOutputStream());
    }

    @RequestMapping(value = "/deleteFile", method = RequestMethod.DELETE)
    public int deleteFile(@RequestParam(value = "fileName") String fileName) {
        gridFsTemplate.delete(new Query().addCriteria(Criteria.where("filename").is(fileName)));
        return 0;
    }
}
