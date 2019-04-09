package top.afanee.blog.service;

import top.afanee.blog.entity.Attachment;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.po.enums.FileTopicType;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-04-07
 */
public interface AttachmentService extends IService<Attachment> {

    void addAttachment(Attachment attachment) throws BussinessException;

    Attachment getAttachmentByTopicIdAndFileType(Integer blogId, FileTopicType blog);

}
