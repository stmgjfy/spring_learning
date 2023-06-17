package learning.spring.binarytea.repository.mybatis;

import learning.spring.binarytea.model.mybatis.TeaMaker;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository("plusTeaMakerMapper")
public interface TeaMakerMapper {
    @Insert("insert into t_tea_maker (name, create_time, update_time)" +
            " values (#{name}, now(), now())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int save(TeaMaker maker);

    @Update("update t_tea_maker set name = #{name}, update_time = now() where id = #{id}")
    int update(TeaMaker maker);

    @Select("select * from t_tea_maker where id = #{id}")
    @Results(id = "teaMakerMap", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "id", property = "orders",
                many = @Many(select = "learning.spring.binarytea.repository.mybatis.OrderMapper.findByMakerId"))
    })
    TeaMaker findById(Long id);

    @Select("select * from t_tea_maker")
    @ResultMap("teaMakerMap")
    List<TeaMaker> findAllWithRowBounds(RowBounds rowBounds);

    @Select("select * from t_tea_maker")
    @ResultMap("teaMakerMap")
    List<TeaMaker> findAllWithPage(int pageSize, int pageNum);
}
