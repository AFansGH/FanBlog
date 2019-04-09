package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Shuoshuo;
import top.afanee.blog.entity.ShuoshuoComment;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.ShuoshuoMapper;
import top.afanee.blog.po.enums.ArticleType;
import top.afanee.blog.po.enums.MessageType;
import top.afanee.blog.po.enums.OrderByEnum;
import top.afanee.blog.po.enums.TextLengthEnum;
import top.afanee.blog.po.model.MessageParams;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.ShuoShuoQuery;
import top.afanee.blog.service.MessageService;
import top.afanee.blog.service.ShuoshuoCommentService;
import top.afanee.blog.service.ShuoshuoService;
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

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-31
 */
@Service
public class ShuoshuoServiceImpl extends ServiceImpl<ShuoshuoMapper, Shuoshuo> implements ShuoshuoService {
    
    @Autowired
    private ShuoshuoMapper shuoshuoMapper;
    
    @Autowired
    private FormateAtService formateAtService;
    @Autowired
    private ShuoshuoCommentService shuoshuoCommentService;
    @Autowired
    private MessageService messageService;
    
    
    @Override
    public PageResult<Shuoshuo> findShuoShuoListByPage(ShuoShuoQuery shuoShuoQuery) {
        Page<Shuoshuo> page = new Page<>(shuoShuoQuery.getPageOn(), shuoShuoQuery.getPageSize());
        EntityWrapper<Shuoshuo> wrapper = new EntityWrapper<>();
        wrapper.where("user_id = {0}", shuoShuoQuery.getUserId());
        wrapper.orderBy(OrderByEnum.CREATE_TIME_DESC.getOrderBy());
        List<Shuoshuo> topicList = shuoshuoMapper.selectPage(page, wrapper);
        PageResult<Shuoshuo> pageResult = new PageResult<>(page,topicList);
        return pageResult;
    }

    @Override
    public Shuoshuo findShuoShuo(ShuoShuoQuery shuoShuoQuery) {
        Shuoshuo shuoshuo = super.selectById(shuoShuoQuery.getId());
        return shuoshuo;
    }

    /**
     * 评论说说需要给被评论者发消息，事务方法
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW, rollbackFor=BussinessException.class)
    public void addShuoShuoComment(ShuoshuoComment shuoShuoComment) throws BussinessException {
        String content = shuoShuoComment.getContent();
        if(StringUtils.isEmpty(content) || content.length() > TextLengthEnum.TEXT.getLength()){
            throw new BussinessException("参数错误");
        }
        content = StringUtils.addLink(content);//给网页加链接
        Set<Integer> userIdSet = new HashSet<Integer>();
        String formatContent = formateAtService.generateRefererLinks(content, userIdSet);
        //TODO给用户发消息
        shuoShuoComment.setContent(formatContent);
        shuoShuoComment.setCreateTime(new Date());
        //更新说说的评论数(原子性操作，应该在sql中体现)
        this.shuoshuoMapper.updateShuoShuoCommentCount(shuoShuoComment.getShuoshuoId());
        this.shuoshuoCommentService.insert(shuoShuoComment);
        
        //this.userService.addMark(MarkEnum.MARK_COMMENT.getMark(), shuoShuoComment.getUserId()); 
        //说说评论之后需要给用户发消息
        Integer shuoshuoId = shuoShuoComment.getShuoshuoId();
        MessageParams messageParams = new MessageParams();
        messageParams.setArticleId(shuoshuoId);
        messageParams.setArticleType(ArticleType.SHUOSHUO);
        ShuoShuoQuery shuoShuoQuery = new ShuoShuoQuery();
        shuoShuoQuery.setId(shuoshuoId);
        Shuoshuo ss = this.findShuoShuo(shuoShuoQuery);
        messageParams.setArticleUserId(ss.getUserId());
        //设置通知信息类型
        messageParams.setMessageType(MessageType.COMMENT_MESSAGE);
        messageParams.setSendUserName(shuoShuoComment.getUserName());
        messageParams.setSendUserId(shuoShuoComment.getUserId());
        userIdSet.add(ss.getUserId());
        messageParams.setReceiveUserIds(userIdSet);
        messageParams.setCommentId(shuoShuoComment.getId());
        messageService.createMessage(messageParams);
        
    }

}
