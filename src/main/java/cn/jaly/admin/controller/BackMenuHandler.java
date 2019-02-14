package cn.jaly.admin.controller;

import cn.jaly.admin.entity.BackMenu;
import cn.jaly.admin.service.BackMenuService;
import cn.jaly.utils.annotations.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 后台菜单管理
 *
 * @author Administrator
 */
@Controller
@RequestMapping("/admin/BackMenu/")
public class BackMenuHandler {

    @Autowired
    private BackMenuService backMenuService;

    @RequestMapping("list")
    public String list(Map<String, Object> request) {
        List<BackMenu> backMenus = backMenuService.getAll();
        request.put("backMenus", backMenus);
        return "admin/back_menu_list";
    }

    @Token(save = true)
    @RequestMapping(value = "{input}", method = RequestMethod.GET)
    public String input(@PathVariable("input") String input,Map<String, Object> request,
                        @RequestParam(value = "pid", required = false) Integer parentId,
                        @RequestParam(value = "id", required = false) Integer id) {
        if(("add".equals(input) && id == null) || ("edit".equals(input) && id != null)) {
            List<BackMenu> backMenus = backMenuService.getAllParents();
            request.put("backMenus", backMenus);

            BackMenu backMenu = new BackMenu();
            if (id == null) {
                backMenu.setParentId(parentId);
            } else {
                backMenu = backMenuService.getById(id);
            }
            request.put("backMenu", backMenu);
            return "admin/back_menu_input";
        }
        return null;
    }

    @Token(remove = true)
    @RequestMapping(value = "{save}", method = RequestMethod.POST)
    public String save(@PathVariable("save") String save, BackMenu backMenu) {
        Integer id = backMenu.getId();
        if(("add".equals(save) && id == null) || ("edit".equals(save) && id != null)) {
            backMenuService.save(backMenu);
            return "redirect:/admin/BackMenu/list";
        }
        return null;
    }

    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public String delete(@RequestParam("id") Integer id) {
        backMenuService.delete(id);
        return "redirect:/admin/BackMenu/list";
    }

    @RequestMapping(value = "batch-{method}", method = RequestMethod.POST)
    public String batchOperate(@PathVariable("method") String method,
                               Integer[] id, Integer[] order) {
        boolean flag = false;
        if (id != null) {
            switch (method) {
                case "sort":
                    backMenuService.updateListOrder(id, order);
                    flag = true;
                    break;
                default:
                    break;
            }
        }
        return flag?"redirect:/admin/BackMenu/list":null;
    }
}