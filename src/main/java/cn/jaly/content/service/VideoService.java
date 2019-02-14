package cn.jaly.content.service;

import cn.jaly.content.dao.CategoryMapper;
import cn.jaly.content.dao.ModelMapper;
import cn.jaly.content.dao.VideoDataMapper;
import cn.jaly.content.dao.VideoMapper;
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
public class VideoService {

	@Autowired
	private VideoMapper videoMapper;

	@Autowired
	private VideoDataMapper videoDataMapper;

	@Autowired
	private CategoryMapper categoryMapper;

	@Autowired
	private ModelMapper modelMapper;

	@Transactional(readOnly=true)
	public List<Video> queryForList(String keyword, String stm, String edm,
									Integer categoryId, Byte status, String order){
		Date startTime = DateTimeUtils.parseSimple(stm);
		Date endTime = DateTimeUtils.parseSimple(edm);
		if(keyword != null && !"".equals(keyword)){
			keyword = "%" + keyword + "%";
		}
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		return videoMapper.queryForList(keyword, startTime, endTime, categoryId, status, order);
	}

    @Transactional(readOnly=true)
	public Video getByIdWithData(Integer id){
		return videoMapper.selectByPrimaryKeyWithData(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		VideoExample example = new VideoExample();
		VideoExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Video> videos = videoMapper.selectByExample(example);
		if(videos == null || videos.isEmpty()){
			return null;
		} else {
			return videos.get(0).getId();
		}
	}

	@Transactional
	public void save(Video video){
		Integer categoryId = video.getCategoryId();

		video.setUpdateTime(new Date());
		VideoData videoData = video.getVideoData();
		if(videoData.getCopyFromId()!=null && videoData.getCopyFromId() == 0){
			videoData.setCopyFromId(null);
		}
		if(video.getId() == null){
			videoData.setHits(0);
			videoMapper.insert(video);
			videoData.setVideoId(video.getId());
			videoDataMapper.insert(videoData);
		} else{
			videoMapper.updateByPrimaryKey(video);
			videoDataMapper.updateByPrimaryKeyWithBLOBs(videoData);
		}

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer id){
		Video video = videoMapper.selectByPrimaryKey(id);
		Integer categoryId = video.getCategoryId();

		videoDataMapper.deleteByPrimaryKey(id);
		videoMapper.deleteByPrimaryKey(id);

		upateModelDataCount(categoryId);
	}

	@Transactional
	public void delete(Integer[] ids) {
		Video video = videoMapper.selectByPrimaryKey(ids[0]);
		Integer categoryId = video.getCategoryId();

		List<Integer> aids = Arrays.asList(ids);
		VideoDataExample videoDataExample = new VideoDataExample();
		VideoDataExample.Criteria criteria = videoDataExample.createCriteria();
		criteria.andVideoIdIn(aids);
		videoDataMapper.deleteByExample(videoDataExample);

		VideoExample videoExample = new VideoExample();
		VideoExample.Criteria criteria1 = videoExample.createCriteria();
		criteria1.andIdIn(aids);
		videoMapper.deleteByExample(videoExample);

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
