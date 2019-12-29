package com.tang.mq.order.mapper;

import com.tang.mq.order.domain.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Classname ${NAME}
 * @Description [ TODO ]
 * @Author Tang
 * @Date 2019/12/29 11:18
 * @Created by ASUS
 */
public interface OrderMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    @Select("select * from test04.order where order_id = #{orderId} ")
    Order selectByOrderId(@Param("orderId") String orderId);

}