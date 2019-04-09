package top.afanee.blog.mapper;

import top.afanee.blog.entity.Message;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author AFan
 * @since 2019-04-02
 */
public interface MessageMapper extends BaseMapper<Message> {

    Integer updateSatatus(Integer userId, Integer[] ids);

    Integer deleteByUserIdAndId(Integer userId, Integer[] ids);

}
