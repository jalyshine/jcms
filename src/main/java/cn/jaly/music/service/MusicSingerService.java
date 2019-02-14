package cn.jaly.music.service;

import cn.jaly.music.dao.MusicSingerMapper;
import cn.jaly.music.entity.MusicSinger;
import cn.jaly.music.entity.MusicSingerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MusicSingerService {

	@Autowired
	private MusicSingerMapper musicSingerMapper;

	@Transactional(readOnly=true)
	public List<MusicSinger> queryForList(Integer areaId, String keyword, Byte sex, String order){
		MusicSingerExample example = new MusicSingerExample();
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		example.setOrderByClause(order);
		MusicSingerExample.Criteria criteria = example.createCriteria();
		if(areaId != null && areaId != 0){
			criteria.andAreaIdEqualTo(areaId);
		}
		if(sex != null){
			criteria.andSexEqualTo(sex);
		}
		if(keyword != null && !"".equals(keyword)){
			criteria.andNameLike("%" + keyword + "%");
		}
		return musicSingerMapper.selectByExampleWithArea(example);
	}
	
	@Transactional(readOnly=true)
	public MusicSinger getById(Integer id){
		return musicSingerMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByName(String name){
		MusicSingerExample example = new MusicSingerExample();
		MusicSingerExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(name);
		List<MusicSinger> musicSingers = musicSingerMapper.selectByExample(example);
		if(musicSingers != null && !musicSingers.isEmpty()){
			return musicSingers.get(0).getId();
		}
		return null;
	}
	
	@Transactional
	public void save(MusicSinger musicSinger){
		musicSinger.setUpdateTime(new Date());
		if(musicSinger.getId() == null){
			musicSinger.setHits(0);
			musicSingerMapper.insert(musicSinger);
		} else {
			musicSingerMapper.updateByPrimaryKeySelective(musicSinger);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		musicSingerMapper.deleteByPrimaryKey(id);
	}
	
}
