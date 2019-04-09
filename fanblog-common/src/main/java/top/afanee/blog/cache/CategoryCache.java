package top.afanee.blog.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import top.afanee.blog.entity.Category;
import top.afanee.blog.service.CategoryService;
import top.afanee.blog.utils.Constants;


@Component
public class CategoryCache {
    
    @Autowired
    private CategoryService categoryService;
    
    
    private static Map<String, List<Category>> categoryCache = null;    //key为一级分类，Value为该类型下的子分类
    private static Map<String, Category> singleCategoryCache = null;    //所有分类（不包含一级分类）
    
    static{
        categoryCache = new HashMap<String, List<Category>>();
        singleCategoryCache = new HashMap<String, Category>();
        categoryCache.put(Constants.CACHE_KEY_BBS_CATEGORY, new ArrayList<Category>());
        categoryCache.put(Constants.CACHE_KEY_KNOWLEDGE_CATEGORY, new ArrayList<Category>());
        categoryCache.put(Constants.CACHE_KEY_ASK_CATEGORY, new ArrayList<Category>());
        categoryCache.put(Constants.CACHE_KEY_EXAM_CATEGORY, new ArrayList<Category>());    
    }
    
    //过滤子分类，分类可能不展示
    public void filterChildren(Category c, String show){
        List<Category> filterChildren = new ArrayList<Category>();
        List<Category> children = c.getChildren();
        for(Category category : children){
            if(show.equals(Constants.CACHE_KEY_BBS_CATEGORY) && Constants.Y.equals(category.getShowInBbs())){
                filterChildren.add(category);
            }
            if(show.equals(Constants.CACHE_KEY_ASK_CATEGORY) && Constants.Y.equals(category.getShowInQuestion())){
                filterChildren.add(category);
            }
            if(show.equals(Constants.CACHE_KEY_KNOWLEDGE_CATEGORY) && Constants.Y.equals(category.getShowInKnowledge())){
                filterChildren.add(category);
            }
            if(show.equals(Constants.CACHE_KEY_EXAM_CATEGORY) && Constants.Y.equals(category.getShowInExam())){
                filterChildren.add(category);
            }
            singleCategoryCache.put(Constants.CACHE_KEY_CATEGORY + category.getCategoryId(), category);
        }
        //将过滤后的子分类放入一级分类中
        c.setChildren(filterChildren);
    }
    
    public void refreshCategoryCache(){
        
        //父子关系已经整理好，全部是一级权限
        List<Category> list = this.categoryService.findCategoryList(null);
        for(Category category : list){
            //将一级权限加入各个模块中
            if(Constants.Y.equals(category.getShowInBbs())){
                categoryCache.get(Constants.CACHE_KEY_BBS_CATEGORY).add(category);
                this.filterChildren(category, Constants.CACHE_KEY_BBS_CATEGORY);
            }
            if(Constants.Y.equals(category.getShowInExam())){
                categoryCache.get(Constants.CACHE_KEY_EXAM_CATEGORY).add(category);
                this.filterChildren(category, Constants.CACHE_KEY_EXAM_CATEGORY);
            }
            if(Constants.Y.equals(category.getShowInKnowledge())){
                categoryCache.get(Constants.CACHE_KEY_KNOWLEDGE_CATEGORY).add(category);
                this.filterChildren(category, Constants.CACHE_KEY_KNOWLEDGE_CATEGORY);
            }
            if(Constants.Y.equals(category.getShowInQuestion())){
                categoryCache.get(Constants.CACHE_KEY_ASK_CATEGORY).add(category);
                this.filterChildren(category, Constants.CACHE_KEY_ASK_CATEGORY);
            }
            singleCategoryCache.put(Constants.CACHE_KEY_CATEGORY + category.getCategoryId(), category);
        }
    }
    
    public List<Category> getBbsCategories(){
        return categoryCache.get(Constants.CACHE_KEY_BBS_CATEGORY);
    }
    public List<Category> getKnowledgeCategories(){
        return categoryCache.get(Constants.CACHE_KEY_KNOWLEDGE_CATEGORY);
    }
    public static Category getCategoryById(Integer categoryId){
        return singleCategoryCache.get(Constants.CACHE_KEY_CATEGORY + categoryId);
    }
}
