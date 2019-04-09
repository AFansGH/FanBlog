package top.afanee.blog.service;

import top.afanee.blog.entity.Shuoshuo;
import top.afanee.blog.entity.ShuoshuoComment;
import top.afanee.blog.exception.BussinessException;
import top.afanee.blog.po.model.PageResult;
import top.afanee.blog.po.query.ShuoShuoQuery;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-31
 */
public interface ShuoshuoService extends IService<Shuoshuo> {

    PageResult<Shuoshuo> findShuoShuoListByPage(ShuoShuoQuery shuoShuoQuery);

    Shuoshuo findShuoShuo(ShuoShuoQuery shuoShuoQuery);

    void addShuoShuoComment(ShuoshuoComment shuoShuoComment)throws BussinessException;

}
