package cn.jaly.music.service;

import cn.jaly.music.dao.MusicMapper;
import cn.jaly.music.entity.Music;
import cn.jaly.utils.common.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MusicService {

	@Autowired
	private MusicMapper musicMapper;

	@Transactional(readOnly=true)
	public List<Music> queryForList(Integer albumId, String keyword, String stm, String edm, String order){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		return musicMapper.queryForList(albumId, keyword, startTime, endTime, order);
	}

	@Transactional(readOnly = true)
	public List<Music> queryByIds(List<Integer> ids){
		return musicMapper.queryByIds(ids);
	}

	@Transactional
	public void delete(Integer id){
		musicMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public void deletes(Integer[] ids){
		musicMapper.batchDelete(ids);
	}
	
	@Transactional
	public void save(Music music){
		music.setUpdateTime(new Date());
		if(music.getId() == null){
			music.setHits(0);
			musicMapper.insert(music);
		} else {
			musicMapper.updateByPrimaryKey(music);
		}
	}

}
