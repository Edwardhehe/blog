package cn.edwardhehe.blog.service;

import cn.edwardhehe.blog.dao.CategoryDao;
import cn.edwardhehe.blog.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lihao
 */
@Service
public class CategoryServices {
    @Autowired
    private CategoryDao categoryDao;

    public List<Category> list() {
        return categoryDao.findAll();
    }

    public Category get(String id) {
        return categoryDao.getOne(id);
    }


    public Category fingdByName(String name) {
        return categoryDao.findByName(name);
    }
}
