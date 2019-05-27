package com.library.controller;

import com.library.bean.Punishment;
import com.library.bean.ReaderCard;
import com.library.service.PunishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class PunishmentController {
    @Autowired
    private PunishmentService punishmentService;

    @RequestMapping("/myBadList.html")
    public ModelAndView myBadList(HttpServletRequest request) {
        ReaderCard readerCard = (ReaderCard) request.getSession().getAttribute("readerCard");
        ModelAndView modelAndView = new ModelAndView("reader_bad_list");
        ArrayList<Punishment> punishments = punishmentService.myBadList(readerCard.getReaderId());
        modelAndView.addObject("list", punishments);
        return modelAndView;
    }

    @RequestMapping("/badList.html")
    public ModelAndView badList() {
        ModelAndView modelAndView = new ModelAndView("admin_bad_list");
        ArrayList<Punishment> punishments = punishmentService.badList();
        modelAndView.addObject("list", punishments);
        return modelAndView;
    }
}
