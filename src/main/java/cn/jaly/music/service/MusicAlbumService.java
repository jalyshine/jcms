package cn.jaly.music.service;

import cn.jaly.music.dao.MusicAlbumMapper;
import cn.jaly.music.dao.MusicMapper;
import cn.jaly.music.entity.Music;
import cn.jaly.music.entity.MusicAlbum;
import cn.jaly.music.entity.MusicAlbumExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class MusicAlbumService {

	@Autowired
	private MusicAlbumMapper musicAlbumMapper;
	
	@Autowired
	private MusicMapper musicMapper;

	@Transactional(readOnly = true)
	public List<MusicAlbum> queryForList(Integer singerId, String keyword, String order) {
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		return musicAlbumMapper.queryForList(singerId, keyword, order);
	}

	@Transactional(readOnly = true)
	public MusicAlbum getById(Integer id) {
		return musicAlbumMapper.selectByPrimaryKey(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		MusicAlbumExample example = new MusicAlbumExample();
		MusicAlbumExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<MusicAlbum> musicAlbums = musicAlbumMapper.selectByExample(example);
		if(musicAlbums != null && !musicAlbums.isEmpty()){
			return musicAlbums.get(0).getId();
		}
		return null;
	}

	@Transactional
	public void save(MusicAlbum musicAlbum, Integer[] mid, String[] ttl, String[] url, Integer[] size) {
		musicAlbum.setUpdateTime(new Date());
		if (musicAlbum.getId() == null) {
			musicAlbum.setHits(0);
			musicAlbumMapper.insert(musicAlbum);
		} else {
			musicAlbumMapper.updateByPrimaryKeySelective(musicAlbum);
		}

		if(ttl != null){
			for(int i = 0; i<ttl.length; i++){
				Music music = new Music();
				music.setUpdateTime(new Date());
				music.setTitle(ttl[i]);
				music.setUrl(url[i]);
				music.setSize(size[i]);
				music.setMusicAlbumId(musicAlbum.getId());
				if(mid[i] == 0){
					musicMapper.insert(music);
				} else{
					music.setId(mid[i]);
					musicMapper.updateByPrimaryKeySelective(music);
				}
			} 
		}
	}

	@Transactional
	public void delete(Integer id) {
		musicAlbumMapper.deleteByPrimaryKey(id);
	}

}
