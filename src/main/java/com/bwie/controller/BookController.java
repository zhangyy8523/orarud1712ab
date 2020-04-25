package com.bwie.controller;

import cn.hutool.core.util.RandomUtil;
import com.bwie.model.TBook;
import com.bwie.model.TBorrowingBook;
import com.bwie.model.TUser;
import com.bwie.service.BookService;
import com.bwie.utils.StaticFlag;
import com.bwie.vo.BookVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @描述：
 * @作者：zhangyuyang
 * @日期：2020/4/22 14:48
 */
@Controller
@RequestMapping("/book")
public class BookController {
    @Autowired
    private RedisTemplate redisTemplate ;



    /*磁盘上的路径*/
    @Value("${file.upload.path}")
    private String filePath ;

    @Autowired
    private BookService bookService;

    @RequestMapping("/queryBookList")
    public ModelAndView queryBookList(BookVo book) {
        ModelAndView modelAndView = new ModelAndView("book_list");
        List<BookVo> books = bookService.queryBookList(book);
        modelAndView.addObject("bookList", books);

        return modelAndView;
    }

    /**
     * 借出
     */
    @RequestMapping("/lendBook")
    public String lendBook(String bookId, HttpServletRequest request) {
        // 需要记录哪个用户操作的
        TUser user = (TUser) request.getSession().getAttribute(StaticFlag.USERINFO);
        try {
            if (bookService.lendBook(user, bookId) > 0) {
                return "redirect:/book/queryBookList";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("数据添加异常");
        }
        return null;
    }

    /**
     * 归还功能
     *
     * @param bookId
     * @return
     */
    @RequestMapping("/restorationBook")
    @ResponseBody
    public int restorationBook(String bookId) {
        if (!StringUtils.isEmpty(bookId)) { //如果id 不为空，则更新数据
            int flag = bookService.restorationBook(bookId);
            return flag;
        }
        return 0;
    }


    /**
     *
     * 去添加页面
     */
    @RequestMapping("/toBookAdd")
    public String toBookAdd(){

        return "book_add";
    }


    @RequestMapping("/add")
    public String add(@RequestParam("file") MultipartFile file, TBook tBook, Model model){
        if (file.getSize()>0){ //对图片 进行上传并且赋值
            String picUrl = StaticFlag.uploadUtils(file, filePath);
            tBook.setPicUrl(picUrl);
        }

        int flag = bookService.add(tBook);
        if (flag>0){
            return "redirect:/book/queryBookList";
        }else{
            model.addAttribute("msg","添加失败");
            return "book_add";
        }
    }

    /**
     * 点赞功能
     * @return
     */
    @RequestMapping("/updown")
    @ResponseBody
    public String updown(String id,HttpServletRequest request){
        //判断当前用户是否登录
        TUser user = (TUser) request.getSession().getAttribute(StaticFlag.USERINFO);
        if(user!=null){
            //判断当前用户是否点赞过，如果为true 则代表用户点赞过
            Boolean aBoolean = redisTemplate.hasKey(user.getId() + "updown_" + id);
            if(!aBoolean){
                redisTemplate.opsForValue().increment(user.getId()+"updown_"+id,1); //完成点赞的功能
                return "yes";
            }else{
                return "no";// 不能点赞
            }
        }else{
            return "noLogin"; //没有登录
        }

    }


    /**
     * 跳转到书籍详情页面，同时点击量新增1
     * @param bookId
     * @param model
     * @return
     */
    @RequestMapping("/toQueryBookInfo")
    public String toQueryBookInfo(String bookId,Model model){
        model.addAttribute("bookId",bookId) ;
        //统计点击量
        redisTemplate.opsForValue().increment(bookId,1); //完成点击量的加1
        return "book_info";
    }


    @RequestMapping("/queryBookInfo")
    @ResponseBody
    public TBook queryBookInfo(String id){
        TBook tBook =  bookService.queryBookInfo(id);
        Boolean aBoolean = redisTemplate.hasKey(id);
        if(aBoolean){ //如果id存在redis中，则去获取他的点击量
            Integer  count = (Integer) redisTemplate.opsForValue().get(id);
            tBook.setTraffic((long)count); //赋值
        }else{ //如果不存在，则设置当前的点击量量为0
            tBook.setTraffic((long)0);
        }
        return tBook;
    }



}
