package top.afanee.blog.service.Impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import top.afanee.blog.entity.Ask;
import top.afanee.blog.entity.Blog;
import top.afanee.blog.entity.Knowledge;
import top.afanee.blog.entity.Message;
import top.afanee.blog.entity.Shuoshuo;
import top.afanee.blog.entity.Topic;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.AskMapper;
import top.afanee.blog.mapper.BlogMapper;
import top.afanee.blog.mapper.KnowledgeMapper;
import top.afanee.blog.mapper.MessageMapper;
import top.afanee.blog.mapper.ShuoshuoMapper;
import top.afanee.blog.mapper.TopicMapper;
import top.afanee.blog.po.enums.ArticleType;
import top.afanee.blog.po.enums.MessageType;
import top.afanee.blog.po.enums.TextLengthEnum;
import top.afanee.blog.po.model.MessageParams;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.MessageQuery;
import top.afanee.blog.service.MessageService;
import top.afanee.blog.utils.Constants;


@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageService {
    
    @Autowired
    private TopicMapper topicMapper;
    
    @Autowired
    private KnowledgeMapper knowledgeMapper;
    
    @Autowired
    private AskMapper askMapper;
    
    @Autowired
    private BlogMapper blogMapper;
    
    @Autowired
    private ShuoshuoMapper shuoShuoMapper;
    
    /**
     * 判断是什么信息@、回复、评论、采纳
     */
    public void createMessage(MessageParams messageParams) {
        MessageType type = messageParams.getMessageType();
        switch (type) {
        case AT_ARTICLE_MESSAGE:
            atMessage(messageParams);
            break;
        case COMMENT_MESSAGE:
            commentMessage(messageParams);
            break;
        case ADOPT_ANSWER:
            adoptAnswerMessage(messageParams);
            break;
        case SYSTEM_MARK:
            changeMarkMessage(messageParams);
        default:
            break;
        }
        
    }

    private void changeMarkMessage(MessageParams messageParams) {
        List<Message> messageList = new ArrayList<Message>();
        Set<Integer> receiveUserIds = messageParams.getReceiveUserIds();
        Date curDate = new Date();
        Message message = null;
        for(Integer receivedUserId : receiveUserIds){
            message = new Message();
            message.setReceivedUserId(receivedUserId);
            message.setUrl(Constants.DOMAIN +"/admin/messageList");
            message.setDescription("<span>系统消息</span>" + 
                            "【" + messageParams.getDes() + "】");
            message.setCreateTime(curDate);
            messageList.add(message);
        }   
        if(!messageList.isEmpty()){
            super.insertBatch(messageList);
        }
    }

    private void adoptAnswerMessage(MessageParams messageParams) {
        List<Message> messageList = new ArrayList<Message>();
        Set<Integer> receiveUserIds = messageParams.getReceiveUserIds();
        Ask askQuery = new Ask();
        askQuery.setAskId(messageParams.getArticleId());
        Ask ask = this.askMapper.selectOne(askQuery);
        String title = ask.getTitle();
        Message message = null;
        removeUser(receiveUserIds, messageParams.getSendUserId());
        Date curDate = new Date();
        for(Integer receivedUserId : receiveUserIds){
            message = new Message();
            message.setReceivedUserId(receivedUserId);
            message.setUrl(getUrl4CommentAndAt(messageParams));
            message.setDescription("<span>" + messageParams.getSendUserName() + "</span>" + 
                            "在【" + messageParams.getArticleType().getDesc() + "】" +"(" + title + ")中采纳了你的答案");
            message.setCreateTime(curDate);
            messageList.add(message);
        }   
        if(!messageList.isEmpty()){
            super.insertBatch(messageList);
        }
        
    }

    private void commentMessage(MessageParams messageParams) {
        List<Message> messageList = new ArrayList<Message>();
        Set<Integer> receiveUserIds = messageParams.getReceiveUserIds();
        ArticleType articleType = messageParams.getArticleType();
        Integer articleId = messageParams.getArticleId();
        String title = "";
        //Integer articleUserId = null;
        if(articleType == ArticleType.TOPIC){
            Topic topicQuery = new Topic();
            topicQuery.setTopicId(articleId);
            Topic topic = this.topicMapper.selectOne(topicQuery);
            title = topic.getTitle();
        }
        else if(articleType == ArticleType.KNOWLEDGE){
            Knowledge knowledgeQuery = new Knowledge();
            knowledgeQuery.setKnowledgeId(articleId);
            Knowledge knowledge = this.knowledgeMapper.selectOne(knowledgeQuery);
            title = knowledge.getTitle();
        }
        else if(articleType == ArticleType.Ask){
            Ask askQuery = new Ask();
            askQuery.setAskId(articleId);
            Ask ask = this.askMapper.selectOne(askQuery);
            title = ask.getTitle();
        }
        else if(articleType == ArticleType.BLOG){
            Blog blogQuery = new Blog();
            blogQuery.setBlogId(articleId);
            Blog blog = this.blogMapper.selectOne(blogQuery);
            title = blog.getTitle();
        }
        else{
            Shuoshuo shuoShuoQuery = new Shuoshuo();
            shuoShuoQuery.setId(articleId);
            Shuoshuo shuoShuo = this.shuoShuoMapper.selectOne(shuoShuoQuery);
            title = shuoShuo.getContent();
            if (title.length() > TextLengthEnum.TEXT_200_LENGTH.getLength()) {
                 title = title.substring(0,
                        (int) TextLengthEnum.TEXT_200_LENGTH.getLength())
                        + "......";
            }
        }
        
        Message message = null;
        removeUser(receiveUserIds, messageParams.getSendUserId());
        Date curDate = new Date();
        for(Integer receivedUserId : receiveUserIds){
            message = new Message();
            message.setReceivedUserId(receivedUserId);
            message.setUrl(getUrl4CommentAndAt(messageParams));
            message.setDescription("<span>" + messageParams.getSendUserName() + "</span>" + 
                            "在【" + messageParams.getArticleType().getDesc() + "】" + "&nbsp;&nbsp;("  + title + ")中回复了你");
            message.setCreateTime(curDate);
            messageList.add(message);
        }   
        if(!messageList.isEmpty()){
            super.insertBatch(messageList);
        }
        
    }

    private void atMessage(MessageParams messageParams) {
        List<Message> messageList = new ArrayList<Message>();
        Set<Integer> receiveUserIds = messageParams.getReceiveUserIds();
        Message message = null;
        //去除自身
        removeUser(receiveUserIds, messageParams.getSendUserId());
        Date curDate = new Date();
        for(Integer receivedUserId : receiveUserIds){
            message = new Message();
            message.setReceivedUserId(receivedUserId);
            message.setUrl(getUrl4CommentAndAt(messageParams));
            message.setDescription("<span>" + messageParams.getSendUserName() + "</span>" + 
                            "在【" + messageParams.getArticleType().getDesc() + "】" + "中提到了你");
            message.setCreateTime(curDate);
            messageList.add(message);
        }   
        if(!messageList.isEmpty()){
            super.insertBatch(messageList);
        }
    }
    
    //将UserIds去除发送者自身id
    private void removeUser(Set<Integer> receiveUserIds, Integer sendUserId) {
        Iterator<Integer> iterator = receiveUserIds.iterator();
        while(iterator.hasNext()){
            if(iterator.next().intValue() == sendUserId){
                iterator.remove();
            }
        }
    }

    public Message getMessageById(Integer id, Integer userId) {
        Message messageQuery = new Message();
        messageQuery.setReceivedUserId(userId);
        messageQuery.setId(id);
        Message message = super.baseMapper.selectOne(messageQuery);
        return message;
    }

    public PageResult<Message> findMessageByPage(MessageQuery messageQuery) {
        
        Page<Message> page = new Page<>(messageQuery.getPageOn(), messageQuery.getPageSize());
        List<Message> list = super.baseMapper.selectPage(page, null);
        return new PageResult<Message>(page, list);
    }

   /* public int findMessageCount(MessageQuery messageQuery) {
        return super.baseMapper.selectCount(messageQuery).intValue();
    }
*/
    public void update(Integer[] ids, Integer userId) throws BussinessException {
        if(ids == null || userId == null || ids.length == 0){
            throw new BussinessException("参数错误");
        }
        super.baseMapper.updateSatatus(userId, ids);
    }

    public void delMessage(Integer userId, Integer[] ids)
            throws BussinessException {
        if(ids == null || userId == null || ids.length == 0){
            throw new BussinessException("参数错误");
        }
        super.baseMapper.deleteByUserIdAndId(userId, ids);
        
    }
    
    
    private String getUrl4CommentAndAt(MessageParams params){
        String location = "";
        if(params.getPageNum() != null){
            location = "#" + params.getPageNum()+ "_" + params.getCommentId();
        }
        switch (params.getArticleType()) {
        case TOPIC:
            return Constants.DOMAIN + "/bbs/" + params.getArticleId() + location; 
        case Ask:
            return Constants.DOMAIN + "/ask/" + params.getArticleId() + location; 
        case KNOWLEDGE:
            return Constants.DOMAIN + "/knowledge/" + params.getArticleId() + location; 
        case BLOG:
            return Constants.DOMAIN + "/user/" + params.getArticleUserId() + "/blog/" + params.getArticleId() + location;
        case SHUOSHUO:
            return Constants.DOMAIN + "/user/" + params.getArticleUserId() + "/shuoshuo/" + params.getArticleId();
        }
        return null;
    }
}

