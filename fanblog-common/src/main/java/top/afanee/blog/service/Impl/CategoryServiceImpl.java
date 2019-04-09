package top.afanee.blog.service.Impl;

import top.afanee.blog.entity.Category;
import top.afanee.blog.mapper.CategoryMapper;
import top.afanee.blog.service.CategoryService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author AFan
 * @since 2019-03-30
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    
    @Autowired
    private CategoryMapper categoryMapper;
    
    @Override
    public List<Category> findCategoryList(Object object) {
        
        
        List<Category> allCategory = categoryMapper.selectList(null);
        allCategory = getChildren(allCategory, 0);
        return allCategory;
    }
    
    //递归整理权限
    public static List<Category> getChildren(List<Category> categories, int id){
        List<Category> children = new ArrayList<Category>();
        for(Category category : categories){
            //category是子权限
            if(category.getPid() == id){
                //看子权限还有孙子权限
                category.setChildren(getChildren(categories, category.getCategoryId()));
                //子权限加入集合
                children.add(category);
            }
        }
        //返回整理好的权限
        return children;
    }
}
