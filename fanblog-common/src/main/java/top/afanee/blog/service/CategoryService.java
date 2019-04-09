package top.afanee.blog.service;

import top.afanee.blog.entity.Category;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author AFan
 * @since 2019-03-30
 */
public interface CategoryService extends IService<Category> {

    List<Category> findCategoryList(Object object);

}
