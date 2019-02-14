package cn.jaly.music.service;

import cn.jaly.music.dao.MusicSpecialMapper;
import cn.jaly.music.entity.MusicSpecial;
import cn.jaly.music.entity.MusicSpecialExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MusicSpecialService {

	@Autowired
	private MusicSpecialMapper musicSpecialMapper;

	@Transactional(readOnly=true)
	public List<MusicSpecial> queryForList(Integer typeId, String keyword, String order){
		MusicSpecialExample example = new MusicSpecialExample();
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		example.setOrderByClause(order);
		MusicSpecialExample.Criteria criteria = example.createCriteria();
		if(typeId != null && typeId != 0){
			criteria.andTypeIdEqualTo(typeId);
		} 
		if(keyword != null && !"".equals(keyword)){
			criteria.andTitleEqualTo("%" + keyword + "%");
		}
		return musicSpecialMapper.selectByExampleWithType(example);
	}

	@Transactional(readOnly=true)
	public MusicSpecial getById(Integer id){
		return musicSpecialMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		MusicSpecialExample example = new MusicSpecialExample();
		MusicSpecialExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<MusicSpecial> musicSpecials = musicSpecialMapper.selectByExample(example);
		if(musicSpecials != null && !musicSpecials.isEmpty()){
			return musicSpecials.get(0).getId();
		}
		return null;
	}

	@Transactional
	public void save(MusicSpecial musicSpecial){
		musicSpecial.setUpdateTime(new Date());
		if(musicSpecial.getId() == null){
			musicSpecial.setHits(0);
			musicSpecialMapper.insert(musicSpecial);
		} else {
			musicSpecialMapper.updateByPrimaryKeySelective(musicSpecial);
		}
	}

	@Transactional
	public void delete(Integer id){
		musicSpecialMapper.deleteByPrimaryKey(id);
	}
}
