package top.afanee.blog.mapper;

import top.afanee.blog.entity.Shuoshuo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AFan
 * @since 2019-03-31
 */
public interface ShuoshuoMapper extends BaseMapper<Shuoshuo> {

    Integer updateShuoShuoCommentCount(Integer id);

}
