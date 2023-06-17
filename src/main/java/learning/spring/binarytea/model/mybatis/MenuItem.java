package learning.spring.binarytea.model.mybatis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    private Long id;
    private String name;
    private Size size;
    private Money price;
    private Date createTime;
    private Date updateTime;
}