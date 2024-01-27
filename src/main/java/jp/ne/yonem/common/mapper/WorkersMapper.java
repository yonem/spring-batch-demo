package jp.ne.yonem.common.mapper;

import jp.ne.yonem.common.entity.Worker;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WorkersMapper {

    void bulkInsert(List<Worker> workers);

    List<Worker> selectAll();
}
