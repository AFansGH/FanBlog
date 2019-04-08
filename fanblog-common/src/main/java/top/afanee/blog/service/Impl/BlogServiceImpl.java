package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Attachment;
import top.afanee.blog.entity.Blog;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.BlogMapper;
import top.afanee.blog.po.enums.ArticleType;
import top.afanee.blog.po.enums.FileTopicType;
import top.afanee.blog.po.enums.MessageType;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.enums.TextLengthEnum;
import top.afanee.blog.po.model.MessageParams;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.BlogQuery;
import top.afanee.blog.po.query.UpdateQuery4ArticleCount;
import top.afanee.blog.service.AttachmentService;
import top.afanee.blog.service.BlogService;
import top.afanee.blog.service.MessageService;
import top.afanee.blog.utils.ImageUtils;
import top.afanee.blog.utils.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-29
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    private FormateAtService formateAtService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private MessageService messageService;
    
    
    
    @Override
    public PageResult<Blog> findBlogByPage(BlogQuery blogQuery) {
        Page<Blog> page = new Page<>(blogQuery.getPageOn(), blogQuery.getPageSize());
        EntityWrapper<Blog> wrapper = new EntityWrapper<>();
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Blog> topicList = baseMapper.selectPage(page, wrapper);
        PageResult<Blog> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }

    
    public void modifyBlog(Blog blog, Attachment attachment) throws BussinessException {
        // TODO Auto-generated method stub
        
    }
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
    public void addBlog(Blog blog, Attachment attachment)
            throws BussinessException {
        if(blog.getTitle() == null || blog.getTitle().length() > TextLengthEnum.TEXT_100_LENGTH.getLength()
                ||StringUtils.isEmpty(blog.getContent()) || blog.getContent().length() > TextLengthEnum.LONGTEXT.getLength()
                ){
            throw new BussinessException("参数错误");
        }
        String title = HtmlUtils.htmlUnescape(blog.getTitle());
        String content = blog.getContent();
        content = StringUtils.replaceLast(content.replaceFirst("<p>", ""), "</p>", "");
        String summary = StringUtils.cleanHtmlTag(HtmlUtils.htmlUnescape(content));
        if (summary.length() > TextLengthEnum.TEXT_200_LENGTH.getLength()) {
            summary = summary.substring(0,
                    (int) TextLengthEnum.TEXT_200_LENGTH.getLength())
                    + "......";
        }
        Set<Integer> userIds = new HashSet<Integer>();
        //为内容@用户的地方添加超链接
        String formatContent = formateAtService.generateRefererLinks(content,
                userIds);
        blog.setTitle(title);
        blog.setSummary(summary);
        blog.setContent(formatContent);
        String blogImage = ImageUtils.getImages(content);
        blog.setBlogImage(blogImage);
//      String blogImageSmall = ImageUtils.createThumbnail(topicImage, true);
//      blog.setTopicImageThum(blogImageSmall);
        Date curDate = new Date();
        blog.setCreateTime(curDate);
        super.baseMapper.insert(blog);
        
        //博客附件
        if(!StringUtils.isEmpty(attachment.getFileName()) &&
                !StringUtils.isEmpty(attachment.getFileUrl())){
            attachment.setTopicId(blog.getBlogId());
            attachment.setTopicType(FileTopicType.BLOG);
            this.attachmentService.addAttachment(attachment);
        }
        //通知信息
        MessageParams messageParams = new MessageParams();
        messageParams.setArticleId(blog.getBlogId());
        messageParams.setArticleType(ArticleType.BLOG);
        messageParams.setArticleUserId(blog.getUserId());
        messageParams.setMessageType(MessageType.AT_ARTICLE_MESSAGE);
        messageParams.setSendUserName(blog.getUserName());
        messageParams.setSendUserId(blog.getUserId());
        messageParams.setReceiveUserIds(userIds);
        messageService.createMessage(messageParams);
        
    }


    @Override
    public Blog showBlog(Integer blogId) throws BussinessException {
        Blog blog = baseMapper.selectById(blogId);
        if(blog == null){
            throw new BussinessException("文章不存在,或已删除");
        }
        blog.setAttachment(this.attachmentService.getAttachmentByTopicIdAndFileType(blog.getBlogId(), FileTopicType.BLOG));
        
        //用于更新博客的阅读、喜欢、收藏次数
        UpdateQuery4ArticleCount updateQuery4ArticleCount = new UpdateQuery4ArticleCount();
        updateQuery4ArticleCount.setAddReadCount(Boolean.TRUE);
        updateQuery4ArticleCount.setArticleId(blogId);
        super.baseMapper.updateInfoCount(updateQuery4ArticleCount);
        return blog;
    }
}
