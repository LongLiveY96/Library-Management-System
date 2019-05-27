package com.library.controller;

import com.library.bean.ReaderCard;
import com.library.bean.ReaderInfo;
import com.library.bean.ReaderRole;
import com.library.service.LoginService;
import com.library.service.ReaderCardService;
import com.library.service.ReaderInfoService;
import com.library.service.ReaderRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class ReaderController {
    @Autowired
    private ReaderInfoService readerInfoService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private ReaderCardService readerCardService;

    @Autowired
    private ReaderRoleService readerRoleService;

    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setReaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    @RequestMapping("allreaders.html")
    public ModelAndView allBooks() {
        ArrayList<ReaderInfo> readers = readerInfoService.readerInfos();
        ModelAndView modelAndView = new ModelAndView("admin_readers");
        modelAndView.addObject("readers", readers);
        return modelAndView;
    }

    @RequestMapping("reader_delete.html")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        //判断是否存在未归还的图书
        if(readerCardService.countBook(readerId) > 0) {
            redirectAttributes.addFlashAttribute("error", "该用户存在未归还的图书，无法删除！");
        } else {
            if (readerInfoService.deleteReaderInfo(readerId) && readerCardService.deleteReaderCard(readerId)) {
                redirectAttributes.addFlashAttribute("succ", "删除成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "删除失败！");
            }
        }
        return "redirect:/allreaders.html";
    }

    @RequestMapping("/reader_info.html")
    public ModelAndView toReaderInfo(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("reader_edit.html")
    public ModelAndView readerInfoEdit(HttpServletRequest request) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerId);
        ReaderCard reader = readerCardService.findReaderByReaderId(readerId);
        ArrayList<ReaderRole> readerRole = readerRoleService.getAllRole();
        ModelAndView modelAndView = new ModelAndView("admin_reader_edit");
        modelAndView.addObject("readerInfo", readerInfo);
        modelAndView.addObject("reader", reader);
        modelAndView.addObject("readerRole", readerRole);
        return modelAndView;
    }
    /**
     *管理员修改信息(可修改权限)
     */
    @RequestMapping("reader_edit_do.html")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, Integer roleId, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        ReaderCard readerCard = readerCardService.findReaderByReaderId(readerId);
        readerCard.setUserName(name);
        readerCard.setRoleId(roleId);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerCard)) {
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/allreaders.html";
    }

    @RequestMapping("reader_add.html")
    public ModelAndView readerInfoAdd() {
        ArrayList<ReaderRole> readerRole = readerRoleService.getAllRole();
        ModelAndView modelAndView = new ModelAndView("admin_reader_add");
        modelAndView.addObject("readerRole", readerRole);
        return modelAndView;
    }

    @RequestMapping("reader_add_do.html")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone,
                                  String password, Integer roleId, RedirectAttributes redirectAttributes) {
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        //检测数据库是否存在此用户（名字和电话号码）
        if(readerInfoService.checkReader(readerInfo)){
            long readerId = readerInfoService.addReaderInfo(readerInfo);
            readerInfo.setReaderId(readerId);
            if (readerId > 0 && readerCardService.addReaderCard(readerInfo, password, roleId)) {
                redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
            } else {
                redirectAttributes.addFlashAttribute("error", "添加读者信息失败！");
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "该用户已存在，无法添加！");
        }
        return "redirect:/allreaders.html";
    }

    @RequestMapping("reader_info_edit.html")
    public ModelAndView readerInfoEditReader(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ReaderInfo readerInfo = readerInfoService.getReaderInfo(readerCard.getReaderId());
        ModelAndView modelAndView = new ModelAndView("reader_info_edit");
        modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    /**
     *普通用户修改个人信息
     */
    @RequestMapping("reader_edit_do_r.html")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ReaderInfo readerInfo = getReaderInfo(readerCard.getReaderId(), name, sex, birth, address, phone);
        readerCard.setUserName(name);
        if (readerInfoService.editReaderInfo(readerInfo) && readerInfoService.editReaderCard(readerCard)) {
            ReaderCard readerCardNew = loginService.findReaderCardByReaderId(readerCard.getReaderId());
            request.getSession().setAttribute("readerCard", readerCardNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:/reader_info.html";
    }
}
