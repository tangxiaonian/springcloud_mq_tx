package com.tang.mq.stock.mapper;

import com.tang.mq.stock.domain.Stock;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname ${NAME}
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/28 22:41
 * @Created by ASUS
 */
public interface StockMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Stock record);

    int insertSelective(Stock record);

    Stock selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Stock record);

    int updateByPrimaryKey(Stock record);

    @Select("select * from  stock where order_id = #{orderId}")
    Stock selectByOrderId(@Param("orderId") String orderId);
}