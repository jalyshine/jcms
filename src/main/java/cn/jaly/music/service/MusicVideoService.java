package cn.jaly.music.service;

import cn.jaly.music.dao.MusicVideoMapper;
import cn.jaly.music.entity.MusicVideo;
import cn.jaly.music.entity.MusicVideoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MusicVideoService {

	@Autowired
	private MusicVideoMapper musicVideoMapper;

	@Transactional(readOnly = true)
	public List<MusicVideo> queryForList(Integer singerId, String keyword, String order) {
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		return musicVideoMapper.queryForList(singerId, keyword, order);
	}

	@Transactional(readOnly = true)
	public MusicVideo getById(Integer id) {
		return musicVideoMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		MusicVideoExample example = new MusicVideoExample();
		MusicVideoExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<MusicVideo> musicVideos = musicVideoMapper.selectByExample(example);
		if(musicVideos != null && !musicVideos.isEmpty()){
			return musicVideos.get(0).getId();
		}
		return null;
	}
	
	@Transactional
	public void save(MusicVideo musicVideo){
		musicVideo.setUpdateTime(new Date());
		if(musicVideo.getId() == null){
			musicVideo.setHits(0);
			musicVideoMapper.insert(musicVideo);
		} else {
			musicVideoMapper.updateByPrimaryKeySelective(musicVideo);
		}
	}
	
	@Transactional
	public void delete(Integer id){
		musicVideoMapper.deleteByPrimaryKey(id);
	}
	
}
