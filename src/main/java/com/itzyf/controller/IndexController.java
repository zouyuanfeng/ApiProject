package com.itzyf.controller;

import com.github.pagehelper.Page;
import com.itzyf.bean.ApiBean;
import com.itzyf.bean.Result;
import com.itzyf.service.ApiService;
import com.itzyf.utils.GlobalConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 依风听雨
 * @version 创建时间：2017/6/30 14:27
 */
@Controller
public class IndexController {

    private static Logger LOGGER = LoggerFactory.getLogger(IndexController.class.getSimpleName());
    private final ApiService apiService;

    @Autowired
    public IndexController(ApiService apiService) {
        this.apiService = apiService;
    }

    @RequestMapping({"/", "", "index"})
    public ModelAndView index(@RequestParam(defaultValue = "0") int index,
            @RequestParam(defaultValue = "ALL") String groupName) {
        ModelAndView mav = new ModelAndView("index");
        Page<ApiBean> apiBeans = groupName.equals("ALL") ? apiService.queryAllToPage(index + 1) :
                apiService.queryAllByGroupToPage(index + 1, groupName);
        mav.addObject("apis", apiBeans);
        mav.addObject("index", index);
        mav.addObject("pages", apiBeans.getPages());

        mav.addObject("groupNames", apiService.queryGroup());
        mav.addObject("groupName", groupName);
        return mav;
    }

    @GetMapping("add")
    public ModelAndView operationUpdate() {
        return new ModelAndView("form");
    }

    @GetMapping("update/{id}")
    public ModelAndView operationAdd(@PathVariable(value = "id", required = false) int id) {
        ModelAndView mav = new ModelAndView("form");
        if (id > 0) {
            mav.addObject("bean", apiService.queryById(id));
        }
        return mav;
    }

    @ResponseBody
    @PostMapping("operation")
    public Result operationPost(@RequestBody ApiBean apiBean) {
        Result result = new Result();
        boolean flag;
        if (apiBean.getId() <= 0) { //新增
            return apiService.addApi(apiBean);
        } else { //修改
            flag = apiService.updateById(apiBean);
        }
        if (flag) {
            result.setMsg("操作成功");
            result.setCode(0);
        } else {
            result.setMsg("操作失败");
            result.setCode(101);
        }
        return result;
    }


    @RequestMapping("search")
    public ModelAndView search(@RequestParam String keyword) {
        ModelAndView mav = new ModelAndView("index");
        List<ApiBean> apiBeans = apiService.queryAllToPage(keyword);
        mav.addObject("search",true);
        mav.addObject("apis", apiBeans);
        mav.addObject("index", 0);
        mav.addObject("pages", 1);
        return mav;
    }

    @ResponseBody
    @RequestMapping("api/{groupName}/{api}")
    public String getApi(@PathVariable("api") String api,
            @PathVariable("groupName") String groupName) {
        return apiService.queryResponseByMethod(groupName, api);
    }

    private <T> Result<T> createResult(T t) {
        Result<T> result = new Result<>();
        result.setMsg("成功");
        result.setCode(0);
        if (t != null) {
            result.setResult(t);
        }
        return result;
    }

    @ResponseBody
    @GetMapping("delete/{id}")
    public Result delete(@PathVariable("id") int id) {
        LOGGER.info("删除ID:" + id);
        boolean b = apiService.deleteById(id);
        if (b) {
            return createResult(null);
        } else {
            Result result = new Result();
            result.setCode(102);
            result.setMsg("操作失败");
            return result;
        }
    }

    @ResponseBody
    @PostMapping("update}")
    public Result update(@RequestBody ApiBean api) {
        return createResult(null);
    }


    @ResponseBody
    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Result<String> uploadFile(@RequestParam("file") MultipartFile file) {
        Result<String> result = new Result<>();
        if (file.isEmpty()) {
            result.setMsg("上传的文件为空");
            result.setCode(102);
            return result;
        }
        LOGGER.info("上传文件:" + file.getOriginalFilename());
        String home = System.getProperty("catalina.home") + "/temp_img/";//临时上传的目录

        File uploadPath = new File(home);
        //如果目录不存在
        if (!uploadPath.exists()) {
            //创建目录
            uploadPath.mkdirs();
        }

        String suffix = file.getOriginalFilename()
                .substring(file.getOriginalFilename().lastIndexOf("."));
        try {
            String path = saveBit(file.getInputStream(), home, suffix);
            result.setMsg("上传成功");
            result.setResult(GlobalConfig.getConfig().getConfigValue("img_path") + path);
            result.setCode(0);
        } catch (IOException e) {
            e.printStackTrace();
            result.setMsg("上传失败");
            result.setCode(102);
        }
        return result;
    }

    private String saveBit(InputStream inStream, String path, String suffix) throws IOException {
//        String guid = Guid.getRandomGUID();

        String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + "" + (
                new Random().nextInt(89) + 10);

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存

        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = outStream.toByteArray();
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File(path + name + suffix);
        //创建输出流
        FileOutputStream fileOutStream = new FileOutputStream(imageFile);
        //写入数据
        fileOutStream.write(data);
        return name + suffix;
    }

    @RequestMapping("upload")
    public String uploadPage() {
        return "upload";
    }

    @ResponseBody
    @RequestMapping("deleteMuti")
    public Result deleteMuti(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            LOGGER.info("id:" + id);
        }
        int[] ints = ids.stream().mapToInt(value -> value).toArray();
        apiService.batchDeleteApis(ints);
        return createResult(null);
    }
}
