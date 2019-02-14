package cn.jaly.content.service;

import cn.jaly.content.dao.WordMapper;
import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.entity.*;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class WordService {

	@Autowired
	private WordMapper wordMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Word> queryForList(String keyword, String stm, String edm, Integer categoryId, String order){
        Date startTime = DateTimeUtils.parseSimple(stm);
        Date endTime = DateTimeUtils.parseSimple(edm);
        if(keyword != null && !"".equals(keyword)){
            keyword = "%" + keyword + "%";
        }
        if(order == null || "".equals(order)){
        	order = "update_time desc";
		}
		return wordMapper.queryForList(keyword, startTime, endTime, categoryId, order);
	}

	@Transactional(readOnly=true)
	public List<Word> queryForList(Integer count){
	    WordExample example = new WordExample();
	    example.setOrderByClause("update_time desc");
        return wordMapper.selectByExample(example);
    }

	@Transactional(readOnly=true)
	public Word getById(Integer id){
		return wordMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		WordExample example = new WordExample();
		WordExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Word> words = wordMapper.selectByExample(example);
		if(words == null || words.isEmpty()){
			return null;
		} else {
			return words.get(0).getId();
		}
	}

	@Transactional
	public void save(Word word){
		Integer categoryId = word.getCategoryId();
		word.setUpdateTime(new Date());
		if(word.getId() == null){
			word.setHits(0);
			wordMapper.insert(word);
		} else{
			wordMapper.updateByPrimaryKeyWithBLOBs(word);
		}
		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer id){
		Word word = wordMapper.selectByPrimaryKey(id);
		Integer categoryId = word.getCategoryId();
		wordMapper.deleteByPrimaryKey(id);
		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer[] ids) {
		Word word = wordMapper.selectByPrimaryKey(ids[0]);
		Integer categoryId = word.getCategoryId();

		List<Integer> aids = Arrays.asList(ids);
		WordExample wordExample = new WordExample();
		WordExample.Criteria criteria1 = wordExample.createCriteria();
		criteria1.andIdIn(aids);
		wordMapper.deleteByExample(wordExample);

		upateModelDataCount(categoryId);
	}

	/**
	 * 更新模型数据，每次内容变动的时候触发
	 * @param categoryId
	 */
	private void upateModelDataCount(Integer categoryId){
		Category category = categoryMapper.selectByPrimaryKeyWithModel(categoryId);
		modelMapper.updateDataCount(category.getModel());
	}

}
