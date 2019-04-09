package top.afanee.blog.po.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import top.afanee.blog.po.enums.BlogStatusEnum;




public class BlogStatusHandler extends BaseTypeHandler<BlogStatusEnum> {
    
    /**
     * BlogStatusEnum 转换为Int类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
            BlogStatusEnum parameter, JdbcType jdbcType) throws SQLException {
        ps.setInt(i, parameter.getType());
        
    }

    @Override
    public BlogStatusEnum getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        Integer type = rs.getInt(columnName);
        return BlogStatusEnum.getStatusByValue(type);
    }

    @Override
    public BlogStatusEnum getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        Integer type = rs.getInt(columnIndex);
        return BlogStatusEnum.getStatusByValue(type);
    }

    @Override
    public BlogStatusEnum getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        Integer type = cs.getInt(columnIndex);
        return BlogStatusEnum.getStatusByValue(type);
    }


}
