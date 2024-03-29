package com.cesaba.siriusmobliemain.tables;

import java.util.ArrayList;
import java.util.List;


//根据数据决定分页
public class PageTableHandler {

    private CountHandler countHandler;
    private ListHandler listHandler;
    private OrderHandler orderHandler;

    public interface ListHandler {
        List<?> list(PageTableRequest request);
    }

    public interface CountHandler {
        int count(PageTableRequest request);
    }

    public interface OrderHandler {
        PageTableRequest order(PageTableRequest request);
    }

    public PageTableHandler(CountHandler countHandler, ListHandler listHandler){
        super();
        this.countHandler = countHandler;
        this.listHandler = listHandler;
    }

    public PageTableHandler(CountHandler countHandler, ListHandler listHandler, OrderHandler orderHandler) {
        this(countHandler, listHandler);
        this.orderHandler = orderHandler;
    }

    public PagesTableResponse handle(PageTableRequest dtRequest){
        int count = 0;
        List<?> list = null;

        count = this.countHandler.count(dtRequest);
        if (count > 0){
            if (orderHandler != null){
                dtRequest = orderHandler.order(dtRequest);
            }
            list = this.listHandler.list(dtRequest);
        }

        if (list == null){
            list = new ArrayList<>();
        }

        return new PagesTableResponse(count, count, list);
    }




}
