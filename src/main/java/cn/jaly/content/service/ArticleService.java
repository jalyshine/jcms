package cn.jaly.content.service;

import cn.jaly.content.dao.ArticleDataMapper;
import cn.jaly.content.dao.ArticleMapper;
import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.*;
import cn.jaly.utils.common.Constant;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private ArticleDataMapper articleDataMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Article> queryForList(String keyword, String stm, String edm, Integer categoryId,
                                      Byte status, String order){
        Date startTime = DateTimeUtils.parseSimple(stm);
        Date endTime = DateTimeUtils.parseSimple(edm);
        if(keyword != null && !"".equals(keyword)){
            keyword = "%" + keyword + "%";
        }
        if(order == null || "".equals(order)){
        	order = "update_time desc";
		}
		return articleMapper.queryForList(keyword, startTime, endTime, categoryId, status, order);
	}

	@Transactional(readOnly=true)
	public List<Article> queryForList(Integer count){
	    ArticleExample example = new ArticleExample();
	    example.setOrderByClause("update_time desc");
        return articleMapper.selectByExample(example);
    }

	@Transactional(readOnly=true)
	public Article getByIdWithData(Integer id){
		return articleMapper.selectByPrimaryKeyWithData(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		ArticleExample example = new ArticleExample();
		ArticleExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Article> articles = articleMapper.selectByExample(example);
		if(articles == null || articles.isEmpty()){
			return null;
		} else {
			return articles.get(0).getId();
		}
	}

	@Transactional
	public void save(Article article){
		Integer categoryId = article.getCategoryId();

		article.setUpdateTime(new Date());
		ArticleData articleData = article.getArticleData();
		if(articleData.getCopyFromId()!=null && articleData.getCopyFromId() == 0){
			articleData.setCopyFromId(null);
		}
		if(article.getId() == null){
			articleData.setHits(0);
			articleMapper.insert(article);
			articleData.setArticleId(article.getId());
			articleDataMapper.insert(articleData);
		} else{
			articleMapper.updateByPrimaryKey(article);
			articleDataMapper.updateByPrimaryKeyWithBLOBs(articleData);
		}

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer id){
		Article article = articleMapper.selectByPrimaryKey(id);
		Integer categoryId = article.getCategoryId();

		articleDataMapper.deleteByPrimaryKey(id);
		articleMapper.deleteByPrimaryKey(id);

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer[] ids) {
		Article article = articleMapper.selectByPrimaryKey(ids[0]);
		Integer categoryId = article.getCategoryId();

		List<Integer> aids = Arrays.asList(ids);
		ArticleDataExample articleDataExample = new ArticleDataExample();
		ArticleDataExample.Criteria criteria = articleDataExample.createCriteria();
		criteria.andArticleIdIn(aids);
		articleDataMapper.deleteByExample(articleDataExample);

		ArticleExample articleExample = new ArticleExample();
		ArticleExample.Criteria criteria1 = articleExample.createCriteria();
		criteria1.andIdIn(aids);
		articleMapper.deleteByExample(articleExample);

		upateModelDataCount(categoryId);
	}

	/**
	 * 更新模型数据，每次内容变动的时候触发
	 * @param categoryId
	 */
	private void upateModelDataCount(Integer categoryId){
		Category category = categoryMapper.selectByPrimaryKeyWithModel(categoryId);
		System.out.println(category.getModel().getTableName() + "-------------");
		modelMapper.updateDataCount(category.getModel());
	}

}
