package cn.jaly.movie.service;

import cn.jaly.movie.dao.MovieAttributeMapper;
import cn.jaly.movie.dao.MovieDataMapper;
import cn.jaly.movie.dao.MovieMapper;
import cn.jaly.movie.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {

	@Autowired
	private MovieMapper movieMapper;

	@Autowired
	private MovieDataMapper movieDataMapper;

	@Autowired
	private MovieAttributeMapper movieAttributeMapper;

	@Transactional(readOnly=true)
	public List<Movie> queryForList(String keyword, Integer areaId, Integer typeId, String order){
		MovieExample example = new MovieExample();
		if(order == null || "".equals(order)){
			order = "update_time desc";
		}
		example.setOrderByClause(order);
		MovieExample.Criteria criteria = example.createCriteria();
		if(areaId != null && areaId != 0){
			criteria.andAreaIdEqualTo(areaId);
		}
		if(typeId != null && typeId != 0){
			criteria.andTypeIdEqualTo(typeId);
		}
		if(keyword != null && !"".equals(keyword)){
			criteria.andTitleLike("%" + keyword + "%");
		}
		List<Movie> movies = movieMapper.selectByExample(example);
		if(movies != null && !movies.isEmpty()){
			List<MovieAttribute> types = getMovieAttributesByName("type");
			List<MovieAttribute> areas = getMovieAttributesByName("area");
			for(int i=0; i<movies.size(); i++){
				Integer tid = movies.get(i).getTypeId();
				Integer aid = movies.get(i).getAreaId();
				for(MovieAttribute type : types){
					if(type.getId() == tid){
						movies.get(i).setType(type);
						break;
					}
				}
				for(MovieAttribute area : areas){
					if(area.getId() == aid){
						movies.get(i).setArea(area);
						break;
					}
				}
			}
		}
		return movies;
	}

	private List<MovieAttribute> getMovieAttributesByName(String attrName){
		MovieAttributeExample example = new MovieAttributeExample();
		MovieAttributeExample.Criteria criteria = example.createCriteria();
		criteria.andAttrNameEqualTo(attrName);
		return movieAttributeMapper.selectByExample(example);
	}

	@Transactional(readOnly=true)
	public Movie getById(Integer id){
		return movieMapper.selectByPrimaryKeyWithData(id);
	}

	@Transactional(readOnly=true)
	public Integer getIdByTitle(String title){
		MovieExample example = new MovieExample();
		MovieExample.Criteria criteria = example.createCriteria();
		criteria.andTitleEqualTo(title);
		List<Movie> movies = movieMapper.selectByExample(example);
		if(movies != null && !movies.isEmpty()){
			return movies.get(0).getId();
		}
		return null;
	}

	@Transactional
	public void save(Movie movie){ 
		movie.setUpdateTime(new Date());
		MovieData movieData = movie.getMovieData();
		if(movie.getId() == null){
			movie.getMovieData().setHits(0);
			movieMapper.insert(movie);
			movieData.setMovieId(movie.getId());
			movieDataMapper.insert(movieData);
		}else {
			movieMapper.updateByPrimaryKeySelective(movie);
			movieDataMapper.updateByPrimaryKeySelective(movieData);
		}
	}

	@Transactional
	public void delete(Integer id){
		movieDataMapper.deleteByPrimaryKey(id);
		movieMapper.deleteByPrimaryKey(id);
	}

	@Transactional
	public void delete(Integer[] ids){
		List<Integer> aids = Arrays.asList(ids);
		MovieDataExample movieDataExample = new MovieDataExample();
		MovieDataExample.Criteria criteria = movieDataExample.createCriteria();
		criteria.andMovieIdIn(aids);
		movieDataMapper.deleteByExample(movieDataExample);

		MovieExample movieExample = new MovieExample();
		MovieExample.Criteria criteria1 = movieExample.createCriteria();
		criteria1.andIdIn(aids);
		movieMapper.deleteByExample(movieExample);
	}
}
