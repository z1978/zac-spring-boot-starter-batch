package com.zac.batch.job.first;

import java.util.ArrayList;
import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import com.zac.batch.BatchConstants;
import com.zac.batch.dto.MemberInfoDto;
import com.zac.batch.dto.PersonDto;
 
/**
 * 写处理类。
 * 
 * @author Zacz
 * 
 * @param <T>
 */

@Component(BatchConstants.MULTI_ITEM_WRITER_ID)
public class MultiItemWriter<T> implements ItemWriter<T> {
    /** 写代理 */
    private List<ItemWriter<? super T>> delegates;
 
    public void setDelegates(List<ItemWriter<? super T>> delegates) {
        this.delegates = delegates;
    }
 
    @Override
    public void write(List<? extends T> items) throws Exception {
        // 学生信息的Writer
        ItemWriter studentWriter = (ItemWriter) delegates.get(0);
        // 商品信息的Writer
        ItemWriter goodsWriter = (ItemWriter) delegates.get(1);
        // 学生信息
        List<PersonDto> studentList = new ArrayList<PersonDto>();
        // 商品信息
        List<MemberInfoDto> goodsList = new ArrayList<MemberInfoDto>();
        // 将传过来的信息按照不同的类型添加到不同的List中
        for (int i = 0; i < items.size(); i++) {
            if ("Student".equals(items.get(i).getClass().getSimpleName())) {
                studentList.add((PersonDto) items.get(i));
            } else {
                goodsList.add((MemberInfoDto) items.get(i));
            }
        }
        // 如果学生List中有数据，就执行学生信息的写
        if (studentList.size() > 0) {
            studentWriter.write(studentList);
        }
        // 如果商品List中有数据，就执行商品信息的写
        if (goodsList.size() > 0) {
            goodsWriter.write(goodsList);
        }
    }

}
