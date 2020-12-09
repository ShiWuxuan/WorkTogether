package com.wuzi.WorkTogether.controller;

import com.wuzi.WorkTogether.domain.Post;
import com.wuzi.WorkTogether.domain.Record;
import com.wuzi.WorkTogether.domain.dto.PageDto;
import com.wuzi.WorkTogether.domain.dto.PostDto;
import com.wuzi.WorkTogether.domain.dto.RecordDto;
import com.wuzi.WorkTogether.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author 施武轩
 * @version 1.0
 * @date 2020/12/7 19:08
 * @lastEditor
 */
@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;

    @RequestMapping("/AllPost/{page}")
    public String queryAllPost(Model model, @PathVariable Integer page){
        PageDto pagination = postService.getPostForPage(page,5);
        model.addAttribute("pagination",pagination);
        return "forum";
    }


    @RequestMapping("/AllRecord/{postId}")
    public String queryPostDetail(Model model, @PathVariable Integer postId){
        Integer number = postService.queryRecordNumber(postId);
        model.addAttribute("number",number);
        List<RecordDto> records = postService.queryPostDetail(postId);
        model.addAttribute("discuss",records);
        PostDto post = postService.queryPostInfo(postId);
        model.addAttribute("postInfo",post);
        return "postDetail";
    }

    @RequestMapping("/submitComment")
    public boolean submitComment(Record record){
        return postService.addRecord(record);
    }

    @RequestMapping("/like")
    public String likePost(Integer postId){
        postService.likePost(postId);
        return "ok";
    }

    @RequestMapping("/gotoMakePost")
    public String gotoMakePost(){
        return "newPost";
    }

    @RequestMapping("/makePost")
    public String makePost(Model model,Post post){
        if(postService.addPost(post)==0){
            model.addAttribute("result","fail");
            return "redirect:/post/AllPost/1";
        }
        else {
            model.addAttribute("result","success");
            return "redirect:/post/AllRecord/" + post.getId();
        }
    }
}
