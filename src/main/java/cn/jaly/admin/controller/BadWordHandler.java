package cn.jaly.admin.controller;

import cn.jaly.admin.entity.BadWord;
import cn.jaly.admin.service.BadWordService;
import cn.jaly.utils.annotations.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/BadWord/")
public class BadWordHandler {

	@Autowired
	private BadWordService badWordService;
	
	@RequestMapping("list")
	public String list(Map<String, Object> request,
					   @RequestParam(value = "kwd", required = false) String keyword,
					   @RequestParam(value = "ps", required = false) Integer ps,
					   @RequestParam(value = "pn", required = false) Integer pn) {

		if (ps == null) {
			ps = 20;
		}
		if (pn == null) {
			pn = 1;
		}

		PageHelper.startPage(pn, ps);
		List<BadWord> badWords = badWordService.getAll(keyword);
		PageInfo page = new PageInfo(badWords);
		request.put("page", page);
		return "admin/bad_word_list";
	}

	@Token(save = true)
	@RequestMapping(value = "{input}", method = RequestMethod.GET)
	public String input(@PathVariable("input") String input, Map<String, Object> request,
						@RequestParam(value = "id", required = false) Integer id){
		if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
			BadWord badWord = new BadWord();
			if (id != null) {
				badWord = badWordService.getById(id);
			}
			request.put("badWord", badWord);
			return "admin/bad_word_input";
		}
		return null;
	}

	@Token(remove = true)
	@RequestMapping(value = "{save}", method = RequestMethod.POST)
	public String save(@PathVariable("save") String save, BadWord badWord){
		Integer id = badWord.getId();
		if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
			badWordService.save(badWord);
			return "redirect:/admin/BadWord/list";
		}
		return null;
	}
	
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String delete(@RequestParam("id") Integer id){
		badWordService.delete(id);
		return "redirect:/admin/BadWord/list";
	}
}
