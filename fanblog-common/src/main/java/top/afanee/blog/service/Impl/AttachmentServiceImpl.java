package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Attachment;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.mapper.AttachmentMapper;
import top.afanee.blog.po.enums.FileTopicType;
import top.afanee.blog.service.AttachmentService;
import top.afanee.blog.utils.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements AttachmentService {

    @Override
    public void addAttachment(Attachment attachment) throws BussinessException {
        if(StringUtils.isEmpty(attachment.getFileName()) || 
                StringUtils.isEmpty(attachment.getFileUrl())
                    ){
                throw new BussinessException("参数错误");
            }
            super.baseMapper.insert(attachment);
        
    }
    
    /**
     * 通过topicId 与 文件类型拿到附件
     */
    @Override
    public Attachment getAttachmentByTopicIdAndFileType(Integer topicId, FileTopicType fileTopicType) {
        Attachment attachmentQuery = new Attachment();
        attachmentQuery.setTopicId(topicId);
        attachmentQuery.setTopicType(fileTopicType);
        Attachment attachment = super.baseMapper.selectOne(attachmentQuery);
        return attachment;
    }

}
